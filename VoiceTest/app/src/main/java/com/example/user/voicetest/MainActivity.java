package com.example.user.voicetest;

import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private Button btnSpeak;
    private EditText txtText;


/*    //setup TTS
    public void onInit(int initStatus){
        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS){
            if (myTTS.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
                myTTS.setLanguage(Locale.US);
        }  else if (initStatus == TextToSpeech.ERROR){
                Toast.makeText(this, "Sorry!Text To Speech failed",Toast.LENGTH_LONG).show();}
    }*/



    @Override
    protected void onCreate(Bundle voiceinput) {
        super.onCreate(voiceinput);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this,this);
        btnSpeak = (Button) findViewById(R.id.btnSpeak);
        txtText = (EditText) findViewById(R.id.txtText);

        //button on click event
        btnSpeak.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View arg0){
                speakOut();
            }
        });
/*
        // check for TTS data
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);*/

    }

    @Override public void onInit(int status){
        if (status == TextToSpeech.SUCSESS){
            int result = tts.setLanguage((Locale.US);

            if(result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "This Language is not supported");
            } else {
                btnSpeak.setEnabled(true);
                speakOut();
            }
        } else { Log.e ("TTS", "Initialization Failed!"); }
    }

    // speak the user text
    public void speakWords(String speech) {

/*        // speak straight away
        myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);*/
    }


    public void onClick(View v) {
        speakWords("hello");
    }


/*    *//**
     * Handle the results from the recognition activity.
     *//*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MY_DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // the user has the necessary data - create the TTS
                myTTS = new TextToSpeech(getApplicationContext(), this);
            } else {
                // no data - install it now
                Intent installTTSIntent = new Intent();
                installTTSIntent
                        .setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }*/


}


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}*/
