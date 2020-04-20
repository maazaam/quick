package com.maaza.quick.user;

import static org.junit.Assert.assertEquals;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maaza.quick.Util;

public class UserHandlerTest {

    @Before
    public void setup() throws Exception {
        Util.start();
        Util.open();
        HttpGet r1 = new HttpGet("http://localhost:8080/quick/base/setup");
        HttpGet r2 = new HttpGet("http://localhost:8080/quick/base/sample");
        try (CloseableHttpClient c = HttpClients.createDefault()) {
            try (CloseableHttpResponse s = c.execute(r1)) {
            }
            try (CloseableHttpResponse s = c.execute(r2)) {
            }
        }
    }

    @Test
    public void testItemHandlerSuccess() throws Exception {
        HttpGet r1 = new HttpGet("http://localhost:8080/quick/user/list");
        HttpGet r2 = new HttpGet("http://localhost:8080/quick/user/find/1");
        HttpGet r3 = new HttpGet("http://localhost:8080/quick/user/drop/1");
        HttpPost r4 = new HttpPost("http://localhost:8080/quick/user/save");
        r4.setHeader("Content-Type", "application/json");
        r4.setEntity(new StringEntity("{\"name\":\"bbbbb\",\"status\":true}"));
        HttpPost r5 = new HttpPost("http://localhost:8080/quick/user/save");
        r5.setHeader("Content-Type", "application/json");
        r5.setEntity(new StringEntity("{\"id\":1,\"name\":\"bbbbb\",\"status\":false}"));
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
            try (CloseableHttpResponse s = c.execute(r4)) {
                assertEquals(200, s.getStatusLine().getStatusCode());
            }
            try (CloseableHttpResponse s = c.execute(r5)) {
                assertEquals(200, s.getStatusLine().getStatusCode());
            }
        }
    }

    @Test
    public void testItemHandlerFailure() throws Exception {
        Util.close();
        HttpGet r1 = new HttpGet("http://localhost:8080/quick/user/list");
        HttpGet r2 = new HttpGet("http://localhost:8080/quick/user/find/1");
        HttpGet r3 = new HttpGet("http://localhost:8080/quick/user/drop/1");
        HttpPost r4 = new HttpPost("http://localhost:8080/quick/user/save");
        r4.setHeader("Content-Type", "application/json");
        r4.setEntity(new StringEntity("{\"name\":\"bbbbb\",\"status\":true}"));
        HttpPost r5 = new HttpPost("http://localhost:8080/quick/user/save");
        r5.setHeader("Content-Type", "application/json");
        r5.setEntity(new StringEntity("{\"id\":1,\"name\":\"bbbbb\",\"status\":false}"));
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
            try (CloseableHttpResponse s = c.execute(r4)) {
                assertEquals(500, s.getStatusLine().getStatusCode());
            }
            try (CloseableHttpResponse s = c.execute(r5)) {
                assertEquals(500, s.getStatusLine().getStatusCode());
            }
        }
        Util.open();
    }

    @After
    public void teardown() throws Exception {
        HttpGet r3 = new HttpGet("http://localhost:8080/quick/base/teardown");
        try (CloseableHttpClient c = HttpClients.createDefault()) {
            try (CloseableHttpResponse s = c.execute(r3)) {
            }
        }
        Util.close();
        Util.stop();
    }
}
