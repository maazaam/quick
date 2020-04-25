package com.maaza.quick.base;

import java.sql.SQLException;

public class BaseService {

    private static final BaseManager MGR = new BaseManager();

    public void setup() throws SQLException {
        MGR.create();
    }

    public void sample() throws SQLException {
        MGR.insert();
    }

    public void teardown() throws SQLException {
        MGR.drop();
    }
}
