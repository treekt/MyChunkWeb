package pl.treekt.mychunk.Service.Interfaces;


import pl.treekt.mychunk.Entity.Web.SMSPayment;

import java.util.List;

public interface ISMSPaymentService {

    List<SMSPayment> getHistory(String email);
    boolean addPayment(SMSPayment smsPayment);
    void deletePayment(SMSPayment smsPayment);
}
