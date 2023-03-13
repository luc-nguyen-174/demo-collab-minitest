package com.demo_collab.service.Province;

import com.demo_collab.connect.Connect;
import com.demo_collab.model.Province;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvinceService implements IProvince {
    private static final Connect connect = new Connect();
    private static final String GET_PROVINCE_BY_ID;
    private static final String INSERT_PROVINCE;
    private static final String EDIT_PROVINCE;
    private static final String SELECT_ALL_PROVINCES;
    private static final String DELETE_PROVINCE;
    static {
        INSERT_PROVINCE = "insert into province(name) values (?)";
        GET_PROVINCE_BY_ID = "select * from province where id = ?";
        SELECT_ALL_PROVINCES = "select * from province";
        DELETE_PROVINCE = "delete from province where id = ?";
        EDIT_PROVINCE = "update province set name = ? where id = ?";
    }

    @Override
    public List<Province> findAll() {
        List<Province> provinces = new ArrayList<>();
        try (Connection con = connect.getConnection();
                PreparedStatement pre = con.prepareStatement(SELECT_ALL_PROVINCES)) {
            System.out.println(pre);
            ResultSet res = pre.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                provinces.add(new Province(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provinces;
    }

    @Override
    public void save(Province customer) {
        System.out.println(INSERT_PROVINCE);
        try (Connection con = connect.getConnection();
            PreparedStatement pre = con.prepareStatement(INSERT_PROVINCE)) {
            pre.setString(1, customer.getName());
            System.out.println(pre);
            pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Province findById(int id) {
        Province province = null;
        try (Connection con = connect.getConnection();
             PreparedStatement pre = con.prepareStatement(GET_PROVINCE_BY_ID)) {
            System.out.println(pre);
            pre.setInt(1, id);
            ResultSet res = pre.executeQuery();
            if (res.next()) {
                String name = res.getString("name");
                province = new Province(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return province;
    }

    @Override
    public void update(int id, Province customer) {
        try (Connection con = connect.getConnection();
            PreparedStatement pre = con.prepareStatement(EDIT_PROVINCE)) {
            pre.setString(1, customer.getName());
            pre.setInt(2, customer.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try (Connection con = connect.getConnection();
             PreparedStatement pre = con.prepareStatement(DELETE_PROVINCE)) {
            pre.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
