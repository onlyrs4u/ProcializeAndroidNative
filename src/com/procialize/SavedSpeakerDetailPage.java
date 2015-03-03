package com.procialize;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.customClasses.Bookmarked;
import com.procialize.libraries.ImageLoader;
import com.procialize.libraries.MLRoundedImageView;
import com.procialize.network.ServiceHandler;
import com.procialize.utility.Constants;

public class SavedSpeakerDetailPage extends Activity implements OnClickListener {
	
	MLRoundedImageView speaker_thumbnail;
	TextView speaker_detail_header;
	TextView speaker_name;
    TextView speaker_designation;
    TextView speaker_comp_name;
    TextView speaker_city;
    
    ImageView sendMessage;
    ImageView setMeeting;
    MLRoundedImageView saveAttendee;
    MLRoundedImageView shareAttendee;
    
//  Loader image - will be shown before loading image
    int loader = R.drawable.ic_launcher;
    Constants constant = new Constants();
    Bookmarked specificSpeaker;
    private ProgressDialog pDialog;
    
    String url_ = "";
    String api_access_token_ = "";
    String subject_id_ = "";
    String subject_type_ = "";
    String event_id_ = "";
    String type_ = "";
	String transaction_type_ = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.speakers_detail);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
		
		specificSpeaker = new Bookmarked();
		specificSpeaker = (Bookmarked)getIntent().getSerializableExtra("SpecificSpeaker");
        
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/HERO.ttf");
        
        speaker_thumbnail = (MLRoundedImageView) findViewById(R.id.speaker_detail_thumbnail);
        
        // Image url
        String image_url = "";
        image_url = constant.WEBSERVICE_URL + constant.SPEAKER_IMAGE_URL + specificSpeaker.getReceiver_attendee_image();
        
        ImageLoader imgLoader = new ImageLoader(SavedSpeakerDetailPage.this);
        
        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView 
        imgLoader.DisplayImage(image_url, loader, speaker_thumbnail);
        
        speaker_detail_header = (TextView) findViewById(R.id.speaker_detail_header);
        speaker_detail_header.setTypeface(typeFace);
		
		speaker_name = (TextView) findViewById(R.id.speaker_detail_name);
		speaker_name.setText(specificSpeaker.getReceiver_first_name()+" "+specificSpeaker.getReceiver_last_name());
		speaker_name.setTypeface(typeFace);
        
		speaker_designation = (TextView) findViewById(R.id.speaker_detail_designation);
		speaker_designation.setText(specificSpeaker.getReceiver_designation());
		speaker_designation.setTypeface(typeFace);
        
		speaker_comp_name = (TextView) findViewById(R.id.speaker_detail_comp_name);
		speaker_comp_name.setText(specificSpeaker.getReceiver_company_name());
		speaker_comp_name.setTypeface(typeFace);
        
		speaker_city = (TextView) findViewById(R.id.speaker_detail_city);
		speaker_city.setText(specificSpeaker.getReceiver_attendee_city());
		speaker_city.setTypeface(typeFace);
		
		url_ = constant.WEBSERVICE_URL + constant.WEBSERVICE_FOLDER + constant.SAVE_SHARE_SOCIAL;
        api_access_token_ = constant.API_ACCESS_TOKEN;
        subject_id_ = specificSpeaker.getReceiver_attendee_id();
        subject_type_ = specificSpeaker.getReceiver_attendee_type();
        event_id_ = "1"; //specificAttendee.getEvent_id()
//        type_ = ""; //Share - Sh, Save - Sav
        transaction_type_ = "Social";// Other option - delete
        
        sendMessage = (ImageView) findViewById(R.id.speaker_detail_send_message);
        setMeeting = (ImageView) findViewById(R.id.speaker_detail_set_meeting);
        saveAttendee = (MLRoundedImageView) findViewById(R.id.speaker_detail_save_attendee);
        shareAttendee = (MLRoundedImageView) findViewById(R.id.speaker_detail_share_attendee);
        
        sendMessage.setOnClickListener(this);
        setMeeting.setOnClickListener(this);
        saveAttendee.setOnClickListener(this);
        shareAttendee.setOnClickListener(this);
        
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view == sendMessage)
		{
			Toast.makeText(SavedSpeakerDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
		else if(view == setMeeting)
		{
			Toast.makeText(SavedSpeakerDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
		else if(view == saveAttendee)
		{
			type_ = "Sav";
//			new SaveShareAttendee().execute();
		}
		else if(view == shareAttendee)
		{
			type_ = "Sh";
//			new SaveShareAttendee().execute();
		}
	}
	
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class SaveShareAttendee extends AsyncTask<Void, Void, Void> {

		String error = "";
		String message = "";
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(SavedSpeakerDetailPage.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		    
			nameValuePair.add(new BasicNameValuePair("api_access_token", api_access_token_));
			nameValuePair.add(new BasicNameValuePair("subject_id", subject_id_));
			nameValuePair.add(new BasicNameValuePair("subject_type", subject_type_));
			nameValuePair.add(new BasicNameValuePair("event_id", event_id_));
			nameValuePair.add(new BasicNameValuePair("type", type_));
			nameValuePair.add(new BasicNameValuePair("transaction_type", transaction_type_));
			
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url_, ServiceHandler.POST, nameValuePair);
			Log.d("Response: ", "> " + jsonStr);
			
			if(jsonStr != null){
				try {
					
					JSONObject jsonResult = new JSONObject(jsonStr);
					error = jsonResult.getString("error");
					message = jsonResult.getString("msg");
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			
			if (pDialog.isShowing())
				pDialog.dismiss();
			
			AlertDialog.Builder builder = new AlertDialog.Builder(SavedSpeakerDetailPage.this);
			builder.setMessage(message)
				   .setTitle(error);
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// User clicked OK button
				}
			});
			AlertDialog alert = builder.create();
			alert.show();
            
			
			Log.d("Created URL : ", ">>>>> " + url_);
		}
	}

}