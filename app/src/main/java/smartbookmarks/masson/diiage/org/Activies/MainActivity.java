package smartbookmarks.masson.diiage.org.Activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import smartbookmarks.masson.diiage.org.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonShowComments;
    private Button buttonAddComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
