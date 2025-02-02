package BeautyNeils.controller.Service;

import BeautyNeils.security.jwt.JwtInfo;
import BeautyNeils.security.jwt.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequiredArgsConstructor
public class ControllerService {
    private final JwtTokenUtil jwtTokenUtil;

    public void htmlAddJwtInfo(ModelAndView modelAndView, String jwt) {
        if (jwt!=null) {
            Claims claims = jwtTokenUtil.getAllClaimsFromToken(jwt);

            JwtInfo jwtInfo = JwtInfo.builder()
                    .email(claims.get("email", String.class))
                    .username(claims.getSubject())
                    .flag(true)
                    .build();
            modelAndView.addObject("jwtInfo", jwtInfo);
        }else {
            JwtInfo jwtInfo = JwtInfo.builder()
                    .flag(false)
                    .build();

            modelAndView.addObject("jwtInfo", jwtInfo);
        }
    }
}
