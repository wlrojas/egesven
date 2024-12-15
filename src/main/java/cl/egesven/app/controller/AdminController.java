package cl.egesven.app.controller;

import cl.egesven.app.dto.ProductoDto;
import cl.egesven.app.dto.ReciboDto;
import cl.egesven.app.dto.ReciboProductoDto;
import cl.egesven.app.entity.EstadoEnvio;
import cl.egesven.app.entity.Producto;
import cl.egesven.app.entity.Recibo;
import cl.egesven.app.entity.ReciboProducto;
import cl.egesven.app.repository.ReciboProductoRepository;
import cl.egesven.app.service.ProductoService;
import cl.egesven.app.service.ReciboService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductoService productoService;
    private final ReciboService reciboService;
    private final ReciboProductoRepository reciboProductoRepository;

    @PostMapping("/producto")
    public ProductoDto registrarProducto(@RequestBody ProductoDto dto) {
        Producto p = productoService.registrarProducto(dto);
        return productoService.toDto(p);
    }

    @PutMapping("/producto")
    public ProductoDto modificarProducto(@RequestBody ProductoDto dto) {
        Producto p = productoService.modificarProducto(dto);
        return productoService.toDto(p);
    }

    @DeleteMapping("/producto/{id}")
    public void eliminarProducto(@PathVariable int id) {
        productoService.eliminarProducto(id);
    }

    @GetMapping("/recibos/{rut}")
    public List<ReciboDto> listarRecibos(@PathVariable String rut) {
        return reciboService.recibosPorCliente(rut)
                .stream().map(this::toDto).toList();
    }

    @GetMapping("/recibos/all")
    public List<ReciboDto> listarTodosRecibos() {
        return reciboService.listarRecibos()
                .stream().map(this::toDto).toList();
    }

    @GetMapping("/recibos/detalle/{idRecibo}")
    public ReciboDto detalleRecibo(@PathVariable int idRecibo) {
        return reciboService.consultarRecibo(idRecibo);
    }

    @PutMapping("/recibos/estado/{idRecibo}")
    public ReciboDto cambiarEstadoRecibo(@PathVariable int idRecibo, @RequestParam int estado) {
        Recibo r = reciboService.modificarEstadosRecibo(idRecibo, estado);
        return toDto(r);
    }

    private ReciboDto toDto(Recibo r) {
        return ReciboDto.builder()
                .id(r.getId())
                .fechaCompra(r.getFechaCompra())
                .total(r.getTotal())
                .metodoPago(r.getMetodoPago())
                .rutCliente(r.getRutCliente())
                .estadoEnvio(EstadoEnvio.fromId(r.getIdEstadoEnvio()).getDescripcion())
                .build();
    }
}
