package data;

import java.util.List;

public class BlogPost {

    String title;
    String content;
    String author;
    int id;

    public BlogPost(int id, String title, String content, String author) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.id = id;
    }
    public BlogPost(String title, String content, String author) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
