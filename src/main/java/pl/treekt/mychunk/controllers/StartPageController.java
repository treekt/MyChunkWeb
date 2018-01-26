package pl.treekt.mychunk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class StartPageController {

    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public String homePage(ModelMap model){
        return "home";
    }

}
