package pl.treekt.mychunk.Dao.Interfaces;

import pl.treekt.mychunk.Entity.Web.Voucher;

import java.util.List;

public interface IVoucherDao {
    List<Voucher> getAllVouchers();
    Voucher getVoucherByCode(String code);
    void addVoucher(Voucher voucher);
    void updateVoucher(Voucher voucher);
    void deleteVoucher(Voucher voucher);
    boolean voucherExists(String code);
}
