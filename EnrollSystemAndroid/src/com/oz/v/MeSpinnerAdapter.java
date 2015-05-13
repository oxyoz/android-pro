package com.oz.v;


import com.main.v.R;




import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MeSpinnerAdapter extends BaseAdapter{

	
	private LayoutInflater inflater;
	
	private String[] data;
	
	public MeSpinnerAdapter(Context context, String[] data) {

		this.inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
	
		this.data = data;
		
		
	}
	
	
	@Override
	public int getCount() {

		return data.length;
	}

	
	@Override
	public Object getItem(int position) {

		return data[position];
	}

	
	@Override
	public long getItemId(int position) {

		return position;
	}
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		TextView item = null;
		
		if(convertView == null)
		{
						
			convertView = this.inflater.inflate(R.layout.item_spinner_info, null);
			
			item = (TextView) convertView.findViewById(R.id.txt_spinner_item);
			
			convertView.setTag(item);
			
		}
		else
		{
			
			item = (TextView)convertView.getTag();
			
		}
		
		item.setText(this.data[position]);
		
		
		return convertView;
	}
	
	

	
}
