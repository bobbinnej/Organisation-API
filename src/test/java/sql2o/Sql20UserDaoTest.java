package sql2o;

import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql20UserDaoTest {


    //define objects i am testing and connection
    private Sql20UserDao sql20UserDao;
    private Connection con;

    //Method that runs before every test
    @BeforeEach
    //setup my database inorder to test my methods
    public void setup(){
        String connect = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'"; // driver is jdbc, the database is H2, the database is in memory meaning its not a physical database like once you stop your application then your database is gone, the name of the database is testing
                                                                                             // INIT=RUNSCRIPT is the script that will be used to generate the database in memory
        //create connection which is creating an sql2o object
        Sql2o sql2o=new Sql2o(connect,"","");
        sql20UserDao=new Sql20UserDao(sql2o); //initialize sql2o user dao
        con= sql2o.open(); //open the connection
    }

    //check whether adduser method is working
    @Test
    public void add_addNewUser_true(){
        User user=new User("jackie", "HR","Pay staff","BOO4");
        int id =user.getId();
        sql20UserDao.addUser(user);
        assertNotEquals(id,user.getId());
    }

     @AfterEach  //this will run after every test
       public void closeCon(){     //close database connection
        con.close();
    }



}