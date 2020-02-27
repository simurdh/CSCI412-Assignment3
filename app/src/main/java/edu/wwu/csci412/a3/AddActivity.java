package edu.wwu.csci412.a3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_add);

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(v);
            }
        });
    }

    public void insert(View v) {
        // get input from user
        EditText firstNameText = (EditText) findViewById(R.id.firstName);
        EditText lastNameText = (EditText) findViewById(R.id.lastName);
        EditText emailText = (EditText) findViewById(R.id.email);

        String firstName = firstNameText.getText().toString();
        String lastName = lastNameText.getText().toString();
        String email = emailText.getText().toString();

        // insert friend into the database
        Friend friend = new Friend(null, firstName, lastName, email);
        dbManager.insert(friend);

        //clear data
        firstNameText.setText("");
        lastNameText.setText("");
        emailText.setText("");
    }

    public void goBack(View v) {
        this.finish();
    }
}
