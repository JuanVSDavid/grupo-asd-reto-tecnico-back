package col.com.grupoasd.app.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import col.com.grupoasd.app.api.models.ActivosFijos;
import col.com.grupoasd.app.api.services.ActivosFijosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(value = "api/activos_fijos")
public class ActivosFijosController {

    @Autowired
    private ActivosFijosService activosFijosService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActivosFijos>> getActivosFijos() throws Exception {
        return ResponseEntity.ok(activosFijosService.getListOfItems());
    }

    @GetMapping(path = "serial/{serial}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActivosFijos> getActivosFijosBySerial(@PathVariable String serial) throws Exception {
        return ResponseEntity.ok(activosFijosService.getActivosFijosBySerial(serial));
    }

    @GetMapping(path = "tipo/{tipo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActivosFijos>> getActivosFijosByTipo(@PathVariable String tipo) throws Exception {
        return ResponseEntity.ok(activosFijosService.getActivosFijosByTipo(tipo));
    }

    @Operation(description = "Para buscar un activo fijo por fecha el formato a pasar es este 'dd-MM-yyyy'")
    @GetMapping(path = "fecha_de_compra/{fechaDeCompra}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ActivosFijos>> getActivosFijosByFechaDeCompra(@PathVariable String fechaDeCompra)
            throws Exception {
        return ResponseEntity.ok(activosFijosService.getActivosFijosByFechaDeCompra(fechaDeCompra));
    }

    @Operation(description = "El responsable puede ser una persona o un area con sus ciudades, tienen que rellenar el campo type con el tipo a asignar explicados previamente")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActivosFijos> insertarActivoFijo(@RequestAttribute("usuario") String username,
            @Validated @RequestBody ActivosFijos activosFijos)
            throws Exception {
        return ResponseEntity.ok(activosFijosService.insertItem(activosFijos, username));
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ActivosFijos> actualizarActivoFijo(@PathVariable String id,
            @Validated @RequestBody ActivosFijos activosFijos) throws Exception {
        return ResponseEntity.ok(activosFijosService.updateItem(id, activosFijos));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarActivoFijo(@PathVariable String id) throws Exception {
        activosFijosService.deleteItem(id);
        return ResponseEntity.ok("Se elimino el activo fijo con id: " + id);
    }
}
