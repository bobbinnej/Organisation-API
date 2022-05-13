package models;

import java.sql.Timestamp;
import java.util.Objects;

//Aspect of inheritance
public class DepartmentNews extends News{

    private int department_id;

    //constructor


    public DepartmentNews(String content, int department_id, int user_id, String type, Timestamp postdate, int department_id1) {
        super(content, department_id, user_id, type, postdate);
        this.department_id = department_id1;
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
    public int getDepartment_id() {
        return department_id;
    }

    @Override
    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }
}
