package com.maaza.quick.base;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maaza.quick.TestUtil;
import com.maaza.quick.Util;

public class BaseHandlerTest {

    @Before
    public void setup() throws IOException, SQLException {
        Util.init();
        Util.startWS();
        Util.startDB();
        Util.startCP();
    }

    @Test
    public void testBaseHandlerSuccess() throws IOException {
        assertEquals(200, TestUtil.httpGet("http://localhost:8080/quick/base/setup"));
        assertEquals(200, TestUtil.httpGet("http://localhost:8080/quick/base/sample"));
        assertEquals(200, TestUtil.httpGet("http://localhost:8080/quick/base/teardown"));
    }

    @Test
    public void testBaseHandlerFailure() throws IOException, SQLException {
        Util.stopCP();
        assertEquals(500, TestUtil.httpGet("http://localhost:8080/quick/base/setup"));
        assertEquals(500, TestUtil.httpGet("http://localhost:8080/quick/base/sample"));
        assertEquals(500, TestUtil.httpGet("http://localhost:8080/quick/base/teardown"));
        Util.startCP();
    }

    @After
    public void teardown() throws SQLException {
        Util.stopCP();
        Util.stopDB();
        Util.stopWS();
    }
}
