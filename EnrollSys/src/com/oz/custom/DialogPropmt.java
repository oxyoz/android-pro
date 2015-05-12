package com.oz.custom;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.TextView;

import com.oz.enroll.adapter.ItemAdapter;
import com.oz.enroll.bean.Entity;

public class DialogPropmt<E extends Entity> {

	Context context;
	
	TextView textView;
	
	List<E> list;
	
	public DialogPropmt(Context context) {
		
		this.context = context;
		
	}
	
	
	public DialogPropmt(Context context, TextView textView, List<E> list) {
		
		this(context);
		
		this.textView = textView;
		
		this.list = list;
		
	}
	
	
	
	public  void builderPrompt(String msg) {

		AlertDialog.Builder builder = new Builder(this.context);

		builder.setTitle("ÌבÊ¾");

		builder.setMessage(msg);

		builder.setPositiveButton("OK", null);

		builder.show();

	}

	
	public void builderListDialog(String title) {

		AlertDialog.Builder builder = new Builder(this.context);

		builder.setTitle(title);

		ItemAdapter<E> adapter = new ItemAdapter<E>(context, this.list);

		builder.setAdapter(adapter, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				textView.setText(list.get(which).getItem());		
							
			}

		});

		builder.show();	
	}

	
	
}
