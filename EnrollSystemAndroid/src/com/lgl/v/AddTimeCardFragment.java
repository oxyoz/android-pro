package com.lgl.v;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lgl.c.AddTimeCard;
import com.lgl.c.RequestHandlerCode;
import com.lgl.c.RequestThreadCode;
import com.lgl.c.SubmitTimeCard;
import com.main.m.StatusMessage;
import com.main.v.R;
import com.oz.v.BasicFragment;
import com.oz.v.MeSpinnerAdapter;

public class AddTimeCardFragment extends BasicFragment {

	private View viewContainer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		viewContainer = inflater.inflate(R.layout.fragment_add_time_card,
				container, false);

		return viewContainer;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		// 初始化各个控件
		init();
	}

	/* 多线程处理 */
	@Override
	public void run() {

		switch (this.requestThreadCode) {

		case SUBMIT_TIME_CARD: {

			AddTimeCard addTimeCard = new AddTimeCard();

			requestHandler(RequestHandlerCode.SUBMIT_ADD_TIME_CARD_RESULT,
					addTimeCard.addTimeCard(SubmitTimeCard.getInstance()));

			break;
		}

		default:
			break;
		}

	}

	/* 异步消息处理 */
	@Override
	public void handlerMethod(Message msg) {

		if (msg.what == RequestHandlerCode.SUBMIT_ADD_TIME_CARD_RESULT.ordinal()) {
			StatusMessage statusMessage = (StatusMessage) msg.obj;

			if (statusMessage.getStatus().endsWith("1")) {

				replaceFragment(new ShowTimeCardFragment());
				
				Toast.makeText(getActivity(), statusMessage.getMessage(),
						Toast.LENGTH_LONG).show();
			} else {

				Toast.makeText(getActivity(), statusMessage.getMessage(),
						Toast.LENGTH_LONG).show();

			}
		}

	}

	// 多线程请求码
	private RequestThreadCode requestThreadCode = RequestThreadCode.ACQUIESCE;

	// 请求开启多线程
	private void requestThread(RequestThreadCode requestThreadCode) {

		// 设置请求代码
		this.requestThreadCode = requestThreadCode;
		// 开启请求处理线程
		new Thread(this).start();

	}

	// 请求异步处理
	private void requestHandler(RequestHandlerCode requestHandlerCode) {

		Message msg = new Message();
		// 设置请求代码
		msg.what = requestHandlerCode.ordinal();
		// 发送异步消息
		getHandler().sendMessage(msg);

	}

	// 请求异步处理
	private void requestHandler(RequestHandlerCode requestHandlerCode,
			StatusMessage statusMessage) {

		Message msg = new Message();
		// 设置请求代码
		msg.what = requestHandlerCode.ordinal();
		// 设置数据
		msg.obj = statusMessage;
		// 发送异步消息
		getHandler().sendMessage(msg);

	}

	private void init() {
		// 实例化各个控件对象
		instanceWeiget();
		// 设置各个控件的事件监听器
		setWeigetEvent();
		// 设置控件显示的数据
		setWeigetData();
	}

	private void setWeigetData() {

	}

	private String startDate;

	private String endDate;

	private void setWeigetEvent() {

		edt_addTimeCardStartTime
				.setOnFocusChangeListener(new OnFocusChangeListener() {

					@Override
					public void onFocusChange(View v, boolean hasFocus) {

						if (hasFocus) {
							Calendar c = Calendar.getInstance();

							int year = c.get(Calendar.YEAR);

							int month = c.get(Calendar.MONTH);

							int day = c.get(Calendar.DAY_OF_MONTH);

							DatePickerDialog dateDialog = new DatePickerDialog(
									getActivity(), new OnDateSetListener() {

										@Override
										public void onDateSet(DatePicker view,
												int year, int monthOfYear,
												int dayOfMonth) {

											Toast.makeText(
													getActivity(),
													"当前日期：" + year + "年"
															+ monthOfYear + "月"
															+ dayOfMonth + "日",
													Toast.LENGTH_LONG).show();

											startDate = year + "-"
													+ monthOfYear + "-"
													+ dayOfMonth + " ";

											TimePickerDialog time = new TimePickerDialog(
													getActivity(),
													new OnTimeSetListener() {

														@Override
														public void onTimeSet(
																TimePicker view,
																int hourOfDay,
																int minute) {

															Toast.makeText(
																	getActivity(),
																	"设置时间："
																			+ hourOfDay
																			+ ":"
																			+ minute
																			+ ":"
																			+ "00",
																	Toast.LENGTH_LONG)
																	.show();

															startDate = startDate
																	+ hourOfDay
																	+ ":"
																	+ minute
																	+ ":"
																	+ "0";
															
															edt_addTimeCardStartTime.setText(startDate);
															
															SubmitTimeCard.getInstance().setStartTime(startDate);
															
														}
													}, 0, 0, true);

											time.show();

										}
									}, year, month, day);

							dateDialog.show();

						}

					}
				});
		
		
		
		edt_addTimeCardEndTime
		.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {

				if (hasFocus) {
					Calendar c = Calendar.getInstance();

					int year = c.get(Calendar.YEAR);

					int month = c.get(Calendar.MONTH);

					int day = c.get(Calendar.DAY_OF_MONTH);

					DatePickerDialog dateDialog = new DatePickerDialog(
							getActivity(), new OnDateSetListener() {

								@Override
								public void onDateSet(DatePicker view,
										int year, int monthOfYear,
										int dayOfMonth) {

									Toast.makeText(
											getActivity(),
											"当前日期：" + year + "年"
													+ monthOfYear + "月"
													+ dayOfMonth + "日",
											Toast.LENGTH_LONG).show();

									endDate = year + "-"
											+ monthOfYear + "-"
											+ dayOfMonth + " ";

									TimePickerDialog time = new TimePickerDialog(
											getActivity(),
											new OnTimeSetListener() {

												@Override
												public void onTimeSet(
														TimePicker view,
														int hourOfDay,
														int minute) {

													Toast.makeText(
															getActivity(),
															"设置时间："
																	+ hourOfDay
																	+ ":"
																	+ minute
																	+ ":"
																	+ "00",
															Toast.LENGTH_LONG)
															.show();

													endDate = endDate
															+ hourOfDay
															+ ":"
															+ minute
															+ ":"
															+ "0";
													
													edt_addTimeCardEndTime.setText(endDate);
													
													SubmitTimeCard.getInstance().setEndTime(endDate);
													
												}
											}, 0, 0, true);

									time.show();

								}
							}, year, month, day);

					dateDialog.show();

				}

			}
		});
		
		
		edt_addTimeCardContent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder builder = new Builder(getActivity());
				
				builder.setTitle("选择内容");
				
				Calendar c = Calendar.getInstance();

				int year = c.get(Calendar.YEAR);
				
				final String[] data = {year+"高招",year+"单招"}; 
				
				MeSpinnerAdapter adapter = new MeSpinnerAdapter(getActivity(),data);
				
				builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						SubmitTimeCard.getInstance().setContent(data[which]);
						
						edt_addTimeCardContent.setText(data[which]);
	
					}
				
				});
				
				builder.show();
				
			}
		});
		
		
		btn_submitTimeCard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
								
				requestThread(RequestThreadCode.SUBMIT_TIME_CARD);
				
			}
		});
		

	}

	private EditText edt_addTimeCardStartTime;

	private EditText edt_addTimeCardEndTime;

	private TextView edt_addTimeCardContent;

	private TextView btn_submitTimeCard;
	
	private void instanceWeiget() {

		btn_submitTimeCard = (TextView) this.viewContainer
				.findViewById(R.id.btn_submit_time_card);
		
		edt_addTimeCardStartTime = (EditText) this.viewContainer
				.findViewById(R.id.edt_add_time_card_startTime);

		edt_addTimeCardEndTime = (EditText) this.viewContainer
				.findViewById(R.id.edt_add_time_card_endTime);

		edt_addTimeCardContent = (TextView) this.viewContainer
				.findViewById(R.id.edt_add_time_card_content);

	}

	
	private void replaceFragment(BasicFragment fragment) {

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.container, fragment);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		transaction.commit();

	}
	
	
}
