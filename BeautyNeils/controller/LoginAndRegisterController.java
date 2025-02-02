package BeautyNeils.controller;

import BeautyNeils.database.service.UserService;
import BeautyNeils.database.dto.LoginDto;
import BeautyNeils.database.dto.RegisterDto;
import BeautyNeils.security.jwt.JwtTokenUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LoginAndRegisterController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/registerUser")
    public String register(RegisterDto registerDto, HttpServletResponse response) {
        if (userService.checkUserEmail(registerDto.getEmail())) {
            String token = jwtTokenUtil.generateToken(registerDto);
            userService.save(registerDto,token);
            Cookie cookie = createCookie(token);

            response.addCookie(cookie);
            return "redirect:/beautyNeils";
        } else {
            return "redirect:/beautyNeils/registerError";
        }
    }

    @GetMapping("/loginUser")
    public ModelAndView login(LoginDto loginDto,
                              ModelAndView modelAndView,
                              HttpServletResponse response) {
        try {
            modelAndView.setViewName("redirect:/beautyNeils");
            String jwtToken = userService.getJwtToken(loginDto.getEmail());
            response.addCookie(createCookie(jwtToken));

            return modelAndView;
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/beautyNeils/loginError");
            return modelAndView;
        }

    }


    //Error
    @GetMapping("/beautyNeils/registerError")
    public ModelAndView registerError(ModelAndView modelAndView) {
        modelAndView.setViewName("registerError");
        return modelAndView;
    }

    @GetMapping("/beautyNeils/loginError")
    public ModelAndView loginError(ModelAndView modelAndView) {
        modelAndView.setViewName("loginError");
        return modelAndView;
    }

    private Cookie createCookie(String token) {
        Cookie cookie = new Cookie("__Authorization_BeautyNeils", token);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7);

        return cookie;
    }
}
