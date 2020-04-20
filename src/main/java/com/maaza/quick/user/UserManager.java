package com.maaza.quick.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.maaza.quick.Util;

public class UserManager {

    private static final String INSERT = "insert into user (id, name, status) values (default, ?, ?)";
    private static final String UPDATE = "update user set name = ?, status = ? where id = ?";
    private static final String DELETE = "delete from user where id = ?";
    private static final String SELECT = "select id, name, status from user where id = ?";
    private static final String SELECT_ALL = "select id, name, status from user";

    public void insert(User user) throws SQLException {
        try (Connection con = Util.get(); PreparedStatement ps = con.prepareStatement(INSERT)) {
            ps.setString(1, user.getName());
            ps.setBoolean(2, user.getStatus());
            ps.executeUpdate();
        }
    }

    public void update(User user) throws SQLException {
        try (Connection con = Util.get(); PreparedStatement ps = con.prepareStatement(UPDATE)) {
            ps.setString(1, user.getName());
            ps.setBoolean(2, user.getStatus());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
        }
    }

    public void delete(Integer id) throws SQLException {
        try (Connection con = Util.get(); PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public User select(Integer id) throws SQLException {
        User user = null;
        try (Connection con = Util.get(); PreparedStatement ps = con.prepareStatement(SELECT)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt(1));
                    user.setName(rs.getString(2));
                    user.setStatus(rs.getBoolean(3));
                }
            }
        }
        return user;
    }

    public List<User> selectAll() throws SQLException {
        List<User> list = new ArrayList<>();
        try (Connection con = Util.get(); PreparedStatement ps = con.prepareStatement(SELECT_ALL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt(1));
                    user.setName(rs.getString(2));
                    user.setStatus(rs.getBoolean(3));
                    list.add(user);
                }
            }
        }
        return list;
    }
}
