package com.example.ahlbe.cse4471;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImageDecryption extends AppCompatActivity {
    private Button b;
    EditText encryptedMsg;
    EditText editKey;
    TextView encryptionOutputTextView;
    TextView tv;
    String salt = "Salt";
    String encrypt = "";
    String key = "";
    private byte[] iv = {-45, -11, 75, -25, 86, 54, 75, 87, -33, 63, -61, 3, 44, -9, 120, -53};
    String decrypted = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_decryption);

        encryptionOutputTextView = (TextView) findViewById(R.id.encryptionOutputTextView);

        FileInputStream fis = null;
        try {
            fis = openFileInput("imageEncryptions.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append("\n\n" + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            TextView encryptedTextTextView = (TextView)findViewById(R.id.encryptedTextTextView);
            encryptedTextTextView.setText(sb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        b = (Button) findViewById(R.id.decryptBtn);
        tv = (TextView) findViewById(R.id.decryptedMsg);
        encryptedMsg = (EditText) findViewById(R.id.encryptedMsg);
        editKey = (EditText) findViewById(R.id.keyMsg);
    }

    public void onClickImgDecBtn(View v) {
        encrypt = encryptedMsg.getText().toString();
        key = editKey.getText().toString();

        Encryption encryption = Encryption.getDefault(key, salt, iv);
        decrypted = encryption.decryptOrNull(encrypt);

        tv.setText("Message \n" + decrypted);

    }
}
