package BeautyNeils.database.service;
import BeautyNeils.database.entity.User;
import BeautyNeils.database.repository.UserRepository;
import BeautyNeils.database.dto.LoginDto;
import BeautyNeils.database.dto.RegisterDto;
import BeautyNeils.database.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor()
public class UserService {
    private final UserRepository userRepository;


    public void save(RegisterDto registerDto, String jwtToken) {
            User user = User.builder()
                    .username(registerDto.getUsername())
                    .password(registerDto.getPassword())
                    .email(registerDto.getEmail())
                    .phone(registerDto.getPhone())
                    .jwt(jwtToken)
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
    }

    public boolean checkUserEmail(String email) {
        return userRepository.getUserByEmail(email).isEmpty();
    }

    public void checkLogin(LoginDto loginDto ) {
        boolean empty = userRepository.getUserByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword()).isEmpty();
        if (empty){
            throw new RuntimeException("No such user");
        }
    }

    public String getJwtToken(String email) {
        User user = userRepository.getUserByEmail(email).stream().findFirst().get();
        return user.getJwt();
    }

    public User getUser(String email) {
        return userRepository.getUserByEmail(email).stream().findFirst().orElse(null);
    }


}