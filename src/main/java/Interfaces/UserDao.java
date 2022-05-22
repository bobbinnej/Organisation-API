package Interfaces;

import models.User;

import java.util.List;

public interface UserDao {
    //CRUD

    void addUser(User user);  // from CRUD this is Create

    List<User> getAll();     // from CRUD this is Read
    User findById(int id);

    void updateUser(User user, String name, String staff_position, String role, int department_id);  // from CRUD this is Update
    void clearAll();        // from CRUD this is Delete


}
