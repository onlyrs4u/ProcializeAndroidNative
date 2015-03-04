package com.procialize;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.procialize.adapters.CustomSpinnerAdapter;
import com.procialize.adapters.WallNotificationListAdapter;
import com.procialize.customClasses.Attendees;
import com.procialize.customClasses.Events;
import com.procialize.customClasses.UserProfile;
import com.procialize.customClasses.UserNotifications;
import com.procialize.customClasses.WallNotifications;
import com.procialize.database.DBHelper;
import com.procialize.network.ServiceHandler;
import com.procialize.parsers.AttendeesParser;
import com.procialize.parsers.EventInfoParser;
import com.procialize.parsers.UserNotificationParser;
import com.procialize.parsers.UserProfileParser;
import com.procialize.parsers.WallNotificationsParser;
import com.procialize.utility.Constants;


public class WallFragment_POST extends SherlockFragment implements OnRefreshListener  {
	
	private ProgressDialog pDialog;
	
	SwipeRefreshLayout swipeLayout; 
	Constants constant;
    
    JSONArray event_list = null;
    JSONArray attendees_list = null;
    
    ArrayList<Events> eventsList;
    ArrayList<Attendees> attendeesList;
    ArrayList<UserProfile> userData;
    ArrayList<WallNotifications> wallNotificationList;
    ArrayList<UserNotifications> userNotificationList;
    
    String url_ = "";
    String app_username_ = "";
    String app_password_ = "";
    String provider_name_ = "";
    String validate_id_ = "";
	String first_name_ = "";
	String last_name_ = "";
	String email_ = "";
	String gender_ = "";
	String country_ = "";
	String language_ = "";
	String location_ = "";
	String profile_image_ = "";
	
	private DBHelper procializeDB;
	private SQLiteDatabase db;
	private EventInfoParser eventParser;
	private AttendeesParser attendeeParser;
	private UserProfileParser userParser;
	private WallNotificationsParser wallNotificationParser;
	private UserNotificationParser userNotificationParser;
	
	private List<WallNotifications> wallNotificationDBList;
	private ArrayList<UserProfile> userProfileDBList;
	private ListView wallNotificationListView;
	private WallNotificationListAdapter WallNotificationAdapter;
	private CustomSpinnerAdapter userProfileAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.wall_fragment, container, false);
		shareUserProfileListener mListener = (shareUserProfileListener) getActivity();
    	mListener.onSharingUserProfile(userProfileDBList);
		return rootView;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    // TODO Auto-generated method stub
	    super.onActivityCreated(savedInstanceState);
	    
	    Runtime rt = Runtime.getRuntime();
	    long maxMemory = rt.maxMemory();
	    Log.v("onCreate", "maxMemory:" + Long.toString(maxMemory));
	    
	    swipeLayout = (SwipeRefreshLayout)getActivity().findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        
        constant = new Constants();
	    
	    url_ = getArguments().getString("url_to_create");
	    app_username_ = getArguments().getString("app_username");
	    app_password_ = getArguments().getString("app_password");
	    provider_name_ = getArguments().getString("provider_name");
	    validate_id_ = getArguments().getString("validate_id");
	    first_name_ = getArguments().getString("first_name");
	    last_name_ = getArguments().getString("last_name");
	    email_ = getArguments().getString("email");
	    gender_ = getArguments().getString("gender");
	    country_ = getArguments().getString("country");
	    language_ = getArguments().getString("language");
	    location_ = getArguments().getString("location");
	    profile_image_ = getArguments().getString("profile_image");
	    
	    eventsList = new ArrayList<Events>();
	    attendeesList = new ArrayList<Attendees>();
	    userData = new ArrayList<UserProfile>();
	    wallNotificationList = new ArrayList<WallNotifications>();
	    userNotificationList = new ArrayList<UserNotifications>();
	    
	    procializeDB = new DBHelper(getActivity());
		db = procializeDB.getWritableDatabase();
		
		wallNotificationListView = (ListView)getActivity().findViewById(R.id.wall_list);
	    
		if(url_.contains(constant.INDEPENDENT_WALL_NOTIFICATION_URL)){
			procializeDB.clearWallNotifcationTable();
			new FetchWallNotification().execute();
		}else{
			new GetEventDetails().execute();
		}
	    
	    
	}
	@Override
    public void onRefresh() {
        // TODO Auto-generated method stub
        /*new Handler().postDelayed(new Runnable() {
            @Override public void run() {
            	procializeDB.clearWallNotifcationTable();
    			new FetchWallNotification().execute();
            }
        }, 5000);*/
		
		
		procializeDB.clearWallNotifcationTable();
		new FetchWallNotification().execute();
        
    }
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetEventDetails extends AsyncTask<Void, Void, Void> {

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
		    
			if(provider_name_.equalsIgnoreCase("facebook"))
			{
				nameValuePair.add(new BasicNameValuePair("email", email_));
				nameValuePair.add(new BasicNameValuePair("first_name", first_name_));
				nameValuePair.add(new BasicNameValuePair("last_name", last_name_));
				nameValuePair.add(new BasicNameValuePair("profile_pic", profile_image_));
				nameValuePair.add(new BasicNameValuePair("fb_id", validate_id_));
				nameValuePair.add(new BasicNameValuePair("public_profile_url", ""));
			}
			else if(provider_name_.equalsIgnoreCase("linkedin"))
			{
				nameValuePair.add(new BasicNameValuePair("email", email_));
				nameValuePair.add(new BasicNameValuePair("first_name", first_name_));
				nameValuePair.add(new BasicNameValuePair("last_name", last_name_));
				nameValuePair.add(new BasicNameValuePair("profile_pic", profile_image_));
				nameValuePair.add(new BasicNameValuePair("linkedin_id", validate_id_));
			}
			else
			{
				nameValuePair.add(new BasicNameValuePair("email", app_username_));
				nameValuePair.add(new BasicNameValuePair("password", app_password_));
			}
			
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url_, ServiceHandler.POST, nameValuePair);
			Log.d("Response: ", "> " + jsonStr);
			
			//Event Parser
			eventParser = new EventInfoParser();
			eventsList = eventParser.EventInfo_Parser(jsonStr);
			
			//Attendees Parser
			attendeeParser = new AttendeesParser();
			attendeesList = attendeeParser.Attendee_Parser(jsonStr);
			
			//User Profile Parser
			userParser = new UserProfileParser();
			userData = userParser.UserData_Parser(jsonStr);
			
			//Wall Notification Parser
			wallNotificationParser = new WallNotificationsParser();
			wallNotificationList = wallNotificationParser.wallNotification_Parser(jsonStr);
			
			//User Notification Parser
			userNotificationParser = new UserNotificationParser();
			userNotificationList = userNotificationParser.userNotification_Parser(jsonStr);
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			
			fireSqliteQueries();
			
			if (pDialog.isShowing())
				pDialog.dismiss();
			
			/**
			 * Updating parsed JSON data into ListView
			 * */
			wallNotificationDBList = procializeDB.getWallNotifications();
			WallNotificationAdapter = new WallNotificationListAdapter(getActivity(), wallNotificationDBList);
			wallNotificationListView.setAdapter(WallNotificationAdapter);
			
			userProfileDBList = procializeDB.getUserProfile();
			userProfileAdapter = new CustomSpinnerAdapter(getActivity(), 0, userProfileDBList);
