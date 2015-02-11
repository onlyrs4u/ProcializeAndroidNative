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
import com.procialize.adapters.SpeakersListAdapter;
import com.procialize.customClasses.Attendees;
import com.procialize.database.DBHelper;

public class SpeakersListFragment extends SherlockFragment {
	
	private ListView speakersList;
	private List<Attendees> speakersDBList;
	private DBHelper dbHelper;
	SpeakersListAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.speakers_fragment, container, false);
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
	    speakersDBList = new ArrayList<Attendees>();
	    
	    speakersList = (ListView)getActivity().findViewById(R.id.speakers_list);
	    speakersDBList = dbHelper.getSpeakersList();
	    
	    adapter = new SpeakersListAdapter(getActivity(), speakersDBList);
	    speakersList.setAdapter(adapter);
	    
	    speakersList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Attendees specificSpeaker = adapter.getSpeakerFromList(position);
				
				Intent speakerDetail = new Intent(getActivity(), SpeakerDetailPage.class);
				speakerDetail.putExtra("SpeakerName", specificSpeaker.getAttendee_first_name()+" "+specificSpeaker.getAttendee_last_name());
				speakerDetail.putExtra("SpeakerDesignation", specificSpeaker.getAttendee_designation());
				speakerDetail.putExtra("SpeakerCompany", specificSpeaker.getAttendee_company_name());
				speakerDetail.putExtra("SpeakerCity", specificSpeaker.getAttendee_city());
				getActivity().startActivity(speakerDetail);
			}
		});
	}

}
