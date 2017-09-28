package org.gisobject.tutorial.restapi.jackson.treemodel;

import org.gisobject.tutorial.restapi.bean.Employee;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.gisobject.tutorial.restapi.jackson.treemodel.TreeModelJacksonJsonProcessor.TREE_MODEL_JACKSON_JSON_PROCESSOR;
import static org.gisobject.tutorial.restapi.test.TestResource.EMPLOYEE_ARRAY;

/**
 * Created by GIS Object on 23/09/2017.
 */
public class TreeModelJacksonJsonProcessorTest {

    @Test
    public void testObjectToJsonMapping() {
        List<Employee> employees;
        try (InputStream inputStream = EMPLOYEE_ARRAY.asInputStream()) {
            employees = TREE_MODEL_JACKSON_JSON_PROCESSOR.readJson(inputStream);
            System.out.println(employees);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}