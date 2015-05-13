package com.lgl.v;

import java.util.List;

import com.lgl.m.TimeCard;
import com.main.v.R;


import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TimeCardAdapter extends BaseAdapter{

	private LayoutInflater inflater;
	
	private List<TimeCard> data;
	
	public TimeCardAdapter(Context context, List<TimeCard> data) {
		
		this.inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		
		this.data = data;
		
	}
	
	@Override
	public int getCount() {		
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = null; 
		
        if (convertView == null)
        {         	
            convertView = this.inflater.inflate(R.layout.item_time_card, null); 
            
            holder = new ViewHolder(); 
            
            holder.txtYear = (TextView)convertView.findViewById(R.id.txt_time_card_year); 
            
            holder.txtContent  = (TextView)convertView.findViewById(R.id.txt_time_card_content);
           
            holder.txtEndTime = (TextView)convertView.findViewById(R.id.txt_time_card_endTime); 
            
            holder.txtStartTime  = (TextView)convertView.findViewById(R.id.txt_time_card_startTime);
            
            convertView.setTag(holder); 
            
        } 
        else 
        {      	
            holder = (ViewHolder)convertView.getTag();             
        } 
        
        holder.txtYear.setText("年份："+data.get(position).getYear());
        
        holder.txtContent.setText("内容："+data.get(position).getContact()); 
       
        holder.txtEndTime.setText("结束时间："+data.get(position).getEndTime().getYear()+"-"
        							+data.get(position).getEndTime().getMonth()+"-"
        							+data.get(position).getEndTime().getDay()+" "
        							+data.get(position).getEndTime().getHours()+":"
        							+data.get(position).getEndTime().getSeconds()+":"
        							+data.get(position).getEndTime().getMinutes()); 
        
        holder.txtStartTime.setText("开始时间："+data.get(position).getStartTime().getYear()+"-"
									  +data.get(position).getStartTime().getMonth()+"-"
									  +data.get(position).getStartTime().getDay()+" "
									  +data.get(position).getStartTime().getHours()+":"
									  +data.get(position).getStartTime().getSeconds()+":"
									  +data.get(position).getStartTime().getMinutes()); 
	
		return convertView;
	}
	
	
	private final class ViewHolder
	{
		
		TextView txtYear;
		
		TextView txtStartTime;
		
		TextView txtContent;
		
		TextView txtEndTime;
		
	}
	
}


