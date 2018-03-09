package pl.treekt.mychunk.Service.Interfaces;

import pl.treekt.mychunk.Entity.Game.Player;
import pl.treekt.mychunk.Entity.Web.Position;

import java.util.List;

public interface IPositionService {
    List<Position> getAllPositions();
    Position getPositionById(long id);
    Boolean addPosition(Position position);
    void updatePosition(Position position);
    void deletePosition(long id);
    List<Player> getLastPurchasers(long id);
}
