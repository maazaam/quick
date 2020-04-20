package com.maaza.quick.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maaza.quick.Util;
import com.maaza.quick.base.BaseService;

public class ItemServiceTest {

    private BaseService baseSvc = new BaseService();
    private ItemService itemSvc = new ItemService();

    @Before
    public void setup() throws Exception {
        Util.open();
        this.baseSvc.setup();
    }

    @Test
    public void testItemService() throws Exception {
        Item item = new Item();
        item.setName("aaaaa");
        item.setStatus(true);
        this.itemSvc.save(item);
        assertTrue(this.itemSvc.find(1).getStatus());
        assertEquals(1, this.itemSvc.list().size());
        item = this.itemSvc.find(1);
        item.setStatus(false);
        this.itemSvc.save(item);
        assertFalse(this.itemSvc.find(1).getStatus());
        assertEquals(1, this.itemSvc.list().size());
        this.itemSvc.drop(1);
        assertNull(this.itemSvc.find(1));
        assertEquals(0, this.itemSvc.list().size());
    }

    @After
    public void teardown() throws Exception {
        this.baseSvc.teardown();
        Util.close();
    }
}
