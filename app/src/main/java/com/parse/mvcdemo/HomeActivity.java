package com.parse.mvcdemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class HomeActivity extends ListActivity {

    private ParseUser mCurrentUser;
    private List<ParseObject> mStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mCurrentUser = ParseUser.getCurrentUser();
        if (mCurrentUser != null) {
            ParseQuery<ParseObject> statusQueries = ParseQuery.getQuery("Status");
            statusQueries.orderByDescending("createdAt");
            statusQueries.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> scoreList, ParseException e) {
                    if (e == null) {
                        mStatus = scoreList;
                        StatusAdapter adapter = new StatusAdapter(HomeActivity.this, R.layout.statuslistrow, scoreList);
                        setListAdapter(adapter);
                    } else {

                    }
                }
            });
        } else {
            Intent sucessIntent = new Intent(HomeActivity.this, LoginActivity.class);
            HomeActivity.this.startActivity(sucessIntent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
            Intent sucessIntent = new Intent(HomeActivity.this, UpdateActivity.class);
            HomeActivity.this.startActivity(sucessIntent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            finish();
            return true;
        }
        if (id == R.id.action_logout) {
            ParseUser.logOutInBackground();
            mCurrentUser = ParseUser.getCurrentUser();
            Intent sucessIntent = new Intent(HomeActivity.this, LoginActivity.class);
            HomeActivity.this.startActivity(sucessIntent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ParseObject statusObject = mStatus.get(position);
        String objectId = statusObject.getObjectId();
        Toast.makeText(this, objectId, Toast.LENGTH_LONG).show();
    }
}
