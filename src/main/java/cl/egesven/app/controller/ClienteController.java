package cl.egesven.app.controller;

import cl.egesven.app.dto.ProductoDto;
import cl.egesven.app.dto.ReciboDto;
import cl.egesven.app.entity.EstadoEnvio;
import cl.egesven.app.entity.Recibo;
import cl.egesven.app.entity.Usuario;
import cl.egesven.app.service.ProductoService;
import cl.egesven.app.service.ReciboService;
import cl.egesven.app.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ProductoService productoService;
    private final ReciboService reciboService;
    private final UsuarioService usuarioService;

    @GetMapping("/productos")
    public List<ProductoDto> listarProductos() {
        return productoService.toDto(productoService.listarTodos());
    }

    @GetMapping("/productos/buscar")
    public List<ProductoDto> buscarProductos(@RequestParam(required = false) String nombre) {
        if (!nombre.isBlank()) {
            return productoService.toDto(productoService.buscarPorNombre(nombre));
        } else {
            return productoService.toDto(productoService.listarTodos());
        }
    }

    @PostMapping("/comprar")
    public ReciboDto realizarCompra(Authentication auth, @RequestParam String metodoPago) {
        String email = auth.getName();
        String rut = carritoServiceObtenerRutDesdeEmail(email);
        Recibo r = reciboService.crearReciboDesdeCarrito(rut, metodoPago);

        ReciboDto dto = new ReciboDto();
        dto.setId(r.getId());
        dto.setFechaCompra(r.getFechaCompra());
        dto.setTotal(r.getTotal());
        dto.setMetodoPago(r.getMetodoPago());
        dto.setRutCliente(r.getRutCliente());
        dto.setEstadoEnvio(EstadoEnvio.fromId(r.getIdEstadoEnvio()).getDescripcion());
        return dto;
    }

    @GetMapping("/recibos")
    public List<ReciboDto> listarRecibos(Authentication auth) {
        String email = auth.getName();
        String rut = carritoServiceObtenerRutDesdeEmail(email);
        return reciboService.recibosPorCliente(rut).stream().map(r -> {
            ReciboDto dto = new ReciboDto();
            dto.setId(r.getId());
            dto.setFechaCompra(r.getFechaCompra());
            dto.setTotal(r.getTotal());
            dto.setMetodoPago(r.getMetodoPago());
            dto.setRutCliente(r.getRutCliente());
            dto.setEstadoEnvio(EstadoEnvio.fromId(r.getIdEstadoEnvio()).getDescripcion());
            return dto;
        }).toList();
    }

    @GetMapping("/recibos/{idRecibo}")
    public ReciboDto reciboPorId(Authentication auth, @PathVariable int idRecibo) {
        return reciboService.consultarRecibo(idRecibo);
    }

    private String carritoServiceObtenerRutDesdeEmail(String email) {
        Usuario u = usuarioService.consultarUsuarioPorEmail(email);
        if (u == null) throw new RuntimeException("Usuario no encontrado por email: " + email);
        return u.getRut();
    }
}
