package cl.egesven.app.controller;

import cl.egesven.app.dto.AuthRequestDto;
import cl.egesven.app.dto.AuthResponseDto;
import cl.egesven.app.entity.Usuario;
import cl.egesven.app.repository.UsuarioRepository;
import cl.egesven.app.security.AuthService;
import cl.egesven.app.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        Usuario u = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), u.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }

        String token = authService.generateToken(u.getEmail());
        String tipoUsuario = u.getTipoUsuario().equals("A")
                ? "ROLE_ADMIN"
                : "ROLE_USER";
        return AuthResponseDto.builder().token(token).tipoUsuario(tipoUsuario).build();
    }
}
