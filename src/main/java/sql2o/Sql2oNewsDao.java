package sql2o;

import Interfaces.NewsDao;
import models.DepartmentNews;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oNewsDao implements NewsDao {
    //sql2o connection object
    private final Sql2o sql2o;

    public static final String GEN_NEWS="general";
    public static final String DEP_NEWS="department";

    //constructor to implement sql2o object
    public Sql2oNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void addGeneralNews() {

    }

    @Override
    public void addDepartmentNews() {

    }
//Get all categories of news
    @Override
    public List<News> getAllNews() {

        List<News>news=new ArrayList<>();
        news.addAll(getGeneralNews());
        news.addAll(getDepartmentNews());
        return news;
    }
//Method to get general news
    @Override
    public List<News> getGeneralNews() {
       String sql="SELECT *FROM news  WHERE type=:type";
       try(Connection con= sql2o.open()){
           return con.createQuery(sql)
                   .throwOnMappingFailure(false)
                   .addParameter("type", GEN_NEWS)
                   .executeAndFetch(News.class);
       }
    }

    //method to get department news
    @Override
    public List<DepartmentNews> getDepartmentNews() {
      String sql="SELECT *FROM news WHERE type=:type";
      try(Connection con= sql2o.open()){
          return con.createQuery(sql)
                  .addParameter("type",DEP_NEWS)
                  .executeAndFetch(DepartmentNews.class);
      }
    }
}
