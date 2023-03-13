package com.demo_collab.service.Role;

import com.demo_collab.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.demo_collab.connect.Connect.getConnection;

public class RoleService implements IRole {
    private static  final  String SELECT_ALL_ROLES = "select * from role";
    private static final  String INSERT = "insert into role (role_id, role_name) values (?,?)";
    private static final String SELECT_ROLE_BY_ID = "select role_id, role_name from role where id = ?";
    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SELECT_ALL_ROLES);
            rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("role_id");
                String name = rs.getString("role_name");
                roles.add(new Role(id, name));
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return roles;
    }

    @Override
    public void save(Role customer) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT);
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getName());
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } finally {
                try {
                    if (statement != null) statement.close();
                    connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }

    }

    @Override
    public Role findById(int id) {
        return null;
    }

    @Override
    public void update(int id, Role customer) {

    }

    @Override
    public void remove(int id) {

    }
}
