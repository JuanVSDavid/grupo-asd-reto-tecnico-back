package col.com.grupoasd.app.api.models.security;

import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import col.com.grupoasd.app.api.utils.GsonUtils;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;

/**
 * @author Juan Ruiz
 * @apiNote Esta clase se encarga de consumir toda la configuración del
 *          aplication.yml, y tiene los métodos para gestionar el JWT.
 */
@Component
public class JwtIO {
    @Value("${jms.jwt.token.secret:secret}")
    private String SECRET;
    @Value("${jms.jwt.timezone:UTC}")
    private String TIMEZONE;
    @Value("${jms.jwt.token.expires_in:3600}")
    private Integer EXPIRES_IN;
    @Value("${jms.jwt.issuer:none}")
    private String ISSUER;

    /**
     * Este método va a construir un HMAC signer usando SHA-256
     * 
     * @param o
     * @return
     */
    public String generateToken(Object o) {
        String subject = GsonUtils.toJson(o);
        Signer signer = HMACSigner.newSHA256Signer(this.SECRET);
        TimeZone timeZone = TimeZone.getTimeZone(this.TIMEZONE);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(timeZone.toZoneId()).plusSeconds(this.EXPIRES_IN);
        JWT jwt = new JWT()
                .setIssuer(this.ISSUER)
                .setIssuedAt(ZonedDateTime.now(timeZone.toZoneId()))
                .setSubject(subject)
                .setExpiration(zonedDateTime);
        return JWT.getEncoder().encode(jwt, signer);
    }

    public Boolean validateToken(String encodedJWT) throws Exception {
        boolean result = false;
        JWT jwt = jwt(encodedJWT);
        try {
            result = jwt.isExpired();
        } catch (Exception e) {
            return true;
        }
        return result;
    }

    public String getPayload(String encodedJWT) throws Exception {
        JWT jwt = jwt(encodedJWT);
        return jwt.subject;
    }

    /**
     * Este método se encarga de decodificar el token
     * 
     * @param encodedJWT
     * @return
     */
    private JWT jwt(String encodedJWT) throws Exception {
        Verifier verifier = HMACVerifier.newVerifier(this.SECRET);
        return JWT.getDecoder().decode(encodedJWT, verifier);
    }
}
