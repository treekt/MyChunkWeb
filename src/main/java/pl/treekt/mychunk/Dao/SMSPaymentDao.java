package pl.treekt.mychunk.Dao;

import org.springframework.stereotype.Repository;
import pl.treekt.mychunk.Dao.Interfaces.ISMSPaymentDao;
import pl.treekt.mychunk.Entity.Web.SMSPayment;
import pl.treekt.mychunk.Entity.Web.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class SMSPaymentDao implements ISMSPaymentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SMSPayment> getAllPayments() {
        String query = "FROM SMSPayment";
        return (List<SMSPayment>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public SMSPayment getPaymentByCode(String code) {
        //TODO: Obejść rollback - wykonuje się gdy kod nie istnieje
        String query = "FROM SMSPayment WHERE code = '" + code + "'";
        SMSPayment smsPayment;
        try{
            smsPayment = (SMSPayment)entityManager.createQuery(query).getSingleResult();
        }catch (Exception e){
            smsPayment = null;
        }
        return smsPayment;
    }

    @Override
    public void addPayment(SMSPayment smsPayment) {
        entityManager.persist(smsPayment);
    }

    @Override
    public void updatePayment(SMSPayment smsPayment) {

    }

    @Override
    public void deletePayment(SMSPayment smsPayment) {
        entityManager.remove(smsPayment);
    }

    @Override
    public boolean paymentExists(String code) {
        return getPaymentByCode(code) != null ? true : false;
    }
}
