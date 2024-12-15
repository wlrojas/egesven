package cl.egesven.app.controller;

import cl.egesven.app.dto.ModificarUsuarioDto;
import cl.egesven.app.dto.RegistroUsuarioDto;
import cl.egesven.app.dto.UsuarioDto;
import cl.egesven.app.entity.Usuario;
import cl.egesven.app.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/registrar")
    public UsuarioDto registrar(@RequestBody RegistroUsuarioDto dto) {
        Usuario u = usuarioService.registrarUsuario(dto);
        return usuarioService.toDto(u);
    }

    @GetMapping("/{rut}")
    public UsuarioDto buscarPorRut(@PathVariable String rut) {
        Usuario u = usuarioService.consultarUsuario(rut);
        if (u == null) return null;
        return usuarioService.toDto(u);
    }

    @PutMapping("/{rut}")
    public UsuarioDto actualizar(@PathVariable String rut, @RequestBody ModificarUsuarioDto dto) {
        Usuario u = usuarioService.modificarUsuario(rut, dto);
        return usuarioService.toDto(u);
    }

    @DeleteMapping("/{rut}")
    public void eliminar(@PathVariable String rut) {
        usuarioService.eliminarUsuario(rut);
    }
}
