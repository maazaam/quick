package com.maaza.quick;

public class App {

    private App() {
    }

    public static void main(String[] args) {
        Util.start();
        Util.open();
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                Util.close();
                Util.stop();
            }
        });
    }
}
