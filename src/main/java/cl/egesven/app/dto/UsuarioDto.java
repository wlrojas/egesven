package cl.egesven.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
}
