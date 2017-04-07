package com.example.ahlbe.cse4471;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CaesarEncryption extends AppCompatActivity {


    private EditText inputString, inputKey;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar_encryption);

        button = (Button)findViewById(R.id.button11);
        inputString = (EditText)findViewById(R.id.editTextD2);
        inputKey = (EditText)findViewById(R.id.editText4);
        button.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v)
                    {

                        String text = inputString.getText().toString();
                        String key = inputKey.getText().toString();
                        String encrypted = CaesarEncryption(text, key);
                        Intent intent = new Intent(v.getContext(), CaesarEncryptedText.class);
                        intent.putExtra("text", encrypted);
                        startActivity(intent);
                    }
                }


        );
    }

    private String CaesarEncryption(String text, String key) {
        String encryptedText ="";
        char[] inputKey = key.toCharArray();
        int keyValue = 0;
        for(int i = 0; i < inputKey.length; i++) {
            keyValue = keyValue + (int) inputKey[i];
        }
        int shift = keyValue % 26;

        for(int i = 0; i < text.length();i++) {
            int c = text.charAt(i);
            if(Character.isLowerCase(c)) {
                c = c + (shift % 26);
                if (c > 'z') {
                    c = c - 26;
                }
            }
            else if (Character.isUpperCase(c)) {
                c = c + (shift % 26);
                if (c > 'Z') {
                    c = c - 26;
                }
            }
            encryptedText += (char) c;
        }
        return encryptedText;
    }




}
