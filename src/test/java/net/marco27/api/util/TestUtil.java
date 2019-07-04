package net.marco27.api.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class TestUtil {
    /** @param obj to be converted to String
     * @return a String json representation of the input object */
    public static String stringifyJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** Create a Json from file
     *
     * @param jsonFile a File containing a Json
     * @param clazz the Class of the json
     * @param <T> the Type
     * @return an instance of the input clazz
     * @throws IOException if something goes wrong */
    public static <T> T getJsonFromFile(final File jsonFile, final Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(jsonFile, clazz);
    }

    /** Create a Json from a String
     *
     * @param jsonString a String representing the json
     * @param clazz the Class of the json
     * @param <T> the Type
     * @return an instance of the input clazz
     * @throws Exception if something goes wrong */
    public static <T> T getJsonFromString(final String jsonString, final Class<T> clazz) throws Exception {
        return new ObjectMapper().readValue(jsonString, clazz);
    }
}
