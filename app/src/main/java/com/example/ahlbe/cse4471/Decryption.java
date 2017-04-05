package com.example.ahlbe.cse4471;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Decryption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);
        final Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClick1(v);
            }
        });
        final Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClick2(v);
            }
        });
        final Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClick3(v);
            }
        });
    }

    public void onClick1(View v) {
        Intent intent = new Intent(this, ImageDecryption.class);
        startActivity(intent);
    }

    public void onClick2(View v) {
        Intent intent = new Intent(this, CaesarDecryption.class);
        startActivity(intent);
    }

    public void onClick3(View v) {
        Intent intent = new Intent(this, CaesarDecryption.class);
        startActivity(intent);
    }


}
