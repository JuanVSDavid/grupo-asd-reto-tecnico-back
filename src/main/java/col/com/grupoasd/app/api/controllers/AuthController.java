package col.com.grupoasd.app.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import col.com.grupoasd.app.api.models.User;
import col.com.grupoasd.app.api.services.AuthService;
import col.com.grupoasd.app.api.validator.AuthValidator;

@RestController
@RequestMapping(path = "api/oauth")
public class AuthController {

    @Autowired
    private AuthValidator validator;

    @Autowired
    private AuthService authService;

    @PostMapping(path = "client_credentials/access_token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody User user, @RequestParam("grant_type") String grantType)
            throws Exception {
        validator.validate(user, grantType);
        return ResponseEntity.ok(authService.login(user));
    }
}
