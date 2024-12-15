package cl.egesven.app.repository;

import cl.egesven.app.entity.EstadoEnvioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoEnvioRepository extends JpaRepository<EstadoEnvioEntity, Integer> {
}
