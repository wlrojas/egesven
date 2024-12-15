package cl.egesven.app.repository;

import cl.egesven.app.entity.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReciboRepository extends JpaRepository<Recibo, Integer> {
    List<Recibo> findByRutCliente(String rutCliente);
}
