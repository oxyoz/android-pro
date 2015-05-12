package com.oz.enroll.fragment;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.oz.bean.StatusMessage;
import com.oz.enroll.adapter.ItemAdapter;
import com.oz.enroll.bean.Entity;
import com.oz.enroll.bean.Major;
import com.oz.enroll.bean.StudentInfo;
import com.oz.enroll.bean.Volunteer;
import com.oz.enroll.config.Action;
import com.oz.enroll.config.ActionCode;
import com.oz.enroll.data.AddressId;
import com.oz.enroll.data.CheckResult;
import com.oz.enroll.data.ListDataSet;
import com.oz.enroll.data.SubmitResult;
import com.oz.enroll.data.SubmitStudentInfo;
import com.oz.fragment.BasicFragment;
import com.oz.main.R;

public class InAddFragment extends BasicFragment {

	private View mContainer;

	private HolderView mHolderView;

	int what = 0;

	private StudentInfo studentInfo = SubmitStudentInfo.getStudentInfo();

	private void sendActionReceiver(String action, int what, String checkContent) {

		Intent intent = new Intent(action);

		intent.putExtra("what", what);

		intent.putExtra("check", checkContent);

		this.getActivity().sendBroadcast(intent);

	}

	@Override
	public void actionReceiver(Context context, Intent intent) {

		this.what = intent.getIntExtra("what", 0);

		switch (this.what) {

		case ActionCode.InAdd.SUBMIT_STUDENT_INFO: {

			if (SubmitResult.getSubmitResult().getStatus().equals("1")) {

				ListDataSet.getInstance().setItAll(null);
				
				replaceFragment(R.id.welcome_panel_content, new StudentInfoFragment());

			} else {

				DialogPropmt dp = new DialogPropmt(getActivity());

				dp.builderPrompt(SubmitResult.getSubmitResult().getStatus());

			}

		}
			break;

		case ActionCode.InAdd.CHECK_EXAMINEES_RESULT: {

			StatusMessage sm = CheckResult.getCheckResult();

			if (sm != null) {

				if (sm.getStatus().equals("0")) {

					mHolderView.edtExaminees.setError(sm.getMessage());

				}

			}

		}
			break;

		case ActionCode.InAdd.CHECK_SCHOOL_RESULT: {

			StatusMessage sm = CheckResult.getCheckResult();

			if (sm != null) {

				if (sm.getStatus().equals("0")) {

					DialogPropmt dp = new DialogPropmt(getActivity());

					dp.builderPrompt(sm.getMessage());

				}

			}

		}
			break;

		}

	}

	
	
	private  class DialogPropmt<E extends Entity>{
		
		final static  int INT_MAJOR = 0x0;
		
		final static  int INT_VOL = 0x1;
		
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

			builder.setTitle("��ʾ");

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
					
					if(changeType == DialogPropmt.INT_MAJOR)
					{
						
						studentInfo.setAttendProfessional(ListDataSet.getInstance()
								.getInMajorList().get(which)
								.getMajorName());
						
					}
						
