package com.procialize;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.procialize.network.ServiceHandler;
import com.procialize.utility.Constants;

public class AppSignUp extends Activity implements OnClickListener{

	private EditText emailEdit;
	private EditText passwordEdit;
	private EditText nameEdit;
	private EditText cityEdit;
	private Button saveBtn;
	private ImageView settingsBtn;
	
	private ProgressDialog pDialog;
	String user_registration_url = "";
	Constants constant;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_sign_up_screen);
		
		constant = new Constants();
		user_registration_url = constant.WEBSERVICE_URL + constant.WEBSERVICE_FOLDER + constant.NEW_REGISTRATION;
		
		//Declaring elements
		emailEdit = (EditText)findViewById(R.id.email_edittext);
		passwordEdit = (EditText)findViewById(R.id.password_edittext);
		nameEdit = (EditText)findViewById(R.id.full_name_edittext);
		cityEdit = (EditText)findViewById(R.id.city_edittext);
		saveBtn = (Button)findViewById(R.id.save_button);
		settingsBtn = (ImageView)findViewById(R.id.settings);
		
		//Applying listener to the elements
		saveBtn.setOnClickListener(this);
		settingsBtn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view == saveBtn)
		{
			if(emailEdit.length() == 0 || passwordEdit.length() == 0 ||
					nameEdit.length() == 0 || cityEdit.length() == 0)
			{
				Toast.makeText(AppSignUp.this, "Please enter data in all the fields", Toast.LENGTH_SHORT).show();
			}
			else
			{
				new userRegistration().execute();
			}
		}
		else if(view == settingsBtn)
		{
			Intent editProfile = new Intent(AppSignUp.this, EditProfileActivity.class);
			startActivity(editProfile);
		}
	}
	
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class userRegistration extends AsyncTask<Void, Void, Void> {
		
		JSONObject jsonObj = null;
		String err = "";
		String message = "";
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(AppSignUp.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();
			List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
			
			nameValuePair.add(new BasicNameValuePair("email", emailEdit.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("password", passwordEdit.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("full_name", nameEdit.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("city", cityEdit.getText().toString()));
			
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(user_registration_url, ServiceHandler.POST, nameValuePair);

			Log.d("User Registration Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					jsonObj = new JSONObject(jsonStr);
					err = jsonObj.getString("error");
					message = jsonObj.getString("msg");
//					error  = jsonObj.getJSONObject("error");
//					jsonObj.getString("error");
//					msg = jsonObj.getJSONObject("msg");
					
					/*
					// Getting JSON Array node
					contacts = jsonObj.getJSONArray(TAG_CONTACTS);

					// looping through All Contacts
					for (int i = 0; i < contacts.length(); i++) {
						JSONObject c = contacts.getJSONObject(i);
						
						String id = c.getString(TAG_ID);
						String name = c.getString(TAG_NAME);
						String email = c.getString(TAG_EMAIL);
						String address = c.getString(TAG_ADDRESS);
						String gender = c.getString(TAG_GENDER);

						// Phone node is JSON Object
						JSONObject phone = c.getJSONObject(TAG_PHONE);
						String mobile = phone.getString(TAG_PHONE_MOBILE);
						String home = phone.getString(TAG_PHONE_HOME);
						String office = phone.getString(TAG_PHONE_OFFICE);

						// tmp hashmap for single contact
						HashMap<String, String> contact = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						contact.put(TAG_ID, id);
						contact.put(TAG_NAME, name);
						contact.put(TAG_EMAIL, email);
						contact.put(TAG_PHONE_MOBILE, mobile);

						// adding contact to contact list
						contactList.add(contact);
					}*/
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
//			ListAdapter adapter = new SimpleAdapter(
//					getActivity(), contactList,
//					R.layout.list_item, new String[] { TAG_NAME, TAG_EMAIL,
//							TAG_PHONE_MOBILE }, new int[] { R.id.name,
//							R.id.email, R.id.mobile });
			Log.d("Created URL : ", ">>>>> " + user_registration_url);
			if(err.equalsIgnoreCase("success"))
				Toast.makeText(AppSignUp.this, ""+message, Toast.LENGTH_LONG).show();
			else
				Toast.makeText(AppSignUp.this, "dfiuafj fjriufhaiu", Toast.LENGTH_LONG).show();
//			list.setAdapter(adapter);
		}

	}

}
