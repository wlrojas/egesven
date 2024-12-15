package cl.egesven.app.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReciboProductoDto {
    private long id;
    private int cantidad;
    private int precioUnitario;
    private int idProducto;
    private String nombreProducto;
}
