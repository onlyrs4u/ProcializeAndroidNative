package com.procialize;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.procialize.adapters.MeetingRequestsListAdapter;
import com.procialize.adapters.SavedSpeakerListAdapter;
import com.procialize.customClasses.Bookmarked;
import com.procialize.customClasses.UserNotifications;
import com.procialize.database.DBHelper;

public class MeetingRequestsFragment extends SherlockFragment {
	
	private ListView meetingRequestList;
	private List<UserNotifications> meetingRequestDBList;
	private DBHelper dbHelper;
	private MeetingRequestsListAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.meeting_requests_fragment, container, false);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    // TODO Auto-generated method stub
	    super.onActivityCreated(savedInstanceState);
	    
	    Runtime rt = Runtime.getRuntime();
	    long maxMemory = rt.maxMemory();
	    Log.v("onCreate", "maxMemory:" + Long.toString(maxMemory));
	    
	    dbHelper = new DBHelper(getActivity());
	    meetingRequestDBList = new ArrayList<UserNotifications>();
	    
	    meetingRequestList = (ListView)getActivity().findViewById(R.id.meeting_requests_list);
	    meetingRequestDBList = dbHelper.getUserMeetingRequests();
	    
	    adapter = new MeetingRequestsListAdapter(getActivity(), meetingRequestDBList);
	    meetingRequestList.setAdapter(adapter);
	    
	    /*meetingRequestList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Bookmarked specificSpeaker = adapter.getSpeakerFromList(position);
				
				Intent speakerDetail = new Intent(getActivity(), SavedSpeakerDetailPage.class);
				speakerDetail.putExtra("SpecificSpeaker", specificSpeaker);
				getActivity().startActivity(speakerDetail);
			}
		});*/

	    
	}

}