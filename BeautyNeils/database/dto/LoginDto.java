package BeautyNeils.database.dto;

import lombok.Data;

@Data
public class LoginDto implements BaseDto {
    private String email;
    private String password;
}
