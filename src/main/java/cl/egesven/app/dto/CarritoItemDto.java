package cl.egesven.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoItemDto {
    @JsonProperty("id_producto")
    private int idProducto;
    private int cantidad;
}
