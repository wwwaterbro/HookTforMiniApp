package com.example.wanghaipei.hooktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View v){
        Toast.makeText(MainActivity.this, toastMessage(), Toast.LENGTH_SHORT).show();
    }

    public String toastMessage(){
        return "there";
    }
}
