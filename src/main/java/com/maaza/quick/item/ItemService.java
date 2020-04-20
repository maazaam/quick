package com.maaza.quick.item;

import java.util.List;

public class ItemService {

    private static final ItemManager MGR = new ItemManager();

    public List<Item> list() throws Exception {
        return MGR.selectAll();
    }

    public Item find(Integer id) throws Exception {
        return MGR.select(id);
    }

    public void drop(Integer id) throws Exception {
        MGR.delete(id);
    }

    public void save(Item item) throws Exception {
        if (item.getId() != null) {
            MGR.update(item);
        } else {
            MGR.insert(item);
        }
    }
}
