package com.example.ahlbe.cse4471;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class ImageEncryption extends AppCompatActivity {
    private Button b;
    EditText message;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_encryption);

        b = (Button) findViewById(R.id.imgbutton);
        tv=(TextView)findViewById(R.id.imgtextview);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = (EditText) findViewById(R.id.message);
                tv.setText("Your Input: \n" + message.getText().toString() + "\nEnd.");
            }
        });
    }
}
