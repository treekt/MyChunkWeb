package pl.treekt.mychunk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.API.Payments.Service.IHomePayService;
import pl.treekt.mychunk.Entity.Game.Player;
import pl.treekt.mychunk.Entity.Web.Position;
import pl.treekt.mychunk.Entity.Web.SMSPayment;
import pl.treekt.mychunk.Entity.Web.User;
import pl.treekt.mychunk.Entity.Web.Voucher;
import pl.treekt.mychunk.Model.ComplaintModel;
import pl.treekt.mychunk.Model.TransactionModel;
import pl.treekt.mychunk.Service.Interfaces.*;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IPlayerService playerService;

    @Autowired
    private IPositionService positionService;

    @Autowired
    private IHomePayService smsService;

    @Autowired
    private ISMSPaymentService smsPaymentService;

    @Autowired
    private IVoucherService voucherService;


    @GetMapping("/shop")
    public ModelAndView shop() {
        ModelAndView modelAndView = new ModelAndView("shop/positionList");
        List<Position> shopPositions = positionService.getAllPositions();
        modelAndView.addObject("positions", shopPositions);
        return modelAndView;
    }

    @GetMapping("/shop/{id}")
    public ModelAndView positionDetails(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("shop/positionDetails");
        Position position = positionService.getPositionById(id);
        modelAndView.addObject("position", position);
        List<Player> purchasers = positionService.getLastPurchasers(id);
        List<Player> lastPurchasers = purchasers.subList(0, 10 >= purchasers.size() ? purchasers.size() : 10);
        modelAndView.addObject("lastPurchasers", lastPurchasers);
        return modelAndView;
    }

    @GetMapping("/shop/{id}/sms")
    public ModelAndView smsPaymentForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("shop/smsPayment");
        Position position = positionService.getPositionById(id);
        modelAndView.addObject("position", position);
        modelAndView.addObject("transaction", new TransactionModel());
        return modelAndView;
    }

    @PostMapping("/shop/{id}/sms")
    public ModelAndView smsPaymentSubmit(@PathVariable long id, @ModelAttribute TransactionModel transaction) {
        ModelAndView modelAndView = new ModelAndView("shop/smsPayment");
        Position position = positionService.getPositionById(id);
        modelAndView.addObject("position", position);
        modelAndView.addObject("transaction", transaction);

        if (getLoggedUser() == null) {
            modelAndView.addObject("error", "Musisz być zalogowany, aby zakupić pozycje!");
            return modelAndView;
        }

        if (!playerService.existsPlayer(transaction.getNickname())) {
            modelAndView.addObject("error", "Podany gracz nie istnieje na serwerze");
            return modelAndView;
        }
        if (!smsService.checkSMS(transaction.getCode())) {
            modelAndView.addObject("error", "Podany kod sms nie istnieje");
            return modelAndView;
        }


        SMSPayment smsPayment = new SMSPayment(
                transaction.getCode(),
                position,
                getLoggedUser(),
                playerService.getPlayerById(transaction.getNickname())
        );

        if (!smsPaymentService.addPayment(smsPayment)) {
            modelAndView.addObject("error", "Podany kod został już zrealizowany...");
            return modelAndView;
        }



        modelAndView.addObject("transaction", new TransactionModel());
        modelAndView.addObject("success", true);

        return modelAndView;
    }


    @GetMapping("/shop/{id}/transfer")
    public ModelAndView transferPaymentForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("shop/transferPayment");
        Position position = positionService.getPositionById(id);
        modelAndView.addObject("position", position);
        modelAndView.addObject("transaction", new TransactionModel());
        return modelAndView;
    }


    @GetMapping("/voucher")
    public ModelAndView voucherForm() {
        ModelAndView modelAndView = new ModelAndView("shop/voucher");
        modelAndView.addObject("voucher", new TransactionModel());
        return modelAndView;
    }

    @PostMapping("/voucher")
    public ModelAndView voucherSubmit(@ModelAttribute TransactionModel transaction) {
        ModelAndView modelAndView = new ModelAndView("shop/voucher");
        modelAndView.addObject("voucher", transaction);

        if (!playerService.existsPlayer(transaction.getNickname())) {
            modelAndView.addObject("error", "Podany gracz nie istnieje na serwerze");
            return modelAndView;
        }
        if (!voucherService.voucherExists(transaction.getCode())) {
            modelAndView.addObject("error", "Podany voucher nie istnieje");
            return modelAndView;
        }

        if (playerService.isVoucherUsed(transaction.getNickname(), transaction.getCode())) {
            modelAndView.addObject("error", "Podany voucher został juz wykorzystany dla tego gracza");
            return modelAndView;
        } else {
            if (voucherService.canRealizeVoucher(transaction.getCode())) {
                Player player = playerService.getPlayerById(transaction.getNickname());
                Voucher voucher = voucherService.getVoucherByCode(transaction.getCode());

                //Increment using counter of voucher
                voucher.getPlayers().add(player);
                voucherService.updateVoucher(voucher);
            } else {
                modelAndView.addObject("error", "Podany voucher został juz wykorzystany maksymalną ilość razy");
                return modelAndView;
            }
        }

        modelAndView.addObject("voucher", new TransactionModel());
        modelAndView.addObject("success", true);
        return modelAndView;
    }

    @GetMapping("/shop/complaint")
    public ModelAndView complaintForm() {
        ModelAndView modelAndView = new ModelAndView("shop/complaint");
        modelAndView.addObject("positions", positionService.getAllPositions());
        modelAndView.addObject("complaint", new ComplaintModel());
        return modelAndView;
    }

    @PostMapping("/shop/complaint")
    public ModelAndView complaintSubmit() {
        ModelAndView modelAndView = new ModelAndView("shop/complaint");
        modelAndView.addObject("positions", positionService.getAllPositions());

        if (false) {
            modelAndView.addObject("error", "Nie udało się wysłać reklamacji");
        }

        modelAndView.addObject("success", true);
        modelAndView.addObject("complaint", new ComplaintModel());
        return modelAndView;
    }


    //private methods

    private User getLoggedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user;
        try {
            user = userService.getUserByEmail(auth.getName());
        } catch (Exception e) {
            user = null;
        }

        return user;
    }
}

