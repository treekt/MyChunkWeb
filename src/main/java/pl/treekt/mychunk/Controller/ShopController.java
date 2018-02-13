package pl.treekt.mychunk.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.treekt.mychunk.Entity.Web.Position;
import pl.treekt.mychunk.Service.Interfaces.IPositionService;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private IPositionService positionService;

    @GetMapping("/shop")
    public ModelAndView shop(){
        ModelAndView modelAndView = new ModelAndView("shop/positionList");
        List<Position> shopPositions = positionService.getAllPositions();
        modelAndView.addObject("positions", shopPositions);
        return modelAndView;
    }
}
