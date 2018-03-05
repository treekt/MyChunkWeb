package pl.treekt.mychunk.Service.Interfaces;

import pl.treekt.mychunk.Entity.Web.SMS;

import java.util.List;

public interface ISMSService {
    List<SMS> getAllSMS();
    SMS getByContent(String code);
    Boolean addSMS(SMS sms);
    void updateSMS(SMS sms);
    void deleteSMS(String code);
}
