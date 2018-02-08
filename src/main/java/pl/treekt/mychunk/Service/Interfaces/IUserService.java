package pl.treekt.mychunk.Service.Interfaces;

import pl.treekt.mychunk.Entity.Web.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getUserByEmail(String email);
    Boolean addUser(User user);
    void updateUser(User user);
    void deleteUser(String email);
}
