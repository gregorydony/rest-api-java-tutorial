package org.gisobject.tutorial.jaxrs.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.gisobject.tutorial.jaxrs.bean.Department;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by GIS Object on 06/10/2017.
 */
public class JsonFileDepartmentDao implements DepartmentDao {

    public static final String FILE_NAME = "departments.json";

    private static String HIRE_DATE_PATTERN = "yyyy-MM-dd";

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private ObjectReader objectReader;

    private ObjectWriter objectWriter;

    public JsonFileDepartmentDao() {
            DateFormat dateFormat =  new SimpleDateFormat(HIRE_DATE_PATTERN);
            ObjectMapper objectMapper = new ObjectMapper();
            objectReader = objectMapper.reader().forType(new TypeReference<List<Department>>(){});
            objectWriter = objectMapper.writer(dateFormat);
    }
    private InputStream getFileInputStream() {
        return this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
    }

    public List<Department> getAllDepartments() {
        readWriteLock.readLock();
        try (InputStream inputStream = getFileInputStream()){
            System.out.println("toto");
            return objectReader.readValue(inputStream);
        } catch (IOException ioe) {
            throw new IllegalStateException("Unable to read " + FILE_NAME, ioe);
        }
    }
}
