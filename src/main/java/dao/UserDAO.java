package dao;

import models.User;

import java.util.List;

public interface UserDAO {
    User findByEmailAndPassword(String email, String password);

    //    List<User> findUsersWithoutRoleGerant();
    User getUserById(int id);

    //    boolean updateUserRole(User user);
    boolean createUser(User newUser);
    int getLastId();
}
