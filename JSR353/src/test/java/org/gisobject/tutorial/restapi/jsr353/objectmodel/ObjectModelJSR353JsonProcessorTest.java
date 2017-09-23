package org.gisobject.tutorial.restapi.jsr353.objectmodel;

import org.gisobject.tutorial.restapi.bean.Employee;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.gisobject.tutorial.restapi.jsr353.objectmodel.ObjectModelJSR353JsonProcessor.OBJECT_MODEL_JSON_PROCESSOR;

/**
 * Created by GIS Object on 27/08/2017.
 */
public class ObjectModelJSR353JsonProcessorTest {

    @Test
    public void testObjectToJsonMapping() throws IOException {
        List<Employee> employees;

        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("emp-array.json")) {
            employees = OBJECT_MODEL_JSON_PROCESSOR.readJson(inputStream);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            OBJECT_MODEL_JSON_PROCESSOR.writeJson(employees, outputStream);
            System.out.println(outputStream.toString(StandardCharsets.UTF_8.name()));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

}