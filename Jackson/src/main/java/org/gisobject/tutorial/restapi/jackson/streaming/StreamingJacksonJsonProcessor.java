package org.gisobject.tutorial.restapi.jackson.streaming;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.gisobject.tutorial.restapi.bean.Employee;
import org.gisobject.tutorial.restapi.json.EmployeeProcessor;
import org.gisobject.tutorial.restapi.json.JsonProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by GIS Object on 29/09/2017.
 */
public enum StreamingJacksonJsonProcessor implements EmployeeProcessor, JsonProcessor<Employee> {

    STREAMING_JACKSON_JSON_PROCESSOR;

    private transient ObjectReader objectReader;

    private transient ObjectWriter objectWriter;

    StreamingJacksonJsonProcessor() {
        DateFormat dateFormat =  new SimpleDateFormat(HIRE_DATE_PATTERN);
        ObjectMapper objectMapper = new ObjectMapper();
        objectReader = objectMapper.reader().forType(new TypeReference<List<Employee>>(){});
        objectWriter = objectMapper.writer(dateFormat);
    }

    @Override
    public List<Employee> readJson(InputStream is) {
        try {
            JsonParser jsonParser = new JsonFactory().createParser(is);
            return objectReader.readValue(jsonParser);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public void writeJson(List<Employee> employees, OutputStream os) {
        try {
            JsonGenerator jsonGenerator = new JsonFactory().createGenerator(os);
            objectWriter.writeValue(jsonGenerator, employees);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

    }
}
