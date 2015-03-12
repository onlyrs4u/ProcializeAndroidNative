package com.procialize;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.procialize.adapters.SavedAttendeeListAdapter;
import com.procialize.customClasses.Bookmarked;
import com.procialize.database.DBHelper;
import com.procialize.network.ServiceHandler;
import com.procialize.parsers.BookmarksParser;
import com.procialize.utility.Constants;

public class SavedAttendeeFragment extends SherlockFragment {
	
	private ProgressDialog pDialog;
	private DBHelper procializeDB;
	private SQLiteDatabase db;
	String url_ = "";
	Constants constant;
	
	ArrayList<Bookmarked> bookmarksList;
	
	private ListView savedAttendeesListView;
	private BookmarksParser bookmarkParser;
	private List<Bookmarked> savedAttendeedDBList;
	private SavedAttendeeListAdapter BookmarkedAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.saved_attendees_fragment, container, false);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    // TODO Auto-generated method stub
	    super.onActivityCreated(savedInstanceState);
	    
	    Runtime rt = Runtime.getRuntime();
	    long maxMemory = rt.maxMemory();
	    Log.v("onCreate", "maxMemory:" + Long.toString(maxMemory));
	    
	    procializeDB = new DBHelper(getActivity());
		db = procializeDB.getWritableDatabase();
		
		savedAttendeedDBList = new ArrayList<Bookmarked>();
		
		constant = new Constants();
		url_ = constant.WEBSERVICE_URL + constant.WEBSERVICE_FOLDER + constant.BOOKMARK_URL + Constants.API_ACCESS_TOKEN;
		
		bookmarksList = new ArrayList<Bookmarked>();
		savedAttendeesListView = (ListView)getActivity().findViewById(R.id.saved_attendees_list);
	    
	    new GetBookmarkDetails().execute();
	}
	
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetBookmarkDetails extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url_, ServiceHandler.POST, nameValuePair);
			Log.d("Response: ", "> " + jsonStr);
			
			//Bookmark Parser
			bookmarkParser = new BookmarksParser();
			bookmarksList = bookmarkParser.BookmarksInfo_Parser(jsonStr);
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			
			procializeDB.insertBookmarkedInfo(bookmarksList, db);
			
			if (pDialog.isShowing())
				pDialog.dismiss();
			
			/**
			 * Updating parsed JSON data into ListView
			 * */
			savedAttendeedDBList = procializeDB.getSavedAttendeeList();
			BookmarkedAdapter = new SavedAttendeeListAdapter(getActivity(), savedAttendeedDBList);
			savedAttendeesListView.setAdapter(BookmarkedAdapter);
			savedAttendeesListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					Bookmarked specificAttendee = BookmarkedAdapter.getSavedAttendeeFromList(position);
					
					Intent attendeeDetail = new Intent(getActivity(), SavedAttendeeDetailPage.class);
					attendeeDetail.putExtra("SpecificAttendee", specificAttendee);
					getActivity().startActivity(attendeeDetail);
				}
			});
						
			Log.d("Created URL : ", ">>>>> " + url_);
		}
	}

}