//			userProfileAdapter.myCustomItem.setAdapter(userProfileAdapter);
			
			Log.d("Created URL : ", ">>>>> " + url_);
		}
	}
	
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class FetchWallNotification extends AsyncTask<Void, Void, Void> {

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
			
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url_, ServiceHandler.POST);
			Log.d("Response: ", "> " + jsonStr);
			
			//Wall Notification Parser
			wallNotificationParser = new WallNotificationsParser();
			wallNotificationList = wallNotificationParser.wallNotification_Parser(jsonStr);
						
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog

			procializeDB.insertWallNotificationData(wallNotificationList, db);
			
			if (pDialog.isShowing())
				pDialog.dismiss();
			
			/**
			 * Updating parsed JSON data into ListView
			 * */
			wallNotificationDBList = procializeDB.getWallNotifications();
			WallNotificationAdapter = new WallNotificationListAdapter(getActivity(), wallNotificationDBList);
			wallNotificationListView.setAdapter(WallNotificationAdapter);
			
			userProfileDBList = procializeDB.getUserProfile();
			userProfileAdapter = new CustomSpinnerAdapter(getActivity(), 0, userProfileDBList);
//			userProfileAdapter.myCustomItem.setAdapter(userProfileAdapter);
			
			Log.d("Created Wall Notification URL : ", ">>>>> " + url_);
			
			new Handler().postDelayed(new Runnable() {
	            @Override public void run() {
	            	swipeLayout.setRefreshing(false);
	            }
	        }, 3000);
			
		}
	}
		
	private void fireSqliteQueries() {
		// TODO Auto-generated method stub
		
		procializeDB.insertEventInfo(eventsList, db);
		procializeDB.insertSpeakersInfo(attendeesList, db);
		procializeDB.insertAttendeesInfo(attendeesList, db);
		procializeDB.insertExhibitorsInfo(attendeesList, db);
		procializeDB.insertUserData(userData, db);
		procializeDB.insertWallNotificationData(wallNotificationList, db);
		procializeDB.insertUserNotificationData(userNotificationList, db);
		
		procializeDB.close();
	}
	
	public interface shareUserProfileListener {
//        public void onSharingUserProfile ();

		public void onSharingUserProfile(ArrayList<UserProfile> userProfileDBList);
    }
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*try {
        	shareUserProfileListener mListener = (shareUserProfileListener) activity;
        	mListener.onSharingUserProfile(userProfileDBList);
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }*/
    }
	
}
