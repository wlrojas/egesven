package cl.egesven.app.repository;

import cl.egesven.app.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    //List<Producto> findByCategoriaIgnoreCase(String categoria);
}
