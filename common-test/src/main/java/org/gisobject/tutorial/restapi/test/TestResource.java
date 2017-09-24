package org.gisobject.tutorial.restapi.test;

import sun.net.TelnetOutputStream;

import java.io.InputStream;

public enum TestResource {

    EMPLOYEE_ARRAY ("emp-array.json"),
    ;

    private String resourceLocation;

    TestResource(String resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public InputStream toInputStream () {
        return this.getClass().getClassLoader().getResourceAsStream(resourceLocation);
    }


}
