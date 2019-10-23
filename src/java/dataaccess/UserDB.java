package dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Role;

public class UserDB {

    public void insert(User user) throws Exception {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        int rows = 0;
        try {
            String preparedQuery
                    = "INSERT INTO User_Table "
                    + "(email, fname, lname, password, role) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(preparedQuery);

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFname());
            ps.setString(3, user.getLname());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole().getRoleID());

            rows = ps.executeUpdate();
            ps.close();

        }  finally {
            connectionPool.freeConnection(connection);
        }
    }

    public void update(User user) throws Exception {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        String UPDATE_STATEMENT = "UPDATE User_Table set active=?, fname=?, lname=?, role=? where email=?";
        int successCount = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_STATEMENT);
            statement.setBoolean(1, user.isActive());
            statement.setString(2, user.getFname());
            statement.setString(3, user.getLname());
            statement.setInt(4, user.getRole().getRoleID());
            statement.setString(5, user.getEmail());
            

            successCount = statement.executeUpdate();
            statement.close();

        } finally {
            connectionPool.freeConnection(connection);
        }
    }

    public List<User> getAll() throws Exception {
        ConnectionPool connectionPool = null;
        Connection connection = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            User user;
            ArrayList<User> users = new ArrayList<>();

            String preparedSQL = "SELECT active, email, fname, lname, role FROM user_table";
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ResultSet product = ps.executeQuery();

            while (product.next()) {
                boolean active = product.getBoolean(1);
                String userEmail = product.getString(2);
                String fname = product.getString(3);
                String lname = product.getString(4);

                int roleID = product.getInt(5);
                RoleDB roleDB = new RoleDB();
                Role role = roleDB.getRole(roleID);

                user = new User(userEmail, fname, lname, null, role);

                user.setActive(active);
                users.add(user);
            }
            return users;
        } finally {
            connectionPool.freeConnection(connection);
        }

    }
    
    public List<User> getAllActive() throws Exception {
        ConnectionPool connectionPool = null;
        Connection connection = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            User user;
            ArrayList<User> users = new ArrayList<>();

            String preparedSQL = "SELECT active, email, fname, lname, role FROM user_table where active=true";
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ResultSet product = ps.executeQuery();

            while (product.next()) {
                boolean active = product.getBoolean(1);
                String userEmail = product.getString(2);
                String fname = product.getString(3);
                String lname = product.getString(4);

                int roleID = product.getInt(5);
                RoleDB roleDB = new RoleDB();
                Role role = roleDB.getRole(roleID);

                user = new User(userEmail, fname, lname, null, role);

                user.setActive(active);
                users.add(user);
            }
            return users;
        } finally {
            connectionPool.freeConnection(connection);
        }
    }

    public User getUser(String email) throws Exception {

        ConnectionPool connectionPool = null;
        Connection connection = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            User user = new User();
            String preparedSQL = "SELECT active, email, fname, lname, password, role FROM user_table WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(preparedSQL);
            ps.setString(1, email);
            ResultSet product = ps.executeQuery();

            while (product.next()) {
                boolean active = product.getBoolean(1);
                String userEmail = product.getString(2);
                String fname = product.getString(3);
                String lname = product.getString(4);
                String password = product.getString(5);
                int roleID = product.getInt(6);
                RoleDB roleDB = new RoleDB();
                Role role = roleDB.getRole(roleID);

                user = new User(userEmail, fname, lname, password, role);
                user.setActive(active);

            }
            return user;
        } finally {
            connectionPool.freeConnection(connection);
        }

    }

    public void delete(User user) throws Exception {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        try {
            String DELETE_STMT = "DELETE FROM User_Table where email = ?";
            PreparedStatement prepare = connection.prepareStatement(DELETE_STMT);
            prepare.setString(1, user.getEmail());

            int rowCount = prepare.executeUpdate();
            prepare.close();

        } finally {
            connectionPool.freeConnection(connection);
        }
    }

}
