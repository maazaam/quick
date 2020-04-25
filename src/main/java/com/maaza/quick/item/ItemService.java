package com.maaza.quick.item;

import java.sql.SQLException;
import java.util.List;

public class ItemService {

    private static final ItemManager MGR = new ItemManager();

    public List<Item> list() throws SQLException {
        return MGR.selectAll();
    }

    public Item find(Integer id) throws SQLException {
        return MGR.select(id);
    }

    public void drop(Integer id) throws SQLException {
        MGR.delete(id);
    }

    public void save(Item item) throws SQLException {
        if (item.getId() != null) {
            MGR.update(item);
        } else {
            MGR.insert(item);
        }
    }
}
