package cl.egesven.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estado_envio")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoEnvioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descripcion;
}
