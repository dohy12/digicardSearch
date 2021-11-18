package com.example.digicardlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    public void onClickBtn1(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickBtn2(View view){

    }

    public void onClickBtn3(View view){

    }

    public void onClickBtn4(View view){

    }

    public void onClickBtn5(View view){

    }
}