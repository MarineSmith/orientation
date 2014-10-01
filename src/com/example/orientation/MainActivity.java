package com.example.orientation;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends Activity implements SensorEventListener {
	
	private ListView lv1;
	private LinearLayout ll1;
	private static final int MAX_SIZE = 12;
	private SensorManager mSensorManager;
	public ArrayList<Orientation> orientation;
	private Sensor mSensor_acc;
	private Sensor mSensor_mag;
	private float[] mLast_acc = new float[3];
	private float[] mLast_mag = new float[3];
	private boolean mLast_acc_set = false;
	private boolean mLast_mag_set = false;
	public Draw_P draw;
	private Orientation_Adapter orientation_;
	
	private float[] mR = new float[9];
	private float[] mOrientation = new float[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initial_item();
		initial_draw();
	}
	
	public void initial_item(){
		lv1 = (ListView)this.findViewById(R.id.lv1);
		ll1 = (LinearLayout)this.findViewById(R.id.ll1);
		mSensorManager = (SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
		mSensor_acc = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensor_mag = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		orientation = new ArrayList<Orientation>();
		orientation_ = new Orientation_Adapter(orientation,MainActivity.this);
		lv1.setAdapter(orientation_);
	}
	
	public void initial_draw(){
		draw = new Draw_P(MainActivity.this,orientation);
		ll1.addView(draw);
		ll1.invalidate();
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		mSensorManager.registerListener(MainActivity.this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_UI);
		mSensorManager.registerListener(MainActivity.this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),SensorManager.SENSOR_DELAY_UI);
	}
	
	protected void onStop(){
		mSensorManager.unregisterListener(this);
		super.onStop();
	}
	
	/***
	 * @value[0] Z-axis azimuth
	 * @value[1] X-axis pitch
	 * @value[2] Y-axis roll
	 */

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub 
		if(event.sensor==mSensor_acc){
			mLast_acc = event.values.clone();
			mLast_acc_set=true;
		}
		if(event.sensor==mSensor_mag){
			mLast_mag = event.values.clone();
			mLast_mag_set=true;
		}
		if(mLast_acc_set&&mLast_mag_set){
			SensorManager.getRotationMatrix(mR, null, mLast_acc, mLast_mag);
			SensorManager.getOrientation(mR, mOrientation);
		}
		if(orientation.size()>MAX_SIZE){
			orientation.remove(0);
			orientation.add(new Orientation(mOrientation[1],mOrientation[2],mOrientation[0]));
		}else{
			orientation.add(new Orientation(mOrientation[1],mOrientation[2],mOrientation[0]));
		}
		LV_refresh();
	}
	
	public void LV_refresh(){
		orientation_.refresh(orientation);
		lv1.setAdapter(orientation_);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
