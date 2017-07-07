package g.booklisting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    private String searchFor;
    private EditText searchField;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchField = (EditText) findViewById(R.id.search_field);
        searchButton = (Button) findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchFor = searchField.getText().toString();
                Log.d("Search","Search string is: " + searchFor);
                saveAndRun();
            }
        });
    }

    private void saveAndRun () {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("search_for", searchFor);
        startActivity(intent);
    }
}
