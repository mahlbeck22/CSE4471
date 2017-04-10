package com.example.ahlbe.cse4471;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDecryption extends AppCompatActivity {
    private Button b;
    EditText encryptedMsg;
    EditText editKey;
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

        b = (Button) findViewById(R.id.decryptBtn);
        tv = (TextView) findViewById(R.id.decryptedMsg);
        encryptedMsg = (EditText) findViewById(R.id.encryptedMsg);
        editKey = (EditText) findViewById(R.id.keyMsg);
    }

    public void onClickImgDecBtn(View v) {
        encrypt = encryptedMsg.getText().toString();
        key = editKey.getText().toString();
        //Log.e("Encrypted text: ", encrypt);
        //Log.e("Key: ", key);

        Encryption encryption = Encryption.getDefault(key, salt, iv);
        decrypted = encryption.decryptOrNull(encrypt);
        //Log.e("Encrypted text: ", decrypted);

        tv.setText("Message \n" + decrypted);

    }
}
