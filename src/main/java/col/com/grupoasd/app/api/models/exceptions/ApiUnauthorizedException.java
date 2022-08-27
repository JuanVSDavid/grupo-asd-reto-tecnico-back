package col.com.grupoasd.app.api.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ApiUnauthorizedException extends Exception {
    public ApiUnauthorizedException(String message) {
        super(message);
    }
}
