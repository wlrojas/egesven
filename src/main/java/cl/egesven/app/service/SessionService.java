package cl.egesven.app.service;

import cl.egesven.app.model.Carrito;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionService {
    private Map<String, Carrito> carritosPorUsuario = new ConcurrentHashMap<>();

    public Carrito obtenerCarrito(String rut) {
        return carritosPorUsuario.computeIfAbsent(rut, k -> new Carrito());
    }

    public void limpiarCarrito(String rut) {
        carritosPorUsuario.remove(rut);
    }
}
