package com.example.tengchinsheng.bustracker;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class NavigationPage extends ActionBarActivity {
    TextView textView;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page);
        textView = (TextView) findViewById(R.id.textView);
        // get the intent from which this activity is called.
        Intent intent = getIntent();

        // fetch value from key-value pair and make it visible on TextView.
        String item = intent.getStringExtra("selected-item");
        textView.setText("You selected " + item);

        System.out.println("Content View Set");
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.UK);
                    autoSpeakPage();
                }
            }
        });
        System.out.println("TTS Worked: Check TTS");

        /*Skip Navigation Button Long Click & Click Listener*/
        Button skip = (Button) findViewById(R.id.button4);
        skip.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish();
                Button b = (Button) v;
                b.getText();
                //do this to all activities later on
                String toSpeak = "You have pressed " + b.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                System.out.println(toSpeak);

                finish();
                System.out.println("inside onLongClick");
                Intent intent = new Intent(NavigationPage.this, DisplayTimetable.class);
                startActivity(intent);
                return true;
            }
        });
        System.out.println("LongClickListener: Return Button");


        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                b.getText();
                String toSpeak = b.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                System.out.println(toSpeak);
            }
        });
        System.out.println("ClickListener: Return Button");

        /*Navigate Button Long Click & Click Listener*/
        Button navigatebtn = (Button) findViewById(R.id.button3);
        navigatebtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish();
                Button b = (Button) v;
                b.getText();
                //do this to all activities later on
                String toSpeak = "You have pressed " + b.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                System.out.println(toSpeak);

                Intent intent = new Intent(NavigationPage.this, NavigatingPage.class);
                startActivity(intent);
                return true;
            }
        });
        System.out.println("LongClickListener: Return Button");

        navigatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                b.getText();
                String toSpeak = b.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                System.out.println(toSpeak);
            }
        });
        System.out.println("ClickListener: Return Button");

        final TextView tv = (TextView) findViewById(R.id.textView);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = tv.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                System.out.println(toSpeak);
            }
        });
        System.out.println("ClickListener: TextView");


        final RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.relativelayout3);
        rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
            }
        });

    }


    public void autoSpeakPage() {
        tts.speak("You are on the third page... Press... Navigate now... to start navigating... or..." +
                "Press... Skip... to skip the navigation and proceed to bus departure times",
                TextToSpeech.QUEUE_FLUSH, null);
    }

}

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigation_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

