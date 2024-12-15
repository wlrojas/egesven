package cl.egesven.app.config;

import cl.egesven.app.security.AuthService;
import cl.egesven.app.security.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final AuthService authService;
    private final CustomUserDetailsService userDetailsService;

    public JwtFilter(AuthService authService, CustomUserDetailsService userDetailsService) {
        this.authService = authService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        log.info("Processing authentication for request: {}", request.getRequestURI());

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            log.info("JWT Token found: {}", token);

            String username = authService.validateToken(token);
            log.info("Extracted username from token: {}", username);

            if (username != null) {
                var userDetails = userDetailsService.loadUserByUsername(username);
                if (userDetails != null) {
                    Authentication auth =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    log.info("User authenticated: {}", username);
                } else {
                    log.info("UserDetailsService returned null for username: {}", username);
                }
            } else {
                log.info("JWT Token validation failed for token: {}", token);
            }
        } else {
            log.info("No JWT Token found in request headers.");
        }

        filterChain.doFilter(request, response);
    }
}
