package com.oz.enroll.service;

import java.util.List;

import org.json.JSONException;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.oz.bean.StatusMessage;
import com.oz.enroll.bean.ItAll;
import com.oz.enroll.config.Action;
import com.oz.enroll.config.ActionCode;
import com.oz.enroll.dao.Check;
import com.oz.enroll.dao.FindItAll;
import com.oz.enroll.dao.GetProperty;
import com.oz.enroll.dao.PreAdd;
import com.oz.enroll.dao.PreAddOutsize;
import com.oz.enroll.dao.SearchSchool;
import com.oz.enroll.data.AddressId;
import com.oz.enroll.data.CheckResult;
import com.oz.enroll.data.ListDataSet;
import com.oz.enroll.data.SubmitResult;
import com.oz.enroll.data.SubmitStudentInfo;

public class HandlerNetworkService extends Service implements Runnable {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private int what;

	private String action;

	private String checkContent;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		this.what = intent.getIntExtra("what", 0);

		this.checkContent = intent.getStringExtra("check");

		this.action = intent.getAction();

		new Thread(this).start();

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void run() {

		if (Action.service.SERVICE_ENROLL_ADDSTUDENTINFO.equals(this.action)) {

		}

		if (Action.service.SERVICE_ENROLL_INADD.equals(this.action)) {

			switch (this.what) {

			case ActionCode.InAdd.PREPARE: {

				PreAdd preAdd = new PreAdd();

				GetProperty getProperty = new GetProperty();

				try {

					ListDataSet.getInstance().setPropertyList(
							getProperty.getProperty());

					ListDataSet.getInstance().setInMajorList(preAdd.getMajor());

					ListDataSet.getInstance().setStaffSchoolList(
							preAdd.getStaffSchool());

					actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_INADD);

				} catch (JSONException e) {

					e.printStackTrace();

				}

			}
				break;

			case ActionCode.InAdd.CHECK_EXAMINEES_RESULT: {

				Check check = new Check();

				StatusMessage sm = check.check(checkContent);

				CheckResult.setCheckResult(sm);

				actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_INADD);

			}
				break;

			case ActionCode.InAdd.CHECK_SCHOOL_RESULT: {

				Check check = new Check();

				StatusMessage sm = check.checkSchool(checkContent);

				CheckResult.setCheckResult(sm);

				actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_INADD);

			}
				break;

			case ActionCode.InAdd.SUBMIT_STUDENT_INFO: {

				com.oz.enroll.dao.AddStudentInfo addStudentInfo = new com.oz.enroll.dao.AddStudentInfo(
						SubmitStudentInfo.getStudentInfo());

				StatusMessage sm = addStudentInfo.postAddStudentInfo();

				SubmitResult.setSubmitResult(sm);

				actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_INADD);

			}
				break;

			case ActionCode.InAdd.ACQUIESCE: {

			}
				break;

			}

		}

		if (Action.service.SERVICE_ENROLL_INADDDEF.equals(this.action)) {

		}

		if (Action.service.SERVICE_ENROLL_INADDOTHER.equals(this.action)) {

			switch (this.what) {

			case ActionCode.InAddOther.CHECK_SCHOOL_RESULT: {

				Check check = new Check();

				StatusMessage sm = check.checkSchool(checkContent);

				CheckResult.setCheckResult(sm);

				actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_INADDOTHER);

			}
				break;
			
			case ActionCode.InAddOther.NETHERLANDS_SELECT: {

				SearchSchool ss = new SearchSchool();

				try {

					ListDataSet.getInstance()
							.setNetherlandsList(
									ss.getNetherlands(AddressId.getInstance()
											.getpId()));
					
					actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_INADDOTHER);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
				break;

			case ActionCode.InAddOther.COUNTY_SELECT: {

				SearchSchool ss = new SearchSchool();

				try {

					ListDataSet.getInstance().setCountyList(
							ss.getCounty(AddressId.getInstance().getnId()));
					
					actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_INADDOTHER);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
				break;

			case ActionCode.InAddOther.SCHOOL_SELECT: {

				SearchSchool ss = new SearchSchool();

				try {

					ListDataSet.getInstance().setSchoolList(
							ss.getSchool(AddressId.getInstance().getcId()));

					actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_INADDOTHER);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
				break;

			}

		}

		if (Action.service.SERVICE_ENROLL_OUTADD.equals(this.action)) {

			switch (this.what) {
			
			case ActionCode.OutAdd.SUBMIT_STUDENT_INFO: {

				com.oz.enroll.dao.AddStudentInfo addStudentInfo = new com.oz.enroll.dao.AddStudentInfo(
						SubmitStudentInfo.getStudentInfo());

				StatusMessage sm = addStudentInfo.postAddOutsize();

				SubmitResult.setSubmitResult(sm);

				actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_OUTADD);

			}
				break;
				
			case ActionCode.OutAdd.PREPARE:{
				
				PreAddOutsize preAddOutsize = new PreAddOutsize();
				
				GetProperty getProperty = new GetProperty();
				
				try {
					
					ListDataSet.getInstance().setOutMajorList(preAddOutsize.getMajor());
				
					ListDataSet.getInstance().setPropertyList(getProperty.getProperty());
					
					actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_OUTADD);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}break;
			
			case ActionCode.OutAdd.CHECK_EXAMINEES_RESULT:{
				
				Check check = new Check();

				StatusMessage sm = check.check(checkContent);

				CheckResult.setCheckResult(sm);

				actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_OUTADD);
				
			}break;
			
			
			case ActionCode.OutAdd.COUNTY_SELECT:{
				
				SearchSchool ss = new SearchSchool();

				try {

					ListDataSet.getInstance().setCountyList(
							ss.getCounty(AddressId.getInstance().getnId()));
					
					actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_OUTADD);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}break;
			
			case ActionCode.OutAdd.NETHERLANDS_SELECT:{
				
				SearchSchool ss = new SearchSchool();

				try {

					ListDataSet.getInstance()
							.setNetherlandsList(
									ss.getNetherlands(AddressId.getInstance()
											.getpId()));
					
					actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_OUTADD);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}break;
			
			case ActionCode.OutAdd.PROVINCE_SELECT:{
				
				SearchSchool ss = new SearchSchool();

				try {

					ListDataSet.getInstance()
							.setPrivnceList(
									ss.getPrivnce());
					
					actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_OUTADD);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}break;
			
			}
			
			
			
			
			
			
		}

		if (Action.service.SERVICE_ENROLL_STUDENTINFO.equals(this.action)) {

			Log.i("HandlerNetworkService run", action + "---" + this.what);

			switch (this.what) {
			case ActionCode.StudentInfo.SELECT_ALL_STUDENT_INFO: {

				try {

					FindItAll itAll = new FindItAll();

					List<ItAll> listItAll = itAll.finditall();

					ListDataSet.getInstance().setItAll(listItAll);

					actionReceiver(Action.UIReceiver.RECEIVER_ENROLL_STUDENTINFO);

				} catch (JSONException e) {

					e.printStackTrace();

				}

			}
				break;

			case ActionCode.StudentInfo.PROMPT: {

			}
				break;

			case ActionCode.StudentInfo.ACQUIESCE: {

			}
				break;

			}

		}

	}

	private void actionReceiver(String action) {

		Intent intent = new Intent(action);

		intent.putExtra("what", this.what);

		this.sendBroadcast(intent);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

}
