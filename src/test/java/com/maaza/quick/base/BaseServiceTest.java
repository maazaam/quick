package com.maaza.quick.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.maaza.quick.Util;

public class BaseServiceTest {

    private BaseService baseSvc = new BaseService();

    @Before
    public void setup() {
        Util.open();
    }

    @Test
    public void testBaseService() throws Exception {
        this.baseSvc.setup();
        this.baseSvc.sample();
        this.baseSvc.teardown();
    }

    @After
    public void teardown() {
        Util.close();
    }
}
