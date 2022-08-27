package col.com.grupoasd.app.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

    @JsonProperty(value = "access_token")
    private String accessToken;
    @JsonProperty(value = "token_type")
    private String tokenType;
    @JsonProperty(value = "expires_in")
    private Integer expiresIn;
    @JsonProperty(value = "issued_at")
    private String issuedAt;
    @JsonProperty(value = "username")
    private String username;
}
