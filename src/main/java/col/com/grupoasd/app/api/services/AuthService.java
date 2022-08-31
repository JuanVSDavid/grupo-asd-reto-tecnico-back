package col.com.grupoasd.app.api.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import col.com.grupoasd.app.api.Repository.UserRepository;
import col.com.grupoasd.app.api.models.JwtResponse;
import col.com.grupoasd.app.api.models.User;
import col.com.grupoasd.app.api.models.exceptions.AlreadyExistsException;
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

    public void validateUser(User user) throws UsernameNotFoundException {
        if (Objects.isNull(userRepository.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()))) {
            throw new UsernameNotFoundException("Bad credentials");
        }
    }

    public User registerUser(User user) throws AlreadyExistsException {
        System.out.println(user.getPassword());
        if (!Objects.isNull(userRepository.findById(user.getUsername()))) {
            return userRepository.save(user);
        }
        throw new AlreadyExistsException("User already exists");
    }

}
