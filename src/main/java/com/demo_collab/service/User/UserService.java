package com.demo_collab.service.User;

import com.demo_collab.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.demo_collab.connect.Connect.getConnection;

public class UserService implements IUser {
    public UserService() {
    }

    private static final String SELECT_ALL_USERS = "select * from user";
    private static final String INSERT = "insert into user (user_id, user_name, phone, province_id, role_id) values (?,?,?,?,?)";
    private static final String SELECT_USER_BY_ID = "select user_id, user_name, phone, province_id, role_id from user where id = ?";

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
        ) {
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                String phone = rs.getString("phone");
                int province_id = rs.getInt("province_id");
                int role_id = rs.getInt("role_id");
                users.add(new User(id, name, phone, province_id, role_id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public void save(User user) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)
        ) {
            connection.setAutoCommit(false);
            statement.setInt(1, user.getUser_id());
            statement.setString(2, user.getUser_name());
            statement.setString(3, user.getPhone());
            statement.setInt(4, user.getProvince_id());
            statement.setInt(5, user.getRole_id());
            connection.commit();
            connection.setAutoCommit(true);
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int id) {
        User users = null;
        try (Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement(SELECT_USER_BY_ID);
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                conn.setAutoCommit(false);
                String userName = rs.getString("user_name");
                String phone = rs.getString("phone");
                int province_id = rs.getInt("province_id");
                int role_id = rs.getInt("role_id");
                users = new User(id, userName, phone, province_id, role_id);
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public boolean update(int id, User user) {

        return false;
    }

    @Override
    public boolean remove(int id) {

        return false;
    }
}
