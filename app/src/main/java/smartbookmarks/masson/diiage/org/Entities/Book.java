package smartbookmarks.masson.diiage.org.Entities;

import java.util.ArrayList;

public class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    ArrayList<Comment> comments;
    private int nbComments;

    public Book() {
        this.nbComments = 0;
    }

    public Book(int id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(String title, String author, String genre, ArrayList<Comment> comments) {
        this.id = 0;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
        this.nbComments = 0;
    }

    public Book(int id, String title, String author, String genre, int nbComments, ArrayList<Comment> comments) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.comments = comments;
        this.nbComments = nbComments;
    }

    //region Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getNbComments() {
        return nbComments;
    }

    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
    }
}
