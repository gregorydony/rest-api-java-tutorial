package org.gisobject.tutorial.restapi.jsr353.streaming;

import org.gisobject.tutorial.restapi.bean.Employee;
import org.gisobject.tutorial.restapi.jsr353.objectmodel.ObjectModelJSR353JsonProcessor;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.gisobject.tutorial.restapi.jsr353.streaming.StreamingJSR353JsonProcessor.STREAMING_JSON_PROCESSOR;

/**
 * Created by GIS Object on 27/08/2017.
 */
public class StreamingJSR353JsonProcessorTest {

    String jsonFileName ="emp-array.json";
    @Test
    public void testObjectToJsonMapping() {
        List<Employee> employees;
        try (InputStream inputStream = ObjectModelJSR353JsonProcessor.class.getClassLoader().getResourceAsStream(jsonFileName)) {
            employees = STREAMING_JSON_PROCESSOR.readJson(inputStream);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            STREAMING_JSON_PROCESSOR.writeJson(employees, outputStream);
            System.out.println(outputStream.toString(StandardCharsets.UTF_8.name()));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
