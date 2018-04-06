package smartbookmarks.masson.diiage.org.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import smartbookmarks.masson.diiage.org.Entities.Comment;

public class CommentHelper {
    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;

    public CommentHelper(SQLiteDatabase db, Context context) {
        //On crée la BDD et sa table
        this.db = db;
        this.databaseHelper = new DatabaseHelper(context);
    }

    public void addComment(Comment comment) {
        DatabaseHelper databaseHel;
        ContentValues contentValues = new ContentValues();
        contentValues.put(databaseHelper.TABLE_COMMENTS_BOOKID, comment.getBookId());
        contentValues.put(databaseHelper.TABLE_COMMENTS_PAGE, comment.getPage());
        contentValues.put(databaseHelper.TABLE_COMMENTS_COMMENT, comment.getComment());
        contentValues.put(databaseHelper.TABLE_COMMENTS_DATE, comment.getDate());
        long inserted = db.insert(databaseHelper.TABLE_COMMENTS, null, contentValues);
    }

    public ArrayList<Comment> getAllCommentsWithBookName() {
        try {
            ArrayList<Comment> comments = new ArrayList<>();

//            String strQuery = "select " +
//                    " c." + databaseHelper.TABLE_COMMENTS_ID +
//                    " c." + databaseHelper.TABLE_COMMENTS_COMMENT +
//                    " c." + databaseHelper.TABLE_COMMENTS_PAGE +
//                    " c." + databaseHelper.TABLE_COMMENTS_DATE +
//                    " c." + databaseHelper.TABLE_COMMENTS_BOOKID +
//                    " b." + databaseHelper.TABLE_BOOKS_TITLE +
//                    " from " + databaseHelper.TABLE_COMMENTS + " c" +
//                    " INNER JOIN " + databaseHelper.TABLE_BOOKS + " b ON " +
//                    " b." + databaseHelper.TABLE_COMMENTS_BOOKID + " = " + " c." + databaseHelper.TABLE_BOOKS_ID;
//
//            Cursor cursor = db.rawQuery(strQuery, new String[] {""});

            Cursor cursor = db.query(databaseHelper.TABLE_COMMENTS,
                    new String[]{
                            databaseHelper.TABLE_COMMENTS_ID,
                            databaseHelper.TABLE_COMMENTS_BOOKID,
                            databaseHelper.TABLE_COMMENTS_COMMENT,
                            databaseHelper.TABLE_COMMENTS_DATE,
                            databaseHelper.TABLE_COMMENTS_PAGE},
                    null,
                    null,
                    null,
                    null,
                    null);

            while (cursor.moveToNext()) {
                Comment comment = setComment(cursor);
                comments.add(comment);
            }

            cursor.close();

            return comments;
        } catch (Exception e) {
            Log.w("Table creations", e.toString());
            return null;
        }
    }

    public ArrayList<Comment> getByBookId(int bookId) {
        try {
            ArrayList<Comment> comments = new ArrayList<>();
            Cursor cursor = db.query(databaseHelper.TABLE_COMMENTS,
                    new String[]{
                            databaseHelper.TABLE_COMMENTS_ID,
                            databaseHelper.TABLE_COMMENTS_BOOKID,
                            databaseHelper.TABLE_COMMENTS_COMMENT,
                            databaseHelper.TABLE_COMMENTS_DATE,
                            databaseHelper.TABLE_COMMENTS_PAGE},
                    "bookId LIKE ?",
                    new String[]{String.valueOf(bookId)},
                    null,
                    null,
                    databaseHelper.TABLE_COMMENTS_DATE +" DESC",
                    null);

            int nbItems = cursor.getCount();

            while (cursor.moveToNext()) {
                Comment comment = setComment(cursor);
                comments.add(comment);
            }

            cursor.close();

            return comments;
        } catch (Exception e) {
            Log.w("Table creations", e.toString());
            return null;
        }
    }

    public Comment setComment(Cursor cursor) {
        if (cursor.getCount() == 0)
            return null;

        //On créé un livre
        Comment comment = new Comment();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        comment.setId(cursor.getInt(0));
        comment.setBookId(cursor.getInt(1));
        comment.setComment(cursor.getString(2));
        comment.setDate(cursor.getString(3));
        comment.setPage(cursor.getInt(4));

        return comment;
    }
}
