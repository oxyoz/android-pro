package com.oz.enroll.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.oz.enroll.bean.ItAll;
import com.oz.enroll.bean.StudentInfo;
import com.oz.main.R;

public class StuInfoAdapter extends BaseAdapter {

	private LayoutInflater mInflater;

	private List<ItAll> listData;

	public StuInfoAdapter(Context context, List<ItAll> list) {

		mInflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);

		listData = list;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent) {

		HolderView holder = null;

		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.item_list_show_student_info, null);

			holder = new HolderView();

			holder.txtvExamineeNumber = (TextView) convertView
					.findViewById(R.id.item_list_show_txt_examineeNumber);

			holder.txtvName = (TextView) convertView
					.findViewById(R.id.item_list_show_txt_name);

			holder.txtvSex = (TextView) convertView
					.findViewById(R.id.item_list_show_txt_sex);

			convertView.setTag(holder);

		} else {

			holder = (HolderView) convertView.getTag();

		}

		holder.txtvExamineeNumber.setText("¿¼ºÅ£º\t"+this.listData.get(position)
				.getExamineeNumber());

		holder.txtvName.setText("ÐÕÃû£º\t"+this.listData.get(position).getStuName());

		holder.txtvSex.setText("ÐÔ±ð£º\t"+this.listData.get(position).getSex());

		if(position%2 == 0)
		{
			
			convertView.setBackgroundColor(0xFFEAEFF7);
			
		}
		else
		{
			
			convertView.setBackgroundColor(0xFFD2DEEF);
			
		}
		
		return convertView;
	}

	private class HolderView {

		TextView txtvName;
		TextView txtvExamineeNumber;
		TextView txtvSex;
	}

}
