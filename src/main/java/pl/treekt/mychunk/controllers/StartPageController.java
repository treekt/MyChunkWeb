package pl.treekt.mychunk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartPageController {

    @GetMapping("/home")
    public String homePage(ModelMap model){
        return "home";
    }

}
