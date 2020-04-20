package com.maaza.quick.base;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maaza.quick.Util;

public class BaseManagerTest {

    private BaseManager baseMgr = new BaseManager();

    @Before
    public void setup() {
        Util.open();
    }

    @Test
    public void testBaseManager() throws SQLException {
        this.baseMgr.create();
        this.baseMgr.insert();
        this.baseMgr.drop();
    }

    @After
    public void teardown() {
        Util.close();
    }
}
