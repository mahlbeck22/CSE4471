package com.example.ahlbe.cse4471;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

public class CaesarDecryption extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    private EditText inputString, inputKey;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceasar_decryption);

        button = (Button)findViewById(R.id.buttonD1);
        inputString = (EditText)findViewById(R.id.editTextD2);
        inputKey = (EditText)findViewById(R.id.editTextD1);
        button.setOnClickListener(

                new View.OnClickListener() {
                    public void onClick(View v)
                    {

                        String text = inputString.getText().toString();
                        String key = inputKey.getText().toString();
                        String decrypted = CaesarDecryption(text, key);
                        Intent intent = new Intent(v.getContext(), CaesarDecryptedText.class);
                        intent.putExtra("text", decrypted);
                        startActivity(intent);
                    }
                }
        );
    }

    private String CaesarDecryption(String text, String key) {
        String decryptedText ="";
        char[] inputText = text.toCharArray();
        char[] inputKey = key.toCharArray();
        int keyValue = 0;
        for(int i = 0; i < inputKey.length; i++) {
            keyValue = keyValue + (int) inputKey[i];
        }
        int shift = keyValue % 26;

        for(int i = 0; i < text.length();i++) {
            int c = text.charAt(i);
            if(Character.isLowerCase(c)) {
                c = c - (shift % 26);
                if (c < 'a') {
                    c = c + 26;
                }
            }
            else if (Character.isUpperCase(c)) {
                c = c - (shift % 26);
                if (c < 'A') {
                    c = c + 26;
                }
            }
            decryptedText += (char) c;
        }
        return decryptedText;
    }
}
