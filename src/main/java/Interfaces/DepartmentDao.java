package Interfaces;

import models.Department;
import models.DepartmentNews;
import models.User;

import java.util.List;

public interface DepartmentDao {
    //CRUD

    //create
    void addDepartment(Department department);

    //Read
    List<Department> getAllDepartment();
    List<User>getDepartmentUsersById(int id);

    List<DepartmentNews>getDepartmentNewsById(int id);

    //update
    void updateDepartment(Department department, String name, String description, int dpt_size);

    //clear
    void clearAllDepartments();

    //find by id
    Department findById(int id);

}
