package com.r.androidlogfile;

import android.app.Application;
import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class App extends Application {

    public void onCreate() {
        super.onCreate();
        //Called when the application is starting, before any activity, service, or receiver objects (excluding content providers) have been created.
        writeLogs();
    }

    public void writeLogs() {
        if (isExternalStorageWritable()) {

            File sd = Environment.getExternalStorageDirectory();
            File logFile = new File(sd.getAbsolutePath(), "/" + "C3FieldLogFile");
            // create app folder
            if (!logFile.exists()) {
                logFile.mkdir();
            }
            File file = new File(logFile, "logcat_" + getCurrentDate() + ".txt");
            // clear the previous logcat and then write the new one to the file
            try {
                Process process = Runtime.getRuntime().exec("logcat -c");
                process = Runtime.getRuntime().exec("logcat -f " + file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (isExternalStorageReadable()) {
            // only readable
        } else {
            // not accessible
        }
    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    public String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd_MMM_yyyy");
        String formattedDate = df.format(c);
        return formattedDate;
    }
}