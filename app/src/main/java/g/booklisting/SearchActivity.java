package g.booklisting;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    private TextView noConnTextView;
    private String searchFor;
    private EditText searchField;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchField = (EditText) findViewById(R.id.search_field);
        searchButton = (Button) findViewById(R.id.search_button);
        noConnTextView = (TextView) findViewById(R.id.no_conn_view);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                getSystemService(Context.CONNECTIVITY_SERVICE);
                if (networkInfo != null && networkInfo.isConnected()) {
                    searchFor = searchField.getText().toString();
                    Log.d("Search","Search string is: " + searchFor);
                    saveAndRun();
                } else {
                    noConnTextView.setText(R.string.no_internet_connection);
                }
            }
        });
    }

    private void saveAndRun () {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("search_for", searchFor);
        startActivity(intent);
    }
}
