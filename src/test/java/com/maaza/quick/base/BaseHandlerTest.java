package com.maaza.quick.base;

import static org.junit.Assert.assertEquals;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maaza.quick.Util;

public class BaseHandlerTest {

    @Before
    public void setup() {
        Util.start();
        Util.open();
    }

    @Test
    public void testBaseHandlerSuccess() throws Exception {
        HttpGet r1 = new HttpGet("http://localhost:8080/quick/base/setup");
        HttpGet r2 = new HttpGet("http://localhost:8080/quick/base/sample");
        HttpGet r3 = new HttpGet("http://localhost:8080/quick/base/teardown");
        try (CloseableHttpClient c = HttpClients.createDefault()) {
            try (CloseableHttpResponse s = c.execute(r1)) {
                assertEquals(200, s.getStatusLine().getStatusCode());
            }
            try (CloseableHttpResponse s = c.execute(r2)) {
                assertEquals(200, s.getStatusLine().getStatusCode());
            }
            try (CloseableHttpResponse s = c.execute(r3)) {
                assertEquals(200, s.getStatusLine().getStatusCode());
            }
        }
    }

    @Test
    public void testBaseHandlerFailure() throws Exception {
        Util.close();
        HttpGet r1 = new HttpGet("http://localhost:8080/quick/base/setup");
        HttpGet r2 = new HttpGet("http://localhost:8080/quick/base/sample");
        HttpGet r3 = new HttpGet("http://localhost:8080/quick/base/teardown");
        try (CloseableHttpClient c = HttpClients.createDefault()) {
            try (CloseableHttpResponse s = c.execute(r1)) {
                assertEquals(500, s.getStatusLine().getStatusCode());
            }
            try (CloseableHttpResponse s = c.execute(r2)) {
                assertEquals(500, s.getStatusLine().getStatusCode());
            }
            try (CloseableHttpResponse s = c.execute(r3)) {
                assertEquals(500, s.getStatusLine().getStatusCode());
            }
        }
        Util.open();
    }

    @After
    public void teardown() {
        Util.close();
        Util.stop();
    }
}
