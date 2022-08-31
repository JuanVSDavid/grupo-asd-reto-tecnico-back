package col.com.grupoasd.app.api.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import col.com.grupoasd.app.api.models.ActivosFijos;

public interface ActivosFijosRepository extends JpaRepository<ActivosFijos, Long> {

    @Query(value = "SELECT * FROM activos_fijos WHERE is_deleted != 1", nativeQuery = true)
    public List<ActivosFijos> findActivosFijosIsNotDeleted();

    @Query(value = "SELECT * FROM activos_fijos WHERE is_deleted != 1 AND id = ?1", nativeQuery = true)
    public ActivosFijos findActivosFijosByIdAndIsNotDeleted(Long id);

    @Query(value = "SELECT * FROM activos_fijos WHERE serial = ?1 AND is_deleted != 1", nativeQuery = true)
    public ActivosFijos findActivosFijosBySerialAndIsNotDeleted(String serial);

    @Query(value = "SELECT * FROM activos_fijos WHERE is_deleted != 1 AND MONTH(fecha_de_compra) = MONTH(?1) AND YEAR(fecha_de_compra) = YEAR(?1) AND DAY(fecha_de_compra) = DAY(?1)", nativeQuery = true)
    public List<ActivosFijos> findActivosFijosBySerialAndFechaDeCompraAndIsNotDeleted(Date fechaDeCompra);

    @Query(value = "SELECT * FROM activos_fijos WHERE is_deleted != 1 AND tipo = ?1", nativeQuery = true)
    public List<ActivosFijos> findActivosFijosByTipoAndIsNotDeleted(String tipo);
}
