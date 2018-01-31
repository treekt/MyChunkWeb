package pl.treekt.mychunk.Service.Interfaces;

import pl.treekt.mychunk.Entity.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(String nickname);
    boolean addUser(User user);
    void updateUser(User user);
    void deleteUser(String nickname);
}
