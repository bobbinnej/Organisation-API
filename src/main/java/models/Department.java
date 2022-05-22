package models;

import java.util.Objects;

public class Department {
    private int id;
    private String name;
    private String description;
    private int dpt_size;

    // constructor

    public Department(String name, String description, int dpt_size) {
        this.name = name;
        this.description = description;
        this.dpt_size = dpt_size;
    }

    public Department(int id, String name, String description, int dpt_size) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dpt_size = dpt_size;
    }

    //overriders


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && dpt_size == that.dpt_size && name.equals(that.name) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, dpt_size);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDpt_size() {
        return dpt_size;
    }

    public void setDpt_size(int dpt_size) {
        this.dpt_size = dpt_size;
    }
}
