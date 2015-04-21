package com.example.user.textfileapps;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends Activity {
    ArrayAdapter<String> m_adapter;
    ArrayList<String> m_listItems = new ArrayList<String>();
    /**
     * Called when the activity is first created.
     */

    Button bt;
    EditText et;
    TextView tv;
    ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bt = (Button) findViewById(R.id.button1);
        et = (EditText) findViewById(R.id.editText1);
        tv = (TextView) findViewById(R.id.textView1);
        lv = (ListView) findViewById(R.id.listView1);
        m_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, m_listItems);
        lv.setAdapter(m_adapter);
        final String input = et.getText().toString();

        bt.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                m_listItems.add(new String(input));
                m_adapter.notifyDataSetChanged();
            }

        });
    }
}