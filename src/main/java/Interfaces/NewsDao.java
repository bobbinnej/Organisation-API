package Interfaces;

import models.DepartmentNews;
import models.News;

import java.util.List;

public interface NewsDao {
    //CRUD
    //create

    void addGeneralNews(News news);

    void addDepartmentNews(DepartmentNews depNews);

    //Read

    List<News> getAllNews();
    List<News>getGeneralNews();
    List<DepartmentNews>getDepartmentNews();

    //update

    void updateGeneralNews(News news, String user_id, String content, int department_id, String type,String title);
    void updateDepartmentNews(DepartmentNews depNews, String user_id, String content, int department_id);


    //clear
    void clearAllNews();
    void clearGeneralNews();
    void clearDepartmentNews();

    //find by id
    News findGeneralNewsById(int id);
    DepartmentNews findDepartmentNewsById(int id);



}
