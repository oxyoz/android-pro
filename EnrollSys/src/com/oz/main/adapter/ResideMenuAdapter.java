package com.oz.main.adapter;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.oz.activity.BasicActivity;
import com.oz.main.R;
import com.oz.main.dao.LoginProxy;
import com.oz.main.data.MenuStringArray;

public class ResideMenuAdapter extends BaseAdapter {

	private String[] astrMenu;
	
	private LayoutInflater mInflater;
	
	public ResideMenuAdapter(BasicActivity activity) {
		
		mInflater = activity.getLayoutInflater();
		
		if(LoginProxy.TYPE_ADMIN.equals(activity.getIntent().getStringExtra("TYPE_USER")))
		{
			
			astrMenu = MenuStringArray.astrManagerMenu;
			
		}
		else if(LoginProxy.TYPE_TEACHER.equals(activity.getIntent().getStringExtra("TYPE_USER")))
		{
			
			astrMenu = MenuStringArray.astrEnrollMenu;
			
		}
		
		
		
	}
	
	@Override
	public int getCount() {
		
		return astrMenu.length;
	}

	@Override
	public Object getItem(int position) {
		
		
		return astrMenu[position];
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	
	@SuppressLint("ViewHolder") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = mInflater.inflate(R.layout.welcome_menu_item, null);
		
		TextView txtvItem = (TextView) view.findViewById(R.id.welcome_menu_item_txt_lable);
		
		txtvItem.setText(astrMenu[position]);
		
		return view;
	}

		
	
}
