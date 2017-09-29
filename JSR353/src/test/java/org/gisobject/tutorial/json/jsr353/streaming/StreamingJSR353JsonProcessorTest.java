package org.gisobject.tutorial.json.jsr353.streaming;

import org.gisobject.tutorial.bean.Employee;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.gisobject.tutorial.json.jsr353.streaming.StreamingJSR353JsonProcessor.STREAMING_JSON_PROCESSOR;
import static org.gisobject.tutorial.test.TestResource.EMPLOYEE_ARRAY;
import static org.junit.Assert.assertEquals;

/**
 * Created by GIS Object on 27/08/2017.
 */
public class StreamingJSR353JsonProcessorTest {

    @Test
    public void testObjectToJsonMapping() {
        List<Employee> employees;
        try (InputStream inputStream = EMPLOYEE_ARRAY.asInputStream()) {
            employees = STREAMING_JSON_PROCESSOR.readJson(inputStream);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            STREAMING_JSON_PROCESSOR.writeJson(employees, outputStream);
            String result = outputStream.toString(StandardCharsets.UTF_8.name());
            assertEquals(EMPLOYEE_ARRAY.asString(), result);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
