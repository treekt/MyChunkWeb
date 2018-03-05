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
    private ISMSDao codeDao;

    @Override
    public List<SMS> getAllSMS() {
        return codeDao.getAllSMS();
    }

    @Override
    public SMS getByContent(String content) {
        return codeDao.getByContent(content);
    }

    @Override
    public Boolean addSMS(SMS sms) {
        if(codeDao.smsExists(sms.getContent())){
            return false;
        }else{
            codeDao.addSMS(sms);
            return true;
        }
    }

    @Override
    public void updateSMS(SMS sms) {
        codeDao.updateSMS(sms);
    }

    @Override
    public void deleteSMS(String content) {
        codeDao.deleteSMS(getByContent(content));
    }
}
