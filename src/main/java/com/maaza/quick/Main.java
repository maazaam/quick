package com.maaza.quick;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Util.init();
            Util.startWS();
            Util.startDB();
            Util.startCP();
            Runtime.getRuntime().addShutdownHook(new Thread() {

                @Override
                public void run() {
                    try {
                        Util.stopCP();
                        Util.stopDB();
                        Util.stopWS();
                    } catch (Exception e) {
                        LOG.error("error", e);
                    }
                }
            });
        } catch (Exception e) {
            LOG.error("error", e);
        }
    }
}
