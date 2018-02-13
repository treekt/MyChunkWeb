package pl.treekt.mychunk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.Entity.Web.Code;
import pl.treekt.mychunk.Entity.Web.Position;
import pl.treekt.mychunk.Service.Interfaces.ICodeService;
import pl.treekt.mychunk.Service.Interfaces.IPositionService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ICodeService codeService;

    @Autowired
    private IPositionService positionService;

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("admin/home");
        return modelAndView;
    }

    @GetMapping("/add-code")
    public ModelAndView addCodeForm(){
        ModelAndView modelAndView = new ModelAndView("admin/addCode");
        modelAndView.addObject("code", new Code());
        return modelAndView;
    }

    @PostMapping("/add-code")
    public ModelAndView addCodeSubmit(@ModelAttribute Code code){
        ModelAndView modelAndView = new ModelAndView("admin/addCode");
        if(codeService.addCode(code)){
            modelAndView.addObject("successMessage", "Pomyślnie dodano kod!");
        }else{

        }
        return modelAndView;
    }

    @GetMapping("/add-shop-position")
    public ModelAndView addShopPositionForm(){
        ModelAndView modelAndView = new ModelAndView("admin/addShopPosition");
        List<Code> codes = codeService.getAllCodes();
        modelAndView.addObject("position", new Position());
        modelAndView.addObject("codes", codes);
        return modelAndView;
    }

    @PostMapping("/add-shop-position")
    public ModelAndView addShopPositionSubmit(@ModelAttribute Position position){
        ModelAndView modelAndView = new ModelAndView("admin/addShopPosition");
        if(positionService.addPosition(position)){
            //I will do something here in future
        }else{
            //I will do something here in future
        }
        return modelAndView;
    }

}
