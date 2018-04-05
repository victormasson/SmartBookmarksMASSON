package smartbookmarks.masson.diiage.org.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import smartbookmarks.masson.diiage.org.Entities.Comment;
import smartbookmarks.masson.diiage.org.R;

public class CommentAdapter extends BaseAdapter {
    private ArrayList<Comment> listComment;
    private Activity activity;

    public CommentAdapter(ArrayList<Comment> listComment, Activity activity) {
        this.listComment = listComment;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listComment.size();
    }

    @Override
    public Object getItem(int i) {
        return listComment.get(i);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        // La vue qui est retournée
        View view;
        // Permet de mémoriser les calculs de findByIds
        CommentViewHolder commentViewHolder;

        // La vue est recyclée si le convertView est null
        if (convertView != null) {
            // On récupère la vue
            view = convertView;
            commentViewHolder = new CommentViewHolder();
        } else {
            // La vue est recylée
            LayoutInflater layoutInflater = this.activity.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.item_comment, parent, false);

            TextView labelComment = view.findViewById(R.id.txtComment);
            TextView labelBookTitle = view.findViewById(R.id.txtBookTitle);
            TextView labelPage = view.findViewById(R.id.txtPageNumber);
            TextView labelDate = view.findViewById(R.id.txtDateComment);

            commentViewHolder = new CommentViewHolder();
        }

        Comment comment = listComment.get(i);
        commentViewHolder.labelComment.setText(comment.getComment());
        commentViewHolder.labelBookTitle.setText(comment.getBoolTitle());
        commentViewHolder.labelPage.setText(comment.getPage());
        commentViewHolder.labelDate.setText(comment.getDate().toString());

        return view;
    }

    private static class CommentViewHolder {
        public TextView labelComment;
        public TextView labelBookTitle;
        public TextView labelPage;
        public TextView labelDate;

        public CommentViewHolder() {
        }

        public CommentViewHolder(TextView labelComment, TextView labelBookTitle, TextView labelPage, TextView labelDate) {
            this.labelComment = labelComment;
            this.labelBookTitle = labelBookTitle;
            this.labelPage = labelPage;
            this.labelDate = labelDate;
        }
    }
}