package col.com.grupoasd.app.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/activos_fijos")
public class ActivosFijosController {

    @GetMapping
    public ResponseEntity<Object> getActivos() {
        return ResponseEntity.ok("Buenas");
    }
}
