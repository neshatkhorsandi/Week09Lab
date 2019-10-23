package services;

import dataaccess.RoleDB;
import models.User;
import java.util.List;
import dataaccess.UserDB;
import java.util.ArrayList;
import models.Role;

public class UserService {
    
    public User get(String email) throws Exception {
        UserDB db = new UserDB();
        User user = db.getUser(email);
        return user;
    }

    public List<User> getAll() throws Exception {
        UserDB db = new UserDB();
        ArrayList<User> users = (ArrayList<User>) db.getAllActive();
        return users;
    }

    public void update(String email, String fname, String lname, String password) throws Exception {
        UserDB db = new UserDB();
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.getRole(2); // TODO: allow roles for users to change
        User user = new User(email, fname, lname, password, role);
        db.update(user);
    }

    public void delete(String email) throws Exception {
        UserDB db = new UserDB();
        User user = get(email);
        user.setActive(false);
        db.update(user);
    }

    public void insert(String email, String fname, String lname, String password) throws Exception {
        UserDB db = new UserDB();
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.getRole(2);  // all new users are regular users
        User user = new User(email, fname, lname, password, role);
        db.insert(user);
    }

}
