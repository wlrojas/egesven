package cl.egesven.app.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReciboDto {
    private int id;
    private LocalDate fechaCompra;
    private int total;
    private String metodoPago;
    private String rutCliente;
    private String estadoEnvio;
    private List<ReciboProductoDto> productos;
}
