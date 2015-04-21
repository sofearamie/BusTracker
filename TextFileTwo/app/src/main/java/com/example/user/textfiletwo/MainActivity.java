package com.example.user.textfiletwo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    EditText fname,fcontent,fnameread;
    Button write,read;
    TextView filecon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fcontent = (EditText)findViewById(R.id.ftext);
        write = (Button)findViewById(R.id.btnwrite);
        read = (Button)findViewById(R.id.btnread);
        filecon = (TextView)findViewById(R.id.filecon);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String filename = fname.getText().toString();
                String filecontent = fcontent.getText().toString();
                FileOperations fop = new FileOperations();
                fop.write(filename, filecontent);
                if(fop.write(filename, filecontent)){
                    Toast.makeText(getApplicationContext(), filename + "New file saved!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "I/O error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String readfilename = fnameread.getText().toString();
                FileOperations fop = new FileOperations();
                String text = fop.read(readfilename);
                if(text != null){
                    filecon.setText(text);
                }
                else {
                    Toast.makeText(getApplicationContext(), "File not Found", Toast.LENGTH_SHORT).show();
                    filecon.setText(null);
                }
            }
        });
    }


    @Override
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
}
