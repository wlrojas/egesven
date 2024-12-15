package cl.egesven.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
}
