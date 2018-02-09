package pl.treekt.mychunk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView("account/register");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("account/register");
        User userExists = userService.getUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Istnieje już użytkownik o takim adresie E-mail");
        }

        if (!bindingResult.hasErrors()) {
            userService.addUser(user);
            modelAndView.addObject("successMessage", "Pomyślnie zarejestrowano!");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("register");
        }
        return modelAndView;
    }
}
