package com.procialize;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.procialize.adapters.MessagesListAdapter;
import com.procialize.customClasses.UserNotifications;
import com.procialize.database.DBHelper;
import com.procialize.network.ServiceHandler;
import com.procialize.parsers.UserNotificationParser;
import com.procialize.utility.Constants;

public class MessagesFragment extends SherlockFragment {
	
	private ProgressDialog pDialog;
	private DBHelper procializeDB;
	private SQLiteDatabase db;
	String url_ = "";
	Constants constant;
	
	ArrayList<UserNotifications> userNotificationList;
	
	private ListView messagesListView;
	private UserNotificationParser userNotificationParser;
	private List<UserNotifications> userNotificationDBList;
	private MessagesListAdapter messagesAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.messages_fragment, container, false);
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
		
		userNotificationDBList = new ArrayList<UserNotifications>();
		
		constant = new Constants();
		url_ = constant.WEBSERVICE_URL + constant.WEBSERVICE_FOLDER + constant.INDEPENDENT_USER_NOTIFICATION_URL + constant.API_ACCESS_TOKEN;
		
		userNotificationList = new ArrayList<UserNotifications>();
		messagesListView = (ListView)getActivity().findViewById(R.id.messages_list);
	    
		procializeDB.clearUserNotifcationTable();
	    new GetUserNotificationDetails().execute();
	}
	
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetUserNotificationDetails extends AsyncTask<Void, Void, Void> {

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
			
			//user notification Parser
			userNotificationParser = new UserNotificationParser();
			userNotificationList = userNotificationParser.userNotification_Parser(jsonStr);
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			
			procializeDB.insertUserNotificationData(userNotificationList, db);
			
			if (pDialog.isShowing())
				pDialog.dismiss();
			
			/**
			 * Updating parsed JSON data into ListView
			 * */
			userNotificationDBList = procializeDB.getUserNotifications();
			messagesAdapter = new MessagesListAdapter(getActivity(), userNotificationDBList);
			messagesListView.setAdapter(messagesAdapter);
			/*messagesListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					UserNotifications userNotify = messagesAdapter.getMessagesFromList(position);
					
					Intent attendeeDetail = new Intent(getActivity(), SavedAttendeeDetailPage.class);
					attendeeDetail.putExtra("SpecificAttendee", userNotify);
					getActivity().startActivity(attendeeDetail);
				}
			});*/
						
			Log.d("Created URL : ", ">>>>> " + url_);
		}
	}

}