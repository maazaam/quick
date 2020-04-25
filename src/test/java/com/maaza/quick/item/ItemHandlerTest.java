package com.maaza.quick.item;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maaza.quick.TestUtil;
import com.maaza.quick.Util;

public class ItemHandlerTest {

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
    public void testItemHandlerSuccess() throws IOException {
        assertEquals(200, TestUtil.httpGet("http://localhost:8080/quick/item/list"));
        assertEquals(200, TestUtil.httpGet("http://localhost:8080/quick/item/find/1"));
        assertEquals(200, TestUtil.httpGet("http://localhost:8080/quick/item/drop/1"));
        assertEquals(200, TestUtil.httpPost("http://localhost:8080/quick/item/save",
                "{\"id\":1,\"name\":\"aaaaa\",\"status\":false}"));
        assertEquals(200,
                TestUtil.httpPost("http://localhost:8080/quick/item/save", "{\"name\":\"aaaaa\",\"status\":true}"));
    }

    @Test
    public void testItemHandlerFailure() throws IOException, SQLException {
        Util.stopCP();
        assertEquals(500, TestUtil.httpGet("http://localhost:8080/quick/item/list"));
        assertEquals(500, TestUtil.httpGet("http://localhost:8080/quick/item/find/1"));
        assertEquals(500, TestUtil.httpGet("http://localhost:8080/quick/item/drop/1"));
        assertEquals(500, TestUtil.httpPost("http://localhost:8080/quick/item/save",
                "{\"id\":1,\"name\":\"aaaaa\",\"status\":false}"));
        assertEquals(500,
                TestUtil.httpPost("http://localhost:8080/quick/item/save", "{\"name\":\"aaaaa\",\"status\":true}"));
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
