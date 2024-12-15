package cl.egesven.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "usuario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String password;
    @Column(name = "tipo_usuario")
    private String tipoUsuario;
}
