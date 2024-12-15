package cl.egesven.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="recibo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recibo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fecha")
    private LocalDate fechaCompra;
    private int total;
    @Column(name = "id_cliente")
    private String rutCliente;
    @Column(name = "id_estado_envio")
    private int idEstadoEnvio;
    @Column(name = "metodo_pago")
    private String metodoPago;
}
