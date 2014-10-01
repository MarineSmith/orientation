package com.example.orientation;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Orientation_Adapter extends BaseAdapter {
	
	private ArrayList<Orientation> orientation_;
	private Context context;
	
	public Orientation_Adapter(ArrayList<Orientation> orientation_,Context context){
		this.orientation_=orientation_;
		this.context=context;
	}
	
	public void refresh(ArrayList<Orientation> orientation_){
		this.orientation_=orientation_;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return orientation_.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/***
	 * X-axis roll
	 * Z-axis pitch
	 * Y-axis azimuth
	 */

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Holder holder = null;
		if(convertView==null){
			LayoutInflater mLayoutInflater = LayoutInflater.from(context);
			convertView = mLayoutInflater.inflate(R.layout.listview, null);
			holder = new Holder();
			holder.listview_tv1=(TextView)convertView.findViewById(R.id.listview_tv1);
			holder.listview_tv2=(TextView)convertView.findViewById(R.id.listview_tv2);
			holder.listview_tv3=(TextView)convertView.findViewById(R.id.listview_tv3);
			convertView.setTag(holder);
		}else{
			holder = (Holder) convertView.getTag();
		}
		Orientation orientation = orientation_.get(position);
		holder.listview_tv1.setText(String.format("r-orien = %.2f ",(float)Math.toDegrees(orientation.getX())));
		holder.listview_tv2.setText(String.format(" a-orien = %.2f",(float)Math.toDegrees(orientation.getY())));
		holder.listview_tv3.setText(String.format(" p-orien = %.2f",(float)Math.toDegrees(orientation.getZ())));
		return convertView;
	}
	class Holder{
		TextView listview_tv1,listview_tv2,listview_tv3;
	}

}
