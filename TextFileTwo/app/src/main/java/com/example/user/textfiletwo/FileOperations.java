package com.example.user.textfiletwo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {
    public FileOperations(){}

    public Boolean write (String filename, String filecontent) {
        try{
            String filepath = "/sdcard/mytextfile.txt";
            File myfile = new File(filepath);

            //if file does not exists, then create it
            if (!myfile.exists()) {
                myfile.createNewFile();
            }

            FileWriter fw = new FileWriter(myfile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(filecontent);
            bw.close();
            Log.d("Success", "Success");
            return true;
         }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String read(String filename){
        BufferedReader br = null;
        String response = null;
        try{
            StringBuffer output = new StringBuffer();
            String fpath = "/sdcard/mytextfile.txt";
            br = new BufferedReader(new FileReader(fpath));
            String line = "";
            while ((line = br.readLine()) != null) {
                output.append(line +"\n");
            }
            response = output.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }
}
