package com.maaza.quick.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maaza.quick.Util;
import com.maaza.quick.base.BaseManager;

public class ItemManagerTest {

    private BaseManager baseMgr = new BaseManager();
    private ItemManager itemMgr = new ItemManager();

    @Before
    public void setup() throws IOException, SQLException {
        Util.init();
        Util.startDB();
        Util.startCP();
        this.baseMgr.create();
    }

    @Test
    public void testItemManager() throws SQLException {
        Item item = new Item();
        item.setName("aaaaa");
        item.setStatus(true);
        this.itemMgr.insert(item);
        assertTrue(this.itemMgr.select(1).getStatus());
        assertEquals(1, this.itemMgr.selectAll().size());
        item = this.itemMgr.select(1);
        item.setStatus(false);
        this.itemMgr.update(item);
        assertFalse(this.itemMgr.select(1).getStatus());
        assertEquals(1, this.itemMgr.selectAll().size());
        this.itemMgr.delete(1);
        assertNull(this.itemMgr.select(1));
        assertEquals(0, this.itemMgr.selectAll().size());
    }

    @After
    public void teardown() throws SQLException {
        this.baseMgr.drop();
        Util.stopCP();
        Util.stopDB();
    }
}
