package pl.treekt.mychunk.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.treekt.mychunk.Dao.Interfaces.IUserDao;
import pl.treekt.mychunk.Entity.Web.User;

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
        String query = "FROM User AS user ORDER BY user.username";
        return (List<User>) entityManager.createQuery(query).getResultList();
    }

    @Override
    public User getUserByEmail(String email) {
        String query = "FROM User WHERE email = '" + email + "'";
        User user;
        try{
           user = (User)entityManager.createQuery(query).getSingleResult();
        }catch (Exception e){
            user = null;
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setRoles(user.getRoles());

        entityManager.flush();
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public boolean userExists(String email) {
        return getUserByEmail(email) != null;
    }
}
