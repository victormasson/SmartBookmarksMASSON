package smartbookmarks.masson.diiage.org.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SmartBooksmarks.db";

    public static final String TABLE_BOOKS = "Books";
    public static final String TABLE_BOOKS_ID = "id";
    public static final String TABLE_BOOKS_TITLE = "title";
    public static final String TABLE_BOOKS_AUTHOR = "author";
    public static final String TABLE_BOOKS_GENRE = "genre";

    public static final String TABLE_COMMENTS = "Comments";
    public static final String TABLE_COMMENTS_ID = "id";
    public static final String TABLE_COMMENTS_BOOKID = "bookId";
    public static final String TABLE_COMMENTS_PAGE = "page";
    public static final String TABLE_COMMENTS_COMMENT = "comment";
    public static final String TABLE_COMMENTS_DATE = "date";

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
            TABLE_COMMENTS_DATE + " DATETIME);";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
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
        }
        catch (Exception e) {
            Log.w("Table creations", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Books;");
        db.execSQL("DROP TABLE Comments;");
        onCreate(db);
    }
}