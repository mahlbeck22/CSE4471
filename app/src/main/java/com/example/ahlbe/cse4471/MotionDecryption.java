package com.example.ahlbe.cse4471;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MotionDecryption extends AppCompatActivity {
    private Button b;
    EditText message;
    TextView keyOutputTextView;
    TextView encryptionOutputTextView;
    EditText textMsg;
    TextView tv;
    String salt = "Salt";
    String encrypt = "";
    String key = "";
    private byte[] iv = {-45, -11, 75, -25, 86, 54, 75, 87, -33, 63, -61, 3, 44, -9, 120, -53};
    String decrypted = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_decryption);

        b = (Button) findViewById(R.id.motionbutton);
        keyOutputTextView = (TextView) findViewById(R.id.keyOutputTextView);
        encryptionOutputTextView = (TextView) findViewById(R.id.encryptionOutputTextView);

        FileInputStream fis = null;
        try {
            fis = openFileInput("motionEncryptions.txt");
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

        SensorManager sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        final float[] mValuesMagnet = new float[3];
        final float[] mValuesAccel = new float[3];
        final float[] mValuesOrientation = new float[3];
        final float[] mRotationMatrix = new float[9];

        final SensorEventListener mEventListener = new SensorEventListener() {
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }

            public void onSensorChanged(SensorEvent event) {
                // Handle the events for which we registered
                switch (event.sensor.getType()) {
                    case Sensor.TYPE_ACCELEROMETER:
                        System.arraycopy(event.values, 0, mValuesAccel, 0, 3);
                        break;

                    case Sensor.TYPE_MAGNETIC_FIELD:
                        System.arraycopy(event.values, 0, mValuesMagnet, 0, 3);
                        break;
                }
            };
        };

        setListeners(sensorManager, mEventListener);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SensorManager.getRotationMatrix(mRotationMatrix, null, mValuesAccel, mValuesMagnet);
                SensorManager.getOrientation(mRotationMatrix, mValuesOrientation);
                String key = String.valueOf(Math.round(mValuesOrientation[1] * 10.0) / 10.0);

                message = (EditText) findViewById(R.id.textMsg);
                encrypt = message.getText().toString();
                Encryption encryption = Encryption.getDefault(key, salt, iv);
                decrypted = encryption.decryptOrNull(encrypt);

                tv = encryptionOutputTextView;
                tv.setText("Message \n" + decrypted);
            }
        });
    }
    // Register the event listener and sensor type.
    public void setListeners(SensorManager sensorManager, SensorEventListener mEventListener)
    {
        sensorManager.registerListener(mEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(mEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
}