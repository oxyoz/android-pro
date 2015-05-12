package com.oz.enroll.adapter;

import com.oz.enroll.data.SubmitStudentInfo;
import com.oz.enroll.fragment.InAddFragment;
import com.oz.enroll.fragment.OutAddFragment;
import com.oz.fragment.BasicFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class AddTypeFragmentAdapter extends FragmentStatePagerAdapter {

	
	public AddTypeFragmentAdapter(FragmentManager fm) {
		super(fm);
		
	}
	
	@Override
	public Fragment getItem(int index) {
		
		SubmitStudentInfo.getStudentInfo().setEmptyAllProperty();
		
		BasicFragment fs = null;
		
		switch(index)
		{
		
		case 0:
		
			fs = new InAddFragment();
			
			break;
			
		case 1:
			
			fs = new OutAddFragment();
			
			break;
		}
		
		return fs;
	}

	@Override
	public int getCount() {
		
		return 2;
	}

	
}
