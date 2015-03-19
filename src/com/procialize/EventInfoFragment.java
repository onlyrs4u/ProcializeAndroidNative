package com.procialize;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.procialize.customClasses.Events;
import com.procialize.database.DBHelper;

public class EventInfoFragment extends SherlockFragment {
	
//	private ListView eventInfo;
	private List<Events> eventsDBList;
	private DBHelper dbHelper;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.event_info_fragment, container, false);
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
	    eventsDBList = new ArrayList<Events>();
	    
//	    eventInfo = (ListView)getActivity().findViewById(R.id.attendees_list);
	    eventsDBList = dbHelper.getEventInfo();
	    
//	    AttendeesListAdapter adapter = new AttendeesListAdapter(getActivity(), speakersDBList);
//	    speakersList.setAdapter(adapter);
	}

}
