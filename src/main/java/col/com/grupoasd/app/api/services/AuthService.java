package col.com.grupoasd.app.api.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import col.com.grupoasd.app.api.Repository.UserRepository;
import col.com.grupoasd.app.api.models.JwtResponse;
import col.com.grupoasd.app.api.models.User;
import col.com.grupoasd.app.api.models.exceptions.UsernameNotFoundException;
import col.com.grupoasd.app.api.models.security.JwtIO;
import col.com.grupoasd.app.api.utils.DateUtils;

@Service
public class AuthService {

    @Autowired
    private JwtIO jwtIO;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private UserRepository userRepository;

    @Value("${jms.jwt.token.expires_in}")
    private Integer EXPIRES_IN;

    public JwtResponse login(User user) throws UsernameNotFoundException {
        validateUser(user);
        JwtResponse jwt = JwtResponse.builder().tokenType("Bearer")
                .accessToken(jwtIO.generateToken(user.getUsername()))
                .issuedAt(Long.toString(dateUtils.getDateMillis()))
                .expiresIn(
                        EXPIRES_IN)
                .username(user.getUsername()).build();
        return jwt;
    }

    public boolean validateUser(User user) throws UsernameNotFoundException {
        boolean result = true;
        if (Objects.isNull(userRepository.getUserByUsername(user.getUsername(), user.getPassword()))) {
            throw new UsernameNotFoundException("Bad credentials");
        }
        return result;
    }

}
