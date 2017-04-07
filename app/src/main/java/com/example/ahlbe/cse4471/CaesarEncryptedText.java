package com.example.ahlbe.cse4471;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class CaesarEncryptedText extends AppCompatActivity {


    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar_encrypted_text);

        Intent intent = getIntent();
        String encryptedText = intent.getStringExtra("text");

        textView = (TextView)findViewById(R.id.textView8);

        textView.setText(encryptedText);
    }

}
