package org.gisobject.tutorial.restapi.jsr353.objectmodel;

import org.gisobject.tutorial.restapi.bean.Employee;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.gisobject.tutorial.restapi.jsr353.objectmodel.ObjectModelJSR353JsonProcessor.OBJECT_MODEL_JSON_PROCESSOR;

/**
 * Created by GIS Object on 27/08/2017.
 */
public class ObjectModelJSR353JsonProcessorTest {

    @Test
    public void testObjectToJsonMapping() throws IOException {
        try (InputStream inputStream = ObjectModelJSR353JsonProcessor.class.getClassLoader().getResourceAsStream("emp-array.json")) {
            List<Employee> employees = OBJECT_MODEL_JSON_PROCESSOR.readJson(inputStream);
            System.out.println(employees);
            System.out.println(OBJECT_MODEL_JSON_PROCESSOR.toJson(employees));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

}