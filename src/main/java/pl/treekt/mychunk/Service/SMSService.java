package pl.treekt.mychunk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.Dao.Interfaces.ISMSDao;
import pl.treekt.mychunk.Entity.Web.SMS;
import pl.treekt.mychunk.Service.Interfaces.ISMSService;

import java.util.List;

@Service
public class SMSService implements ISMSService {

    @Autowired
    private ISMSDao smsDao;

    @Override
    public List<SMS> getAllSMS() {
        return smsDao.getAllSMS();
    }

    @Override
    public SMS getByContent(String content) {
        return smsDao.getByContent(content);
    }

    @Override
    public Boolean addSMS(SMS sms) {
        if(smsDao.smsExists(sms.getContent())){
            return false;
        }else{
            smsDao.addSMS(sms);
            return true;
        }
    }

    @Override
    public void updateSMS(SMS sms) {
        smsDao.updateSMS(sms);
    }

    @Override
    public void deleteSMS(String content) {
        smsDao.deleteSMS(getByContent(content));
    }
}
