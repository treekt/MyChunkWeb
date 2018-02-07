package pl.treekt.mychunk.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.Entity.Game.Armor;
import pl.treekt.mychunk.Entity.Game.Skill;
import pl.treekt.mychunk.Entity.Game.Player;
import pl.treekt.mychunk.Payments.Model.CheckResult;
import pl.treekt.mychunk.Payments.SMSPaymentService;
import pl.treekt.mychunk.Service.Interfaces.IArmorService;
import pl.treekt.mychunk.Service.Interfaces.IPlayerService;
import pl.treekt.mychunk.Service.Interfaces.ISkillService;


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

}
