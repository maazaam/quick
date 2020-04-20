package com.maaza.quick.base;

public class BaseService {

    private static final BaseManager MGR = new BaseManager();

    public void setup() throws Exception {
        MGR.create();
    }

    public void sample() throws Exception {
        MGR.insert();
    }

    public void teardown() throws Exception {
        MGR.drop();
    }
}
