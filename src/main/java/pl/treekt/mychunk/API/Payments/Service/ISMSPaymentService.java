package pl.treekt.mychunk.API.Payments.Service;

import pl.treekt.mychunk.API.Payments.Entity.SmsResponse;

public interface ISMSPaymentService {

    boolean checkSMS(String code);
}
