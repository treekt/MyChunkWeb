package pl.treekt.mychunk.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.Entity.Game.Armor;
import pl.treekt.mychunk.Entity.Game.Skill;
import pl.treekt.mychunk.Entity.Game.Player;
import pl.treekt.mychunk.Entity.Web.User;
import pl.treekt.mychunk.Payments.Model.CheckResult;
import pl.treekt.mychunk.Payments.SMSPaymentService;
import pl.treekt.mychunk.Service.Interfaces.IArmorService;
import pl.treekt.mychunk.Service.Interfaces.IPlayerService;
import pl.treekt.mychunk.Service.Interfaces.ISkillService;
import pl.treekt.mychunk.Service.Interfaces.IUserService;


import javax.validation.Valid;
import java.util.List;

@Controller
public class StartPageController {

    @Autowired
    private IPlayerService playerService;

    @Autowired
    private ISkillService skillService;

    @Autowired
    private IArmorService armorService;

    @Autowired
    private SMSPaymentService smsPaymentService;

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String homePage(ModelMap model){
        List<Player> players = playerService.getAllPlayers();
        List<Player> lastOnlinePlayers = players.subList(0, 2 >= players.size() ? players.size() : 2);

        CheckResult checkResult = smsPaymentService.checkSMS(0, "code");

        model.addAttribute("players", lastOnlinePlayers);
        model.addAttribute("api", checkResult);
        return "home";
    }



    @GetMapping("/ranking")
    public String ranking(ModelMap model){
        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players", players);
        return "ranking";
    }

    @GetMapping("/profil/{nickname}")
    public ModelAndView profil(@PathVariable String nickname){
        Player user = playerService.getPlayerById(nickname);
        List<Skill> skills = skillService.getAllSkills(nickname);
        List<Armor> armors = armorService.getAllArmors(nickname);

        ModelAndView model = new ModelAndView("profil");
        model.addObject("user", user);
        model.addObject("armors", armors);
        model.addObject("skills", skills);
        return model;
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }


    @GetMapping("/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView("registration");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.getUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.addUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @GetMapping("/admin/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUsername() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }


}
