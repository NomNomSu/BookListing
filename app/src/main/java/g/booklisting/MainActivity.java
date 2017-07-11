package g.booklisting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.app.LoaderManager;
import android.content.Loader;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    private TextView mEmptyStateTextView;
    private static final String BOOKAPI_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=";
    private BookAdapter bookAdapter;
    private static final int BOOK_LOADER_ID = 1;
    private String searchFor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        searchFor = extras.getString("search_for");
        Log.d("Extras data", searchFor);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        ListView bookListView = (ListView) findViewById(R.id.book_list);
        bookAdapter = new BookAdapter(this, new ArrayList<Book>());
        bookListView.setAdapter(bookAdapter);
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(mEmptyStateTextView);
    }


    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Log.d("Now searching for: ", BOOKAPI_REQUEST_URL + searchFor + "&limit=50");
        return new BooksLoader(this, BOOKAPI_REQUEST_URL + searchFor + "&limit=50");
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        mEmptyStateTextView.setText(R.string.no_books);
        bookAdapter.clear();
        if (books != null && !books.isEmpty()) {
            bookAdapter.addAll(books);
        }
    }
    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        bookAdapter.clear();
    }


}


