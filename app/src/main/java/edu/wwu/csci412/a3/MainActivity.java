package edu.wwu.csci412.a3;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private String emailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DatabaseManager(this);
        emailInput = null;

        EditText searchBar = findViewById(R.id.emailBox);
        searchBar.setOnKeyListener(new SearchBarListener());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                // switch to add screen
                Intent addIntent = new Intent(this, AddActivity.class);
                this.startActivity(addIntent);
                return true;
            case R.id.action_remove:
                // switch to remove screen
                Intent removeIntent = new Intent(this, RemoveActivity.class);
                this.startActivity(removeIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class SearchBarListener implements View.OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent keyEvent) {
            if (keyCode == keyEvent.KEYCODE_ENTER) {
                EditText editText = (EditText) v;
                if (editText.getText() != null) {
                    emailInput = editText.getText().toString();
                    TextView display = (TextView) findViewById(R.id.displayInfo);

                    Friend friend = dbManager.selectByEmail(emailInput);
                    if (friend != null) {
                        // display first and last name
                        display.setText(friend.getFirstName() + " " + friend.getLastName());

                    } else {
                        // no friend found for specified email
                        display.setText(R.string.not_found);
                    }
                }
            }
            return true;
        }
    }
}
