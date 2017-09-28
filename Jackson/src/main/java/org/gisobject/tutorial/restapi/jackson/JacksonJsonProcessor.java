package org.gisobject.tutorial.restapi.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import org.gisobject.tutorial.restapi.json.JsonProcessor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by GIS Object on 24/09/2017.
 */
public interface JacksonJsonProcessor<E> extends JsonProcessor<E> {

    /**
     * Maps a {@link JsonNode} to a instance of {@link E}.
     * @param jsonNode The {@link JsonNode} to convert
     * @return an instance of {@link E}
     */
    @NotNull
    E fromJson(@NotNull JsonNode jsonNode);

    /**
     * Maps a {@link JsonNode} to a list of {@link E}
     * @param jsonNode a {@link JsonNode}
     * @return a list of {@link E}
     */
    @NotNull
    List<E> fromJsonArray(@NotNull JsonNode jsonNode);

    /**
     * Maps a list of {@link E} to a {@link JsonNode}
     * @param listOfElements a list of {@link E}
     * @return a {@link JsonNode}
     */
    @NotNull
    JsonNode toJson(@NotNull List<E> listOfElements);

    /**
     * Maps a {@link E} to a {@link JsonNode}.
     * @param element the element to transform in  a JSON Object
     * @return a {@link JsonNode}
     */
    @NotNull
    JsonNode toJson(@NotNull E element);

}
