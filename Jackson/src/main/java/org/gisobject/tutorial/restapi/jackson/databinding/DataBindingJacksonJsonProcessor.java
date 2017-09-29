package org.gisobject.tutorial.restapi.jackson.databinding;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.gisobject.tutorial.restapi.bean.Employee;
import org.gisobject.tutorial.restapi.json.EmployeeJsonProcessor;
import org.gisobject.tutorial.restapi.json.JsonProcessor;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by GIS Object on 29/09/2017.
 */
public enum DataBindingJacksonJsonProcessor implements EmployeeJsonProcessor, JsonProcessor<Employee> {

    DATA_BINDING_JACKSON_JSON_PROCESSOR;

    private transient ObjectReader objectReader;

    private transient ObjectWriter objectWriter;

    DataBindingJacksonJsonProcessor() {
        DateFormat dateFormat =  new SimpleDateFormat(HIRE_DATE_PATTERN);
        ObjectMapper objectMapper = new ObjectMapper();
        objectReader = objectMapper.reader().forType(new TypeReference<List<Employee>>(){});
        objectWriter = objectMapper.writer(dateFormat);
    }

    @NotNull
    public List<Employee> readJson(@NotNull InputStream inputStream) {
        try {
            return objectReader.readValue(inputStream);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public void writeJson(List<Employee> employees, OutputStream os) {
        try {
            objectWriter.writeValue(os, employees);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }


}
