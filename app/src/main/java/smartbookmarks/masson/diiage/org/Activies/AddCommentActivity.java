package smartbookmarks.masson.diiage.org.Activies;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import smartbookmarks.masson.diiage.org.Adapter.SpinnerBooksAdapter;
import smartbookmarks.masson.diiage.org.Database.BookHelper;
import smartbookmarks.masson.diiage.org.Database.CommentHelper;
import smartbookmarks.masson.diiage.org.Database.DatabaseHelper;
import smartbookmarks.masson.diiage.org.Entities.Book;
import smartbookmarks.masson.diiage.org.Entities.Comment;
import smartbookmarks.masson.diiage.org.R;

public class AddCommentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<Book> books;
    Book book;
    TextView txtPageNumber;
    TextView txtComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        txtPageNumber = findViewById(R.id.txtPageNumber);
        txtComment = findViewById(R.id.txtComment);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        BookHelper bookHelper = new BookHelper(db, this);
        final CommentHelper commentHelper = new CommentHelper(db, this);
        books =  bookHelper.getBooks();
        Spinner spinnerBook = (Spinner)findViewById(R.id.spnBooks);
        SpinnerBooksAdapter adapter = new SpinnerBooksAdapter(books, this);
        spinnerBook.setAdapter(adapter);
        spinnerBook.setOnItemSelectedListener(this);

        Button btnValidComment = findViewById(R.id.btnValidComment);
        btnValidComment.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                try {
                    int id = 1;
                    int idBook = book.getId();
                    String pageNumber = txtPageNumber.getText().toString();
                    int pageNumberInt = 0;
                    if (!pageNumber.equals("")) pageNumberInt = Integer.parseInt(pageNumber);
                    String commentaire = txtComment.getText().toString();

                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date dateNow = new Date();
                    String date = dateFormat.format(dateNow).toString();
                    Comment comment = new Comment(id, idBook, pageNumberInt, commentaire, date);
                    commentHelper.addComment(comment);
                    Intent intent = new Intent(AddCommentActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        book = (Book)parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
