package cl.egesven.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDto {
    private int id;
    private String nombre;
    private String descripcion;
    private int precio;
    private int idCategoria;
    private int stock;
    private String urlImagen;
}
