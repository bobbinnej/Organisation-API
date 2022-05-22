package sql2o;

import models.Department;
import models.DepartmentNews;
import models.News;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oNewsDaoTest {

    //define objects i am testing and connection
    private Sql2oNewsDao sql2oNewsDao;
    private Sql2oUserDao sql2oUserDao;
    private static Sql2oDepartmentDao sql2oDepartmentDao;
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
        sql2oUserDao=new Sql2oUserDao(sql2o);
        sql2oDepartmentDao=new Sql2oDepartmentDao(sql2o);
        con= sql2o.open(); //open the connection
    }

    //Test we can add general news
    @Test
    public void addGeneralNews(){
     News news=new News("Mps should reject the new budget amendments",33,"VVb","politics","Budget changes");
     sql2oNewsDao.addGeneralNews(news);
     assertEquals(1,news.getId());
    }

    // Test to add department news
    @Test
    public void addDepartmentNews(){
        DepartmentNews depNews =new DepartmentNews(1,"Employees that will be promoted", 21, "BOO9","Buzz", "Employee promotion");
        sql2oNewsDao.addDepartmentNews(depNews);
        assertEquals(1,depNews.getId());

    }

    //Test to get all news
    @Test
    public void getAllNews(){

        User user=setUpUser();
        sql2oUserDao.addUser(user);
        Department department=setUpDepartment();
        sql2oDepartmentDao.addDepartment(department);
        DepartmentNews depNews =new DepartmentNews(1,"Employees that will be promoted", 21, "BOO9","Buzz", "Employee promotion");
        sql2oNewsDao.addDepartmentNews(depNews);
        News news=new News("Mps should reject the new budget amendments",33,"VVb","politics","Budget changes");
        sql2oNewsDao.addGeneralNews(news);
        assertEquals(2,sql2oNewsDao.getAllNews().size());

    }

    //test to clear all news
    @Test
    public void clearAllNews(){
        User user=setUpUser();
        sql2oUserDao.addUser(user);
        Department department=setUpDepartment();
        sql2oDepartmentDao.addDepartment(department);
        DepartmentNews depNews =new DepartmentNews(1,"Employees that will be promoted", 21, "BOO9","Buzz", "Employee promotion");
        sql2oNewsDao.addDepartmentNews(depNews);
        News news=new News("Mps should reject the new budget amendments",33,"VVb","politics","Budget changes");
        sql2oNewsDao.addGeneralNews(news);

        sql2oNewsDao.clearAllNews();
        assertEquals(0,sql2oNewsDao.getAllNews().size());
    }

    //find news by id
    @Test
    public void findNewsById(){
        Department department=setUpDepartment();
        sql2oDepartmentDao.addDepartment(department);
        DepartmentNews depNews =new DepartmentNews(1,"Employees that will be promoted", 21, "BOO9","Buzz", "Employee promotion");
        sql2oNewsDao.addDepartmentNews(depNews);
        News news=new News("Mps should reject the new budget amendments",33,"VVb","politics","Budget changes");
        sql2oNewsDao.addGeneralNews(news);

        DepartmentNews findDepartmentNews=sql2oNewsDao.findDepartmentNewsById(depNews.getDepartment_id());
        News findGeneralNews=sql2oNewsDao.findGeneralNewsById(news.getId());

        assertEquals(false, depNews.equals(findDepartmentNews));
        assertEquals(false, news.equals(findGeneralNews));
    }



    @AfterEach  //this will run after every test
    public void closeCon(){     //close database connection
        con.close();
    }





    private User setUpUser() {
        return new User("jackie","HR","Guidance",2);
    }

    private Department setUpDepartment(){
        return new Department(1,"Department of communication","Deals with all matters connumication",24);
    }
}