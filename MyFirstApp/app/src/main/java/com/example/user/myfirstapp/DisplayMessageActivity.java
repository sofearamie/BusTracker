package com.example.user.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;

import com.example.tengchinsheng.first33.R;
import com.example.user.myfirstapp.DisplayMessageActivity;
import com.example.user.myfirstapp.MyActivity;


public class DisplayMessageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //add TextView as the root view of the activity's layout by passing it to SetContentView()


        Intent intent = getIntent();
        String message = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);

        TextView textView = new TextView(this); //create a TextView object.
        textView.setTextSize(40);
        textView.setText(message); //Set the text size and message

        setContentView(textView);
        //Extract the message delivered by MyActivity with this line above


      // setContentView(R.layout.activity_display_message);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*When the user presses one of the action buttons or
        another item in the action overflow, the system calls this method*/

        /* Handle action bar item clicks here. The action bar will
        automatically handle clicks on the Home/Up button, so long
        as you specify a parent activity in AndroidManifest.xml.*/

        /*call getItemId() on the given MenuItem to determine
        which item was pressed
        -->the return ID matches the value you declared in the corresponding
        <item> element's android:id attribute*/

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);


/*        switch (item.getItemId()){
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

}