package org.gisobject.tutorial.jaxrs.dao;

import org.gisobject.tutorial.jaxrs.bean.Department;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by GIS Object on 06/10/2017.
 */
public interface DepartmentDao {

    @NotNull
    List<Department> getAllDepartments();
}
