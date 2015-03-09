package com.procialize;

import java.lang.reflect.Field;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class NotificationFragment extends SherlockFragment {

	private FragmentTabHost mNotificationTabHost;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// Create FragmentTabHost
		mNotificationTabHost = new FragmentTabHost(getSherlockActivity());
		
		// Locate fragment1.xml to create FragmentTabHost
		mNotificationTabHost.setup(getSherlockActivity(), getChildFragmentManager(), R.layout.notification_fragment);
		// Create Tab 1
		mNotificationTabHost.addTab(mNotificationTabHost.newTabSpec("tab1").setIndicator("Messages"), MessagesFragment.class, null);
		// Create Tab 2
//		mNotificationTabHost.addTab(mNotificationTabHost.newTabSpec("tab2").setIndicator("Alerts"), AgendaFragment.class, null);
		// Create Tab 3
		mNotificationTabHost.addTab(mNotificationTabHost.newTabSpec("tab3").setIndicator("Meeting Requests"), MeetingRequestsFragment.class, null);
		// Create Tab 4
//		mNotificationTabHost.addTab(mNotificationTabHost.newTabSpec("tab4").setIndicator("Reminders"), ReminderFragment.class, null);
		
		return mNotificationTabHost;
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
		mNotificationTabHost = null;
	}
	
}
