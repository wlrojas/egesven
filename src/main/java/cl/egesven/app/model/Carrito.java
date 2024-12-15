package cl.egesven.app.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrito {
    private List<CarritoItem> items = new ArrayList<>();

    public void agregarProducto(CarritoItem item) {
        items.stream().filter(i -> i.getProducto().getId() == item.getProducto().getId())
                .findFirst()
                .ifPresentOrElse(i -> i.setCantidad(i.getCantidad() + item.getCantidad())
                ,() -> items.add(item));
    }

    public void quitarProducto(int idProducto) {
        items.removeIf(i -> i.getProducto().getId() == idProducto);
    }

    public int calcularTotal() {
        return items.stream().mapToInt(item -> item.getProducto().getPrecio() * item.getCantidad()).sum();
    }
}
