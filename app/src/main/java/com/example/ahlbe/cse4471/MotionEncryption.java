package com.example.ahlbe.cse4471;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MotionEncryption extends AppCompatActivity {
    private Button b;
    EditText message;
    TextView keyOutputTextView;
    TextView encryptionOutputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_encryption);

        b = (Button) findViewById(R.id.motionbutton);
        keyOutputTextView = (TextView) findViewById(R.id.keyOutputTextView);
        encryptionOutputTextView = (TextView) findViewById(R.id.encryptionOutputTextView);

        SensorManager sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        final float[] mValuesAccel = new float[3];
        final float[] mValuesOrientation = new float[3];
        final float[] mRotationMatrix = new float[9];

        final SensorEventListener mEventListener = new SensorEventListener() {
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }

            public void onSensorChanged(SensorEvent event) {
                switch (event.sensor.getType()) {
                    case Sensor.TYPE_ACCELEROMETER:
                        System.arraycopy(event.values, 0, mValuesAccel, 0, 3);
                        break;
                }
            };
        };

        sensorManager.registerListener(mEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                message = (EditText) findViewById(R.id.message);
                String input = message.getText().toString();
                keyOutputTextView.setText("Your Input: \n" + input + "\nEnd.");
                SensorManager.getOrientation(mRotationMatrix, mValuesOrientation);
                final CharSequence test;
                test = "Key: " + (Math.round(mValuesOrientation[1] * 10.0) / 10.0);
                keyOutputTextView.setText(test);
                String key = String.valueOf(Math.round(mValuesOrientation[1] * 10.0) / 10.0);
                String salt = "boop";
                byte[] iv = new byte[16];
                Encryption encryption = Encryption.getDefault(key, salt, iv);
                String encrypted = encryption.encryptOrNull(input);
                encryptionOutputTextView.setText(encrypted);
            }
        });
    }
}
