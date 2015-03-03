package com.procialize;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.procialize.adapters.CustomDrawerAdapter;
import com.procialize.customClasses.DrawerItem;

public class MainActivity_old extends Activity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	CustomDrawerAdapter adapter;

	List<DrawerItem> dataList;
	TabHost tabHost;
	TabSpec tab1,tab2,tab3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initializing
		dataList = new ArrayList<DrawerItem>();
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		// Add Drawer Item to dataList
		dataList.add(new DrawerItem(true)); // adding a spinner to the list
		
		dataList.add(new DrawerItem(R.drawable.ic_action_about, "Event Info")); // adding a header to the list
		dataList.add(new DrawerItem(R.drawable.ic_action_camera, "Notifications"));// adding a header to the list
		
		dataList.add(new DrawerItem("Messages", R.drawable.ic_action_cloud));
		dataList.add(new DrawerItem("Alerts", R.drawable.ic_action_email));
		dataList.add(new DrawerItem("Meeting Requests", R.drawable.ic_action_gamepad));
		dataList.add(new DrawerItem("Reminders", R.drawable.ic_action_good));

		dataList.add(new DrawerItem(R.drawable.ic_action_group, "My Calendar")); // adding a header to the list
		dataList.add(new DrawerItem(R.drawable.ic_action_help, "Leaderboard")); // adding a header to the list
				
		dataList.add(new DrawerItem(R.drawable.ic_action_import_export, "Bookmarked")); // adding a header to the list
		dataList.add(new DrawerItem("Saved Exhibitors", R.drawable.ic_action_labels));
		dataList.add(new DrawerItem("Saved Attendees", R.drawable.ic_action_search));
		dataList.add(new DrawerItem("Saved Speakers", R.drawable.ic_action_settings));
		
		dataList.add(new DrawerItem(R.drawable.ic_action_video, "Speakers")); // adding a header to the list
		dataList.add(new DrawerItem(R.drawable.ic_drawer, "Sponsors")); // adding a header to the list

		adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item, dataList, null);

		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		/*if (savedInstanceState == null) { 
			if (dataList.get(0).isSpinner() & dataList.get(1).getTitle() != null) {
				SelectItem(2);
			} else if (dataList.get(0).getTitle() != null) {
				SelectItem(1);
			} else {
				SelectItem(0);
			}
		}*/
		
		/*//Tab Widget Sample Code
		tabHost  = (TabHost)findViewById(R.id.tab_host);
		
		tab1 = tabHost .newTabSpec("First Tab");
        tab2 = tabHost .newTabSpec("Second Tab");
        tab3 = tabHost .newTabSpec("Third tab");
        
     // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
         tab1.setIndicator("Tab1");
        // tab1.setContent(new Intent(this,Tab1Activity.class));
         tab1.setContent(new Intent(this,ProfileActivity.class));
         
         tab2.setIndicator("Tab2");
         //tab2.setContent(new Intent(this,Tab2Activity.class));
         tab2.setContent(new Intent(this,EditProfileActivity.class));

         tab3.setIndicator("Tab3");
         //tab3.setContent(new Intent(this,Tab3Activity.class));
         tab3.setContent(new Intent(this,AppSignUp.class));
         
         tabHost.setup();
         *//** Add the tabs  to the TabHost to display. *//*
         tabHost.addTab(tab1);
         tabHost.addTab(tab2);
         tabHost.addTab(tab3);*/
         
		TabHost tabs = (TabHost)findViewById(R.id.tab_host);
		
		tabs.setup();
		
		TabHost.TabSpec spec;
		
		spec = tabs.newTabSpec("tag1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Analog Clock");
		tabs.addTab(spec);
	
		spec = tabs.newTabSpec("tag2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("DigitalClock");
		tabs.addTab(spec);	
		
		spec = tabs.newTabSpec("tag3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Button");
		tabs.addTab(spec);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void SelectItem(int possition) {

		Fragment fragment = null;
		Bundle args = new Bundle();
		switch (possition) {

		case 1:
			/*fragment = new EventInfoFragment();
			args.putString(EventInfoFragment.ITEM_NAME, dataList.get(possition).getItemName());
			args.putInt(EventInfoFragment.IMAGE_RESOURCE_ID, dataList.get(possition).getTitleImgResId());*/
			break;
		case 2:
			/*fragment = new MessagesFragment();
			args.putString(MessagesFragment.ITEM_NAME, dataList.get(possition).getItemName());
			args.putInt(MessagesFragment.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());*/
			break;
		case 3:
			/*fragment = new AlertsFragment();
			args.putString(AlertsFragment.ITEM_NAME, dataList.get(possition).getItemName());
			args.putInt(AlertsFragment.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());*/
			break;
		case 4:
			/*fragment = new MeetingRequestsFragment();
			args.putString(MeetingRequestsFragment.ITEM_NAME, dataList.get(possition).getItemName());
			args.putInt(MeetingRequestsFragment.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());*/
			break;
		case 5:
			/*fragment = new ReminderFragment();
			args.putString(ReminderFragment.ITEM_NAME, dataList.get(possition).getItemName());
			args.putInt(ReminderFragment.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());*/
			break;
		case 6:
			fragment = new MyCalendarFragment();
			args.putString(MyCalendarFragment.ITEM_NAME, dataList.get(possition).getItemName());
			args.putInt(MyCalendarFragment.IMAGE_RESOURCE_ID, dataList.get(possition).getTitleImgResId());
			break;	
		case 7:
			fragment = new LeaderboardFragment();
			args.putString(LeaderboardFragment.ITEM_NAME, dataList.get(possition).getItemName());
			args.putInt(LeaderboardFragment.IMAGE_RESOURCE_ID, dataList.get(possition).getTitleImgResId());
			break;
		case 8:
			/*fragment = new SavedExhibitorFragment();
			args.putString(SavedExhibitorFragment.ITEM_NAME, dataList.get(possition).getItemName());
			args.putInt(SavedExhibitorFragment.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());*/
			break;
		case 9:
			/*fragment = new SavedAttendeeFragment();
			args.putString(SavedAttendeeFragment.ITEM_NAME, dataList.get(possition).getItemName());
			args.putInt(SavedAttendeeFragment.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());*/
			break;
		case 10:
			/*fragment = new SavedSpeakerFragment();
			args.putString(SavedSpeakerFragment.ITEM_NAME, dataList.get(possition).getItemName());
			args.putInt(SavedSpeakerFragment.IMAGE_RESOURCE_ID, dataList.get(possition).getImgResID());*/
			break;
		case 11:
			/*fragment = new SpeakersListFragment();
			args.putString(SpeakersListFragment.ITEM_NAME, dataList.get(possition).getItemName());
			args.putInt(SpeakersListFragment.IMAGE_RESOURCE_ID, dataList.get(possition).getTitleImgResId());*/
			break;
		case 12:
			/*fragment = new SponsorsFragment();
			args.putString(SponsorsFragment.ITEM_NAME, dataList.get(possition).getItemName());
			args.putInt(AlertsFragment.IMAGE_RESOURCE_ID, dataList.get(possition).getTitleImgResId());*/
			break;	
			
		default:
			break;
		}

		fragment.setArguments(args);
		FragmentManager frgManager = getFragmentManager();
		frgManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

		mDrawerList.setItemChecked(possition, true);
		setTitle(dataList.get(possition).getItemName());
		mDrawerLayout.closeDrawer(mDrawerList);

	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		return false;
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//			if (dataList.get(position).getTitle() == null) {
				SelectItem(position);
//			}

		}
	}

}
