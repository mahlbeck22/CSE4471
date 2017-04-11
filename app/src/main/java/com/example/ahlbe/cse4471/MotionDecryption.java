package com.example.ahlbe.cse4471;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_decryption);

        b = (Button) findViewById(R.id.motionbutton);
        keyOutputTextView = (TextView) findViewById(R.id.keyOutputTextView);
        encryptionOutputTextView = (TextView) findViewById(R.id.encryptionOutputTextView);

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
                keyOutputTextView.setText("Your Input: \n" + input + "\nEnd.");
                SensorManager.getRotationMatrix(mRotationMatrix, null, mValuesAccel, mValuesMagnet);
                SensorManager.getOrientation(mRotationMatrix, mValuesOrientation);
                final CharSequence test;
                test = "Key: " + (Math.round(mValuesOrientation[1] * 10.0) / 10.0);
                keyOutputTextView.setText(test);
                String key = String.valueOf(Math.round(mValuesOrientation[1] * 10.0) / 10.0);
                // TODO
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