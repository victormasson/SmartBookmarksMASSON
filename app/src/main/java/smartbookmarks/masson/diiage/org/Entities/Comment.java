package smartbookmarks.masson.diiage.org.Entities;

import java.util.Date;

public class Comment {
    private int id;
    private int bookId;
    private int page;
    private String comment;
    private String date;
    private String bookTitle;

    public Comment() {
    }

    public Comment(int id, int bookId, int page, String comment, String date, String bookTitle) {
        this.id = id;
        this.bookId = bookId;
        this.page = page;
        this.comment = comment;
        this.date = date;
        this.bookTitle = bookTitle;
    }

    public Comment(int id, int bookId, int page, String comment, String date) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
