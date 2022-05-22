package sql2o;

import Interfaces.UserDao;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql20UserDao implements UserDao {

    //sql2o connection object
    private final Sql2o sql2o;

    //constructor to implement sql2o object
    public Sql20UserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void addUser(User user) {
        //define our connection for adding
        String sql="INSERT INTO users(name,department_id, staff_position, role,) VALUES(:name,:department_id, :staff_position, :role)";
        //create connection
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    //connect your inputs with your data
                    .bind(user)
                    .executeUpdate()
                    .getKey();

            //update our user id
            user.setId(id);
        }catch (Sql2oException e){
            System.out.println(e.getMessage());
        }


    }
    //method to get all users
    @Override
    public List<User> getAll() {
        //define our connection for adding
        String sql="SELECT *FROM users";
        //create connection
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql, true)
                     .executeAndFetch(User.class);

        }

    }
  //method to find users by id
    @Override
    public User findById(int id) {
        //define our connection for adding
        String sql="SELECT *FROM users WHERE id=:id";
        //create connection
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql, true)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

//Method to update user
    @Override
    public void updateUser(User user, String name, String staff_position, String role, int department_id) {
        String sql="UPDATE users set(name,staff_position,role,department_id)=(:name, :staff_position, :role,:department_id) WHERE id=:id";
        try(Connection con=sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("staff_position",staff_position)
                    .addParameter("role",role)
                    .addParameter("department_id",department_id)
                    .addParameter("id",user.getId())
                    .executeUpdate();

            user.setName(name);
            user.setStaff_position(staff_position);
            user.setRole(role);
            user.setDepartment_id(department_id);


        }

    }

    //method to clear all users
    @Override
    public void clearAll() {
        String sql="SELECT FROM users";
        //create connection
        try (Connection con = sql2o.open()) {
           con.createQuery(sql).executeUpdate();

        }


    }

}
