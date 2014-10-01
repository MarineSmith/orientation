package com.example.orientation;

public class Orientation {
	
	private float orien_X,orien_Y,orien_Z;
	
	public Orientation(float orien_Z,float orien_X,float orien_Y){
		this.orien_X=orien_X;
		this.orien_Y=orien_Y;
		this.orien_Z=orien_Z;
	}
	public float getX(){return orien_X;}
	public float getY(){return orien_Y;}
	public float getZ(){return orien_Z;}
}
