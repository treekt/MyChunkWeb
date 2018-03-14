package pl.treekt.mychunk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.Dao.Interfaces.IVoucherDao;
import pl.treekt.mychunk.Entity.Web.Voucher;
import pl.treekt.mychunk.Service.Interfaces.IVoucherService;

import java.util.List;

@Service
public class VoucherService implements IVoucherService {

    @Autowired
    private IVoucherDao voucherDao;

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherDao.getAllVouchers();
    }

    @Override
    public Voucher getVoucherByCode(String code) {
        return voucherDao.getVoucherByCode(code);
    }

    @Override
    public boolean addVoucher(Voucher voucher) {
        if(voucherDao.voucherExists(voucher.getCode())){
            return false;
        }else{
            voucherDao.addVoucher(voucher);
            return true;
        }
    }

    @Override
    public void updateVoucher(Voucher voucher) {
        voucherDao.updateVoucher(voucher);
    }

    @Override
    public void deleteVoucher(Voucher voucher) {
        voucherDao.deleteVoucher(voucher);
    }

    @Override
    public boolean voucherExists(String code) {
        return voucherDao.voucherExists(code);
    }

    @Override
    public boolean canRealizeVoucher(String code) {
        Voucher voucher = getVoucherByCode(code);
        if(voucher.getPlayers().size() < voucher.getMax()){
            return true;
        }
        return false;
    }
}
