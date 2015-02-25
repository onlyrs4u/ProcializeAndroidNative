package com.procialize;

import java.lang.reflect.Field;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class Fragment1 extends SherlockFragment {
	// Declare Variable
	private FragmentTabHost mTabHost;
	String url = "";
	String app_username = "";
	String app_password = "";
	String provider_name = "";
	String validate_id = "";
	String first_name = "";
	String last_name = "";
	String email = "";
	String gender = "";
	String country = "";
	String language = "";
	String location = "";
	String profile_image = "";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// Create FragmentTabHost
		mTabHost = new FragmentTabHost(getSherlockActivity());
		url = getArguments().getString("url_to_create");
		app_username = getArguments().getString("app_username");
		app_password = getArguments().getString("app_password");
		provider_name = getArguments().getString("provider_name");
		validate_id = getArguments().getString("validate_id");
		first_name = getArguments().getString("first_name");
		last_name = getArguments().getString("last_name");
		email = getArguments().getString("email");
		gender = getArguments().getString("gender");
		country = getArguments().getString("country");
		language = getArguments().getString("language");
		location = getArguments().getString("location");
		profile_image = getArguments().getString("profile_image");
		
		Bundle wallArgs = new Bundle();
		wallArgs.putString("url_to_create", url);
		wallArgs.putString("app_username", app_username);
		wallArgs.putString("app_password", app_password);
		wallArgs.putString("provider_name", provider_name);
		wallArgs.putString("validate_id", validate_id);
		wallArgs.putString("first_name", first_name);
		wallArgs.putString("last_name", last_name);
		wallArgs.putString("email", email);
		wallArgs.putString("gender", gender);
		wallArgs.putString("country", country);
		wallArgs.putString("language", language);
		wallArgs.putString("location", location);
		wallArgs.putString("profile_image", profile_image);
		
		// Locate fragment1.xml to create FragmentTabHost
		mTabHost.setup(getSherlockActivity(), getChildFragmentManager(), R.layout.fragment1);
		// Create Tab 1
		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("", getResources().getDrawable(R.drawable.wall_selector)), WallFragment_POST.class, wallArgs);
		// Create Tab 2
		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("", getResources().getDrawable(R.drawable.agenda_selector)), AgendaFragment.class, null);
		// Create Tab 3
		mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("", getResources().getDrawable(R.drawable.attendees_selector)), AttendeesListFragment.class, null);
		// Create Tab 4
		mTabHost.addTab(mTabHost.newTabSpec("tab4").setIndicator("", getResources().getDrawable(R.drawable.exhibitors_selector)), ExhibitorsListFragment.class, null);
		
		return mTabHost;
	}

	// Detach FragmentTabHost
	@Override
	public void onDetach() {
		super.onDetach();

		try {
			Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);

		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	// Remove FragmentTabHost 
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mTabHost = null;
	}

}
