package org.gisobject.tutorial.restapi.jackson.streaming;

import org.gisobject.tutorial.restapi.bean.Employee;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.gisobject.tutorial.restapi.jackson.streaming.StreamingJacksonJsonProcessor.STREAMING_JACKSON_JSON_PROCESSOR;
import static org.gisobject.tutorial.restapi.test.TestResource.EMPLOYEE_ARRAY;
import static org.junit.Assert.assertEquals;

/**
 * Created by GIS Object on 29/09/2017.
 */
public class StreamingJacksonJsonProcessorTest {

    @Test
    public void testObjectToJsonMapping() {
        List<Employee> employees;
        try (InputStream inputStream = EMPLOYEE_ARRAY.asInputStream()) {
            employees = STREAMING_JACKSON_JSON_PROCESSOR.readJson(inputStream);
            System.out.println(employees);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            STREAMING_JACKSON_JSON_PROCESSOR.writeJson(employees, outputStream);
            String result = outputStream.toString(StandardCharsets.UTF_8.name());
            assertEquals(EMPLOYEE_ARRAY.asString(), result);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

}