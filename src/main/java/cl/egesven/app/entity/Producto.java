package cl.egesven.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
    private int precio;
    @Column(name = "id_categoria")
    private int idCategoria;
    private int stock;
    @Column(name = "url_imagen")
    private String urlImagen;
}
