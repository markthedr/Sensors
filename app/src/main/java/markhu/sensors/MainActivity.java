package markhu.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "Sensors";

    private TextView mAccel;
    private TextView mGyro;
    private TextView mMagnet;

    SensorManager mSm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAccel = (TextView) findViewById(R.id.tvAccelerometer);
        mGyro = (TextView) findViewById(R.id.tvGyroscope);
        mMagnet = (TextView) findViewById(R.id.tvMagnetic);

    }

    @Override
    protected void onStart(){
        super.onStart();
        mSm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelSensor = mSm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor gyroSensor = mSm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Sensor magnetSensor = mSm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSm.registerListener( this,accelSensor,SensorManager.SENSOR_DELAY_NORMAL);
        mSm.registerListener( this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSm.registerListener( this, magnetSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        switch (event.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                mAccel.setText("For on x: " + event.values[0] + " on y " + event.values[1] + " on z: " + event.values[2]);
                break;
            case Sensor.TYPE_GYROSCOPE:
                mGyro.setText("Rate of Rotation on X: " + event.values[0] + " on y " + event.values[1] + " on z: " + event.values[2]);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mMagnet.setText("Strength on X: " + event.values[0] + " on y " + event.values[1] + " on z: " + event.values[2]);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStop(){
        super.onStop();
        mSm.unregisterListener(this);
    }
}
