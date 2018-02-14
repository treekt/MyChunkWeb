package pl.treekt.mychunk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.Dao.Interfaces.ICodeDao;
import pl.treekt.mychunk.Entity.Web.Code;
import pl.treekt.mychunk.Service.Interfaces.ICodeService;

import java.util.List;

@Service
public class CodeService implements ICodeService {

    @Autowired
    private ICodeDao codeDao;

    @Override
    public List<Code> getAllCodes() {
        return codeDao.getAllCodes();
    }

    @Override
    public Code getByCode(String code) {
        return codeDao.getByCode(code);
    }

    @Override
    public Boolean addCode(Code code) {
        if(codeDao.codeExists(code.getContent())){
            return false;
        }else{
            codeDao.addCode(code);
            return true;
        }
    }

    @Override
    public void updateCode(Code code) {
        codeDao.updateCode(code);
    }

    @Override
    public void deleteCode(String code) {
        codeDao.deleteCode(getByCode(code));
    }
}
