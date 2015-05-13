package com.main.v;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;



public class NavigationDrawerFragment extends Fragment {

	private NavigationDrawerCallbacks mCallbacks;


	//抽屉事件
	private ActionBarDrawerToggle mDrawerToggle;

	//抽屉布局对象
	private DrawerLayout mDrawerLayout;
	
	//抽屉菜单布局
	private View mDrawerView;
	
	private View mFragmentContainerView;
	

	public NavigationDrawerFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
		//初始化个控件
		init();
		
	}

	
	private void init()
	{		
		//实例化各个控件对象
		instanceWeiget();
		//设置各个控件的事件监听器
		setWeigetEvent();	
		//设置控件显示的数据
		setWeigetData();
		
	}
	
	
	private ExpandableListView menuList ;
	
	private CircleImageView head;
	
	private TextView headName;
	
	private void instanceWeiget()
	{
		
		this.menuList = (ExpandableListView) mDrawerView.findViewById(R.id.menuLit);
		
		this.head = (CircleImageView) mDrawerView.findViewById(R.id.drawer_img_head);
		
		this.headName = (TextView) mDrawerView.findViewById(R.id.drawer_txt_head_name);
				
	}
	
	
	private void setWeigetEvent()
	{
		
		this.head.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				int userInfoFragment = -1;
				
				mCallbacks.onNavigationDrawerItemSelected(userInfoFragment);
				
//				mDrawerLayout.closeDrawer(mFragmentContainerView);
				
			}
		});
				
		
		
		this.menuList.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				mCallbacks.onNavigationDrawerChildItemSelected(groupPosition, childPosition);
				
				//mDrawerLayout.closeDrawer(mFragmentContainerView);
				
				return false;
			}
		});
				
		this.menuList.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				
				mCallbacks.onNavigationDrawerItemSelected(groupPosition);
				
				//mDrawerLayout.closeDrawer(mFragmentContainerView);
				
				return false;
			}
		});
		
		
	}
	
	
	
	private void setWeigetData()
	{
		
		if(getActivity() instanceof TeacherMenuActivity)
		{
		
		ExpandableListAdapter adapter = ((TeacherMenuActivity)getActivity()).getAdapter();
		
		this.menuList.setAdapter(adapter);
		
		}
		
		if(getActivity() instanceof ManageMenuActivity)
		{
		
		ExpandableListAdapter adapter = ((ManageMenuActivity)getActivity()).getAdapter();
		
		this.menuList.setAdapter(adapter);
		
		}
		
	}
	
	
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mDrawerView = inflater.inflate(
				R.layout.fragment_navigation_drawer, container, false);
		
		return mDrawerView;
	}

	
	
	public boolean isDrawerOpen() {
		return mDrawerLayout != null
				&& mDrawerLayout.isDrawerOpen(mFragmentContainerView);
	}


	public void setUp(View fragmentContainerView, DrawerLayout drawerLayout) {
		
		mFragmentContainerView = fragmentContainerView;
		
		mDrawerLayout = drawerLayout;
		
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		
		mDrawerToggle = new IActionBarDrawerToggle(
				getActivity(),
				mDrawerLayout,
				R.drawable.ic_drawer, 
				R.string.open_navigation_drawer,
				R.string.close_navigation_drawer);
		//绘制抽屉图标
		mDrawerToggle.syncState();
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		mDrawerLayout.openDrawer(mFragmentContainerView);
	}

	

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
    	
        super.onConfigurationChanged(newConfig);
        
        mDrawerToggle.onConfigurationChanged(newConfig);
        
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        if (mDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
	
	
	
	private final class IActionBarDrawerToggle 
	extends ActionBarDrawerToggle{

		public IActionBarDrawerToggle(
				Activity activity,
				DrawerLayout drawerLayout, 
				int drawerImageRes,
				int openDrawerContentDescRes, 
				int closeDrawerContentDescRes) 
		{
			
			super(activity, 
					drawerLayout, 
					drawerImageRes, 
					openDrawerContentDescRes,
					closeDrawerContentDescRes);
			
		}
	
		
		@Override
		public void onDrawerClosed(View drawerView) {
			super.onDrawerClosed(drawerView);
			
			getActivity().invalidateOptionsMenu();
			
			
		}
		
		
		@Override
		public void onDrawerOpened(View drawerView) {
			super.onDrawerOpened(drawerView);
			
			getActivity().invalidateOptionsMenu();
			
			
		}
		
		
	}
	
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (NavigationDrawerCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					"Activity must implement NavigationDrawerCallbacks.");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
	}



	private ActionBar getActionBar() {
		return getActivity().getActionBar();
	}

	

	public static interface NavigationDrawerCallbacks {
		

		void onNavigationDrawerItemSelected(int groupPosition);
		
		void onNavigationDrawerChildItemSelected(int groupPosition,int childPosition);
		
	}
	
}

