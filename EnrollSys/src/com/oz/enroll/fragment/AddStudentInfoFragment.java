package com.oz.enroll.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup;

import com.oz.enroll.adapter.AddTypeFragmentAdapter;
import com.oz.enroll.data.AddressId;
import com.oz.fragment.BasicFragment;
import com.oz.main.R;

public class AddStudentInfoFragment extends BasicFragment {

	private View mContainer;

	private HolderView mHolderView;

	@Override
	public void actionReceiver(Context context, Intent intent) {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mContainer = inflater.inflate(R.layout.fragment_add_student_info,
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
		// 实例化各个控件
		initWidget();
		// 设置各个控件的数据
		updateWidgetAttr();
		// 设置各个控件的事件监听器
		addWidgetEvent();

	}

	private void initWidget() {

		mHolderView = new HolderView(
				this.mContainer
						.findViewById(R.id.add_student_info_rdg_add_type),
				this.mContainer.findViewById(R.id.add_student_info_v_pointer),
				this.mContainer.findViewById(R.id.add_student_info_vp_form));

	}

	private void updateWidgetAttr() {

		AddTypeFragmentAdapter adapter = new AddTypeFragmentAdapter(
				this.getFragmentManager());

		mHolderView.vpForm.setAdapter(adapter);

	}

	private int intCheckedRadioButtonId;

	private void addWidgetEvent() {

		mHolderView.vpForm
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						
						switch (arg0) {
						case 0:

							mHolderView.rdgType
									.check(R.id.add_student_info_rd_in);
							
							break;

						case 1:

							mHolderView.rdgType
									.check(R.id.add_student_info_rd_out);
							
							break;
						}

					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
					}
				});

		this.intCheckedRadioButtonId = mHolderView.rdgType
				.getCheckedRadioButtonId();

		mHolderView.rdgType
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {

						case R.id.add_student_info_rd_in:

							if (intCheckedRadioButtonId != R.id.add_student_info_rd_in) {

								intCheckedRadioButtonId = R.id.add_student_info_rd_in;

								Animation anim = AnimationUtils.loadAnimation(
										getActivity(), R.anim.rd_in_anim);

								anim.setFillAfter(true);
								
								mHolderView.vPointer.startAnimation(anim);

								mHolderView.vpForm.setCurrentItem(0);
								
							}

							break;

						case R.id.add_student_info_rd_out:

							if (intCheckedRadioButtonId != R.id.add_student_info_rd_out) {

								intCheckedRadioButtonId = R.id.add_student_info_rd_out;

								Animation anim = AnimationUtils.loadAnimation(
										getActivity(), R.anim.rd_out_anim);

								anim.setFillAfter(true);
								
								mHolderView.vPointer.startAnimation(anim);

								mHolderView.vpForm.setCurrentItem(1);
								
							}

							break;

						}

					}
				});

	}

	@Override
	public void onDestroy() {

		super.onDestroy();
	}

	private class HolderView {

		RadioGroup rdgType;

		View vPointer;

		ViewPager vpForm;

		public HolderView() {
		}

		public HolderView(View... vs) {

			for (View v : vs) {

				switch (v.getId()) {

				case R.id.add_student_info_rdg_add_type:

					rdgType = (RadioGroup) v;

					break;

				case R.id.add_student_info_v_pointer:

					vPointer = v;

					break;

				case R.id.add_student_info_vp_form:

					vpForm = (ViewPager) v;

					break;

				}

			}

		}

	}

}
