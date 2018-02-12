package pl.treekt.mychunk.Service.Interfaces;

import pl.treekt.mychunk.Entity.Web.Position;

import java.util.List;

public interface IPositionService {
    List<Position> getAllPositions();
    Position getPositionByTitle(String title);
    Boolean addPosition(Position position);
    void updatePosition(Position position);
    void deletePosition(String title);
}
