package smartbookmarks.masson.diiage.org.Database;

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

    public CommentHelper(Context context) {
        //On crée la BDD et sa table
        databaseHelper = new DatabaseHelper(context);
    }

    public void initDb(){
        db = databaseHelper.getWritableDatabase();
    }

    public ArrayList<Comment> getAllCommentsWithBookName() {
        try {
            initDb();
            ArrayList<Comment> comments = new ArrayList<>();
//        String strQuery = "select " +
//                " c." + databaseHelper.TABLE_COMMENTS_ID +
//                " c." + databaseHelper.TABLE_COMMENTS_COMMENT +
//                " c." + databaseHelper.TABLE_COMMENTS_PAGE +
//                " c." + databaseHelper.TABLE_COMMENTS_DATE +
//                " b." + databaseHelper.TABLE_BOOKS_TITLE +
//                " from " + databaseHelper.TABLE_COMMENTS + " c" +
//                " INNER JOIN " + databaseHelper.TABLE_BOOKS + " b ON " +
//                " b." + databaseHelper.TABLE_COMMENTS_BOOKID + " = " + " c." + databaseHelper.TABLE_BOOKS_ID
//                ;
//
//        Cursor cursor = db.rawQuery(strQuery, null);

//            String strQuery = "select " +
//                " c." + databaseHelper.TABLE_COMMENTS_ID +
//                " c." + databaseHelper.TABLE_COMMENTS_COMMENT +
//                " c." + databaseHelper.TABLE_COMMENTS_PAGE +
//                " c." + databaseHelper.TABLE_COMMENTS_DATE +
//                " from " + databaseHelper.TABLE_COMMENTS + " c";
//
//        Cursor cursor = db.rawQuery(strQuery, null);

            Cursor cursor = db.query(databaseHelper.TABLE_COMMENTS,
                    new String[]{databaseHelper.TABLE_COMMENTS_ID,databaseHelper.TABLE_COMMENTS_BOOKID,databaseHelper.TABLE_COMMENTS_PAGE,databaseHelper.TABLE_COMMENTS_COMMENT},
                    null,
                    null,
                    null,
                    null,
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
        comment.setBookId(cursor.getInt(0));
        comment.setBookId(cursor.getInt(1));
        comment.setPage(cursor.getInt(2));
        comment.setComment(cursor.getString(3));
//        comment.setDate(Date.valueOf(cursor.getString(3)));
//        comment.setBookTitle(cursor.getString(4));

        return comment;
    }
}
