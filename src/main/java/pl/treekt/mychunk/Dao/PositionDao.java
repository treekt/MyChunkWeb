package pl.treekt.mychunk.Dao;

import org.springframework.stereotype.Repository;
import pl.treekt.mychunk.Dao.Interfaces.IPositionDao;
import pl.treekt.mychunk.Entity.Web.Position;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PositionDao implements IPositionDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Position> getAllPositions() {
        String query = "FROM Position";
        return (List<Position>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public Position getPositionById(long id) {
        String query = "FROM Position WHERE id = " + id;
        try{
            return (Position) entityManager.createQuery(query).getSingleResult();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public void addPosition(Position position) {
        entityManager.persist(position);
    }

    @Override
    public void updatePosition(Position position) {
        
    }

    @Override
    public void deletePosition(Position position) {
        entityManager.remove(position);
    }

    @Override
    public boolean positionExists(long id) {
        return getPositionById(id) != null;
    }
}
