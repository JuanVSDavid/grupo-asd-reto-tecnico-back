package col.com.grupoasd.app.api.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import col.com.grupoasd.app.api.models.User;
import col.com.grupoasd.app.api.models.exceptions.ApiUnauthorizedException;

@Component
public class AuthValidator {

    private static final String CLIENT_CREDENTIALS = "client_credentials";

    public void validate(User user, String grantType) throws ApiUnauthorizedException {
        if (grantType.isEmpty() || !grantType.equals(CLIENT_CREDENTIALS)) {
            messageApiUnathorized("Grant type is invalid");
        }
        if (Objects.isNull(user) || user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            messageApiUnathorized("User is null");
        }
    }

    private void messageApiUnathorized(String message) throws ApiUnauthorizedException {
        throw new ApiUnauthorizedException(message);
    }
}
