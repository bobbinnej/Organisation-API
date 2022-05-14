package sql2o;

import Interfaces.UserDao;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
}
