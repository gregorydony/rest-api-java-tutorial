package org.gisobject.tutorial.jaxrs;

import org.gisobject.tutorial.jaxrs.bean.Department;
import org.gisobject.tutorial.jaxrs.dao.DepartmentDao;
import org.gisobject.tutorial.jaxrs.dao.JsonFileDepartmentDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by GISObject on 06/10/2017.
 */

@Path("departments")
public class DepartmentService {
    
    DepartmentDao departmentDao = new JsonFileDepartmentDao();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Department> findAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Department findById(@PathParam("id") int id) {
        Department department = new Department();
        department.setId(3);
        return department;
    }
}
