package smartbookmarks.masson.diiage.org.Activies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import smartbookmarks.masson.diiage.org.Adapter.CommentAdapter;
import smartbookmarks.masson.diiage.org.Database.CommentHelper;
import smartbookmarks.masson.diiage.org.Entities.Comment;
import smartbookmarks.masson.diiage.org.R;

public class CommentsActivity extends AppCompatActivity {

    private ListView listViewComments;
    private ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        CommentHelper helper = new CommentHelper(this);
        comments = helper.getAllCommentsWithBookName();

        try {
            listViewComments = (ListView) findViewById(R.id.lvComments);
            CommentAdapter commentAdapter = new CommentAdapter(comments, CommentsActivity.this);
            listViewComments.setAdapter(commentAdapter);
        } catch (Exception e) {
            Log.d("View", e.getMessage());
        }
    }
}
