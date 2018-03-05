package pl.treekt.mychunk.API.Payments.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.API.Payments.Entity.SmsResponse;
import pl.treekt.mychunk.API.Payments.SMSPaymentManager;

@Service
public class SMSPaymentService implements ISMSPaymentService {

    @Autowired
    private SMSPaymentManager smsPaymentManager;

    @Override
    public boolean checkSMS(String code) {
        SmsResponse smsResponse = smsPaymentManager.checkSMS(code);
        if(smsResponse.getCode() == 1) return true;
        return false;
    }
}
