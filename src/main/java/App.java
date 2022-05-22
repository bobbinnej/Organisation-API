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

        static

    }
}
