package com.czp.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.czp.annotation.MyAnnotation;

@MyAnnotation
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}