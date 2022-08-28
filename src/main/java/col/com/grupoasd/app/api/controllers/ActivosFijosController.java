package col.com.grupoasd.app.api.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import col.com.grupoasd.app.api.models.ActivosFijos;
import col.com.grupoasd.app.api.models.exceptions.AlreadyExistsException;
import col.com.grupoasd.app.api.models.exceptions.NotFoundException;
import col.com.grupoasd.app.api.services.ActivosFijosService;

@RestController
@RequestMapping(value = "api/activos_fijos")
public class ActivosFijosController {

    @Autowired
    private ActivosFijosService activosFijosService;

    @GetMapping
    public ResponseEntity<List<ActivosFijos>> getActivosFijos() {
        return ResponseEntity.ok(activosFijosService.getListOfItems());
    }

    @GetMapping(path = "serial/{serial}")
    public ResponseEntity<ActivosFijos> getActivosFijosBySerial(@PathVariable String serial) throws Exception {
        return ResponseEntity.ok(activosFijosService.getActivosFijosBySerial(serial));
    }

    @GetMapping(path = "tipo/{tipo}")
    public ResponseEntity<List<ActivosFijos>> getActivosFijosByTipo(@PathVariable String tipo) throws Exception {
        return ResponseEntity.ok(activosFijosService.getActivosFijosByTipo(tipo));
    }

    @GetMapping(path = "fecha_de_compra/{fechaDeCompra}")
    public ResponseEntity<List<ActivosFijos>> getActivosFijosByFechaDeCompra(@PathVariable Date fechaDeCompra)
            throws Exception {
        return ResponseEntity.ok(activosFijosService.getActivosFijosByFechaDeCompra(fechaDeCompra));
    }

    @PostMapping
    public ResponseEntity<ActivosFijos> insertarActivoFijo(@Validated @RequestBody ActivosFijos activosFijos)
            throws Exception {
        return ResponseEntity.ok(activosFijosService.insertItem(activosFijos));
    }

    @PutMapping("{id}")
    public ResponseEntity<ActivosFijos> actualizarActivoFijo(@PathVariable String id,
            @Validated @RequestBody ActivosFijos activosFijos) throws Exception {
        return ResponseEntity.ok(activosFijosService.updateItem(id, activosFijos));
    }
}
