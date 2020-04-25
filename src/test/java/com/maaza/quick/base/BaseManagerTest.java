package com.maaza.quick.base;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maaza.quick.Util;

public class BaseManagerTest {

    private BaseManager baseMgr = new BaseManager();

    @Before
    public void setup() throws IOException, SQLException {
        Util.init();
        Util.startDB();
        Util.startCP();
    }

    @Test
    public void testBaseManager() throws SQLException {
        this.baseMgr.create();
        this.baseMgr.insert();
        this.baseMgr.drop();
    }

    @After
    public void teardown() throws SQLException {
        Util.stopCP();
        Util.stopDB();
    }
}
