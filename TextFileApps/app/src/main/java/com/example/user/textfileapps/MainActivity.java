package com.example.user.textfileapps;

import com.example.user.textfileapps.R;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;



public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.example.user.textfileapps.MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //onClick for Save Button
    public void SaveText(View view) {

        System.out.println("button clicked");
        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        String messageName = editTextName.getText().toString();

        EditText editTextAge = (EditText) findViewById(R.id.editTextAge);
        String messageAge = editTextAge.getText().toString();

        EditText editTextHP = (EditText) findViewById(R.id.editTextPhone);
        String messageHP = editTextHP.getText().toString();


        if ((isAlphabets(messageName)) && (isNumber(messageAge) && (isNumber(messageHP)))) {
            System.out.println("passed by alphabets and isnumber checking");
            try {
                System.out.println("i'm inside try liao");

                //open myfile.txt for writing
                OutputStreamWriter out = new OutputStreamWriter(openFileOutput("textfile.txt", MODE_PRIVATE));
                System.out.println("open textfile.txt for writing");


                //write the contents on mytext to the file
                out.write("Name: " + messageName + "\n");
                out.write("Age: " + messageAge + "\n");
                out.write("Phone: " + messageHP + "\n");
                out.write("\n");

                System.out.println("I'm here at out.write(mytext)");

                Toast.makeText(getApplicationContext(), "File saved. You may READ the file now", Toast.LENGTH_SHORT).show();

                //close the file
                out.close();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "I/O error", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "INVALID", Toast.LENGTH_SHORT).show();

        }
    }




    //onClick for Read Button
    public void ReadText (View view) {
        Intent intent = new Intent(this, ReadActivity.class);



       try {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(
                    openFileInput("textfile.txt")));

            String inputString;
            StringBuffer stringBuffer = new StringBuffer();

            while ((inputString = inputReader.readLine()) != null) {
                stringBuffer.append(inputString + "\n");
            }

            System.out.println("while stringbuffer liao");

       /*     displaytext.setText(stringBuffer.toString());*/


            String message = stringBuffer.toString();
            System.out.println("INSIDE String message = stringBuffer.toString();");

            intent.putExtra(EXTRA_MESSAGE, message);
            //param1 - the name of the value you are passing so you can also use that name to retrieve it
            //param2 - VALUE you want to pass
            System.out.println("INSIDE  intent.putExtra(EXTRA_MESSAGE, message);");

            startActivity(intent);
            System.out.println("INSIDE startActivity(intent);");
       } catch (IOException e) {
            e.printStackTrace();
       }
    }

/*    public boolean isValid (String textValid) {
        return (!isAlphabets(textValid) || isNumber(textValid));
    }*/

    private boolean isNumber(String checkNum) {
        //return (checkNum.matches("//d+"));
        return checkNum.matches ("[0-9]+");
    }

    public boolean isAlphabets (String checkText){
/*        isEmpty(checkText);
        char[] chars = checkText.toCharArray();

        for (char c: chars){
            if(!Character.isLetter(c)){
                return false;
            }
        }
        return true;*/
        return (checkText.matches ("[a-zA-Z]+"));
    }

/*    public boolean isEmpty (String passage) {

        if (passage == null) {
            Toast.makeText(getApplicationContext(), "Do not leave empty field", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;*/

        /* Scanner scanner = new Scanner(System.in);
        int n = 0;
        while (!scanner.hasNext()){
                n++;
                scanner.next();
            }
        if (n>=1)
            return false;
        return true;*/


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

/*-------------------NOTES-----------------------------------
String MY_FILE_NAME = “mytextfile.txt”;

// Create a new output file stream
FileOutputStream fileos = openFileOutput(MY_FILE_NAME, Context.MODE_PRIVATE);

// Create a new file input stream
FileInputStream fileis = openFileInput(My_FILE_NAME);
------------------------------------------------------------*/