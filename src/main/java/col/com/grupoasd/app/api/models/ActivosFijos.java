package col.com.grupoasd.app.api.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ActivosFijos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    @Column(unique = true, nullable = false)
    private String serial;
    private String numeroInternoDeInventario;
    private String peso;
    private String alto;
    private String ancho;
    private String largo;
    private String valorCompra;
    private Date fechaDeCompra;
    @ManyToOne(cascade = CascadeType.ALL)
    private Responsable responsable;
    @JsonIgnore
    private Boolean isDeleted = false;
    private String creadoPor;
}
