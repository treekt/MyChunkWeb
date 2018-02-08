package pl.treekt.mychunk.Dao;

import pl.treekt.mychunk.Entity.Game.Player;
import org.springframework.stereotype.Repository;
import pl.treekt.mychunk.Dao.Interfaces.IPlayerDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class PlayerDao implements IPlayerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Player> getAllPlayers() {
        String query = "FROM Player AS player ORDER BY player.nickname";
        return (List<Player>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public Player getPlayerById(String nickname) {
        return entityManager.find(Player.class, nickname);
    }


    @Override
    public void addPlayer(Player player) {
        entityManager.persist(player);
    }

    @Override
    public void updatePlayer(Player player) {
        Player newUser =  getPlayerById(player.getNickname());
        newUser.setKills(player.getKills());
        newUser.setDeaths(player.getDeaths());
        newUser.setAssists(player.getAssists());
        newUser.setTokens(player.getTokens());
        newUser.setLastOnline(player.getLastOnline());

        entityManager.flush();
    }

    @Override
    public void deletePlayer(String nickname) {
        entityManager.remove(getPlayerById(nickname));
    }

    @Override
    public boolean playerExists(String nickname) {
        return getPlayerById(nickname) != null ? true : false;
    }



}
