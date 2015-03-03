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
import com.procialize.adapters.SavedSpeakerListAdapter;
import com.procialize.customClasses.Bookmarked;
import com.procialize.database.DBHelper;

public class SavedSpeakerFragment extends SherlockFragment {
	
	private ListView savedExhibitorList;
	private List<Bookmarked> savedSpeakersDBList;
	private DBHelper dbHelper;
	private SavedSpeakerListAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.saved_speakers_fragment, container, false);
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
	    savedSpeakersDBList = new ArrayList<Bookmarked>();
	    
	    savedExhibitorList = (ListView)getActivity().findViewById(R.id.saved_speakers_list);
	    savedSpeakersDBList = dbHelper.getSavedSpeakersList();
	    
	    adapter = new SavedSpeakerListAdapter(getActivity(), savedSpeakersDBList);
	    savedExhibitorList.setAdapter(adapter);
	    
	    savedExhibitorList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Bookmarked specificSpeaker = adapter.getSpeakerFromList(position);
				
				Intent speakerDetail = new Intent(getActivity(), SavedSpeakerDetailPage.class);
				speakerDetail.putExtra("SpecificSpeaker", specificSpeaker);
				getActivity().startActivity(speakerDetail);
			}
		});

	    
	}


}