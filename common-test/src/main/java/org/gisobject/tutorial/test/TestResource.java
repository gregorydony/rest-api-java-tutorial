package org.gisobject.tutorial.test;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

public enum TestResource {

    EMPLOYEE_ARRAY("emp-array.json"),
    ;

    private static ConcurrentHashMap<TestResource, String> contentCache = new ConcurrentHashMap<>();

    private String resourceLocation;

    TestResource(@NotNull String resourceLocation) {
        this.resourceLocation = resourceLocation;
    }

    public InputStream asInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(resourceLocation);
    }

    public String asString() {
        return contentCache.computeIfAbsent(this, testResource -> toContentString(testResource));

    }

    private static String toContentString(TestResource testResource) {
        try (InputStream inputStream = testResource.asInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException ioe) {
            throw new IllegalStateException("Unable to read " + testResource);
        }
    }
    
}
