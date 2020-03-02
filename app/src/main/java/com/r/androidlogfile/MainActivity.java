package com.r.androidlogfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (int i = 0; i < 10; i++) {
            Log.e("Loop is=>", "" + i);
        }
        ((App) getApplication()).writeLogs();
    }
}
