package pl.treekt.mychunk.Dao.Interfaces;

import pl.treekt.mychunk.Entity.Web.User;

import java.util.List;

public interface IUserDao {
    List<User> getAllUsers();
    User getUserByEmail(String email);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    boolean userExists(String email);
}
