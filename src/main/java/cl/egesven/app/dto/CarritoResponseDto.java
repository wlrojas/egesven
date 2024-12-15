package cl.egesven.app.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoResponseDto {
    private List<CarritoItemDetalleDto> items;
    private int total;
}

