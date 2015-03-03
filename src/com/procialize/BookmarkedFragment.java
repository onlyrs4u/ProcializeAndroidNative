package com.procialize;

import java.lang.reflect.Field;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class BookmarkedFragment extends SherlockFragment {

	private FragmentTabHost mBookmarkedTabHost;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// Create FragmentTabHost
		mBookmarkedTabHost = new FragmentTabHost(getSherlockActivity());
		
		// Locate fragment1.xml to create FragmentTabHost
		mBookmarkedTabHost.setup(getSherlockActivity(), getChildFragmentManager(), R.layout.bookmarked_fragment);
		// Create Tab 1
		mBookmarkedTabHost.addTab(mBookmarkedTabHost.newTabSpec("tab1").setIndicator("Saved Attendees"), SavedAttendeeFragment.class, null);
		// Create Tab 2
		mBookmarkedTabHost.addTab(mBookmarkedTabHost.newTabSpec("tab2").setIndicator("Saved Exhibitors"), SavedExhibitorFragment.class, null);
		// Create Tab 3
		mBookmarkedTabHost.addTab(mBookmarkedTabHost.newTabSpec("tab3").setIndicator("Saved Speakers"), SavedSpeakerFragment.class, null);
		
		return mBookmarkedTabHost;
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
		mBookmarkedTabHost = null;
	}
			
}
