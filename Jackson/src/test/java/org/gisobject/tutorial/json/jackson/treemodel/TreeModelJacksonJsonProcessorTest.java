package org.gisobject.tutorial.json.jackson.treemodel;

import org.gisobject.tutorial.bean.Employee;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.gisobject.tutorial.test.TestResource.EMPLOYEE_ARRAY;
import static org.junit.Assert.assertEquals;

/**
 * Created by GIS Object on 23/09/2017.
 */
public class TreeModelJacksonJsonProcessorTest {

    @Test
    public void testObjectToJsonMapping() {
        List<Employee> employees;
        try (InputStream inputStream = EMPLOYEE_ARRAY.asInputStream()) {
            employees = TreeModelJacksonJsonProcessor.TREE_MODEL_JACKSON_JSON_PROCESSOR.readJson(inputStream);
            System.out.println(employees);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            TreeModelJacksonJsonProcessor.TREE_MODEL_JACKSON_JSON_PROCESSOR.writeJson(employees, outputStream);
            String result = outputStream.toString(StandardCharsets.UTF_8.name());
            assertEquals(EMPLOYEE_ARRAY.asString(), result);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

    }
}