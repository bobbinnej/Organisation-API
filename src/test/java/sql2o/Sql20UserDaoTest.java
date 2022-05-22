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
    private Sql2oUserDao sql2oUserDao;
    private Connection con;

    //Method that runs before every test
    @BeforeEach
    //setup my database inorder to test my methods
    public void setup(){
        String connect = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'"; // driver is jdbc, the database is H2, the database is in memory meaning its not a physical database like once you stop your application then your database is gone, the name of the database is testing
                                                                                             // INIT=RUNSCRIPT is the script that will be used to generate the database in memory
        //create connection which is creating an sql2o object
        Sql2o sql2o=new Sql2o(connect,"","");
        sql2oUserDao=new Sql2oUserDao(sql2o); //initialize sql2o user dao
        con= sql2o.open(); //open the connection
    }

    //check whether adduser method is working
    @Test
    public void add_addNewUser_true(){
        User user=new User("jackie", "HR","Pay staff",2);
        int id =user.getId();
        sql2oUserDao.addUser(user);
        assertNotEquals(id,user.getId());
    }

    //Test to see if we get all users
    @Test
    public void getAll_returnsAllUser_true(){
        User user=new User("jackie", "HR","Pay staff",2);
        User user1=new User("offset", "Secretary","Keep office records",3);
        sql2oUserDao.addUser(user);
        sql2oUserDao.addUser(user1);
        assertEquals(2, sql2oUserDao.getAll().size());
    }

    //Test to update user information
    @Test
    public void update_returnsUpdatedUser(){
        User user = setUpUser();
        sql2oUserDao.addUser(user);

    }

    //Test to find user by id
     @Test
    public void findUserByID(){

        User user=setUpUser();
        User user1=setUpUser();
        User findUser=sql2oUserDao.findById(user1.getId());
        assertEquals(false,user.equals(findUser));
    }

    //Test to clear all users
    @Test
    public void deleteAllUsers(){
        User user=setUpUser();
        User user1=setUpUser();
        sql2oUserDao.clearAll();
        assertEquals(0,sql2oUserDao.getAll().size());
    }

    public User setUpUser() {
        return new User("Sarah","Head of Communications", "Relay infromation",5);
    }

    @AfterEach  //this will run after every test
       public void closeCon(){     //close database connection
        con.close();
    }



}