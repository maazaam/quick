package com.maaza.quick;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.maaza.quick.base.BaseHandler;
import com.maaza.quick.item.ItemHandler;
import com.maaza.quick.user.UserHandler;
import com.zaxxer.hikari.HikariDataSource;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;

public class Util {

    private static final String PROPS = "quick.properties";

    private static final String WS_HOST = "ws.host";
    private static final String WS_PORT = "ws.port";
    private static final String WS_NAME = "ws.name";
    private static final String WS_PATH = "ws.path";

    private static final String DB_PATH = "db.path";
    private static final String DB_USER = "db.user";
    private static final String DB_PASS = "db.pass";
    private static final String DB_POOL = "db.pool";

    private static Properties ps;
    private static Undertow ws;
    private static HikariDataSource cp;

    private Util() {
    }

    public static void init() throws IOException {
        ps = new Properties();
        try (InputStream is = ClassLoader.getSystemResourceAsStream(PROPS)) {
            ps.load(is);
        }
    }

    public static void startWS() {
        System.setProperty("org.jboss.logging.provider", "slf4j");
        ws = Undertow.builder().addHttpListener(Integer.parseInt(ps.getProperty(WS_PORT)), ps.getProperty(WS_HOST))
                .setHandler(Handlers.path().addPrefixPath(ps.getProperty(WS_NAME),
                        Handlers.path().addPrefixPath(ps.getProperty(WS_PATH),
                                Handlers.resource(new ClassPathResourceManager(ClassLoader.getSystemClassLoader(),
                                        ps.getProperty(WS_PATH))))
                                .addPrefixPath("base", Handlers.routing().get("setup", BaseHandler::setup)
                                        .get("sample", BaseHandler::sample).get("teardown", BaseHandler::teardown))
                                .addPrefixPath("item",
                                        Handlers.routing().get("list", ItemHandler::list)
                                                .get("find/{id}", ItemHandler::find).get("drop/{id}", ItemHandler::drop)
                                                .post("save", ItemHandler::save))
                                .addPrefixPath("user",
                                        Handlers.routing().get("list", UserHandler::list)
                                                .get("find/{id}", UserHandler::find).get("drop/{id}", UserHandler::drop)
                                                .post("save", UserHandler::save))))
                .build();
        ws.start();
    }

    public static void stopWS() {
        ws.stop();
    }

    public static void startDB() throws SQLException {
        try (Connection con = DriverManager.getConnection(ps.getProperty(DB_PATH), ps.getProperty(DB_USER),
                ps.getProperty(DB_PASS)); PreparedStatement ps = con.prepareStatement("drop table t if exists")) {
            ps.executeUpdate();
        }
    }

    public static void stopDB() throws SQLException {
        try (Connection con = DriverManager.getConnection(ps.getProperty(DB_PATH), ps.getProperty(DB_USER),
                ps.getProperty(DB_PASS)); PreparedStatement ps = con.prepareStatement("shutdown")) {
            ps.executeUpdate();
        }
    }

    public static void startCP() throws SQLException {
        cp = new HikariDataSource();
        cp.setJdbcUrl(ps.getProperty(DB_PATH));
        cp.setUsername(ps.getProperty(DB_USER));
        cp.setPassword(ps.getProperty(DB_PASS));
        cp.setMaximumPoolSize(Integer.parseInt(ps.getProperty(DB_POOL)));
        try (Connection con = cp.getConnection();
                PreparedStatement ps = con.prepareStatement("drop table t if exists")) {
            ps.executeUpdate();
        }
    }

    public static void stopCP() {
        cp.close();
    }

    public static Connection getCon() throws SQLException {
        return cp.getConnection();
    }
}
