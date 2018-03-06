package pl.treekt.mychunk.Service.Interfaces;

import pl.treekt.mychunk.Entity.Web.Voucher;

import java.util.List;

public interface IVoucherService {
    List<Voucher> getAllVouchers();
    Voucher getVoucherByCode(String code);
    boolean addVoucher(Voucher voucher);
    void updateVoucher(Voucher voucher);
    void deleteVoucher(Voucher voucher);
    boolean voucherExists(String code);
    boolean canRealizeVoucher(String code);
}
