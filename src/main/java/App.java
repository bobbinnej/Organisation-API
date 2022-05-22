import com.google.gson.Gson;
import models.Department;
import models.DepartmentNews;
import models.News;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import sql2o.Sql2oDepartmentDao;
import sql2o.Sql2oNewsDao;
import sql2o.Sql2oUserDao;

import static spark.Spark.*;


public class App {
 private static Connection con;

    public static void main(String[] args) {
        Sql2oUserDao userDao;
        Sql2oNewsDao newsDao;
        Sql2oDepartmentDao departmentDao;
        Gson gson=new Gson();
        Sql2o sql2o=new Sql2o("jdbc:postgresql://localhost:5432/news", "postgres","1870691bbn");

        userDao=new Sql2oUserDao(sql2o);
        newsDao=new Sql2oNewsDao(sql2o);
        departmentDao=new Sql2oDepartmentDao(sql2o);
        con= sql2o.open();

        staticFileLocation("/public");

        //get all users
        get("/users","application/json", (request,respond)->{
            return gson.toJson(userDao.getAll());
        });
        get("/users/:id", (request, respond) -> {
            int user_id = Integer.parseInt(request.params("id"));
            return gson.toJson(userDao.findById(user_id));
        });
        post("/Users/new", "application/json", (request, respond) -> {
            User user = gson.fromJson(request.body(), User.class);

            userDao.addUser(user);
            respond.status(201);
            respond.type("application/json");
            respond.redirect("/users");
            return gson.toJson(user);
        });

        //departments

        get("/departments", (request,respond)->{
            return gson.toJson(departmentDao.getAllDepartment());
        });
        post("/Departments/new", "application/json",(request,respond)->{
            Department department=gson.fromJson(request.body(), Department.class);
            departmentDao.addDepartment(department);
            respond.status(201);
            respond.redirect("/departments");
            return gson.toJson(department);
        });
        get("/departments/:id",(request,respond)->{
            int dpt_id=Integer.parseInt(request.params("id"));
            return gson.toJson(departmentDao.findById(dpt_id));
        });
        get("/departments/:id/users",(request,respond)->{
            int dpt_id=Integer.parseInt(request.params("id"));
            return gson.toJson(departmentDao.getDepartmentUsersById(dpt_id));
        });
        get("/departments/:id/news", (request,respond)->{
            int dpt_id=Integer.parseInt(request.params("id"));
            return gson.toJson(departmentDao.getDepartmentNewsById(dpt_id));

        });


        //news
        get("/news",(request,respond)->{
            return gson.toJson(newsDao.getAllNews());
        });
        get("/news/general",(request,respond)->{
            return gson.toJson(newsDao.getGeneralNews());
        });
        get("/news/department",(request,respond)->{
            return gson.toJson(newsDao.getDepartmentNews());
        });
        post("/News/new","application/json",(request,respond)->{
            News news=gson.fromJson(request.body(),News.class);
            newsDao.addGeneralNews(news);
            respond.status(201);
            respond.type("application/json");
            respond.redirect("/news/general");
            return gson.toJson(news);
        });
        post("DepartmentNews/new","application/json",(request,respond)->{
            DepartmentNews depNews=gson.fromJson(request.body(),DepartmentNews.class);
            newsDao.addDepartmentNews(depNews);
            respond.status(201);
            respond.type("application/json");
            respond.redirect("/news/department");
            return gson.toJson(depNews);
        });

        



    }
}
