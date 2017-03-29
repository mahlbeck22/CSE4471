package com.example.ahlbe.cse4471;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import static android.R.attr.onClick;
import static android.R.id.message;
import static com.example.ahlbe.cse4471.R.id.editText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClick2(v);
            }
        });
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClick1(v);
            }
        });
    }

    public void onClick1(View v) {
        Intent intent = new Intent(this, Encryption.class);
        startActivity(intent);
    }

    public void onClick2(View v) {
        Intent intent = new Intent(this, Decryption.class);
        startActivity(intent);
    }


}
