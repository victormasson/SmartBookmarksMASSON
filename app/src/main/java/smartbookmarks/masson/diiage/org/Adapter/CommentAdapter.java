package smartbookmarks.masson.diiage.org.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import smartbookmarks.masson.diiage.org.Entities.Comment;
import smartbookmarks.masson.diiage.org.R;

public class CommentAdapter extends BaseAdapter {
    private ArrayList<Comment> listComment;
    private Activity context;
    private LayoutInflater layoutInflater;

    public CommentAdapter(ArrayList<Comment> listComment, Activity context) {
        this.listComment = listComment;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listComment.size();
    }

    @Override
    public Comment getItem(int i) {
        return listComment.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        // La vue qui est retournée
        View view = convertView;
        // Permet de mémoriser les calculs de findByIds
        CommentViewHolder commentViewHolder;

        // La vue est recyclée si le convertView est null
        if (convertView != null) {
            // On récupère la vue
            commentViewHolder = (CommentViewHolder)view.getTag();
        } else {
            view = layoutInflater.inflate(R.layout.item_comment, null);
            commentViewHolder = new CommentViewHolder((TextView) view.findViewById(R.id.txtComment),
                    (TextView) view.findViewById(R.id.txtBookTitle),
                    (TextView) view.findViewById(R.id.txtPageNumber),
                    (TextView) view.findViewById(R.id.txtDateComment));
            view.setTag(commentViewHolder);
        }

        Comment comment = getItem(i);
        commentViewHolder.labelComment.setText(comment.getComment());
        commentViewHolder.labelBookTitle.setText(comment.getBookTitle());
        commentViewHolder.labelPage.setText(String.valueOf(comment.getPage()));
        commentViewHolder.labelDate.setText(comment.getDate());

        return view;
    }

    private class CommentViewHolder {
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