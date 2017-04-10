package com.example.ahlbe.cse4471;
import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;
import android.view.*;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


public class ImageEncryption extends AppCompatActivity {
    private Button b;
    EditText message;
    EditText link;
    TextView tv;
    ImageView image;
    String url = "";
    String salt = "Salt";
    String encrypted = "";
    private byte[] iv = {-45, -11, 75, -25, 86, 54, 75, 87, -33, 63, -61, 3, 44, -9, 120, -53};
    String msg = "";


    public byte[] getIV() {
        return iv;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_encryption);

        image = (ImageView) findViewById(R.id.imageView);
        b = (Button) findViewById(R.id.motionbutton);
        tv = (TextView) findViewById(R.id.imgtextview);

        link = (EditText) findViewById(R.id.urlLink);
        message = (EditText) findViewById(R.id.message);


    }

    public void onClickBtn(View v) {
        url = link.getText().toString();
        msg = message.getText().toString();

        Picasso.with(this).load(url).into(image, new Callback.EmptyCallback(){
            @Override
            public void onSuccess() {

                Encryption encryption = Encryption.getDefault(url, salt, iv);
                encrypted = encryption.encryptOrNull(msg);

                tv.setText("Encrypted message: \n" + encrypted);
                //Log.e("Encrypted text: ", encrypted);
            }

            @Override
            public void onError() {
                Picasso.with(ImageEncryption.this).invalidate(url);

                Intent intent = new Intent(ImageEncryption.this, ImageEncryptionError.class);
                startActivity(intent);
            }
        });
    }
}



