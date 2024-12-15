package cl.egesven.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoItemDetalleDto {
    private int idProducto;
    private String nombre;
    private int precio;
    private int cantidad;
}
