package pl.treekt.mychunk.Dao;

import pl.treekt.mychunk.Entity.User;
import org.springframework.stereotype.Repository;
import pl.treekt.mychunk.Dao.Interfaces.IUserDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserDao implements IUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        String query = "FROM User as user ORDER BY user.nickname";
        return (List<User>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public User getUserById(String nickname) {
        return entityManager.find(User.class, nickname);
    }


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        User newUser =  getUserById(user.getNickname());
        newUser.setKills(user.getKills());
        newUser.setDeaths(user.getDeaths());
        newUser.setAssists(user.getAssists());
        newUser.setTokens(user.getTokens());
        newUser.setLastOnline(user.getLastOnline());

        entityManager.flush();
    }

    @Override
    public void deleteUser(String nickname) {
        entityManager.remove(getUserById(nickname));
    }

    @Override
    public boolean userExists(String nickname) {
        return getUserById(nickname) != null ? true : false;
    }


}
