package pl.treekt.mychunk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.Dao.Interfaces.ISMSPaymentDao;
import pl.treekt.mychunk.Entity.Web.SMSPayment;
import pl.treekt.mychunk.Service.Interfaces.ISMSPaymentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SMSPaymentService implements ISMSPaymentService{

    @Autowired
    private ISMSPaymentDao smsPaymentDao;

    @Override
    public List<SMSPayment> getHistory(String email) {
        List<SMSPayment> allPayments = smsPaymentDao.getAllPayments();
        List<SMSPayment> history = new ArrayList<SMSPayment>();

        for(SMSPayment payment : allPayments){
            if(payment.getUser().getEmail().equals(email)){
                history.add(payment);
            }
        }

        return history;
    }

    @Override
    public boolean addPayment(SMSPayment smsPayment) {
        if(smsPaymentDao.paymentExists(smsPayment.getCode())){
            return false;
        }else{
            smsPaymentDao.addPayment(smsPayment);
            return true;
        }
    }

    @Override
    public void deletePayment(SMSPayment smsPayment) {
        smsPaymentDao.deletePayment(smsPayment);
    }



}
