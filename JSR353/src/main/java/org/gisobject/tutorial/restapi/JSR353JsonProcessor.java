package org.gisobject.tutorial.restapi;


import org.gisobject.tutorial.restapi.json.JsonProcessor;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This interface is intended to be implemented by several existing APIs mapping
 * JSON to Java Object or Java Object to JSON
 *
 * Created by GISObject on 27/08/2017.
 */
public interface JSR353JsonProcessor<E> extends JsonProcessor<E> {

    String EMPLOYEE_ID = "employeeId";
    String FIRST_NAME = "firstName";
    String LAST_NAME = "lastName";
    String EMAIL = "email";
    String HIRE_DATE_PATTERN = "yyyy-MM-dd";
    String HIRE_DATE = "hireDate";

    /**
     * Maps a {@link JsonObject} to a instance of {@link E}.
     * @param jsonObject The {@link JsonObject} to convert
     * @return an instance of {@link E}
     */
    @NotNull
    E fromJson(@NotNull JsonObject jsonObject);

    /**
     * Maps a {@link JsonArray} to a list of {@link E}
     * @param jsonArray a {@link JsonArray}
     * @return a list of {@link E}
     */
    @NotNull
    List<E> fromJson(@NotNull JsonArray jsonArray);

    /**
     * Maps a list of {@link E} to a {@link JsonArray}
     * @param listOfElements a list of {@link E}
     * @return a {@link JsonArray}
     */
    @NotNull
    JsonArray toJson(@NotNull List<E> listOfElements);

    /**
     * Maps a {@link E} to a {@link JsonObject}.
     * @param element the element to transform in  a JSON Object
     * @return a {@link JsonObject}
     */
    @NotNull
    JsonObject toJson(@NotNull E element);
}
