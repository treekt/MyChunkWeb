package pl.treekt.mychunk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.API.Payments.Entity.Transaction;
import pl.treekt.mychunk.API.Payments.Service.ISMSPaymentService;
import pl.treekt.mychunk.API.Payments.Service.SMSPaymentService;
import pl.treekt.mychunk.Entity.Web.Position;
import pl.treekt.mychunk.Model.TransactionModel;
import pl.treekt.mychunk.API.Payments.SMSPaymentManager;
import pl.treekt.mychunk.API.Payments.TransferPaymentManager;
import pl.treekt.mychunk.Service.Interfaces.IPlayerService;
import pl.treekt.mychunk.Service.Interfaces.IPositionService;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private IPlayerService playerService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private ISMSPaymentService smsPaymentService;


    @GetMapping("/shop")
    public ModelAndView shop(){
        ModelAndView modelAndView = new ModelAndView("shop/positionList");
        List<Position> shopPositions = positionService.getAllPositions();
        modelAndView.addObject("positions", shopPositions);
        return modelAndView;
    }

    @GetMapping("/shop/{id}")
    public ModelAndView positionDetails(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("shop/positionDetails");
        Position position = positionService.getPositionById(id);
        modelAndView.addObject("position", position);
        return modelAndView;
    }

    @GetMapping("/shop/{id}/sms")
    public ModelAndView smsPaymentForm(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("shop/smsPayment");
        Position position = positionService.getPositionById(id);
        modelAndView.addObject("position", position);
        modelAndView.addObject("transaction", new TransactionModel());
        return modelAndView;
    }

    @PostMapping("/shop/{id}/sms")
    public ModelAndView smsPaymentSubmit(@PathVariable long id, @ModelAttribute TransactionModel transaction){
        ModelAndView modelAndView = new ModelAndView("shop/smsPayment");
        Position position = positionService.getPositionById(id);
        modelAndView.addObject("position", position);
        modelAndView.addObject("transaction", transaction);

        if(!playerService.existsPlayer(transaction.getNickname())){
            modelAndView.addObject("error", "Podany gracz nie istnieje na serwerze");
            return modelAndView;
        }
        if(!smsPaymentService.checkSMS(transaction.getCode())){
            modelAndView.addObject("error", "Podany kod sms nie istnieje");
            return modelAndView;
        }

        modelAndView.addObject("transaction", new TransactionModel());
        modelAndView.addObject("success", true);

        return modelAndView;
    }


    @GetMapping("/shop/{id}/transfer")
    public ModelAndView transferPaymentForm(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("shop/transferPayment");
        Position position = positionService.getPositionById(id);
        modelAndView.addObject("position", position);
        modelAndView.addObject("transaction", new TransactionModel());
        return modelAndView;
    }

//    @PostMapping("/shop/{id}/transfer")
//    public String transferPaymentSubmit(@PathVariable long id, @ModelAttribute TransactionModel transaction, HttpServletRequest request){
//
//    }
}
