package pl.treekt.mychunk.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController{



    @GetMapping("/error")
    public ModelAndView error(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("error");
        String errorCode = "";
        String errorMsg = "";

        switch (getErrorCode(httpRequest)) {
            case 400: {
                errorCode = "404";
                errorMsg = "Złe zapytanie";
                break;
            }
            case 401: {
                errorCode = "401";
                errorMsg = "Nie masz dostępu";
                break;
            }
            case 404: {
                errorCode = "404";
                errorMsg = "Strona nie istnieje";
                break;
            }
            case 500: {
                errorCode = "500";
                errorMsg = "Wewnętrzny błąd serwera";
                break;
            }
        }
        errorPage.addObject("errorCode", errorCode);
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
