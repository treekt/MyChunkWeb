package pl.treekt.mychunk.Dao;

import org.springframework.stereotype.Repository;
import pl.treekt.mychunk.Dao.Interfaces.IVoucherDao;
import pl.treekt.mychunk.Entity.Web.Voucher;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class VoucherDao implements IVoucherDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Voucher> getAllVouchers() {
        String query = "FROM Voucher";
        return (List<Voucher>)entityManager.createQuery(query).getResultList();
    }

    @Override
    public Voucher getVoucherByCode(String code) {
        String query = "FROM Voucher WHERE code = '" + code + "'";
        Voucher voucher;
        try{
            voucher = (Voucher)entityManager.createQuery(query).getSingleResult();
        }catch(Exception e){
            voucher = null;
        }
        return voucher;
    }

    @Override
    public void addVoucher(Voucher voucher) {
        entityManager.persist(voucher);
    }

    @Override
    public void updateVoucher(Voucher voucher) {
        entityManager.flush();
    }

    @Override
    public void deleteVoucher(Voucher voucher) {
        entityManager.remove(voucher);
    }

    @Override
    public boolean voucherExists(String code) {
        return getVoucherByCode(code) != null;
    }
}
