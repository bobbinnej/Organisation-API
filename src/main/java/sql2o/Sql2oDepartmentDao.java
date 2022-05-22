package sql2o;

import Interfaces.DepartmentDao;
import models.Department;
import models.DepartmentNews;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;
import java.util.stream.Collectors;

public class Sql2oDepartmentDao implements DepartmentDao {

    private final Sql2oNewsDao newsDao;
    private final Sql2oUserDao userDao;
    //sql2o connection object
    private final Sql2o sql2o;

    public Sql2oDepartmentDao(Sql2oNewsDao newsDao, Sql2oUserDao userDao, Sql2o sql2o) {
        this.newsDao = newsDao;
        this.userDao = userDao;
        this.sql2o = sql2o;
    }


// add a department
    @Override
    public void addDepartment(Department department) {
        String sql="INSERT INTO departments (name, description, dpt_size) VALUES(:name, :decription, :dpt_size)";
        try(Connection con= sql2o.open()){
            int id=(int) con.createQuery(sql,true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);


        }

    }

    //method to get all departments
    @Override
    public List<Department> getAllDepartment() {
        String sql="SELECT *FROM departments";
        try(Connection con= sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Department.class);

        }

    }
//get Department users by id
    @Override
    public List<User> getDepartmentUsersById(int id) {
       return userDao.getAll().stream()
               .filter(user->user.getDepartment_id()==id)
               .collect(Collectors.toList());

    }
//get department news by id
    @Override
    public List<DepartmentNews> getDepartmentNewsById(int id) {
         return newsDao.getDepartmentNews().stream()
                 .filter(dept->dept.getDepartment_id()==id)
                 .collect(Collectors.toList());

    }
// update department
    @Override
    public void updateDepartment(Department department, String name, String description, int dpt_size) {
        String sql="UPDATE departments set(name, description, dpt_size)=(:name, :description, :dpt_size)";
        try(Connection con= sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("description", description)
                    .addParameter("dpt_size", dpt_size)
                    .executeUpdate();
            department.setName(name);
            department.setDescription(description);
            department.setDpt_size(dpt_size);

        }
    }


    //clear all departments
    @Override
    public void clearAllDepartments() {
        String sql="DELETE FROM departments";
        try(Connection con=sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }

    }

    @Override
    public Department findById(int id) {
        String sql="SELECT *FROM departments where id=:id";
        try(Connection con= sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }
}
