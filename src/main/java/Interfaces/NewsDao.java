package Interfaces;

import models.DepartmentNews;
import models.News;

import java.util.List;

public interface NewsDao {
    //CRUD
    //create

    void addGeneralNews();
    void addDepartmentNews();

    //Read

    List<News> getAllNews();
    List<News>getGeneralNews();
    List<DepartmentNews>getDepartmentNews();
}
