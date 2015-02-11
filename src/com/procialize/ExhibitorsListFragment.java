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
import com.procialize.adapters.ExhibitorsListAdapter;
import com.procialize.customClasses.Attendees;
import com.procialize.database.DBHelper;


public class ExhibitorsListFragment extends SherlockFragment {
	
	private ListView exhibitorsList;
	private List<Attendees> exhibitorDBList;
	private DBHelper dbHelper;
	ExhibitorsListAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.exhibitors_fragment, container, false);
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
	    exhibitorDBList = new ArrayList<Attendees>();
	    
	    exhibitorsList = (ListView)getActivity().findViewById(R.id.exhibitors_list);
	    exhibitorDBList = dbHelper.getExhibitorsList();
	    
	    adapter = new ExhibitorsListAdapter(getActivity(), exhibitorDBList);
	    exhibitorsList.setAdapter(adapter);
	    
	    exhibitorsList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Attendees specificExhibitor = adapter.getExhibitorFromList(position);
				
				Intent exhibitorDetail = new Intent(getActivity(), ExhibitorDetailPage.class);
				exhibitorDetail.putExtra("ExhibitorName", specificExhibitor.getAttendee_first_name()+" "+specificExhibitor.getAttendee_last_name());
				exhibitorDetail.putExtra("ExhibitorDesignation", specificExhibitor.getAttendee_designation());
				exhibitorDetail.putExtra("ExhibitorCompany", specificExhibitor.getAttendee_company_name());
				exhibitorDetail.putExtra("ExhibitorCity", specificExhibitor.getAttendee_city());
				getActivity().startActivity(exhibitorDetail);
			}
		});
	}

}
