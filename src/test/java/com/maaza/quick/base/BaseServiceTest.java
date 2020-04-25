package com.maaza.quick.base;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maaza.quick.Util;

public class BaseServiceTest {

    private BaseService baseSvc = new BaseService();

    @Before
    public void setup() throws IOException, SQLException {
        Util.init();
        Util.startDB();
        Util.startCP();
    }

    @Test
    public void testBaseService() throws SQLException {
        this.baseSvc.setup();
        this.baseSvc.sample();
        this.baseSvc.teardown();
    }

    @After
    public void teardown() throws SQLException {
        Util.stopCP();
        Util.stopDB();
    }
}
