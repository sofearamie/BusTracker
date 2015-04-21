package com.example.tengchinsheng.bustracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {
    TextToSpeech tts;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.setLanguage(Locale.UK);
                    autoSpeakPage();

                }
            }
        });

        System.out.println("TTS Worked");



        Button next = (Button) findViewById(R.id.button);
        next.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                finish();
                Button b = (Button) v;
                b.getText();
                //do this to all activities later on
                String toSpeak = "You have pressed " + b.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                tts.speak(toSpeak, TextToSpeech.QUEUE_ADD, null);
                System.out.println(toSpeak);

                System.out.println("inside onLongClick");
                Intent intent = new Intent(MainActivity.this, LocationResult.class);
                startActivity(intent);
                return true;
            }
        });
        System.out.println("LongClickListener");

        next.setOnClickListener(new View.OnClickListener() {
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

        final RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.relativelayout1);
        rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
            }
        });



    }


    @Override
    public void onDestroy() {
        // Don't forget to shutdown!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public void autoSpeakPage(){
        tts.speak("You are on the first page. " +
                "Press... Locate... to locate your nearest bus stop",
                TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    //catches the onKeyDown button event
    public boolean onKeyDown (int keyCode, KeyEvent event){

        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                Toast.makeText(this, "Keycode Back Pressed",Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Press again back for exit",Toast.LENGTH_SHORT).show();
                counter++;
                if (counter> 1){
                    super.onBackPressed();  //calling the system's back pressed
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                Toast.makeText(this, "Keycode volume up pressed",Toast.LENGTH_SHORT).show();
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Toast.makeText(this, "Keycode volume down pressed",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //catches on KeyUp button event
    @Override
    public boolean onKeyUp (int keyCode, KeyEvent event){
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                Toast.makeText(this, "KEYCODE_BACK Pressed",Toast.LENGTH_SHORT).show();
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                Toast.makeText(this, "KEYCODE_VOLUME_UP Pressed",Toast.LENGTH_SHORT).show();
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                Toast.makeText(this, "KEYCODE_VOLUME_DOWN Pressed",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    	// works for API level 5 and lower
    //hehehe
	@Override
	public void onBackPressed() {
        Toast.makeText(this, "onBackPressed Pressed",Toast.LENGTH_SHORT).show();
        super.onBackPressed();
	}

    // catches the long press button event (longer than 2 seconds)
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Toast.makeText(this, "This key has been pressed for a long time", Toast.LENGTH_SHORT).show();
        return true;
    }
}

/*
  public void speakText(View view){
      String toSpeak = findViewById(R.id.button).toString();
      Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
      tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
  }
s


*/
/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*//*



*/
/*    @Override
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
/*






*/
