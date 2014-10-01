package com.example.orientation;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;

public class Draw_P extends View {
	
	/***
	 * @height 330
	 * @width 480
	 */
	
	private Context context;
	public Paint mPaintX,mPaintY,mPaintZ,mPaint;
	private ArrayList<Orientation> orientation_;

	public Draw_P(Context context,ArrayList<Orientation> orientation_) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.orientation_=orientation_;
		init_paint();
	}
	
	private void init_paint(){
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLACK);
		mPaintX = new Paint();
		mPaintX.setAntiAlias(true);
		mPaintX.setColor(Color.RED);
		mPaintY = new Paint();
		mPaintY.setAntiAlias(true);
		mPaintY.setColor(Color.BLUE);
		mPaintZ = new Paint();
		mPaintZ.setAntiAlias(true);
		mPaintZ.setColor(Color.rgb(0x1f, 0x6c, 0xbd));
	}
	
	public void onDraw(Canvas mCanvas){
		mCanvas.drawLine(0, mCanvas.getHeight()/2, mCanvas.getWidth(), mCanvas.getHeight()/2, mPaint);
		for(int i=1;i<orientation_.size();i++){
			mCanvas.drawLine(40*(i-1), 165-(change2degree(orientation_.get(i-1).getX())), 40*i, 165-(change2degree(orientation_.get(i).getX())), mPaintX);
			mCanvas.drawLine(40*(i-1), 165-(change2degree(orientation_.get(i-1).getY())), 40*i, 165-(change2degree(orientation_.get(i).getY())), mPaintY);
			mCanvas.drawLine(40*(i-1), 165-(change2degree(orientation_.get(i-1).getZ())), 40*i, 165-(change2degree(orientation_.get(i).getZ())), mPaintZ);
		}
		this.invalidate();
	}
	
	public float change2degree(float result){
		return ((float)Math.toDegrees((float)result)/2);
	}

}
