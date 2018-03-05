package pl.treekt.mychunk.Dao.Interfaces;

import pl.treekt.mychunk.Entity.Web.SMSPayment;
import pl.treekt.mychunk.Utils.Enums.SkillType;

import java.util.List;

public interface ISMSPaymentDao {
    List<SMSPayment> getAllPayments();
    SMSPayment getPaymentByCode(String code);
    void addPayment(SMSPayment smsPayment);
    void updatePayment(SMSPayment smsPayment);
    void deletePayment(SMSPayment smsPayment);
    boolean paymentExists(String code);
}
