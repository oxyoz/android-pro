package com.oz.v;

import java.util.List;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.main.v.R;
import com.oz.m.ItAll;

public class StudentInfoAdpater extends BaseAdapter {

	private LayoutInflater inflater;
	
	private List<ItAll> data;
	
	public StudentInfoAdpater(Context context, List<ItAll> data) {

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
            convertView = this.inflater.inflate(R.layout.item_student_info, null); 
            
            holder = new ViewHolder(); 
            
            holder.txtStudentName = (TextView)convertView.findViewById(R.id.txt_student_name); 
            
            holder.txtExamineeNumber  = (TextView)convertView.findViewById(R.id.txt_student_examineeNumber);
           
            convertView.setTag(holder); 
            
        } 
        else 
        {      	
            holder = (ViewHolder)convertView.getTag();             
        } 
        
        holder.txtStudentName.setText("ÐÕÃû£º"+data.get(position).getStuName());
        
        holder.txtExamineeNumber.setText("¿¼ÉúºÅ£º"+data.get(position).getExamineeNumber());
              
		return convertView;
	}
	
	
	private final class ViewHolder
	{
		
		TextView txtExamineeNumber;
		TextView txtStudentName;
		
	}

}
