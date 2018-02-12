package pl.treekt.mychunk.Dao;

import org.springframework.stereotype.Repository;
import pl.treekt.mychunk.Dao.Interfaces.ICodeDao;
import pl.treekt.mychunk.Entity.Web.Code;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class CodeDao implements ICodeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Code> getAllCodes() {
        String query = "FROM Code";
        return (List<Code>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public Code getByCode(String code) {
        String query = "FROM Code WHERE code = '" + code + "'";
        try{
            return (Code) entityManager.createQuery(query).getSingleResult();
        }catch(Exception e){
           return null;
        }
    }

    @Override
    public void addCode(Code code) {
        entityManager.persist(code);
    }

    @Override
    public void updateCode(Code code) {
//        ...
    }

    @Override
    public void deleteCode(Code code) {
        entityManager.remove(code);
    }

    @Override
    public boolean codeExists(String code) {
        return getByCode(code) != null;
    }
}
