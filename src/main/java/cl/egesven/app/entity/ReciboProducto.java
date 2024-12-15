package cl.egesven.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="recibo_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReciboProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int cantidad;
    @Column(name = "precio_unitario")
    private int precioUnitario;
    @Column(name = "id_producto")
    private int idProducto;
    @Column(name = "id_recibo")
    private int idRecibo;

}
