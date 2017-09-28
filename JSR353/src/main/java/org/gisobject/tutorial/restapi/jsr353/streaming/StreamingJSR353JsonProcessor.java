package org.gisobject.tutorial.restapi.jsr353.streaming;

import org.gisobject.tutorial.restapi.json.EmployeeJsonProcessor;
import org.gisobject.tutorial.restapi.jsr353.JSR353JsonProcessor;
import org.gisobject.tutorial.restapi.bean.Employee;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.gisobject.tutorial.restapi.jsr353.objectmodel.ObjectModelJSR353JsonProcessor.OBJECT_MODEL_JSON_PROCESSOR;

/**
 * Created by GIS Object on 27/08/2017.
 */
public enum StreamingJSR353JsonProcessor implements EmployeeJsonProcessor, JSR353JsonProcessor<Employee> {

    STREAMING_JSON_PROCESSOR;

    @Override
    public List<Employee> readJson(InputStream inputStream) {
        try (// Create JsonParser to parse JSON data from a stream
             JsonParser jsonParser = Json.createParser(inputStream)) {
            jsonParser.next();
            return fromJson(jsonParser.getArray());
        }
    }

    @Override
    public void writeJson(List<Employee> employees, OutputStream os) {
        try (JsonGenerator jsonGenerator = Json.createGenerator(os)) {
            jsonGenerator.writeStartArray();
            DateFormat dateFormat = new SimpleDateFormat(HIRE_DATE_PATTERN);
            for (Employee employee : employees) {
                // Writes the JSON object for each Employee object
                jsonGenerator.writeStartObject()
                        .write(EMPLOYEE_ID, employee.getEmployeeId())
                        .write(FIRST_NAME, employee.getFirstName())
                        .write(LAST_NAME, employee.getLastName())
                        .write(EMAIL, employee.getEmail())
                        .write(HIRE_DATE, dateFormat.format(employee.getHireDate())).writeEnd();
            }
            jsonGenerator.writeEnd();
        }
    }

    @Override
    public Employee fromJson(JsonObject jsonObject) {
        return OBJECT_MODEL_JSON_PROCESSOR.fromJson(jsonObject);
    }

    @Override
    public List<Employee> fromJson(JsonArray jsonArray) {
        return OBJECT_MODEL_JSON_PROCESSOR.fromJson(jsonArray);
    }

    @Override
    public JsonArray toJson(@NotNull List<Employee> employees) {
        return null;
    }

    @Override
    public JsonObject toJson(@NotNull Employee employee) {
        return null;
    }
}
