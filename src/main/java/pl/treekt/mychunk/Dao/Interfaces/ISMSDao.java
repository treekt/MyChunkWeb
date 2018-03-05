package pl.treekt.mychunk.Dao.Interfaces;

import pl.treekt.mychunk.Entity.Web.SMS;

import java.util.List;

public interface ISMSDao {
    List<SMS> getAllSMS();
    SMS getByContent(String content);
    void addSMS(SMS sms);
    void updateSMS(SMS sms);
    void deleteSMS(SMS sms);
    boolean smsExists(String content);
}
