package pl.treekt.mychunk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.Entity.Web.User;
import pl.treekt.mychunk.Service.Interfaces.IUserService;

import javax.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("account/login");
        return modelAndView;
    }


    @GetMapping("/register")
    public ModelAndView registerForm(){
        ModelAndView modelAndView = new ModelAndView("account/register");
        modelAndView.addObject("userObject", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerSubmit(@ModelAttribute("userObject") User user) {
        ModelAndView modelAndView = new ModelAndView("account/register");
        if(!userService.addUser(user)){
            modelAndView.addObject("error", "Taki użytkownik już istnieje!");
            return modelAndView;
        }

        modelAndView.addObject("userObject", user);
        modelAndView.addObject("success", true);
        return modelAndView;
    }
}
