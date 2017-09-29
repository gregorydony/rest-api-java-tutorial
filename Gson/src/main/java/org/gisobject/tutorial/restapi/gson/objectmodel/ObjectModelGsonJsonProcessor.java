package org.gisobject.tutorial.restapi.gson.objectmodel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.gisobject.tutorial.restapi.bean.Employee;
import org.gisobject.tutorial.restapi.json.EmployeeProcessor;
import org.gisobject.tutorial.restapi.json.JsonProcessor;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by GIS Object on 29/09/2017.
 */
public enum ObjectModelGsonJsonProcessor implements EmployeeProcessor, JsonProcessor<Employee> {

    OBJECT_MODEL_GSON_JSON_PROCESSOR;

    private transient Gson gson;

    private transient Type type;

    ObjectModelGsonJsonProcessor() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(HIRE_DATE_PATTERN);
        gson = gsonBuilder.create();
        type = new TypeToken<List<Employee>>(){}.getType();
    }

    @Override
    public List<Employee> readJson(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return gson.fromJson(reader, type);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public void writeJson(List<Employee> input, OutputStream os) {
        String json = gson.toJson(input);
        try {
            os.write(json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
