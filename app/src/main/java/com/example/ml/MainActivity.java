package com.example.ml;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ml.helper.ImagehelperActivity;
import com.example.ml.image.FlowerClassicationActivity;
import com.example.ml.image.ImageclassificationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onGotoimageActivity(View view) {
        //start a helper activity
        Intent intent = new Intent(this, ImageclassificationActivity.class);
        startActivity(intent);


    }

    public void onGotoflowerActivity(View view) {
        //start a helper activity
        Intent intent = new Intent(this, FlowerClassicationActivity.class);
        startActivity(intent);

    }
}