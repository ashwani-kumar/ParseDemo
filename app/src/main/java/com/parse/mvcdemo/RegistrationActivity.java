package com.parse.mvcdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class RegistrationActivity extends ActionBarActivity {

    protected EditText mUserNameTextView;
    protected EditText mEmailTextView;
    protected EditText mPasswordTextView;
    protected Button mRegisterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mUserNameTextView = (EditText) findViewById(R.id.editText3);
        mEmailTextView = (EditText) findViewById(R.id.editText);
        mPasswordTextView = (EditText) findViewById(R.id.editText2);
        mRegisterButton = (Button) findViewById(R.id.button);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    private void registerUser() {
        ParseUser user = new ParseUser();
        user.setUsername(mUserNameTextView.getText().toString().trim());
        user.setPassword(mPasswordTextView.getText().toString().trim());
        user.setEmail(mEmailTextView.getText().toString().trim());

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Intent sucessIntent = new Intent(RegistrationActivity.this, HomeActivity.class);
                    RegistrationActivity.this.startActivity(sucessIntent);
                    RegistrationActivity.this.finish();
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
