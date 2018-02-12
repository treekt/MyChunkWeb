package pl.treekt.mychunk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.Dao.Interfaces.IPositionDao;
import pl.treekt.mychunk.Entity.Web.Position;
import pl.treekt.mychunk.Service.Interfaces.IPositionService;

import java.util.List;

@Service
public class PositionService implements IPositionService {

    @Autowired
    private IPositionDao positionDao;

    @Override
    public List<Position> getAllPositions() {
        return positionDao.getAllPositions();
    }

    @Override
    public Position getPositionByTitle(String title) {
        return positionDao.getPositionByTitle(title);
    }

    @Override
    public Boolean addPosition(Position position) {
        if(positionDao.positionExists(position.getTitle())){
            return false;
        }else{
            positionDao.addPosition(position);
            return true;
        }
    }

    @Override
    public void updatePosition(Position position) {

    }

    @Override
    public void deletePosition(String title) {
        positionDao.deletePosition(getPositionByTitle(title));
    }
}
