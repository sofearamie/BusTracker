package com.example.user.textfilerecovery;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.TextView;


public class ReadActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //add TextView as the root view of the activity's layout by passing it to SetContentView()


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = new TextView(this); //create a TextView object.
        textView.setTextSize(30);
        textView.setText(message); //Set the text size and message

        setContentView(textView);
    }
}
