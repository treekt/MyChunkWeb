package pl.treekt.mychunk.Dao.Interfaces;

import pl.treekt.mychunk.Entity.Game.Player;

import java.util.List;

public interface IPlayerDao {
    List<Player> getAllPlayers();
    Player getPlayerById(String nickname);
    void addPlayer(Player user);
    void updatePlayer(Player user);
    void deletePlayer(String nickname);
    boolean playerExists(String nickname);
}
