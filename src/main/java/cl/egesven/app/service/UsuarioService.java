package cl.egesven.app.service;

import cl.egesven.app.dto.ModificarUsuarioDto;
import cl.egesven.app.dto.RegistroUsuarioDto;
import cl.egesven.app.dto.UsuarioDto;
import cl.egesven.app.entity.Usuario;
import cl.egesven.app.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(RegistroUsuarioDto dto) {
        Usuario u = Usuario.builder()
        .rut(dto.getRut())
        .nombre(dto.getNombre())
        .apellido(dto.getApellido())
        .email(dto.getEmail())
        .direccion(dto.getDireccion())
        .password(passwordEncoder.encode(dto.getPassword()))
        .tipoUsuario("C")
        .build();
        return usuarioRepository.save(u);
    }

    public Usuario consultarUsuario(String rut) {
        return usuarioRepository.findById(rut).orElse(null);
    }

    public Usuario consultarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public Usuario modificarUsuario(String rut, ModificarUsuarioDto dto) {
        Usuario u = usuarioRepository.findById(rut).orElseThrow();
        u.setNombre(dto.getNombre());
        u.setApellido(dto.getApellido());
        u.setEmail(dto.getEmail());
        u.setDireccion(dto.getDireccion());
        return usuarioRepository.save(u);
    }

    public void eliminarUsuario(String rut) {
        usuarioRepository.deleteById(rut);
    }

    public UsuarioDto toDto(Usuario usuario) {
        UsuarioDto dto = UsuarioDto.builder()
        .rut(usuario.getRut())
        .nombre(usuario.getNombre())
        .apellido(usuario.getApellido())
        .email(usuario.getEmail())
        .direccion(usuario.getDireccion())
        .build();
        return dto;
    }
}
