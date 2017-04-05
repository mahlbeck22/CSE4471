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
        setContentView(R.layout.activity_encryption);
        final Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClick1(v);
            }
        });
        final Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClick2(v);
            }
        });
        final Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClick3(v);
            }
        });
    }

    public void onClick1(View v) {
        Intent intent = new Intent(this, CaesarDecryption.class);
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
