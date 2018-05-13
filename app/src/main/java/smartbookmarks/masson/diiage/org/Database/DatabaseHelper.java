package smartbookmarks.masson.diiage.org.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import smartbookmarks.masson.diiage.org.Entities.Book;
import smartbookmarks.masson.diiage.org.Entities.Comment;

public class DatabaseHelper extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SmartBooksmarks.db";

    public static final String TABLE_BOOKS = "Books";
    public static final String TABLE_BOOKS_ID = "id";
    public static final String TABLE_BOOKS_TITLE = "title";
    public static final String TABLE_BOOKS_AUTHOR = "author";
    public static final String TABLE_BOOKS_GENRE = "genre";
    public static final String TABLE_BOOKS_AUTHORID = "authorId";

    public static final String TABLE_COMMENTS = "Comments";
    public static final String TABLE_COMMENTS_ID = "id";
    public static final String TABLE_COMMENTS_BOOKID = "bookId";
    public static final String TABLE_COMMENTS_PAGE = "page";
    public static final String TABLE_COMMENTS_COMMENT = "comment";
    public static final String TABLE_COMMENTS_DATE = "date";

    public static final String TABLE_AUTHORS = "Authors";
    public static final String TABLE_AUTHORS_ID = "id";
    public static final String TABLE_AUTHORS_NAME = "name";
    public static final String TABLE_AUTHORS_BIRTHDAY = "birthday";

    private static final String CREATE_TABLE_BOOKS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS + "(" +
            TABLE_BOOKS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            TABLE_BOOKS_TITLE + " TEXT," +
            TABLE_BOOKS_AUTHOR + " TEXT," +
            TABLE_BOOKS_GENRE + " TEXT);";

    private static final String CREATE_TABLE_COMMENTS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_COMMENTS + "(" +
            TABLE_COMMENTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            TABLE_COMMENTS_BOOKID + " INTEGER," +
            TABLE_COMMENTS_PAGE + " INTEGER," +
            TABLE_COMMENTS_COMMENT + " TEXT," +
            TABLE_COMMENTS_DATE + " TEXT);";

    private static final String CREATE_TABLE_AUTHORS =
            "CREATE TABLE IF NOT EXISTS " + TABLE_AUTHORS + "(" +
                    TABLE_AUTHORS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    TABLE_AUTHORS_NAME + " TEXT," +
                    TABLE_AUTHORS_BIRTHDAY + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            // Create all table
            db.execSQL(CREATE_TABLE_BOOKS);
            db.execSQL(CREATE_TABLE_COMMENTS);

            String strInsertBooks = "INSERT INTO " + TABLE_BOOKS + " (" + TABLE_BOOKS_ID + ", " + TABLE_BOOKS_TITLE + ", " + TABLE_BOOKS_AUTHOR + ", " + TABLE_BOOKS_GENRE + ") VALUES";

            db.execSQL(strInsertBooks + "(" + 1 + ",'Les fleurs du mal','Charles Baudelaire','Poèmes');");
            db.execSQL(strInsertBooks + "(" + 2 + ",'Germinal','Emile Zola','Roman');");
            db.execSQL(strInsertBooks + "(" + 3 + ",'Les misérables','Victor Hugo','Roman');");
            db.execSQL(strInsertBooks + "(" + 4 + ",'1984','George Orwell','Science-Fiction');");
            db.execSQL(strInsertBooks + "(" + 5 + ",'Le Meilleur des mondes','Aldous Huxley','Science-Fiction');");
            db.execSQL(strInsertBooks + "(" + 6 + ",'Vingt mille lieues sous les mers','Jules Verne','Aventure');");
            db.execSQL(strInsertBooks + "(" + 7 + ",'Les Trois Mousquetaires','Alexandre Dumas','Aventure');");

            String strInsertComments = "INSERT INTO " + TABLE_COMMENTS + " (" + TABLE_COMMENTS_ID + ", " + TABLE_COMMENTS_BOOKID + ", " + TABLE_COMMENTS_PAGE + ", " + TABLE_COMMENTS_COMMENT + TABLE_COMMENTS_DATE + ") VALUES";

            db.execSQL(strInsertComments + "(" + 1 + "," + 1 + "," + 1 + "," + "'commentaire'" + "'30/01/2010'" + ");");
        }
        catch (Exception e) {
            Log.w("Table creations", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion <= this.DATABASE_VERSION) {
            try {
                // add author ID
                String alterBook = "ALTER TABLE " + TABLE_BOOKS + " ADD COLUMN " + TABLE_BOOKS_AUTHORID + "INTEGER;";

                // add author table
                db.execSQL(CREATE_TABLE_AUTHORS);

                // insert authors
                String strInsertAuthors = "INSERT INTO " + TABLE_AUTHORS + " (" + TABLE_AUTHORS_ID + ", " + TABLE_AUTHORS_NAME + ", " + TABLE_AUTHORS_BIRTHDAY + ") VALUES ";
                db.execSQL(strInsertAuthors + "(" + 1 + ",'Charles Baudelaire','1900');");
                db.execSQL(strInsertAuthors + "(" + 2 + ",'Emile Zola','1897');");
                db.execSQL(strInsertAuthors + "(" + 3 + ",'Victor Hugo','1794');");
                db.execSQL(strInsertAuthors + "(" + 4 + ",'George Orwell','1984');");
                db.execSQL(strInsertAuthors + "(" + 5 + ",'Aldous Huxley','1431');");
                db.execSQL(strInsertAuthors + "(" + 6 + ",'Jules Verne','2010');");
                db.execSQL(strInsertAuthors + "(" + 7 + ",'Alexandre Dumas','1842');");
            }
            catch (Exception e) {
                Log.w("Table creations", e.toString());
            }
        }
    }

    /**
     * Add comment on book
     * @param db database
     * @param comment comment of the book
     */
    public void addComment(SQLiteDatabase db, Comment comment) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_COMMENTS_BOOKID, comment.getBookId());
        contentValues.put(TABLE_COMMENTS_PAGE, comment.getPage());
        contentValues.put(TABLE_COMMENTS_COMMENT, comment.getComment());
        contentValues.put(TABLE_COMMENTS_DATE, comment.getDate());
        long inserted = db.insert(TABLE_COMMENTS, null, contentValues);
    }

    /**
     * get all comments
     * @param db database
     * @return all comments
     */
    public ArrayList<Comment> getComments(SQLiteDatabase db){
        ArrayList<Comment> result = new ArrayList<Comment>();

        Cursor cursor = db.query(
                "Comments",
                new String[]{"id", "bookId", "page", "comment", "date"},
                "",
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            int bookId = cursor.getInt(1);
            int page = cursor.getInt(2);
            String comment = cursor.getString(3);
            String date = cursor.getString(4);

            Comment c = new Comment(id, bookId, page, comment, date);
            Book b = getBook(db, bookId);
            c.setBookTitle(b.getTitle().toString());
            result.add(c);
        }
        return result;
    }

    /**
     * get all books
     * @param db database
     * @return all books
     */
    public ArrayList<Book> getBooks(SQLiteDatabase db){
        ArrayList<Book> result = new ArrayList<Book>();

        Cursor cursor = db.query(
                "Books",
                new String[]{"id", "title", "author", "genre"},
                "",
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String author = cursor.getString(2);
            String genre = cursor.getString(3);
            result.add(new Book(id, title, author, genre));
        }

        return result;
    }

    /**
     * Get a book
     * @param db database
     * @param bookId book
     * @return a book
     */
    public Book getBook(SQLiteDatabase db, long bookId){
        Book result = new Book();

        Cursor cursor = db.query(
                "Books",
                new String[]{"id", "title", "author", "genre"},
                "id = ?",
                new String[]{String.valueOf(bookId)},
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String author = cursor.getString(2);
            String genre = cursor.getString(3);

            result = new Book(id, title, author, genre);
        }

        return result;
    }


    /***
     * get the average of comments by book.
     * @param db database
     * @return the average
     */
    public double getAverageCommentsByBook(SQLiteDatabase db){
        ArrayList<Book> listBook = this.getBooks(db);
        ArrayList<Comment> listComment = this.getComments(db);

        int nbC = listComment.size();
        int nbB = listBook.size();
        double average = nbC * 100 / nbB;
        return average;
    }
}