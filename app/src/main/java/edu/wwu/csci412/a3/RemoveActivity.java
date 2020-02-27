package edu.wwu.csci412.a3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RemoveActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    public void updateView() {
        ArrayList<Friend> friends = dbManager.selectAll();
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup group = new RadioGroup(this);
        for (Friend friend : friends) {
            RadioButton rb = new RadioButton(this);
            rb.setId(friend.getId());
            rb.setText(friend.toString());
            group.addView(rb);
        }
        RadioButtonHandler rbh = new RadioButtonHandler();
        group.setOnCheckedChangeListener(rbh);

        //back button
        Button backButton = new Button(this);
        backButton.setText(R.string.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveActivity.this.finish();
            }
        });

        scrollView.addView(group);
        layout.addView(scrollView);

        //put the back button on the bottom of the screen
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0, 0, 0, 50);
        layout.addView(backButton, params);

        setContentView(layout);
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // delete friend from the database
            dbManager.deleteById(checkedId);
            Toast.makeText(RemoveActivity.this, "Friend removed", Toast.LENGTH_SHORT).show();

            updateView();
        }
    }
}
