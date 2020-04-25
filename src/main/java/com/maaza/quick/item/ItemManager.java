package com.maaza.quick.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.maaza.quick.Util;

public class ItemManager {

    private static final String INSERT = "insert into item (id, name, status) values (default, ?, ?)";
    private static final String UPDATE = "update item set name = ?, status = ? where id = ?";
    private static final String DELETE = "delete from item where id = ?";
    private static final String SELECT = "select id, name, status from item where id = ?";
    private static final String SELECT_ALL = "select id, name, status from item";

    public void insert(Item item) throws SQLException {
        try (Connection con = Util.getCon(); PreparedStatement ps = con.prepareStatement(INSERT)) {
            ps.setString(1, item.getName());
            ps.setBoolean(2, item.getStatus());
            ps.executeUpdate();
        }
    }

    public void update(Item item) throws SQLException {
        try (Connection con = Util.getCon(); PreparedStatement ps = con.prepareStatement(UPDATE)) {
            ps.setString(1, item.getName());
            ps.setBoolean(2, item.getStatus());
            ps.setInt(3, item.getId());
            ps.executeUpdate();
        }
    }

    public void delete(Integer id) throws SQLException {
        try (Connection con = Util.getCon(); PreparedStatement ps = con.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Item select(Integer id) throws SQLException {
        Item item = null;
        try (Connection con = Util.getCon(); PreparedStatement ps = con.prepareStatement(SELECT)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    item = new Item();
                    item.setId(rs.getInt(1));
                    item.setName(rs.getString(2));
                    item.setStatus(rs.getBoolean(3));
                }
            }
        }
        return item;
    }

    public List<Item> selectAll() throws SQLException {
        List<Item> list = new ArrayList<>();
        try (Connection con = Util.getCon(); PreparedStatement ps = con.prepareStatement(SELECT_ALL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Item item = new Item();
                    item.setId(rs.getInt(1));
                    item.setName(rs.getString(2));
                    item.setStatus(rs.getBoolean(3));
                    list.add(item);
                }
            }
        }
        return list;
    }
}
