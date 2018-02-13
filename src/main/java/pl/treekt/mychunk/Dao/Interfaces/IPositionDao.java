package pl.treekt.mychunk.Dao.Interfaces;

import pl.treekt.mychunk.Entity.Web.Position;

import java.util.List;

public interface IPositionDao {
    List<Position> getAllPositions();
    Position getPositionById(long id);
    void addPosition(Position position);
    void updatePosition(Position position);
    void deletePosition(Position position);
    boolean positionExists(long id);
}
