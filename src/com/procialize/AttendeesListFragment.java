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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.procialize.adapters.AttendeesListAdapter;
import com.procialize.customClasses.Attendees;
import com.procialize.database.DBHelper;

public class AttendeesListFragment extends SherlockFragment {
	
	private ListView attendeesList;
	private List<Attendees> attendeesDBList;
	private DBHelper dbHelper;
	private AttendeesListAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.attendees_fragment, container, false);
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
	    attendeesDBList = new ArrayList<Attendees>();
	    
	    attendeesList = (ListView)getActivity().findViewById(R.id.attendees_list);
	    attendeesDBList = dbHelper.getAttendeesList();
	    
	    adapter = new AttendeesListAdapter(getActivity(), attendeesDBList);
	    attendeesList.setAdapter(adapter);
	    
	    attendeesList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Attendees specificAttendee = adapter.getAttendeeFromList(position);
				
				Intent attendeeDetail = new Intent(getActivity(), AttendeeDetailPage.class);
				attendeeDetail.putExtra("AttendeeName", specificAttendee.getAttendee_first_name()+" "+specificAttendee.getAttendee_last_name());
				attendeeDetail.putExtra("AttendeeDesignation", specificAttendee.getAttendee_designation());
				attendeeDetail.putExtra("AttendeeCompany", specificAttendee.getAttendee_company_name());
				attendeeDetail.putExtra("AttendeeCity", specificAttendee.getAttendee_city());
				getActivity().startActivity(attendeeDetail);
			}
		});
	    
	}

}
