package pl.treekt.mychunk.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.Entity.Armor;
import pl.treekt.mychunk.Entity.Skill;
import pl.treekt.mychunk.Entity.User;
import pl.treekt.mychunk.Payments.Model.CheckResult;
import pl.treekt.mychunk.Payments.SMSPaymentService;
import pl.treekt.mychunk.Service.Interfaces.IArmorService;
import pl.treekt.mychunk.Service.Interfaces.ISkillService;
import pl.treekt.mychunk.Service.Interfaces.IUserService;


import java.util.List;

@Controller
public class StartPageController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISkillService skillService;

    @Autowired
    private IArmorService armorService;

    @Autowired
    private SMSPaymentService smsPaymentService;

    @GetMapping("/")
    public String homePage(ModelMap model){
        List<User> users = userService.getAllUsers();
        List<User> lastOnlineUsers = users.subList(0, 2 >= users.size() ? users.size() : 2);

        CheckResult checkResult = smsPaymentService.checkSMS(0, "code");

        model.addAttribute("users", lastOnlineUsers);
        model.addAttribute("api", checkResult);
        return "home";
    }



    @GetMapping("/ranking")
    public String ranking(ModelMap model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "ranking";
    }

    @GetMapping("/profil/{nickname}")
    public ModelAndView profil(@PathVariable String nickname){
        User user = userService.getUserById(nickname);
        List<Skill> skills = skillService.getAllSkills(nickname);
        List<Armor> armors = armorService.getAllArmors(nickname);

        ModelAndView model = new ModelAndView();
        model.setViewName("profil");
        model.addObject("user", user);
        model.addObject("armors", armors);
        model.addObject("skills", skills);
        return model;
    }

}
