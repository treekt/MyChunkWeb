package pl.treekt.mychunk.API.Payments.Service;

import pl.treekt.mychunk.API.Payments.Entity.SmsResponse;

public interface IHomePayService {

    boolean checkSMS(String code);
}
