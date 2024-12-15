package cl.egesven.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroUsuarioDto {
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String password;
}
