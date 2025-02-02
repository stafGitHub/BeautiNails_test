package BeautyNeils.controller;

import BeautyNeils.controller.Service.ControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/beautyNeils")
@RequiredArgsConstructor
public class WebHtmlController {
    private final ControllerService controllerService;

    @GetMapping
    public ModelAndView index(ModelAndView modelAndView ,
                              @CookieValue(value = "__Authorization_BeautyNeils", required = false) String jwt) {
        modelAndView.setViewName("index");
        controllerService.htmlAddJwtInfo(modelAndView, jwt);

        return modelAndView;
    }

    @GetMapping("/info")
    public ModelAndView beautyNeils(ModelAndView modelAndView,
                                    @CookieValue(value = "__Authorization_BeautyNeils", required = false) String jwt) {
        modelAndView.setViewName("beautyNeils");
        controllerService.htmlAddJwtInfo(modelAndView, jwt);
        return modelAndView;
    }

    @GetMapping("services")
    public ModelAndView services(ModelAndView modelAndView,
                                 @CookieValue(value = "__Authorization_BeautyNeils", required = false) String jwt) {
        modelAndView.setViewName("services");
        controllerService.htmlAddJwtInfo(modelAndView, jwt);
        return modelAndView;
    }

    @GetMapping("contact")
    public ModelAndView contact(ModelAndView modelAndView,
                                @CookieValue(value = "__Authorization_BeautyNeils", required = false) String jwt) {
        modelAndView.setViewName("contact");
        controllerService.htmlAddJwtInfo(modelAndView, jwt);
        return modelAndView;
    }

    @GetMapping("/gallery")
    public ModelAndView gallery(ModelAndView modelAndView,
                                @CookieValue(value = "__Authorization_BeautyNeils", required = false) String jwt) {
        modelAndView.setViewName("gallery");
        controllerService.htmlAddJwtInfo(modelAndView, jwt);
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView modelAndView,
                                 @CookieValue(value = "__Authorization_BeautyNeils", required = false) String jwt) {
        modelAndView.setViewName("register");
        controllerService.htmlAddJwtInfo(modelAndView, jwt);
        return modelAndView;
    }
}
