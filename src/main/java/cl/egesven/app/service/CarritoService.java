package cl.egesven.app.service;

import cl.egesven.app.dto.CarritoItemDetalleDto;
import cl.egesven.app.dto.CarritoItemDto;
import cl.egesven.app.dto.CarritoResponseDto;
import cl.egesven.app.entity.Producto;
import cl.egesven.app.model.Carrito;
import cl.egesven.app.model.CarritoItem;
import org.springframework.stereotype.Service;

@Service
public class CarritoService {

    private final ProductoService productoService;
    private final SessionService sessionService;

    public CarritoService(ProductoService productoService, SessionService sessionService) {
        this.productoService = productoService;
        this.sessionService = sessionService;
    }

    public void agregarProducto(String rut, CarritoItemDto itemDto) {
        Carrito carrito = sessionService.obtenerCarrito(rut);
        Producto p = productoService.consultarProducto(itemDto.getIdProducto());
        if (p == null) throw new RuntimeException("Producto no encontrado");
        CarritoItem item = CarritoItem.builder().producto(p).cantidad(itemDto.getCantidad()).build();
        carrito.agregarProducto(item);
    }

    public void quitarProducto(String rut, int idProducto) {
        Carrito carrito = sessionService.obtenerCarrito(rut);
        carrito.quitarProducto(idProducto);
    }

    public void limpiarCarrito(String rut) {
        sessionService.limpiarCarrito(rut);
    }

    public CarritoResponseDto obtenerCarritoDto(String rut) {
        Carrito c = sessionService.obtenerCarrito(rut);
        var itemsDto = c.getItems().stream().map(i ->
                new CarritoItemDetalleDto(
                        i.getProducto().getId(),
                        i.getProducto().getNombre(),
                        i.getProducto().getPrecio(),
                        i.getCantidad()
                )
        ).toList();
        return new CarritoResponseDto(itemsDto, c.calcularTotal());
    }
}
