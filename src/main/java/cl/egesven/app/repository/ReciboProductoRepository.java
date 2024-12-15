package cl.egesven.app.repository;

import cl.egesven.app.entity.ReciboProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReciboProductoRepository extends JpaRepository<ReciboProducto, Long> {
    List<ReciboProducto> findByIdRecibo(int idRecibo);
}
