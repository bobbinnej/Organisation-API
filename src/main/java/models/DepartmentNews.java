package models;

import java.util.Objects;

//Aspect of inheritance
public class DepartmentNews extends News{

    private int department_id;

    //constructor

    public DepartmentNews(int id, String content, int department_id, String user_id, String type, String title) {
        super(id, content, department_id, user_id, type, title);
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
    public int getDepartment_id() {
        return department_id;
    }

    @Override
    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }
}
