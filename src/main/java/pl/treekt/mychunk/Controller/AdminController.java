package pl.treekt.mychunk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.Entity.Web.SMS;
import pl.treekt.mychunk.Entity.Web.Command;
import pl.treekt.mychunk.Entity.Web.Position;
import pl.treekt.mychunk.Entity.Web.Voucher;
import pl.treekt.mychunk.Model.TransactionModel;
import pl.treekt.mychunk.Service.Interfaces.*;
import pl.treekt.mychunk.Utils.SharedUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value= "/admin")
public class AdminController {

    @Autowired
    private ISMSService smsService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private ICommandService commandService;

    @Autowired
    private IVoucherService voucherService;

    @Autowired
    private ISMSPaymentService smsPaymentService;

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public ModelAndView home() {
        return new ModelAndView("admin/home");
    }

    @GetMapping("user-list")
    public ModelAndView userList(){
        ModelAndView modelAndView = new ModelAndView("admin/userList");
        modelAndView.addObject("userList", userService.getAllUsers());
        return modelAndView;
    }


    @GetMapping("/add-sms")
    public ModelAndView addSMSForm() {
        ModelAndView modelAndView = new ModelAndView("admin/addSMS");
        modelAndView.addObject("sms", new SMS());
        return modelAndView;
    }

    @PostMapping("/add-sms")
    public ModelAndView addSMSSubmit(@ModelAttribute SMS sms) {
        ModelAndView modelAndView = new ModelAndView("admin/addSMS");
        modelAndView.addObject("sms", sms);

        if (!smsService.addSMS(sms)) {
            modelAndView.addObject("error", "Podany sms już istnieje!");
            return modelAndView;
        }

        modelAndView.addObject("sms", new SMS());
        modelAndView.addObject("success", true);
        return modelAndView;
    }


    @GetMapping("/position-list")
    public ModelAndView positionList(){
        ModelAndView modelAndView = new ModelAndView("admin/positionList");
        modelAndView.addObject("positionList", positionService.getAllPositions());
        return modelAndView;
    }

    @GetMapping("/add-shop-position")
    public ModelAndView addShopPositionForm() {
        ModelAndView modelAndView = new ModelAndView("admin/addPosition");
        List<SMS> smsList = smsService.getAllSMS();

        Position position = new Position();
        List<Command> commands = new ArrayList<Command>();
        commands.add(new Command());
        position.setCommands(commands);

        modelAndView.addObject("position", position);
        modelAndView.addObject("smsList", smsList);
        return modelAndView;
    }

    @PostMapping("/add-shop-position")
    public ModelAndView addShopPositionSubmit(@ModelAttribute Position position) {
        ModelAndView modelAndView = new ModelAndView("admin/addPosition");
        if (positionService.addPosition(position)) {
            for (Command command : position.getCommands()) {
                command.setPosition(position);
                commandService.addCommand(command);
            }
            List<SMS> smsList = smsService.getAllSMS();
            modelAndView.addObject("smsList", smsList);
            modelAndView.addObject("success", true);
        } else {
            //I will do something here in future
        }
        return modelAndView;
    }

    @PostMapping(value = "/add-shop-position", params = {"addCommand"})
    public ModelAndView addRow(@ModelAttribute Position position) {
        ModelAndView modelAndView = new ModelAndView("admin/addPosition");

        Command command = new Command();
        command.setId(SharedUtils.randomNegativeId());
        position.getCommands().add(command);

        List<SMS> smsList = smsService.getAllSMS();
        modelAndView.addObject("smsList", smsList);
        modelAndView.addObject("position", position);
        return modelAndView;
    }

    @PostMapping(value = "/add-shop-position", params = {"removeCommand"})
    public ModelAndView removeRow(@ModelAttribute Position position, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin/addPosition");

        Long commandId = Long.valueOf(request.getParameter("removeCommand"));

        for (Command command : position.getCommands()) {
            if (command.getId() == commandId) {
                position.getCommands().remove(command);
                break;
            }
        }


        List<SMS> smsList = smsService.getAllSMS();
        modelAndView.addObject("smsList", smsList);
        modelAndView.addObject("position", position);
        return modelAndView;
    }


    @GetMapping("/sms-list")
    public ModelAndView smsList() {
        ModelAndView modelAndView = new ModelAndView("admin/smsList");
        List<SMS> smsList = smsService.getAllSMS();
        modelAndView.addObject("smsList", smsList);
        return modelAndView;
    }

    @GetMapping("/voucher-list")
    public ModelAndView voucherList() {
        ModelAndView modelAndView = new ModelAndView("admin/voucherList");
        List<Voucher> voucherList = voucherService.getAllVouchers();
        modelAndView.addObject("voucherList", voucherList);
        return modelAndView;
    }

    @GetMapping("/add-voucher")
    public ModelAndView addVoucherForm() {
        ModelAndView modelAndView = new ModelAndView("admin/addVoucher");
        modelAndView.addObject("voucher", new Voucher());
        modelAndView.addObject("positions", positionService.getAllPositions());
        return modelAndView;
    }

    @PostMapping("/add-voucher")
    public ModelAndView addVoucherForm(@ModelAttribute Voucher voucher) {
        ModelAndView modelAndView = new ModelAndView("admin/addVoucher");
        modelAndView.addObject("voucher", voucher);
        modelAndView.addObject("positions", positionService.getAllPositions());

        if(voucherService.voucherExists(voucher.getCode())){
            modelAndView.addObject("error", "Taki voucher juz istnieje");
            return modelAndView;
        }


        voucherService.addVoucher(voucher);

        modelAndView.addObject("voucher", new Voucher());
        modelAndView.addObject("success", true);
        return modelAndView;
    }

    @GetMapping("/shop-history")
    public ModelAndView getShopHistory(){
        ModelAndView modelAndView = new ModelAndView("admin/shopHistory");
        modelAndView.addObject("paymentList", smsPaymentService.getAllPayments());
        return modelAndView;
    }

}
