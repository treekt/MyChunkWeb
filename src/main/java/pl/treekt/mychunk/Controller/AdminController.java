package pl.treekt.mychunk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.Entity.Web.SMS;
import pl.treekt.mychunk.Entity.Web.Command;
import pl.treekt.mychunk.Entity.Web.Position;
import pl.treekt.mychunk.Service.Interfaces.ISMSService;
import pl.treekt.mychunk.Service.Interfaces.ICommandService;
import pl.treekt.mychunk.Service.Interfaces.IPositionService;
import pl.treekt.mychunk.Utils.SharedUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private ISMSService smseservice;

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
        modelAndView.addObject("code", new SMS());
        return modelAndView;
    }

    @PostMapping("/add-code")
    public ModelAndView addsmsesubmit(@ModelAttribute SMS sms){
        ModelAndView modelAndView = new ModelAndView("admin/addCode");
        if(smseservice.addSMS(sms)){
            modelAndView.addObject("successMessage", "Pomyślnie dodano kod SMS!");
        }else{
            //I will do something here in future
        }
        return modelAndView;
    }

    @GetMapping("/add-shop-position")
    public ModelAndView addShopPositionForm(){
        ModelAndView modelAndView = new ModelAndView("admin/addShopPosition");
        List<SMS> sms = smseservice.getAllSMS();

        Position position = new Position();
        List<Command> commands = new ArrayList<Command>();
        commands.add(new Command());
        position.setCommands(commands);

        modelAndView.addObject("position", position);
        modelAndView.addObject("smses", sms);
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
            List<SMS> sms = smseservice.getAllSMS();
            modelAndView.addObject("smses", sms);
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

        List<SMS> sms = smseservice.getAllSMS();
        modelAndView.addObject("smses", sms);
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


        List<SMS> sms = smseservice.getAllSMS();
        modelAndView.addObject("smses", sms);
        modelAndView.addObject("position", position);
        return modelAndView;
    }



    @GetMapping("/code-list")
    public ModelAndView codeList(){
        ModelAndView modelAndView = new ModelAndView("admin/codeList");
        List<SMS> sms = smseservice.getAllSMS();
        modelAndView.addObject("smses", sms);
        return modelAndView;
    }

}
