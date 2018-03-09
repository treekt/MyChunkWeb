package pl.treekt.mychunk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.Dao.Interfaces.IPositionDao;
import pl.treekt.mychunk.Entity.Game.Player;
import pl.treekt.mychunk.Entity.Web.Position;
import pl.treekt.mychunk.Entity.Web.SMSPayment;
import pl.treekt.mychunk.Service.Interfaces.IPositionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class PositionService implements IPositionService {

    @Autowired
    private IPositionDao positionDao;

    @Override
    public List<Position> getAllPositions() {
        return positionDao.getAllPositions();
    }

    @Override
    public Position getPositionById(long id) {
        return positionDao.getPositionById(id);
    }

    @Override
    public Boolean addPosition(Position position) {
        if(positionDao.positionExists(position.getId())){
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
    public void deletePosition(long id) {
        positionDao.deletePosition(getPositionById(id));
    }

    @Override
    public List<Player> getLastPurchasers(long id) {
        List<Player> purchasers = new ArrayList<Player>();
        List<SMSPayment> smsPayments = new ArrayList<SMSPayment>(getPositionById(id).getSmsHistories());
        Collections.sort(smsPayments);
        for(SMSPayment smsPayment : smsPayments){
            purchasers.add(smsPayment.getPlayer());
        }

        return purchasers;
    }
}
