package com.procialize;

import java.util.ArrayList;
import java.util.List;

import org.brickred.socialauth.Profile;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.procialize.adapters.CustomDrawerAdapter;
import com.procialize.adapters.MenuListAdapter;
import com.procialize.customClasses.DrawerItem;
import com.procialize.utility.Constants;

public class MainActivity extends SherlockFragmentActivity {

	// Declare Variable
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	MenuListAdapter mMenuAdapter;
	String[] title;
	String[] subtitle;
	int[] icon;
	Fragment fragment1 = new Fragment1();
	Fragment speakerListFrag = new SpeakersListFragment();
	Fragment notificationFrag = new NotificationFragment();
	Fragment eventInfoFrag = new EventInfoFragment();
	Fragment bookmarkedFrag = new BookmarkedFragment();
	CustomDrawerAdapter adapter;
	List<DrawerItem> dataList;
	
	private String url_to_create = "";
	Constants constant;
	Profile profileMap;
	String provider_name;
	String appUsername;
	String appPassword;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drawer_main);
		
		constant = new Constants();
		provider_name = getIntent().getStringExtra("provider");
		appUsername = getIntent().getStringExtra("appUsername");
		appPassword = getIntent().getStringExtra("appPassword");
		
		if(provider_name.equalsIgnoreCase("") || provider_name.equalsIgnoreCase(null) || provider_name.equalsIgnoreCase("facebook"))
		{
			url_to_create = constant.WEBSERVICE_URL + constant.WEBSERVICE_FOLDER + constant.FB_LOGIN;
		}
		else if(provider_name.equalsIgnoreCase("") || provider_name.equalsIgnoreCase(null) || provider_name.equalsIgnoreCase("linkedin"))
		{
			url_to_create = constant.WEBSERVICE_URL + constant.WEBSERVICE_FOLDER + constant.LINKEDIN_LOGIN;
		}
		else if(provider_name.equalsIgnoreCase("") || provider_name.equalsIgnoreCase(null) || provider_name.equalsIgnoreCase("manual_login"))
		{
			url_to_create = constant.WEBSERVICE_URL + constant.WEBSERVICE_FOLDER + constant.MANUAL_LOGIN;
		}
		
		if(!(provider_name.equalsIgnoreCase("manual_login")))
		{
			profileMap = (Profile) getIntent().getSerializableExtra("profile");
			Log.d("Custom-UI", "Validate ID = " + profileMap.getValidatedId());
			Log.d("Custom-UI", "First Name  = " + profileMap.getFirstName());
			Log.d("Custom-UI", "Last Name   = " + profileMap.getLastName());
			Log.d("Custom-UI", "Email       = " + profileMap.getEmail());
			Log.d("Custom-UI", "Gender  	= " + profileMap.getGender());
			Log.d("Custom-UI", "Country  	= " + profileMap.getCountry());
			Log.d("Custom-UI", "Language  	= " + profileMap.getLanguage());
			Log.d("Custom-UI", "Location 	= " + profileMap.getLocation());
			Log.d("Custom-UI", "Profile Image URL  = " + profileMap.getProfileImageURL());
		}
		
		dataList = new ArrayList<DrawerItem>();

		// Locate DrawerLayout in drawer_main.xml
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Locate ListView in drawer_main.xml
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set a custom shadow that overlays the main content when the drawer opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		// Pass results to MenuListAdapter Class
//		mMenuAdapter = new MenuListAdapter(this, title, subtitle, icon);

		// Set the MenuListAdapter to the ListView
