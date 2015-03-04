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
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.customClasses.UserProfile;
import com.procialize.libraries.MLRoundedImageView;
import com.procialize.network.ServiceHandler;
import com.procialize.parsers.UserProfileParser;
import com.procialize.utility.Constants;

public class AppSignUp extends Activity implements OnClickListener{

	private TextView signupLabel;
	private TextView mandateTextLabel;
	private EditText emailEdit;
	private EditText passwordEdit;
	private EditText firstNameEdit;
	private EditText lastNameEdit;
	private EditText cityEdit;
	private MLRoundedImageView saveBtn;
//	private ImageView settingsBtn;
	
	private ProgressDialog pDialog;
	String user_registration_url = "";
	Constants constant;
	
	private UserProfileParser userParser;
	ArrayList<UserProfile> userData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.app_sign_up_screen);
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
		
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/HERO.ttf");
		
		constant = new Constants();
		user_registration_url = constant.WEBSERVICE_URL + constant.WEBSERVICE_FOLDER + constant.NEW_REGISTRATION;
		userData = new ArrayList<UserProfile>();
		
		//Declaring elements
		signupLabel = (TextView)findViewById(R.id.signup);
		signupLabel.setTypeface(typeFace);
		mandateTextLabel = (TextView)findViewById(R.id.mandate_text);
		mandateTextLabel.setTypeface(typeFace);
		emailEdit = (EditText)findViewById(R.id.email_edittext);
		emailEdit.setTypeface(typeFace);
		passwordEdit = (EditText)findViewById(R.id.password_edittext);
		passwordEdit.setTypeface(typeFace);
		firstNameEdit = (EditText)findViewById(R.id.first_name_edittext);
		firstNameEdit.setTypeface(typeFace);
		lastNameEdit = (EditText)findViewById(R.id.last_name_edittext);
		lastNameEdit.setTypeface(typeFace);
		cityEdit = (EditText)findViewById(R.id.city_edittext);
		cityEdit.setTypeface(typeFace);
		saveBtn = (MLRoundedImageView)findViewById(R.id.save_button);
//		settingsBtn = (ImageView)findViewById(R.id.settings);
		
		//Applying listener to the elements
		saveBtn.setOnClickListener(this);
//		settingsBtn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view == saveBtn)
		{
			if(emailEdit.length() == 0 || passwordEdit.length() == 0 || firstNameEdit.length() == 0 || lastNameEdit.length() == 0 || cityEdit.length() == 0)
			{
				Toast.makeText(AppSignUp.this, "Please enter data in all the fields", Toast.LENGTH_SHORT).show();
			}
			else
			{
				new userRegistration().execute();
			}
		}
		/*else if(view == settingsBtn)
		{
			Intent editProfile = new Intent(AppSignUp.this, EditProfileActivity.class);
			startActivity(editProfile);
		}*/
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
			nameValuePair.add(new BasicNameValuePair("first_name", firstNameEdit.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("last_name", lastNameEdit.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("city", cityEdit.getText().toString()));
			
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(user_registration_url, ServiceHandler.POST, nameValuePair);

			Log.d("User Registration Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					jsonObj = new JSONObject(jsonStr);
					err = jsonObj.getString("error");
					message = jsonObj.getString("msg");
					if(err.equalsIgnoreCase("success"))
					{
						//User Profile Parser
						userParser = new UserProfileParser();
						userData = userParser.UserData_Parser(jsonStr);
					}
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
			String userEmail = "";
			String userPassword = "";
			
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			
			Log.d("Created URL : ", ">>>>> " + user_registration_url);
			
//			if(!(message.equalsIgnoreCase("")) || (message.equalsIgnoreCase(null)))
			Toast.makeText(AppSignUp.this, ""+message, Toast.LENGTH_LONG).show();
			
			if(err.equalsIgnoreCase("success"))
			{
				for (int i = 0; i < userData.size(); i++) {
					userEmail = userData.get(i).getEmail();
					userPassword = userData.get(i).getPassword();
				}
				Intent intent = new Intent(AppSignUp.this, MainActivity.class);
				intent.putExtra("appUsername", userEmail);
				intent.putExtra("appPassword", userPassword);
				intent.putExtra("provider", "manual_login");
				startActivity(intent);
			}
		}
	}
}
