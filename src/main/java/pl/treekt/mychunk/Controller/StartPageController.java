package pl.treekt.mychunk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.Entity.Skill;
import pl.treekt.mychunk.Entity.User;
import pl.treekt.mychunk.Service.Interfaces.ISkillService;
import pl.treekt.mychunk.Service.Interfaces.IUserService;

import java.util.List;

@Controller
public class StartPageController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISkillService skillService;

    @GetMapping("/")
    public String homePage(ModelMap model){
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

        ModelAndView model = new ModelAndView();
        model.setViewName("profil");
        model.addObject("user", user);
        model.addObject("skills", skills);
        return model;
    }

}
