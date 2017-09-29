package org.gisobject.tutorial.json.gson.streaming;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.gisobject.tutorial.bean.Employee;
import org.gisobject.tutorial.json.EmployeeProcessor;
import org.gisobject.tutorial.json.JsonProcessor;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by GIS Object on 29/09/2017.
 */
public enum StreamingGsonJsonProcessor implements EmployeeProcessor, JsonProcessor<Employee> {

    STREAMING_GSON_JSON_PROCESSOR;

    private transient Gson gson;

    private transient Type type;

    StreamingGsonJsonProcessor() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat(HIRE_DATE_PATTERN);
        gson = gsonBuilder.create();
        type = new TypeToken<List<Employee>>(){}.getType();
    }


    @Override
    public List<Employee> readJson(InputStream is) {
        try (JsonReader jsonReader = new JsonReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return gson.fromJson(jsonReader, type);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Override
    public void writeJson(List<Employee> input, OutputStream os) {
        try (JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8))) {
            gson.toJson(input, type, jsonWriter);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }
}