					if(changeType == DialogPropmt.INT_VOL)
					{
						
						
						studentInfo.setVoluntarily(ListDataSet
								.getInstance().getVolunteerList()
								.get(which).getVolunteer());
						
					}
					
				}

			});

			builder.show();	
		}

		
		
		
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mContainer = inflater.inflate(
				R.layout.fragment_add_student_info_fragment_in_add, container,
				false);

		return this.mContainer;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		init();

	}

	/**
	 * init ��ʼ������
	 * 
	 * */

	private void init() {

		isAdd();

		initStudentInfo();
		// ʵ���������ؼ�
		initWidget();
		// ���ø����ؼ�������
		updateWidgetAttr();
		// ���ø����ؼ����¼�������
		addWidgetEvent();

	}

	private void isAdd() {
		if (ListDataSet.getInstance().getPropertyList() == null) {

			replaceFragment(R.id.welcome_panel_content,
					new StudentInfoFragment());

		}

	}

	private void initWidget() {

		mHolderView = new HolderView(
				this.mContainer.findViewById(R.id.in_edt_examinees),
				this.mContainer.findViewById(R.id.in_edt_id),
				this.mContainer.findViewById(R.id.in_edt_stuName),
				this.mContainer.findViewById(R.id.in_edt_tel),
				this.mContainer.findViewById(R.id.in_rdg_school_type),
				this.mContainer.findViewById(R.id.in_rdg_sex),
				this.mContainer.findViewById(R.id.in_txt_major),
				this.mContainer.findViewById(R.id.in_txt_volunteer),
				this.mContainer.findViewById(R.id.in_txt_property),
				this.mContainer.findViewById(R.id.in_txt_submit),
				this.mContainer.findViewById(R.id.in_txt_reset));

	}

	private void initStudentInfo() {
		
		// ��Ϊʡ��Ĭ�����
		studentInfo.setFlag("0");
		// ���ñ�ʾ
		studentInfo.setIdNumber("ʡ��");
		// �����Ա�
		studentInfo.setSex("��");

		studentInfo.setVoluntarily("��һ־Ը");

		studentInfo.setAttendProfessional(ListDataSet.getInstance()
				.getInMajorList().get(0).getMajorName());

		studentInfo.setProperty(ListDataSet.getInstance().getPropertyList()
				.get(0).getMessage());

	}

	private void updateWidgetAttr() {

		mHolderView.txtvProperty.setText(ListDataSet.getInstance()
				.getPropertyList().get(0).getMessage());

		if(ListDataSet.getInstance()
				.getPropertyList().get(0).getMessage().equals("����"))
		{
			
			this.mHolderView.txtvVolunteer.setText(null);
			
			this.mHolderView.txtvVolunteer.setHint("־Ը����ѡ");
			
			this.mHolderView.txtvVolunteer.setEnabled(false);
				
		}
		else{
			
		this.mHolderView.txtvVolunteer.setText(ListDataSet.getInstance()
				.getVolunteerList().get(0).getVolunteer());

		}
		
		if (studentInfo.getFlag().equals("0")) {

			replaceFragment(R.id.in_container_school, new InAddDefFragment());

		}

	}

	private void addWidgetEvent() {

		mHolderView.btnSubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				String strExaminees = mHolderView.edtExaminees.getText()
						.toString().trim();

				String strStuName = mHolderView.edtStuName.getText().toString()
						.trim();

				if (strExaminees == null || strExaminees.equals("")
						|| strExaminees.length() != 14) {

					DialogPropmt dp = new DialogPropmt(getActivity());

					dp.builderPrompt("���������벻��ȷ��");

				} else if (strExaminees == null || strStuName.equals("")
						|| strStuName.length() < 2) {

					DialogPropmt dp = new DialogPropmt(getActivity());

					dp.builderPrompt("�������벻��ȷ��");

				} else {

					studentInfo.setExamineeNumber(strExaminees);

					studentInfo.setStuName(strStuName);

					sendActionReceiver(Action.receiver.RECEIVER_ENROLL_INADD,
							ActionCode.InAdd.SUBMIT_STUDENT_INFO, "");

				}

			}
		});

		mHolderView.rdgSex
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.in_rd_man:

							studentInfo.setSex("��");

							break;

						case R.id.in_rd_woman:

							studentInfo.setSex("Ů");

							break;
						}

					}
				});

		mHolderView.rdgSchoolType
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {

						case R.id.in_rd_def:

							studentInfo.setFlag("0");

							replaceFragment(R.id.in_container_school,
									new InAddDefFragment());

							break;

						case R.id.in_rd_other:

							studentInfo.setFlag("1");

							replaceFragment(R.id.in_container_school,
									new InAddOtherFragment());

							break;

						}

					}
				});

		mHolderView.edtExaminees
				.setOnFocusChangeListener(new View.OnFocusChangeListener() {

					@Override
					public void onFocusChange(View v, boolean hasFocus) {

						EditText view = (EditText) v;

						String content = view.getText().toString().trim();

						if (!hasFocus) {
							if (content != null || !content.equals("")) {

								if (content.length() == 14) {

									sendActionReceiver(
											Action.receiver.RECEIVER_ENROLL_INADD,
											ActionCode.InAdd.CHECK_EXAMINEES_RESULT,
											content);

								} else {

									view.setError("���ݱ���Ϊ14λ����");

								}

							} else {

								view.setError("����Ϊ�գ�");

							}

						}

					}
				});

		mHolderView.txtvMajor.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (ListDataSet.getInstance().getInMajorList() != null) {

					DialogPropmt<Major> items = new DialogPropmt<Major>(
							getActivity(), mHolderView.txtvMajor, ListDataSet
									.getInstance().getInMajorList(),DialogPropmt.INT_MAJOR);

					items.builderListDialog("רҵ");

				}

			}
		});

		mHolderView.txtvVolunteer
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						if (ListDataSet.getInstance().getVolunteerList() != null) {

							DialogPropmt<Volunteer> items = new DialogPropmt<Volunteer>(
									getActivity(), mHolderView.txtvVolunteer, ListDataSet
											.getInstance().getVolunteerList(),DialogPropmt.INT_VOL);

							items.builderListDialog("־Ը");

						}

					}
				});

	}

	public void replaceFragment(int containerViewId, BasicFragment fragment) {

		FragmentManager fragmentManager = this.getFragmentManager();

		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();

		fragmentTransaction.replace(containerViewId, fragment);

		fragmentTransaction.commit();

	}

	@Override
	public void onDestroy() {

		super.onDestroy();

		this.mContainer = null;

		this.mHolderView = null;

		this.studentInfo = null;

		System.gc();

	}

	private class HolderView {

		TextView txtvProperty;

		TextView txtvMajor;

		TextView txtvVolunteer;

		TextView btnReset;

		TextView btnSubmit;

		EditText edtExaminees;

		EditText edtStuName;

		EditText edtId;

		EditText edtTel;

		RadioGroup rdgSex;

		RadioGroup rdgSchoolType;

		public HolderView() {
		}

		public HolderView(View... vs) {

			for (View v : vs) {

				switch (v.getId()) {

				case R.id.in_edt_examinees:

					this.edtExaminees = (EditText) v;

					break;

				case R.id.in_edt_stuName:

					this.edtStuName = (EditText) v;

					break;

				case R.id.in_edt_id:

					this.edtId = (EditText) v;

					break;

				case R.id.in_edt_tel:

					this.edtTel = (EditText) v;

					break;

				case R.id.in_txt_property:

					this.txtvProperty = (TextView) v;

					break;

				case R.id.in_rdg_sex:

					this.rdgSex = (RadioGroup) v;

					break;

				case R.id.in_txt_major:

					this.txtvMajor = (TextView) v;

					break;

				case R.id.in_txt_volunteer:

					this.txtvVolunteer = (TextView) v;

					break;

				case R.id.in_rdg_school_type:

					this.rdgSchoolType = (RadioGroup) v;

					break;

				case R.id.in_txt_reset:

					this.btnReset = (TextView) v;

					break;

				case R.id.in_txt_submit:

					this.btnSubmit = (TextView) v;

					break;

				}

			}

		}

	}

}
