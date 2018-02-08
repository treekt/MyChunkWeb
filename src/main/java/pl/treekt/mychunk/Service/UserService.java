package pl.treekt.mychunk.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.treekt.mychunk.Dao.Interfaces.IUserDao;
import pl.treekt.mychunk.Entity.Web.User;
import pl.treekt.mychunk.Service.Interfaces.IUserService;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public Boolean addUser(User user) {
        if(userDao.userExists(user.getEmail())){
            return false;
        }else{
            userDao.addUser(user);
            return true;
        }
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(String email) {
        userDao.deleteUser(getUserByEmail(email));
    }
}
