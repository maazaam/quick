package com.maaza.quick.user;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maaza.quick.TestUtil;
import com.maaza.quick.Util;

public class UserHandlerTest {

    @Before
    public void setup() throws IOException, SQLException {
        Util.init();
        Util.startWS();
        Util.startDB();
        Util.startCP();
        TestUtil.httpGet("http://localhost:8080/quick/base/setup");
        TestUtil.httpGet("http://localhost:8080/quick/base/sample");
    }

    @Test
    public void testUserHandlerSuccess() throws IOException {
        assertEquals(200, TestUtil.httpGet("http://localhost:8080/quick/user/list"));
        assertEquals(200, TestUtil.httpGet("http://localhost:8080/quick/user/find/1"));
        assertEquals(200, TestUtil.httpGet("http://localhost:8080/quick/user/drop/1"));
        assertEquals(200, TestUtil.httpPost("http://localhost:8080/quick/user/save",
                "{\"id\":1,\"name\":\"bbbbb\",\"status\":false}"));
        assertEquals(200,
                TestUtil.httpPost("http://localhost:8080/quick/user/save", "{\"name\":\"bbbbb\",\"status\":true}"));
    }

    @Test
    public void testUserHandlerFailure() throws IOException, SQLException {
        Util.stopCP();
        assertEquals(500, TestUtil.httpGet("http://localhost:8080/quick/user/list"));
        assertEquals(500, TestUtil.httpGet("http://localhost:8080/quick/user/find/1"));
        assertEquals(500, TestUtil.httpGet("http://localhost:8080/quick/user/drop/1"));
        assertEquals(500, TestUtil.httpPost("http://localhost:8080/quick/user/save",
                "{\"id\":1,\"name\":\"bbbbb\",\"status\":false}"));
        assertEquals(500,
                TestUtil.httpPost("http://localhost:8080/quick/user/save", "{\"name\":\"bbbbb\",\"status\":true}"));
        Util.startCP();
    }

    @After
    public void teardown() throws IOException, SQLException {
        TestUtil.httpGet("http://localhost:8080/quick/base/teardown");
        Util.stopCP();
        Util.stopDB();
        Util.stopWS();
    }
}