//		mDrawerList.setAdapter(mMenuAdapter);
		
		// Add Drawer Item to dataList
		dataList.add(new DrawerItem(true)); // adding a spinner to the list
				
		dataList.add(new DrawerItem(R.drawable.ic_action_about, "Home")); //Home Header
		
		dataList.add(new DrawerItem(R.drawable.ic_action_about, "Event Info"));  //Event Info Header
		dataList.add(new DrawerItem(R.drawable.ic_action_camera, "Notifications")); //Notification Header
		dataList.add(new DrawerItem(R.drawable.ic_action_group, "My Calendar")); //My Calendar Header
		dataList.add(new DrawerItem(R.drawable.ic_action_help, "Leaderboard")); //Leaderboard Header
		dataList.add(new DrawerItem(R.drawable.ic_action_import_export, "Bookmarked")); //Bookmarked Header		
		dataList.add(new DrawerItem(R.drawable.ic_action_video, "Speakers")); //Main Speaker List Header
		dataList.add(new DrawerItem(R.drawable.ic_drawer, "Our Sponsors")); //Our Sponsors Header
		
		adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item, dataList);
		
		mDrawerList.setAdapter(adapter);
		
		// Capture button clicks on side menu
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.titlebackgroundcolor));
		getSupportActionBar().setCustomView(R.layout.custom_title);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);	
		}
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}*/

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {

			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}

		return super.onOptionsItemSelected(item);
	}

	// The click listener for ListView in the navigation drawer
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (dataList.get(position).isSpinner()) {
				selectItem(-1);	
			}else{
				selectItem(position);
			}
		}
	}
	
	private void selectItem(int position) {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		Bundle args = new Bundle();
		// Locate Position
		switch (position) {
		case -1:
			Toast.makeText(MainActivity.this, "Coding sucks", Toast.LENGTH_SHORT).show();
			break;
		case 0:
			args.putString("url_to_create", url_to_create);
			args.putString("provider_name", provider_name);
			
			if(!(provider_name.equalsIgnoreCase("manual_login")))
			{
				args.putString("validate_id", profileMap.getValidatedId());
				args.putString("first_name", profileMap.getFirstName());
				args.putString("last_name", profileMap.getLastName());
				args.putString("email", profileMap.getEmail());
				args.putString("gender", profileMap.getGender());
				args.putString("country", profileMap.getCountry());
				args.putString("language", profileMap.getLanguage());
				args.putString("location", profileMap.getLocation());
				args.putString("profile_image", profileMap.getProfileImageURL());	
			}
			else
			{
				args.putString("app_password", appPassword);
				args.putString("app_username", appUsername);
			}
			fragment1.setArguments(args);
			ft.replace(R.id.content_frame, fragment1);
			break;
		case 1:
			//Home - Wall
			Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
			args.putString("url_to_create", url_to_create);
			args.putString("provider_name", provider_name);
			
			if(!(provider_name.equalsIgnoreCase("manual_login")))
			{
				args.putString("validate_id", profileMap.getValidatedId());
				args.putString("first_name", profileMap.getFirstName());
				args.putString("last_name", profileMap.getLastName());
				args.putString("email", profileMap.getEmail());
				args.putString("gender", profileMap.getGender());
				args.putString("country", profileMap.getCountry());
				args.putString("language", profileMap.getLanguage());
				args.putString("location", profileMap.getLocation());
				args.putString("profile_image", profileMap.getProfileImageURL());	
			}
			else
			{
				args.putString("app_password", appPassword);
				args.putString("app_username", appUsername);
			}
			fragment1.setArguments(args);
			ft.replace(R.id.content_frame, fragment1);
			break;
		case 2:
			//Event Info
			Toast.makeText(MainActivity.this, "Event Info ", Toast.LENGTH_SHORT).show();
			ft.replace(R.id.content_frame, eventInfoFrag);
			break;
		case 3:
			//Notifications
			Toast.makeText(MainActivity.this, "Notifications", Toast.LENGTH_SHORT).show();
			ft.replace(R.id.content_frame, notificationFrag);
			break;
		case 4:
			//My Calendar
			Toast.makeText(MainActivity.this, "My Calendar", Toast.LENGTH_SHORT).show();
			break;
		case 5:
			//Leaderboard
			Toast.makeText(MainActivity.this, "Leaderboard", Toast.LENGTH_SHORT).show();
			break;
		case 6:
			//Bookmarked
			Toast.makeText(MainActivity.this, "Bookmarked", Toast.LENGTH_SHORT).show();
			ft.replace(R.id.content_frame, bookmarkedFrag);
			break;
		case 7:
			//Main Speakers List
			Toast.makeText(MainActivity.this, "Main Speakers List", Toast.LENGTH_SHORT).show();
			ft.replace(R.id.content_frame, speakerListFrag);
			break;
		case 8:
			//Our Sponsors
			Toast.makeText(MainActivity.this, "Our Sponsors", Toast.LENGTH_SHORT).show();
			break;
		}
		ft.commit();
		mDrawerList.setItemChecked(position, true);
		// Close drawer
		mDrawerLayout.closeDrawer(mDrawerList);
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
	
}
