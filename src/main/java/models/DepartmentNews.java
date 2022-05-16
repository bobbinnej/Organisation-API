package models;

import java.sql.Timestamp;
import java.util.Objects;

//Aspect of inheritance
public class DepartmentNews extends News{

    private String department_id;

    //constructor


    public DepartmentNews(String content, String department_id, String user_id, String type, String title) {
        super(content, department_id, user_id, type,title);
        this.department_id = department_id;
    }

    //overriders

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DepartmentNews that = (DepartmentNews) o;
        return department_id == that.department_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department_id);
    }

    //getters and setters

    @Override
    public String getDepartment_id() {
        return department_id;
    }

    @Override
    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }
}
