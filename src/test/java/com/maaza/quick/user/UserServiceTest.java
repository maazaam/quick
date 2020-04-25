package com.maaza.quick.user;

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
import com.maaza.quick.base.BaseService;

public class UserServiceTest {

    private BaseService baseSvc = new BaseService();
    private UserService userSvc = new UserService();

    @Before
    public void setup() throws IOException, SQLException {
        Util.init();
        Util.startDB();
        Util.startCP();
        this.baseSvc.setup();
    }

    @Test
    public void testUserService() throws SQLException {
        User user = new User();
        user.setName("aaaaa");
        user.setStatus(true);
        this.userSvc.save(user);
        assertTrue(this.userSvc.find(1).getStatus());
        assertEquals(1, this.userSvc.list().size());
        user = this.userSvc.find(1);
        user.setStatus(false);
        this.userSvc.save(user);
        assertFalse(this.userSvc.find(1).getStatus());
        assertEquals(1, this.userSvc.list().size());
        this.userSvc.drop(1);
        assertNull(this.userSvc.find(1));
        assertEquals(0, this.userSvc.list().size());
    }

    @After
    public void teardown() throws SQLException {
        this.baseSvc.teardown();
        Util.stopCP();
        Util.stopDB();
    }
}
