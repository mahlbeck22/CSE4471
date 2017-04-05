package com.example.ahlbe.cse4471;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;

public class ImageEncryption extends AppCompatActivity {
    private Button b;
    EditText message;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_encryption);

        b = (Button) findViewById(R.id.motionbutton);
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
