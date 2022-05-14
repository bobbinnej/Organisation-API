package models;

import java.util.Objects;

public class User {

    private int id;
    private String name;
    private String staff_position;
    private String role;
    private String department_id;

    // constructor
    public User(String name, String staff_position, String role, String department_id) {
        this.name = name;
        this.staff_position = staff_position;
        this.role = role;
        this.department_id = department_id;
    }

    public User(int id, String name, String staff_position, String role, String department_id) {
        this.id = id;
        this.name = name;
        this.staff_position = staff_position;
        this.role = role;
        this.department_id = department_id;
    }

    //overriders

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return id == users.id && name.equals(users.name) && staff_position.equals(users.staff_position) && role.equals(users.role) && department_id.equals(users.department_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, staff_position, role, department_id);
    }


    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaff_position() {
        return staff_position;
    }

    public void setStaff_position(String staff_position) {
        this.staff_position = staff_position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }




}
