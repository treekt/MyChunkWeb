package pl.treekt.mychunk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.Entity.Web.Position;
import pl.treekt.mychunk.Model.TransactionModel;
import pl.treekt.mychunk.Payments.Model.Transfer;
import pl.treekt.mychunk.Payments.TransferPaymentManager;
import pl.treekt.mychunk.Service.Interfaces.IPositionService;

import java.net.URI;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class ShopController {

    @Autowired
    private IPositionService positionService;

    @Autowired
    private TransferPaymentManager transferPaymentManager;

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
        modelAndView.addObject("transaction", new TransactionModel());
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

    @PostMapping("/shop/{id}/transfer")
    public ResponseEntity<String> transferPaymentSubmit(@PathVariable long id, @ModelAttribute TransactionModel transaction){ ;
        return transferPaymentManager.sendTransfer();
    }
}
