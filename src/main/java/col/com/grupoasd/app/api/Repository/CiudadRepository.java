package col.com.grupoasd.app.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import col.com.grupoasd.app.api.models.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
