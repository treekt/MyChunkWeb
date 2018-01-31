package pl.treekt.mychunk.Dao.Interfaces;

import pl.treekt.mychunk.Entity.User;

import java.util.List;

public interface IUserDao {
    List<User> getAllUsers();
    User getUserById(String nickname);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(String nickname);
    boolean userExists(String nickname);
}
