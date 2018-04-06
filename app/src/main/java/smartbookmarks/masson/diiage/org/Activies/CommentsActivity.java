package smartbookmarks.masson.diiage.org.Activies;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import smartbookmarks.masson.diiage.org.Adapter.CommentAdapter;
import smartbookmarks.masson.diiage.org.Database.CommentHelper;
import smartbookmarks.masson.diiage.org.Database.DatabaseHelper;
import smartbookmarks.masson.diiage.org.Entities.Comment;
import smartbookmarks.masson.diiage.org.R;

public class CommentsActivity extends AppCompatActivity {
    CommentAdapter commentAdapter;
    private ListView listViewComments;
    private ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        comments = new ArrayList<Comment>();

        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        CommentHelper commentHelper = new CommentHelper(db, this);
        comments = commentHelper.getAllCommentsWithBookName();

        listViewComments = (ListView) findViewById(R.id.lvComments);
        CommentAdapter adapter = new CommentAdapter(comments, this);
        listViewComments.setAdapter(adapter);
    }
}
