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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class NavigatingPage extends ActionBarActivity {
    ListView listView;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigating_page);

        listView = (ListView) findViewById(R.id.listView);
        String[] list = {"10m: Turn left", "Walk to your North", "15m: Turn right"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_bus_stop, R.id.busList, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListClickHandler());
        listView.setOnItemLongClickListener(new ListLongClickHandler());

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

        final TextView tv = (TextView) findViewById(R.id.textView2);
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

        /*Reached Button Long Click & Click Listener*/
        Button reachedbtn = (Button) findViewById(R.id.button6);
        reachedbtn.setOnClickListener(new View.OnClickListener() {
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

        reachedbtn.setOnLongClickListener(new View.OnLongClickListener() {
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
                Intent intent = new Intent(NavigatingPage.this, DisplayTimetable.class);
                startActivity(intent);
                return true;
            }
        });


        final RelativeLayout rlayout = (RelativeLayout) findViewById(R.id.relativelayout4);
        rlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vb.vibrate(100);
            }
        });

    }

    public class ListClickHandler implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            TextView listText = (TextView) view.findViewById(R.id.busList);

            /*read the top navigation*/
            String toSpeak = listText.getText().toString();
            tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            System.out.println(toSpeak);

        }
    }

    public class ListLongClickHandler implements AdapterView.OnItemLongClickListener{
        @Override
        public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long arg3){
            finish();
            TextView listText = (TextView) view.findViewById(R.id.busList);
            String text = listText.getText().toString();
            Intent intent = new Intent(NavigatingPage.this, DisplayTimetable.class);
            intent.putExtra("selected-item", text);
            startActivity(intent);
            return true;
        }
    }

    public void autoSpeakPage(){
        tts.speak("You are on page number 4.... " +
                "Touch the screen to repeat the navigation assist...and.... " +
                "Press... reached...once you have reached the bus stop",
                TextToSpeech.QUEUE_FLUSH, null);
    }
}

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_navigating_page, menu);
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



