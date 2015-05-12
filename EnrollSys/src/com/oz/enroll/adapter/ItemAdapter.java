package com.oz.enroll.adapter;

import java.util.List;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oz.enroll.bean.Entity;
import com.oz.main.R;

public class ItemAdapter<E extends Entity> extends BaseAdapter {

	private LayoutInflater mInflater;

	private List<E> listData;
	
	
	public ItemAdapter(Context context, List<E> list) {
		
		mInflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);

		listData = list;
		
	}
	
	
	@Override
	public int getCount() {
		
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		HolderView  holder = null;
		
		if(convertView == null)
		{
			
			holder = new HolderView();
			
			convertView = mInflater.inflate(R.layout.item, null);
			
			holder.item = (TextView) convertView.findViewById(R.id.txt_item);
			
			convertView.setTag(holder);
			
		}
		else
		{
			
			holder = (HolderView) convertView.getTag();
			
		}
		
		
		holder.item.setText(listData.get(position).getItem());
		
		
		return convertView;
	}

	private class HolderView
	{
		
		TextView item;
		
	}
	
}
