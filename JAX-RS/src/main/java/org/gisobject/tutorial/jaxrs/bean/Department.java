package org.gisobject.tutorial.jaxrs.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by GISObject on 06/10/2017.
 */
@XmlRootElement(name="department")
@XmlAccessorType(XmlAccessType.FIELD)
public class Department implements Serializable {

    @XmlElement(name="id")
    private Integer id;

    public Department(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
