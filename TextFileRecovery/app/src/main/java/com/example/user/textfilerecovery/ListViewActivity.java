package com.example.user.textfilerecovery;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ListViewActivity extends Activity {
    ArrayAdapter<String> m_adapter;
    ArrayList<String> m_listItems = new ArrayList<>();
    Button bt;
    EditText et;
    TextView tv;
    ListView lv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("onCreate ListViewActivity");

        super.onCreate(savedInstanceState);
        System.out.println("created");

        Intent intent = getIntent();
        System.out.println("get intent");



        bt = (Button) findViewById(R.id.buttonSave);    //button Save from activity_main.xml
        System.out.println("INSIDE startActivity(intent);");

        et = (EditText) findViewById(R.id.editTextName);    //user input at Name field
        System.out.println(" et ");

        tv = (TextView) findViewById(R.id.textview1);   //where do i get this?
        System.out.println(" tv ");

        lv = (ListView) findViewById(R.id.listView1);   //listview
        System.out.println("lv");

        m_adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, m_listItems);
        System.out.println("m_adapter");

        lv.setAdapter(m_adapter);
        System.out.println("setAdapter");

        final String input = et.getText().toString();
        System.out.println("final String input = et.getText().toString();");

        System.out.println("initialised everything");

        bt.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                System.out.println("onclick bt;");

                // TODO Auto-generated method stub

                String input = et.getText().toString();
                if (null != input && input.length() > 0) {
                    m_listItems.add(input);
                    m_adapter.notifyDataSetChanged();
                }
            }
        });

        setContentView(R.layout.activity_list_view);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
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
}
