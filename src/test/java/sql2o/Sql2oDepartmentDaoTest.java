package sql2o;

import models.Department;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oDepartmentDaoTest {
    //define objects i am testing and connection
    private Sql2oDepartmentDao sql2oDepartmentDao;
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
        sql2oDepartmentDao=new Sql2oDepartmentDao(sql2o); //initialize sql2o user dao
        con= sql2o.open(); //open the connection
    }


    //add a department
    @Test
    public void addNewDepartment(){
        Department department=new Department(1,"Department of Communication", "responsible for relaying all communication",12);
        int id= department.getId();
        sql2oDepartmentDao.addDepartment(department);
        assertEquals(id,department.getId());
    }
    //delete all departments
    @Test
    public void deleteAlldepartments(){
        Department department=new Department(1,"Department of Communication", "responsible for relaying all communication",12);
        Department department1=new Department(1,"Department of Communication", "responsible for relaying all communication",12);
        sql2oDepartmentDao.addDepartment(department1);
        sql2oDepartmentDao.addDepartment(department);

        sql2oDepartmentDao.clearAllDepartments();
        assertEquals(0,sql2oDepartmentDao.getAllDepartment().size());

    }

    //get all departments
    @Test
    public void getAllDepartments(){
        Department department=new Department(1,"Department of Communication", "responsible for relaying all communication",12);
        Department department1=new Department(1,"Department of Communication", "responsible for relaying all communication",12);
        sql2oDepartmentDao.addDepartment(department1);
        sql2oDepartmentDao.addDepartment(department);

      assertEquals(2, sql2oDepartmentDao.getAllDepartment().size());

    }
    //get department  by id
    @Test
    public void findDepartmentById(){
        Department department=new Department(1,"Department of Communication", "responsible for relaying all communication",12);
        Department department1=new Department(2,"Department of Communication", "responsible for relaying all communication",12);

        Department findById=sql2oDepartmentDao.findById(department1.getId());
        assertEquals(false,department.equals(findById));
    }

//    //get department users by id
//    @Test
//    public void getDepartmentUsersById(){
//        Department department=new Department(1,"Department of Communication", "responsible for relaying all communication",12);
//        sql2oDepartmentDao.addDepartment(department);
//
//        User user=setUpUser();
//        User user1=new User(2, "Wambui", "clerical-officer","printing",34);
//
//       sql2oUserDao.addUser(user);
//       sql2oUserDao.addUser(user1);
//
//
//    }


    public User setUpUser() {
        return new User("Sarah","Head of Communications", "Relay infromation",5);
    }

    @AfterEach  //this will run after every test
    public void closeCon(){     //close database connection
        con.close();
    }


}