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
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.customClasses.Attendees;
import com.procialize.libraries.ImageLoader;
import com.procialize.libraries.MLRoundedImageView;
import com.procialize.network.ServiceHandler;
import com.procialize.utility.Constants;

public class AttendeeDetailPage extends Activity implements OnClickListener {

	MLRoundedImageView attendee_thumbnail;
	TextView attendee_detail_header;
	TextView attendee_name;
	TextView attendee_designation;
	TextView attendee_comp_name;
	TextView attendee_city;

	ImageView sendMessage;
	ImageView setMeeting;
	MLRoundedImageView saveAttendee;
	MLRoundedImageView shareAttendee;

	// Loader image - will be shown before loading image
	int loader = R.drawable.ic_launcher;
	Constants constant = new Constants();
	Attendees specificAttendee;
	private ProgressDialog pDialog;

	String url_ = "";
	String sensMsgUrl = "http://procialize.in/test/API/event_api_call/send_message";
	String api_access_token_ = "";
	String subject_id_ = "";
	String subject_type_ = "";
	String event_id_ = "1";
	String type_ = "";
	String transaction_type_ = "";
	String image_url = "";
	Editable value;
	String sendMsg = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.attendees_detail);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.custom_title);

		specificAttendee = new Attendees();
		specificAttendee = (Attendees) getIntent().getSerializableExtra(
				"SpecificAttendee");

		Typeface typeFace = Typeface.createFromAsset(getAssets(),
				"fonts/HERO.ttf");

		attendee_thumbnail = (MLRoundedImageView) findViewById(R.id.attendee_detail_thumbnail);

		// Image url
		image_url = constant.WEBSERVICE_URL + constant.ATTENDEE_IMAGE_URL
				+ specificAttendee.getAttendee_image();

		ImageLoader imgLoader = new ImageLoader(AttendeeDetailPage.this);

		// whenever you want to load an image from url
		// call DisplayImage function
		// url - image url to load
		// loader - loader image, will be displayed before getting image
		// image - ImageView
		imgLoader.DisplayImage(image_url, loader, attendee_thumbnail);

		attendee_detail_header = (TextView) findViewById(R.id.attendee_detail_header);
		attendee_detail_header.setTypeface(typeFace);

		attendee_name = (TextView) findViewById(R.id.attendee_detail_name);
		attendee_name.setText(specificAttendee.getAttendee_first_name() + " "
				+ specificAttendee.getAttendee_last_name());
		attendee_name.setTypeface(typeFace);

		attendee_designation = (TextView) findViewById(R.id.attendee_detail_designation);
		attendee_designation
				.setText(specificAttendee.getAttendee_designation());
		attendee_name.setTypeface(typeFace);

		attendee_comp_name = (TextView) findViewById(R.id.attendee_detail_comp_name);
		attendee_comp_name.setText(specificAttendee.getAttendee_company_name());
		attendee_name.setTypeface(typeFace);

		attendee_city = (TextView) findViewById(R.id.attendee_detail_city);
		attendee_city.setText(specificAttendee.getAttendee_city());
		attendee_name.setTypeface(typeFace);

		url_ = constant.WEBSERVICE_URL + constant.WEBSERVICE_FOLDER
				+ constant.SAVE_SHARE_SOCIAL;
		api_access_token_ = Constants.API_ACCESS_TOKEN;
		subject_id_ = specificAttendee.getAttendee_id();
		subject_type_ = specificAttendee.getAttendee_type();
		event_id_ = "1"; // specificAttendee.getEvent_id()
		// type_ = ""; //Share - Sh, Save - Sav
		transaction_type_ = "Social";// Other option - delete

		sendMessage = (ImageView) findViewById(R.id.attendee_detail_send_message);
		setMeeting = (ImageView) findViewById(R.id.attendee_detail_set_meeting);
		saveAttendee = (MLRoundedImageView) findViewById(R.id.attendee_detail_save_attendee);
		shareAttendee = (MLRoundedImageView) findViewById(R.id.attendee_detail_share_attendee);

		sendMessage.setOnClickListener(this);
		setMeeting.setOnClickListener(this);
		saveAttendee.setOnClickListener(this);
		shareAttendee.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if (view == sendMessage)

		{
			String msg = "To: " + "Sender";
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("Message");
			alert.setMessage(msg);

			// Set an EditText view to get user input
			final EditText input = new EditText(this);
			alert.setView(input);

			alert.setPositiveButton("Send",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							value = input.getText();
							sendMsg = (String) value.toString();
							new SendMessageAttendee().execute();

						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

						}
					});

			alert.show();

		} else if (view == setMeeting) {
			Toast.makeText(AttendeeDetailPage.this, "Coming soon",
					Toast.LENGTH_SHORT).show();
		} else if (view == saveAttendee) {
			type_ = "Sav";
			new SaveShareAttendee().execute();
		} else if (view == shareAttendee) {
			type_ = "Sh";
			Intent sharingIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
			Uri screenshotUri = Uri.parse(image_url);
			sharingIntent.setType("image/*");
			sharingIntent.putExtra(Intent.EXTRA_TEXT,
					"Body text of the new status");
			sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
			startActivity(Intent.createChooser(sharingIntent,
					"Share image using"));
			// new SaveShareAttendee().execute();
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
			pDialog = new ProgressDialog(AttendeeDetailPage.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

			nameValuePair.add(new BasicNameValuePair("api_access_token",
					api_access_token_));
			nameValuePair
					.add(new BasicNameValuePair("subject_id", subject_id_));
			nameValuePair.add(new BasicNameValuePair("subject_type",
					subject_type_));
			nameValuePair.add(new BasicNameValuePair("event_id", event_id_));
			nameValuePair.add(new BasicNameValuePair("type", type_));
			nameValuePair.add(new BasicNameValuePair("transaction_type",
					transaction_type_));

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url_, ServiceHandler.POST,
					nameValuePair);
			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
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

			AlertDialog.Builder builder = new AlertDialog.Builder(
					AttendeeDetailPage.this);
			builder.setMessage(message).setTitle(error);
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked OK button
						}
					});
			AlertDialog alert = builder.create();
			alert.show();

			Log.d("Created URL : ", ">>>>> " + url_);
		}
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class SendMessageAttendee extends AsyncTask<Void, Void, Void> {

		String error = "";
		String message = "";

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(AttendeeDetailPage.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
			
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();

			nameValuePair.add(new BasicNameValuePair("api_access_token",
					"077e29b11be80ab57e1a2ecabb7da330"));
			nameValuePair.add(new BasicNameValuePair("event_id", event_id_));
			nameValuePair.add(new BasicNameValuePair("message_text", sendMsg));
			nameValuePair
					.add(new BasicNameValuePair("target_attendee_id", "249"));
			nameValuePair.add(new BasicNameValuePair("target_user_type", "A"));

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(sensMsgUrl,
					ServiceHandler.POST, nameValuePair);
			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
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

			AlertDialog.Builder builder = new AlertDialog.Builder(
					AttendeeDetailPage.this);
			builder.setMessage(message).setTitle(error);
			builder.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
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
