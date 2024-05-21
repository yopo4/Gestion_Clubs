package dao;

import models.User;

public interface UserDAO {
    User findByEmailAndPassword(String email, String password);
}
