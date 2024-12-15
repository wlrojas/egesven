package cl.egesven.app.model;

import cl.egesven.app.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoItem {
    private Producto producto;
    private int cantidad;
}

