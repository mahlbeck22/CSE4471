package com.example.ahlbe.cse4471;

import java.io.FileOutputStream;
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
    TextView successTextBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_encryption);

        b = (Button) findViewById(R.id.motionbutton);
        successTextBox = (TextView) findViewById(R.id.successTextBox);

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
                message = (EditText) findViewById(R.id.message);
                String input = message.getText().toString();
                SensorManager.getRotationMatrix(mRotationMatrix, null, mValuesAccel, mValuesMagnet);
                SensorManager.getOrientation(mRotationMatrix, mValuesOrientation);
                final CharSequence test;
                test = "Key: " + (Math.round(mValuesOrientation[1] * 10.0) / 10.0);
                String key = String.valueOf(Math.round(mValuesOrientation[1] * 10.0) / 10.0);
                String salt = "Salt";
                byte[] iv = {-45, -11, 75, -25, 86, 54, 75, 87, -33, 63, -61, 3, 44, -9, 120, -53};
                Encryption encryption = Encryption.getDefault(key, salt, iv);
                String encrypted = encryption.encryptOrNull(input);

                String filename = "motionEncryptions.txt";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, MODE_APPEND);
                    outputStream.write(encrypted.getBytes());
                    outputStream.close();
                    successTextBox.setText("Encrypted message: \n" + encrypted);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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