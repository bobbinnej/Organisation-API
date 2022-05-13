package models;

import java.sql.Timestamp;
import java.util.Objects;

public class News {

    private int id;
    private String content;
    private int department_id;
    private  int user_id;
    private String type;
    private Timestamp postdate;


    // constructor
    public News(String content, int department_id, int user_id, String type, Timestamp postdate) {
        this.content = content;
        this.department_id = department_id;
        this.user_id = user_id;
        this.type = type;
        this.postdate = postdate;
    }

    public News(int id, String content, int department_id, int user_id, String type, Timestamp postdate) {
        this.id = id;
        this.content = content;
        this.department_id = department_id;
        this.user_id = user_id;
        this.type = type;
        this.postdate = postdate;
    }

    // overriders

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id && department_id == news.department_id && user_id == news.user_id && content.equals(news.content) && type.equals(news.type) && postdate.equals(news.postdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, department_id, user_id, type, postdate);
    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getPostdate() {
        return postdate;
    }

    public void setPostdate(Timestamp postdate) {
        this.postdate = postdate;
    }
}



