package com.example.ahlbe.cse4471;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ImageEncryptionError extends AppCompatActivity {
    private Button b;
    TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_encryption_error);

        b = (Button) findViewById(R.id.errorBtn);
        tv = (TextView) findViewById(R.id.errorMsg);

        tv.setText("No image detected. Please enter a link for an image.");
    }

    public void onClickError(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
