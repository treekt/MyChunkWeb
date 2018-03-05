package pl.treekt.mychunk.API.Payments.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.API.Payments.Entity.SmsResponse;
import pl.treekt.mychunk.API.Payments.HomePayManager;

@Service
public class HomePayService implements IHomePayService {

    @Autowired
    private HomePayManager homePayManager;

    @Override
    public boolean checkSMS(String code) {
        SmsResponse smsResponse = homePayManager.checkSMS(code);
        if(smsResponse.getCode() == 1) return true;
        return false;
    }
}
