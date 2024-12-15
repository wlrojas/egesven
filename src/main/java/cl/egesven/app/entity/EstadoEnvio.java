package cl.egesven.app.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EstadoEnvio {
    PENDIENTE(1, "Pendiente"),
    PROCESANDO(2, "Procesando"),
    ENVIADO(3, "Enviado"),
    ENTREGADO(4, "Entregado");

    private final int id;
    private final String descripcion;

    EstadoEnvio(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public static EstadoEnvio fromId(int id) {
        return Arrays.stream(EstadoEnvio.values()).filter(value -> value.getId() == id).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Estado no válido: " + id));
    }
}
