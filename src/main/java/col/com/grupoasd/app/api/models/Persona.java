package col.com.grupoasd.app.api.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "personas")
@JsonTypeName("persona")
public class Persona extends Responsable {

}
