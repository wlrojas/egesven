package cl.egesven.app.service;

import cl.egesven.app.dto.ReciboDto;
import cl.egesven.app.dto.ReciboProductoDto;
import cl.egesven.app.entity.EstadoEnvio;
import cl.egesven.app.entity.Producto;
import cl.egesven.app.entity.Recibo;
import cl.egesven.app.entity.ReciboProducto;
import cl.egesven.app.model.Carrito;
import cl.egesven.app.repository.ReciboProductoRepository;
import cl.egesven.app.repository.ReciboRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReciboService {

    private final ReciboRepository reciboRepository;
    private final ReciboProductoRepository reciboProductoRepository;
    private final SessionService sessionService;
    private final ProductoService productoService;

    public ReciboService(ReciboRepository reciboRepository, ReciboProductoRepository reciboProductoRepository, SessionService sessionService, ProductoService productoService) {
        this.reciboRepository = reciboRepository;
        this.reciboProductoRepository = reciboProductoRepository;
        this.sessionService = sessionService;
        this.productoService = productoService;
    }

    public Recibo crearReciboDesdeCarrito(String rut, String metodoPago) {
        Carrito carrito = sessionService.obtenerCarrito(rut);
        int subtotal = carrito.calcularTotal();
        int impuesto = (int) (subtotal * 0.19);
        int envio = 3000;
        int total = subtotal + impuesto + envio;

        Recibo r = Recibo.builder()
        .fechaCompra(LocalDate.now())
        .total(total)
        .rutCliente(rut)
        .idEstadoEnvio(1)
        .metodoPago(metodoPago)
        .build();
        reciboRepository.save(r);

        carrito.getItems().forEach(item -> {
            ReciboProducto rp = new ReciboProducto();
            rp.setCantidad(item.getCantidad());
            rp.setPrecioUnitario(item.getProducto().getPrecio());
            rp.setIdProducto(item.getProducto().getId());
            rp.setIdRecibo(r.getId());
            reciboProductoRepository.save(rp);

            Producto p = productoService.consultarProducto(item.getProducto().getId());
            p.setStock(p.getStock() - item.getCantidad());
            productoService.modificarProducto(productoService.toDto(p));
        });
        sessionService.limpiarCarrito(rut);
        return r;
    }

    public ReciboDto consultarRecibo(int idRecibo) {
        Recibo r = reciboRepository.findById(idRecibo).orElse(null);
        List<ReciboProducto> listRp = reciboProductoRepository.findByIdRecibo(idRecibo);
        ReciboDto dto = new ReciboDto();
        List<ReciboProductoDto> rpDto = listRp.stream().map(rp ->
                ReciboProductoDto.builder()
                        .id(rp.getId())
                        .cantidad(rp.getCantidad())
                        .precioUnitario(rp.getPrecioUnitario())
                        .idProducto(rp.getIdProducto())
                        .nombreProducto(productoService.consultarProducto(rp.getIdProducto()).getNombre())
                        .build()).toList();
        dto.setId(idRecibo);
        dto.setProductos(rpDto);
        dto.setMetodoPago(r.getMetodoPago());
        dto.setRutCliente(r.getRutCliente());
        dto.setTotal(r.getTotal());
        dto.setFechaCompra(r.getFechaCompra());
        dto.setEstadoEnvio(EstadoEnvio.fromId(r.getIdEstadoEnvio()).getDescripcion());
        return dto;
    }

    public List<Recibo> recibosPorCliente(String rut) {
        return reciboRepository.findByRutCliente(rut);
    }

    public List<Recibo> listarRecibos() {
        return reciboRepository.findAll();
    }

    public Recibo modificarEstadosRecibo(int idRecibo, int idEstadoEnvio) {
        Recibo r = reciboRepository.findById(idRecibo).orElseThrow();
        r.setIdEstadoEnvio(idEstadoEnvio);
        return reciboRepository.save(r);
    }
}
