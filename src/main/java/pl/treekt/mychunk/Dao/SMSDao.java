package pl.treekt.mychunk.Dao;

import org.springframework.stereotype.Repository;
import pl.treekt.mychunk.Dao.Interfaces.ISMSDao;
import pl.treekt.mychunk.Entity.Web.SMS;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class SMSDao implements ISMSDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SMS> getAllSMS() {
        String query = "FROM SMS";
        return (List<SMS>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public SMS getByContent(String content) {
        String query = "FROM Code WHERE content = '" + content + "'";
        try{
            return (SMS) entityManager.createQuery(query).getSingleResult();
        }catch(Exception e){
           return null;
        }
    }

    @Override
    public void addSMS(SMS sms) {
        entityManager.persist(sms);
    }

    @Override
    public void updateSMS(SMS sms) {
        entityManager.flush();
    }

    @Override
    public void deleteSMS(SMS sms) {
        entityManager.remove(sms);
    }

    @Override
    public boolean smsExists(String content) {
        return getByContent(content) != null;
    }
}
