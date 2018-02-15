package pl.treekt.mychunk.Controller;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.Entity.Web.Code;
import pl.treekt.mychunk.Entity.Web.Command;
import pl.treekt.mychunk.Entity.Web.Position;
import pl.treekt.mychunk.Service.Interfaces.ICodeService;
import pl.treekt.mychunk.Service.Interfaces.ICommandService;
import pl.treekt.mychunk.Service.Interfaces.IPositionService;
import pl.treekt.mychunk.Utils.SharedUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ICodeService codeService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private ICommandService commandService;

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
            modelAndView.addObject("successMessage", "Pomyślnie dodano kod SMS!");
        }else{
            //I will do something here in future
        }
        return modelAndView;
    }

    @GetMapping("/add-shop-position")
    public ModelAndView addShopPositionForm(){
        ModelAndView modelAndView = new ModelAndView("admin/addShopPosition");
        List<Code> codes = codeService.getAllCodes();

        Position position = new Position();
        List<Command> commands = new ArrayList<Command>();
        commands.add(new Command());
        position.setCommands(commands);

        modelAndView.addObject("position", position);
        modelAndView.addObject("codes", codes);
        return modelAndView;
    }

    @PostMapping("/add-shop-position")
    public ModelAndView addShopPositionSubmit(@ModelAttribute Position position){
        ModelAndView modelAndView = new ModelAndView("admin/addShopPosition");
        if(positionService.addPosition(position)){
            for(Command command : position.getCommands()){
                command.setPosition(position);
                commandService.addCommand(command);
            }
            List<Code> codes = codeService.getAllCodes();
            modelAndView.addObject("codes", codes);
            modelAndView.addObject("successMessage", "Pomyślnie dodano pozycje w sklepie!");
        }else{
            //I will do something here in future
        }
        return modelAndView;
    }

    @PostMapping(value = "/add-shop-position", params = { "addCommand" })
    public ModelAndView addRow(@ModelAttribute Position position){
        ModelAndView modelAndView = new ModelAndView("admin/addShopPosition");

        Command command = new Command();
        command.setId(SharedUtils.randomNegativeId());
        position.getCommands().add(command);

        List<Code> codes = codeService.getAllCodes();
        modelAndView.addObject("codes", codes);
        modelAndView.addObject("position", position);
        return modelAndView;
    }

    @PostMapping(value = "/add-shop-position", params = { "removeCommand" })
    public ModelAndView removeRow(@ModelAttribute Position position, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("admin/addShopPosition");

        Long commandId = Long.valueOf(request.getParameter("removeCommand"));

        for(Command command : position.getCommands()){
            if(command.getId() == commandId){
                position.getCommands().remove(command);
                break;
            }
        }


        List<Code> codes = codeService.getAllCodes();
        modelAndView.addObject("codes", codes);
        modelAndView.addObject("position", position);
        return modelAndView;
    }



    @GetMapping("/code-list")
    public ModelAndView codeList(){
        ModelAndView modelAndView = new ModelAndView("admin/codeList");
        List<Code> codes = codeService.getAllCodes();
        modelAndView.addObject("codes", codes);
        return modelAndView;
    }

}
