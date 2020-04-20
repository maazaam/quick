package com.maaza.quick;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.maaza.quick.base.BaseHandler;
import com.maaza.quick.item.ItemHandler;
import com.maaza.quick.user.UserHandler;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;

public class Util {

    private static Undertow srv;
    private static HikariDataSource src;

    private Util() {
    }

    public static void start() {
        System.setProperty("org.jboss.logging.provider", "slf4j");
        srv = Undertow.builder().addHttpListener(8080, "localhost")
                .setHandler(Handlers.path().addPrefixPath("/quick", Handlers.path()
                        .addPrefixPath("/page",
                                Handlers.resource(
                                        new ClassPathResourceManager(ClassLoader.getSystemClassLoader(), "page")))
                        .addPrefixPath("/base",
                                Handlers.routing().get("/setup", BaseHandler::setup).get("/sample", BaseHandler::sample)
                                        .get("/teardown", BaseHandler::teardown))
                        .addPrefixPath("/item",
                                Handlers.routing().get("/list", ItemHandler::list).get("/find/{id}", ItemHandler::find)
                                        .get("/drop/{id}", ItemHandler::drop).post("/save", ItemHandler::save))
                        .addPrefixPath("/user",
                                Handlers.routing().get("/list", UserHandler::list).get("/find/{id}", UserHandler::find)
                                        .get("/drop/{id}", UserHandler::drop).post("/save", UserHandler::save))))
                .build();
        srv.start();
    }

    public static void stop() {
        srv.stop();
    }

    public static void open() {
        Properties p = new Properties();
        try (InputStream s = ClassLoader.getSystemResourceAsStream("hikaricp.properties")) {
            p.load(s);
        } catch (IOException e) {
        }
        src = new HikariDataSource(new HikariConfig(p));
    }

    public static void close() {
        src.close();
    }

    public static Connection get() throws SQLException {
        return src.getConnection();
    }
}
