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


}
