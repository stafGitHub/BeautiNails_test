package BeautyNeils.security.jwt;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtInfo {
    private String username;
    private String email;
    private boolean flag;
}
