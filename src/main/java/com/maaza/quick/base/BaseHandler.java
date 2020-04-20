package com.maaza.quick.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.StatusCodes;

public class BaseHandler {

    private static final Logger LOG = LoggerFactory.getLogger(BaseHandler.class);

    private static final BaseService SVC = new BaseService();

    private BaseHandler() {
    }

    public static void setup(HttpServerExchange ex) {
        LOG.debug("setup");
        try {
            SVC.setup();
            ex.setStatusCode(StatusCodes.OK);
        } catch (Exception e) {
            LOG.error("setup error", e);
            ex.setStatusCode(StatusCodes.INTERNAL_SERVER_ERROR);
        }
    }

    public static void sample(HttpServerExchange ex) {
        LOG.debug("sample");
        try {
            SVC.sample();
            ex.setStatusCode(StatusCodes.OK);
        } catch (Exception e) {
            LOG.error("sample error", e);
            ex.setStatusCode(StatusCodes.INTERNAL_SERVER_ERROR);
        }
    }

    public static void teardown(HttpServerExchange ex) {
        LOG.debug("teardown");
        try {
            SVC.teardown();
            ex.setStatusCode(StatusCodes.OK);
        } catch (Exception e) {
            LOG.error("teardown error", e);
            ex.setStatusCode(StatusCodes.INTERNAL_SERVER_ERROR);
        }
    }
}
