package com.maaza.quick.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maaza.quick.Util;
import com.maaza.quick.base.BaseManager;

public class UserManagerTest {

    private BaseManager baseMgr = new BaseManager();
    private UserManager userMgr = new UserManager();

    @Before
    public void setup() throws SQLException {
        Util.open();
        this.baseMgr.create();
    }

    @Test
    public void testUserManager() throws SQLException {
        User user = new User();
        user.setName("aaaaa");
        user.setStatus(true);
        this.userMgr.insert(user);
        assertTrue(this.userMgr.select(1).getStatus());
        assertEquals(1, this.userMgr.selectAll().size());
        user = this.userMgr.select(1);
        user.setStatus(false);
        this.userMgr.update(user);
        assertFalse(this.userMgr.select(1).getStatus());
        assertEquals(1, this.userMgr.selectAll().size());
        this.userMgr.delete(1);
        assertNull(this.userMgr.select(1));
        assertEquals(0, this.userMgr.selectAll().size());
    }

    @After
    public void teardown() throws SQLException {
        this.baseMgr.drop();
        Util.close();
    }
}
