package smartbookmarks.masson.diiage.org.Entities;

import java.util.Date;

public class Comment {
    private int id;
    private int bookId;
    private int page;
    private String comment;
    private Date date;
    private String boolTitle;

    public Comment() {
    }

    public Comment(int id, int bookId, int page, String comment, Date date, String boolTitle) {
        this.id = id;
        this.bookId = bookId;
        this.page = page;
        this.comment = comment;
        this.date = date;
        this.boolTitle = boolTitle;
    }

    public Comment(int id, int bookId, int page, String comment, Date date) {
        this.id = id;
        this.bookId = bookId;
        this.page = page;
        this.comment = comment;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBoolTitle() {
        return boolTitle;
    }

    public void setBoolTitle(String boolTitle) {
        this.boolTitle = boolTitle;
    }
}