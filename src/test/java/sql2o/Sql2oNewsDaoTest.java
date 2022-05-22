package sql2o;

import models.News;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oNewsDaoTest {

    //define objects i am testing and connection
    private Sql2oNewsDao sql2oNewsDao;
    private Connection con;

    //Method that runs before every test
    @BeforeEach
    //setup my database inorder to test my methods
    public void setup(){
        String connect = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'"; // driver is jdbc, the database is H2, the database is in memory meaning its not a physical database like once you stop your application then your database is gone, the name of the database is testing
        // INIT=RUNSCRIPT is the script that will be used to generate the database in memory

        //create connection which is creating an sql2o object
        Sql2o sql2o=new Sql2o(connect,"","");
        sql2oNewsDao =new Sql2oNewsDao(sql2o);//initialize sql2o user dao
        con= sql2o.open(); //open the connection
    }

    //Test we can add new news
    @Test
    public void add_addNewNews_true(){


    }

    @AfterEach  //this will run after every test
    public void closeCon(){     //close database connection
        con.close();
    }





    private User setUpUser() {
        return new User("jackie","HR","Guidance",2);
    }
}