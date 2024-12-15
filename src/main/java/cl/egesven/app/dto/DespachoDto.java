package cl.egesven.app.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DespachoDto {
    private int idDespacho;
    private LocalDate fecha;
    private String estado;
    private int intentos;
    private int idRecibo;
}
