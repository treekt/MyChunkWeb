package pl.treekt.mychunk.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.API.Minecraft.Service.IMinecraftService;
import pl.treekt.mychunk.Entity.Game.Armor;
import pl.treekt.mychunk.Entity.Game.Skill;
import pl.treekt.mychunk.Entity.Game.Player;
import pl.treekt.mychunk.API.Payments.HomePayManager;
import pl.treekt.mychunk.Entity.Web.User;
import pl.treekt.mychunk.Service.Interfaces.IArmorService;
import pl.treekt.mychunk.Service.Interfaces.IPlayerService;
import pl.treekt.mychunk.Service.Interfaces.ISkillService;
import pl.treekt.mychunk.Service.Interfaces.IUserService;


import java.util.List;

@Controller
@ControllerAdvice
public class MainController {

    @Autowired
    private IPlayerService playerService;

    @Autowired
    private ISkillService skillService;

    @Autowired
    private IArmorService armorService;

    @Autowired
    private HomePayManager homePayManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMinecraftService minecraftService;


    @ModelAttribute
    public void defaultAttributes(Model model){
        model.addAttribute("onlinePlayers", minecraftService.getOnlinePlayers());
        model.addAttribute("maxPlayers", minecraftService.getMaxPlayers());
        model.addAttribute("playersCounter", playerService.countPlayers());
        model.addAttribute("shotsCounter", playerService.countShots());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user;
        try{
            user = userService.getUserByEmail(auth.getName());
        }catch(Exception e){
            user = null;
        }
        model.addAttribute("user", user);
    }

    @GetMapping("")
    public ModelAndView home(){
        ModelAndView  modelAndView = new ModelAndView("home");
        List<Player> players = playerService.getAllPlayers();
        List<Player> lastOnlinePlayers = players.subList(0, 2 >= players.size() ? players.size() : 2);

        return modelAndView;
    }

    @GetMapping("/ranking")
    public String ranking(ModelMap model){
        List<Player> players = playerService.getAllPlayers();
        model.addAttribute("players", players);
        return "ranking";
    }

    @GetMapping("/profil/{nickname}")
    public ModelAndView profil(@PathVariable String nickname){
        Player player = playerService.getPlayerById(nickname);
        List<Skill> skills = skillService.getAllSkills(nickname);
        List<Armor> armors = armorService.getAllArmors(nickname);

        ModelAndView model = new ModelAndView("profil");
        model.addObject("player", player);
        
        model.addObject("armors", armors);
        model.addObject("skills", skills);
        return model;
    }



}
