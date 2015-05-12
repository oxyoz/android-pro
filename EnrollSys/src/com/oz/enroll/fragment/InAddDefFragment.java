package com.oz.enroll.fragment;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oz.enroll.adapter.ItemAdapter;
import com.oz.enroll.bean.Entity;
import com.oz.enroll.bean.StaffSchool;
import com.oz.enroll.bean.StudentInfo;
import com.oz.enroll.data.ListDataSet;
import com.oz.enroll.data.SubmitStudentInfo;
import com.oz.fragment.BasicFragment;
import com.oz.main.R;

public class InAddDefFragment extends BasicFragment {

	private View mContainer;

	private TextView txtvSchool;

	private StudentInfo studentInfo = SubmitStudentInfo.getStudentInfo();

	@Override
	public void actionReceiver(Context context, Intent intent) {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mContainer = inflater.inflate(
				R.layout.fragment_add_student_info_fragment_in_add_def_school,
				container, false);

		return this.mContainer;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		init();

	}

	/**
	 * init 初始化方法
	 * 
	 * */

	private void init() {

		initStudentInfo();
		// 实例化各个控件
		initWidget();
		// 设置各个控件的数据
		updateWidgetAttr();
		// 设置各个控件的事件监听器
		addWidgetEvent();

	}

	private void initStudentInfo() {

		if (ListDataSet.getInstance().getStaffSchoolList() != null) {

			studentInfo.setSchoolId(ListDataSet.getInstance()
					.getStaffSchoolList().get(0).getSchoolId());

		}

	}

	private void initWidget() {

		txtvSchool = (TextView) mContainer
				.findViewById(R.id.def_school_txt_schoolId);

	}

	private void updateWidgetAttr() {

	}

	private void addWidgetEvent() {

		this.txtvSchool.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (ListDataSet.getInstance().getStaffSchoolList() != null) {

					DialogPropmt<StaffSchool> items = new DialogPropmt<StaffSchool>(
							getActivity(), (TextView) v, ListDataSet
									.getInstance().getStaffSchoolList(),DialogPropmt.INT_SCHOOL);

					items.builderListDialog("学校");
					
					

				}

			}
		});

	}

	
private  class DialogPropmt<E extends Entity>{
		
		final static  int INT_SCHOOL = 0x0;
		
		
		Context context;
		
		TextView textView;
		
		List<E> list;
		
		int changeType;
		
		public DialogPropmt(Context context) {
			
			this.context = context;
			
		}
		
		
		public DialogPropmt(Context context, TextView textView, List<E> list, int changeType) {
			
			this(context);
			
			this.textView = textView;
			
			this.list = list;
			
			this.changeType = changeType;
			
		}
		
		
		
		public  void builderPrompt(String msg) {

			AlertDialog.Builder builder = new Builder(this.context);

			builder.setTitle("提示");

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
					
					if(changeType == DialogPropmt.INT_SCHOOL)
					{
						
						studentInfo.setSchoolId(ListDataSet.getInstance()
								.getStaffSchoolList().get(which)
								.getSchoolId());
						
					}
						
					
				}

			});

			builder.show();	
		}

		
		
		
	}
	
	
	
	@Override
	public void onDestroy() {

		super.onDestroy();
	}

}
