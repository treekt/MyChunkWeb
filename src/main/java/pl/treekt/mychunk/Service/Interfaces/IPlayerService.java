package pl.treekt.mychunk.Service.Interfaces;

import pl.treekt.mychunk.Entity.Game.Player;

import java.util.List;

public interface IPlayerService {
    List<Player> getAllPlayers();
    Player getPlayerById(String nickname);
    boolean addPlayer(Player player);
    void updatePlayer(Player player);
    void deletePlayer(String nickname);
}
