package g.booklisting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private static Context mContext;

    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
        mContext = context;
    }

    public static class BookView {
        TextView title;
        TextView author;


        public BookView (View view) {
            title = (TextView) view.findViewById(R.id.book_title);
            author = (TextView) view.findViewById(R.id.book_author);
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        BookView bookView;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_list, parent, false);
            bookView = new BookView(convertView);
            convertView.setTag(bookView);
        } else { bookView = (BookView) convertView.getTag(); }

        Book current = getItem(position);

        bookView.title.setText(current.getTitle());
        bookView.author.setText(current.getAuthor());
        return convertView;
    }

}
