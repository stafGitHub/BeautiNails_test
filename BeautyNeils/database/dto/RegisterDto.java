package BeautyNeils.database.dto;

import lombok.*;

@Data
public class RegisterDto implements BaseDto {
    String username;
    String password;
    String email;
    String phone;
}