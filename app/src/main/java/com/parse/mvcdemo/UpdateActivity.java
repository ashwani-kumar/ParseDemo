package com.parse.mvcdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by Ashwani on 25-07-2015.
 */
public class UpdateActivity extends Activity {

    private EditText mStatusEditView;
    private Button mUpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mStatusEditView = (EditText) findViewById(R.id.updateStatusEt);
        mUpdateButton = (Button) findViewById(R.id.updateStatus);
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String statusMessage = mStatusEditView.getText().toString();
                if (statusMessage != null && !statusMessage.isEmpty()) {
                    ParseObject statusObject = new ParseObject("Status");
                    statusObject.put("newstatus", statusMessage);
                    statusObject.put("user", ParseUser.getCurrentUser().getUsername());
                    statusObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Intent sucessIntent = new Intent(UpdateActivity.this, HomeActivity.class);
                                UpdateActivity.this.startActivity(sucessIntent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else {
                                Toast.makeText(UpdateActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
                finish();
            }
        });
    }
}
