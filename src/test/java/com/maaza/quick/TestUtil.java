package com.maaza.quick;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestUtil {

    private TestUtil() {
    }

    public static int httpGet(String url) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");
        return con.getResponseCode();
    }

    public static int httpPost(String url, String data) throws IOException {
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()))) {
            bw.write(data);
        }
        return con.getResponseCode();
    }
}
