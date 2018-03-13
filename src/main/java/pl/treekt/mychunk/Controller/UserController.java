package pl.treekt.mychunk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.Entity.Web.User;
import pl.treekt.mychunk.Service.Interfaces.ISMSPaymentService;
import pl.treekt.mychunk.Service.Interfaces.IUserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISMSPaymentService smsPaymentService;

    @GetMapping("")
    public ModelAndView home(){
        return new ModelAndView("user/home");
    }

    @GetMapping("/shop-history")
    public ModelAndView getShopHistory(){
        ModelAndView modelAndView = new ModelAndView("user/shopHistory");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName());
        modelAndView.addObject("email", user.getEmail());
        modelAndView.addObject("paymentList", smsPaymentService.getPaymentsByEmail(user.getEmail()));
        return modelAndView;
    }
}
