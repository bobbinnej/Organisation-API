package sql2o;

import Interfaces.NewsDao;
import models.DepartmentNews;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

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
    public void addGeneralNews(News news) {
        String sql="INSERT INTO news(content,user_id,type,title) VALUES(:content,:user_id,:type,:title)";
        try(Connection con= sql2o.open()){
            int id =(int) con.createQuery(sql,true)
                    .addParameter("content",news.getContent())
                    .addParameter("user_id",news.getUser_id())
                    .addParameter("type", news.getType())
                    .addParameter("title", news.getTitle())
                    .executeUpdate()
                    .getKey();

            news.setId(id);

        }


    }

    @Override
    public void addDepartmentNews(DepartmentNews depNews) {
        String sql="INSERT INTO news(content,department_id, user_id,type,title) VALUES(:content,:department_id,:user_id,:type,:title)";
        try(Connection con= sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .addParameter("content", depNews.getContent())
                    .addParameter("department_id", depNews.getDepartment_id())
                    .addParameter("user_id", depNews.getUser_id())
                    .addParameter("type", depNews.getType())
                    .addParameter("title", depNews.getTitle())
                    .executeUpdate()
                    .getKey();
            depNews.setId(id);
        }


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

    //method to update general news
    @Override
    public void updateGeneralNews(News news, String user_id, String content, int department_id, String type, String title) {
        String sql="UPDATE news set( user_id, content, department_id, type, title)=( :user_id, :content, :department_id, :type, :title) WHERE id=:id";
        try(Connection con= sql2o.open()){
            con.createQuery(sql)
                    .addParameter("user_id",user_id)
                    .addParameter("content",content)
                    .addParameter("department_id",department_id)
                    .addParameter("type",type)
                    .addParameter("title",title)
                    .addParameter("id",news.getId())
                            .executeUpdate();

                    news.setUser_id(user_id);
                    news.setContent(content);
                    news.setDepartment_id(department_id);
                    news.setType(type);
                    news.setTitle(title);

        }

    }
//Method to update department news
    @Override
    public void updateDepartmentNews(DepartmentNews depNews, String user_id, String content, int department_id) {
        String sql="UPDATE news set(user_id, content, department_id)=(:user_id, :content, :department_id) WHERE id=:id";
        try(Connection con= sql2o.open()){
            con.createQuery(sql)
                    .addParameter("user_id", user_id)
                    .addParameter("content", content)
                    .addParameter("department_id",department_id)
                    .addParameter("id", depNews.getId())
                    .executeUpdate();
            depNews.setUser_id(user_id);
            depNews.setContent(content);
            depNews.setDepartment_id(department_id);

        }

    }
    //Method to clear all news
    @Override
    public void clearAllNews() {
       String sql="DELETE FROM news";
       try(Connection con= sql2o.open()){
           con.createQuery(sql).executeUpdate();
       }
    }
//method to delete general news
    @Override
    public void clearGeneralNews() {
        String sql="DELETE FROM news WHERE type=:type";
        try(Connection con= sql2o.open()){
            con.createQuery(sql)
                    .addParameter("type", GEN_NEWS)
                    .executeUpdate();
        }

    }
//method to clear all department news
    @Override
    public void clearDepartmentNews() {
        String sql="DELETE FROM news WHERE type=:type";
        try(Connection con= sql2o.open()){
            con.createQuery(sql)
                    .addParameter("type", DEP_NEWS)
                    .executeUpdate();
        }

    }

    //method to find general news by id
    @Override
    public News findGeneralNewsById(int id) {
        String sql="SELECT *FROM  news WHERE type=:type and id=:id";
        try(Connection con= sql2o.open()){
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("type", GEN_NEWS)
                    .addParameter("id", id)
                    .executeAndFetchFirst(News.class);
        }
    }

    //find department news by id
    @Override
    public DepartmentNews findDepartmentNewsById(int id) {
        String sql = "SELECT *FROM  news WHERE type=:type and id=:id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("type", DEP_NEWS)
                    .addParameter("id", id)
                    .executeAndFetchFirst(DepartmentNews.class);
        }
    }




}
