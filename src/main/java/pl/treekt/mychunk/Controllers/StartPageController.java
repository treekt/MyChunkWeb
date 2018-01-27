package pl.treekt.mychunk.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StartPageController {

    @GetMapping("/")
    public String homePage(ModelMap model){
        return "home";
    }


    @GetMapping("/ranking")
    public String rankPage(ModelMap model){
        return "ranking";
    }

}
