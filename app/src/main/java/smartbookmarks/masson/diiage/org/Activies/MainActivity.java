package smartbookmarks.masson.diiage.org.Activies;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import smartbookmarks.masson.diiage.org.Database.DatabaseHelper;
import smartbookmarks.masson.diiage.org.Entities.Book;
import smartbookmarks.masson.diiage.org.Entities.Comment;
import smartbookmarks.masson.diiage.org.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonShowComments;
    private Button buttonAddComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        buttonShowComments = (Button)findViewById(R.id.btnComments);
        buttonAddComment = (Button)findViewById(R.id.btnAddComment);

        buttonShowComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
                startActivity(intent);
            }
        });

        buttonAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCommentActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<Comment> comments = helper.getComments(db);
        ArrayList<Book> books = helper.getBooks(db);
        double commentsByBook = helper.getAverageCommentsByBook(db);

        TextView txtStats = findViewById(R.id.txtStats);
        txtStats.setText("Books: " + books.size()
                + "\nComment: " + comments.size()
                + "\nAverage: " + commentsByBook + "%");
    }
}
