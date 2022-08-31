package col.com.grupoasd.app.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import col.com.grupoasd.app.api.models.User;
import col.com.grupoasd.app.api.services.AuthService;
import col.com.grupoasd.app.api.validator.AuthValidator;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "api/oauth")
public class AuthController {

    @Autowired
    private AuthValidator validator;

    @Autowired
    private AuthService authService;

    @Operation(description = "Para obtener el token el grant_type a pasar es 'client_credentials'")
    @PostMapping(path = "client_credentials/access_token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@Validated @RequestBody User user, @RequestParam("grant_type") String grantType)
            throws Exception {
        validator.validate(user, grantType);
        return ResponseEntity.ok(authService.login(user));
    }

    @PostMapping(path = "register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@Validated @RequestBody User user) throws Exception {
        return ResponseEntity.ok(authService.registerUser(user));
    }
}
