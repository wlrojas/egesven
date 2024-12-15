package cl.egesven.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModificarUsuarioDto {
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
}
