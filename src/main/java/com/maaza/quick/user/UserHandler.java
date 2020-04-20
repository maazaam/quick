package com.maaza.quick.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.StatusCodes;

public class UserHandler {

    private static final Logger LOG = LoggerFactory.getLogger(UserHandler.class);

    private static final UserService SVC = new UserService();
    private static final Gson GSN = new Gson();

    private UserHandler() {
    }

    public static void list(HttpServerExchange ex) {
        LOG.debug("list");
        try {
            ex.setStatusCode(StatusCodes.OK);
            ex.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            ex.getResponseSender().send(GSN.toJson(SVC.list()));
        } catch (Exception e) {
            LOG.error("list error", e);
            ex.setStatusCode(StatusCodes.INTERNAL_SERVER_ERROR);
        }
    }

    public static void find(HttpServerExchange ex) {
        LOG.debug("find");
        try {
            Integer id = Integer.parseInt(ex.getQueryParameters().get("id").getFirst());
            ex.setStatusCode(StatusCodes.OK);
            ex.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
            ex.getResponseSender().send(GSN.toJson(SVC.find(id)));
        } catch (Exception e) {
            LOG.error("find error", e);
            ex.setStatusCode(StatusCodes.INTERNAL_SERVER_ERROR);
        }
    }

    public static void drop(HttpServerExchange ex) {
        LOG.debug("drop");
        try {
            Integer id = Integer.parseInt(ex.getQueryParameters().get("id").getFirst());
            SVC.drop(id);
            ex.setStatusCode(StatusCodes.OK);
        } catch (Exception e) {
            LOG.error("drop error", e);
            ex.setStatusCode(StatusCodes.INTERNAL_SERVER_ERROR);
        }
    }

    public static void save(HttpServerExchange ex) {
        LOG.debug("save");
        ex.getRequestReceiver().receiveFullString((e, m) -> {
            try {
                User user = GSN.fromJson(m, User.class);
                SVC.save(user);
                e.setStatusCode(StatusCodes.OK);
            } catch (Exception x) {
                LOG.error("save error", x);
                e.setStatusCode(StatusCodes.INTERNAL_SERVER_ERROR);
            }
        });
    }
}
