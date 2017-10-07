package org.gisobject.tutorial.jaxrs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by GIS Object on 06/10/2017.
 */

@ApplicationPath("webresources")
public class RestAppConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(DepartmentService.class);
        return resources;
    }
}
