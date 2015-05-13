package com.main.v;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.lgl.v.AddTimeCardFragment;
import com.lgl.v.ShowTimeCardFragment;
import com.mian.c.MenuStringArray;
import com.oz.v.AddedStudentInfoFragment;
import com.oz.v.BasicFragment;
import com.oz.v.UserInfoFragment;
import com.zyp.v.FeedbackFragment;
import com.zzy.v.ResetPasswordFragment;

public class ManageMenuActivity extends BasicActivity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private DrawerLayout mDrawerLayout;

	private View mFragmentContainerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// ȥ����Ϣ��
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.activity_teacher_menu);
		// ���ñ���������ɫ
		getActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.color.loginBgColor));
		// ����FrameLayout��Ƭ
		replaceFragment(new UserInfoFragment());

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		mFragmentContainerView = findViewById(R.id.navigation_drawer);

		mNavigationDrawerFragment.setUp(mFragmentContainerView, mDrawerLayout);

	}

	/* ���ؼ��¼������� */
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode) {

		case KeyEvent.KEYCODE_BACK:

			if (!mDrawerLayout.isDrawerOpen(mFragmentContainerView)) {

				AlertDialog.Builder builder = new Builder(this);

				builder.setTitle("��ʾ");

				builder.setMessage("\n\t\t\t\t\t\t\t\t\tȷ���˳�ϵͳ��\n\t");

				builder.setPositiveButton("ȷ��", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						System.exit(0);

						System.gc();

					}
				});

				builder.setNeutralButton("ȡ��", null);

				builder.show();
			} else {

				mDrawerLayout.closeDrawer(mFragmentContainerView);

			}

			break;

		}

		return false;
	};

	// �˵�����
	String[] group = MenuStringArray.groupManager;
	// ����˵��Ӳ˵�
	String[][] child = MenuStringArray.childManager;

	public ExpandableListAdapter getAdapter() {

		ExpandableListAdapter adapter = new ExpandableListAdapter(this, group,
				child);
		return adapter;
	}

	@Override
	public void onNavigationDrawerItemSelected(int groupPosition) {

		switch (groupPosition) {

		case -1:

			replaceFragment(new UserInfoFragment());

			break;

		case 0:

			replaceFragment(new AddedStudentInfoFragment());

			break;

		case 1:

			// ///////////////

			break;

		case 2:

			replaceFragment(new ShowTimeCardFragment());

			break;

		case 3:

			// //////////////

			break;

		}

	}

	@Override
	public void onNavigationDrawerChildItemSelected(int groupPosition,
			int childPosition) {

		switch (groupPosition) {

		case 0:
			switch (childPosition) {

			case 0:

				// replaceFragment(new InAddFragment());

				break;

			case 1:

				break;
			}

			break;

		case 1:
			switch (childPosition) {

			case 0:

				// replaceFragment(new InAddFragment());

				break;

			case 1:

				break;
			}

			break;

		case 2:
			switch (childPosition) {

			case 0:

				replaceFragment(new AddTimeCardFragment());

				break;
			}

			break;

		case 3:

			switch (childPosition) {

			case 0:

				// replaceFragment(new InAddFragment());

				break;
			}

			break;

		case 4:

			switch (childPosition) {

			case 0:

				replaceFragment(new ResetPasswordFragment());

				break;
			}

			break;

		case 5:
			// //////////////////////////
			break;

		case 6:

			switch (childPosition) {

			case 0:

				replaceFragment(new FeedbackFragment());

				break;
			}

			break;
		}

	}

	private void replaceFragment(BasicFragment fragment) {

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.container, fragment);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		transaction.commit();

	}

}