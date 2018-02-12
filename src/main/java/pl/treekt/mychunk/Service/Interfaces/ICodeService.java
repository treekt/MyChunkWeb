package pl.treekt.mychunk.Service.Interfaces;

import org.springframework.stereotype.Service;
import pl.treekt.mychunk.Entity.Web.Code;

import java.util.List;

public interface ICodeService {
    List<Code> getAllCodes();
    Code getByCode(String code);
    Boolean addCode(Code code);
    void updateCode(Code code);
    void deleteCode(String code);
}
