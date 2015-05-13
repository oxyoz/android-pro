package com.main.v;


import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	String[] group;
	
	String[][] child;
	
	LayoutInflater inflater;
	
	public ExpandableListAdapter(Context context) {
		
		inflater = (LayoutInflater) context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		
	}
	
	public ExpandableListAdapter(Context context,String[] group,String[][] child) {
		this(context);
		this.group = group;
		this.child = child;
	}
	
	
	@Override
	public int getGroupCount() {
		
		return group.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		
		return child[groupPosition].length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		
		return group[groupPosition];
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		
		return child[groupPosition][childPosition];
	}

	@Override
	public long getGroupId(int groupPosition) {
		
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		TextView title;
		
		if(convertView == null)
		{
			
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.item_expandable_list_group, null);
			
			convertView  = layout;
			
			title = (TextView) layout.findViewById(R.id.txt_expandable_list_group_title);
			
			convertView.setTag(title);
			
		}
		else
		{
			
			title = (TextView) convertView.getTag();
			
		}
		
		title.setText(group[groupPosition]);
		
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		
		TextView title;
		
		if(convertView == null)
		{
			
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.item_expandable_list_child, null);
			
			convertView  = layout;
			
			title = (TextView) convertView.findViewById(R.id.txt_expandable_list_child_title);
			
			convertView.setTag(title);
			
		}
		else
		{
			
			title = (TextView) convertView.getTag();
			
		}
		
		title.setText(child[groupPosition][childPosition]);
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		
		return true;
	}
	
	
}

	final class  GroupView{
		
		TextView title;
	} 

	final class  ChildView{
		
		TextView title;
	} 
