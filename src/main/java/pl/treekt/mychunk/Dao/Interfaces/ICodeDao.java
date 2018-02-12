package pl.treekt.mychunk.Dao.Interfaces;

import pl.treekt.mychunk.Entity.Web.Code;

import java.util.List;

public interface ICodeDao {
    List<Code> getAllCodes();
    Code getByCode(String code);
    void addCode(Code code);
    void updateCode(Code code);
    void deleteCode(Code code);
    boolean codeExists(String code);
}
