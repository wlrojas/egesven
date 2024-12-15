package cl.egesven.app.controller;

import cl.egesven.app.dto.CarritoItemDto;
import cl.egesven.app.dto.CarritoResponseDto;
import cl.egesven.app.entity.Usuario;
import cl.egesven.app.service.CarritoService;
import cl.egesven.app.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@RequiredArgsConstructor
public class CarritoController {
    private final CarritoService carritoService;
    private final UsuarioService usuarioService;

    @PostMapping("/agregar")
    public void agregarProducto(Authentication auth, @RequestBody CarritoItemDto item) {
        String email = auth.getName();
        String rut = carritoServiceObtenerRutDesdeEmail(email);
        carritoService.agregarProducto(rut, item);
    }

    @DeleteMapping("/quitar/{idProducto}")
    public void quitarProducto(Authentication auth, @PathVariable int idProducto) {
        String email = auth.getName();
        String rut = carritoServiceObtenerRutDesdeEmail(email);
        carritoService.quitarProducto(rut, idProducto);
    }

    @GetMapping
    public CarritoResponseDto verCarrito(Authentication auth) {
        String email = auth.getName();
        String rut = carritoServiceObtenerRutDesdeEmail(email);
        return carritoService.obtenerCarritoDto(rut);
    }

    @DeleteMapping("/limpiar")
    public void limpiarCarrito(Authentication auth) {
        String email = auth.getName();
        String rut = carritoServiceObtenerRutDesdeEmail(email);
        carritoService.limpiarCarrito(rut);
    }

    private String carritoServiceObtenerRutDesdeEmail(String email) {
        Usuario u = usuarioService.consultarUsuarioPorEmail(email);
        if (u == null) throw new RuntimeException("Usuario no encontrado por email: " + email);
        return u.getRut();
    }
}
