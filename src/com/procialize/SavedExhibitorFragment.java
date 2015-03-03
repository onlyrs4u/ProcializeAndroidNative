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
import com.procialize.adapters.SavedExhibitorListAdapter;
import com.procialize.customClasses.Attendees;
import com.procialize.customClasses.Bookmarked;
import com.procialize.database.DBHelper;

public class SavedExhibitorFragment extends SherlockFragment {
	
	private ListView savedExhibitorList;
	private List<Bookmarked> savedExhibitorsDBList;
	private DBHelper dbHelper;
	private SavedExhibitorListAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.saved_exhibitors_fragment, container, false);
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
	    savedExhibitorsDBList = new ArrayList<Bookmarked>();
	    
	    savedExhibitorList = (ListView)getActivity().findViewById(R.id.saved_exhibitors_list);
	    savedExhibitorsDBList = dbHelper.getSavedExhibitorList();
	    
	    adapter = new SavedExhibitorListAdapter(getActivity(), savedExhibitorsDBList);
	    savedExhibitorList.setAdapter(adapter);
	    
	    savedExhibitorList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Bookmarked specificExhibitor = adapter.getExhibitorFromList(position);
				
				Intent exhibitorDetail = new Intent(getActivity(), SavedExhibitorDetailPage.class);
				exhibitorDetail.putExtra("SpecificExhibitor", specificExhibitor);
				getActivity().startActivity(exhibitorDetail);
			}
		});
	    
	}


}