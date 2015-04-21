package com.example.tengchinsheng.bustracker;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class OnBus extends ActionBarActivity {
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_bus);

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


        final RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.relativelayout6);
        rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = "You are arriving in " + "blablabla" + "minutes";
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                System.out.println(toSpeak);

                /*Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);*/
            }
        });

        final TextView tv1 = (TextView) findViewById(R.id.textView4);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = "You are arriving in " + "***" + "minutes";
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                System.out.println(toSpeak);
            }
        });
        System.out.println("ClickListener: TextView");

        final TextView tv2 = (TextView) findViewById(R.id.textView6);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = "You are arriving in " + "***" + "minutes";
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                System.out.println(toSpeak);
            }
        });
        System.out.println("ClickListener: TextView");

        final TextView tv3 = (TextView) findViewById(R.id.textView7);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = "You are arriving in " + "***" + " minutes";
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
                System.out.println(toSpeak);
            }
        });
        System.out.println("ClickListener: TextView");

        /*offbus Button Long Click & Click Listener*/
        Button offbus = (Button) findViewById(R.id.button9);
        offbus.setOnClickListener(new View.OnClickListener() {
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
        System.out.println("ClickListener: Repeat Button");

        offbus.setOnLongClickListener(new View.OnLongClickListener() {
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

                System.out.println("inside onLongClick");
                Intent intent = new Intent(OnBus.this, FavouriteTrip.class);
                startActivity(intent);
                return true;
            }
});
        System.out.println("LongClickListener: Return Button");
    }

    public void autoSpeakPage(){
        tts.speak("This page will estimate the distance of your location to your destination.",
                TextToSpeech.QUEUE_FLUSH, null);
    }
}


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_on_bus, menu);
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
    }*/



