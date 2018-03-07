package pl.treekt.mychunk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.Dao.Interfaces.IPlayerDao;
import pl.treekt.mychunk.Entity.Game.Player;
import pl.treekt.mychunk.Entity.Web.Voucher;
import pl.treekt.mychunk.Service.Interfaces.IPlayerService;

import java.util.Collections;
import java.util.List;

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private IPlayerDao playerDao;

    @Override
    public List<Player> getAllPlayers() {
        List<Player> users = playerDao.getAllPlayers();
        Collections.sort(users);
        return users;
    }

    @Override
    public Player getPlayerById(String nickname) {
        return playerDao.getPlayerById(nickname);
    }

    @Override
    public boolean addPlayer(Player player) {
        if(playerDao.playerExists(player.getNickname())){
            return false;
        }else {
            playerDao.addPlayer(player);
            return true;
        }
    }

    @Override
    public void updatePlayer(Player player) {
        playerDao.updatePlayer(player);
    }

    @Override
    public void deletePlayer(String nickname) {
        playerDao.deletePlayer(nickname);
    }

    @Override
    public boolean existsPlayer(String nickname) {
        return playerDao.playerExists(nickname);
    }

    @Override
    public boolean isVoucherUsed(String nickname, String code) {
        for(Voucher voucher : getPlayerById(nickname).getVouchers()){
            if(voucher.getCode().equals(code)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int countPlayers() {
        return getAllPlayers().size();
    }

    @Override
    public long countShots() {
        long shots = 0;
        for(Player player : getAllPlayers()){
            shots += player.getSumOfShots();
        }
        return shots;
    }
}
