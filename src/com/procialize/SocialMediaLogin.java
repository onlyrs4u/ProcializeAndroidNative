package com.procialize;

import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.database.DBHelper;

public class SocialMediaLogin extends Activity implements OnClickListener{
	
	// SocialAuth Components
	SocialAuthAdapter adapter;
	
	//Android components
	private Button facebookLoginBtn;
	private Button linkedInLoginBtn;
	private TextView alreadyHaveAccount;
	private EditText appUsername;
	private EditText appPassword;
	private Button loginBtn;
	private TextView createNewAccount;
	private DBHelper procializeDB;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.social_media_login_screen);
		
		//Declaring elements
		facebookLoginBtn = (Button)findViewById(R.id.fb_login);
		linkedInLoginBtn = (Button)findViewById(R.id.linkedin_login);
		alreadyHaveAccount = (TextView)findViewById(R.id.already_account_text);
		appUsername = (EditText)findViewById(R.id.app_username);
		appPassword = (EditText)findViewById(R.id.app_password);
		loginBtn = (Button)findViewById(R.id.login_button);
		createNewAccount = (TextView)findViewById(R.id.create_new_account);
		
		// Applying listeners to elements
		facebookLoginBtn.setOnClickListener(this);
		linkedInLoginBtn.setOnClickListener(this);
		alreadyHaveAccount.setOnClickListener(this);
		loginBtn.setOnClickListener(this);
		createNewAccount.setOnClickListener(this);
		
		procializeDB = new DBHelper(this);
		SQLiteDatabase db = procializeDB.getReadableDatabase();
		
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view == facebookLoginBtn)
		{
			//Toast.makeText(SocialMediaLogin.this, "Facebook login coming soon !!", Toast.LENGTH_SHORT).show();
			
			// Add it to Library
			adapter = new SocialAuthAdapter(new ResponseListener());

			// Add providers
			adapter.addProvider(Provider.FACEBOOK, R.drawable.facebook);

			// Enable Provider
			adapter.enable(facebookLoginBtn);
			
		}
		else if(view == linkedInLoginBtn)
		{
			//Toast.makeText(SocialMediaLogin.this, "LinkedIn login coming soon !!", Toast.LENGTH_SHORT).show();
			// Add it to Library
			adapter = new SocialAuthAdapter(new ResponseListener());

			// Add providers
			adapter.addProvider(Provider.LINKEDIN, R.drawable.linkedin);

			// Enable Provider
			adapter.enable(linkedInLoginBtn);
		}
		else if(view == alreadyHaveAccount)
		{
			appUsername.setVisibility(View.VISIBLE);
			appPassword.setVisibility(View.VISIBLE);
			loginBtn.setVisibility(View.VISIBLE);
		}
		else if(view == loginBtn)
		{
			if(appUsername.length() == 0 || appPassword.length() == 0)
			{
				Toast.makeText(SocialMediaLogin.this, "Username and password are mandatory", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Intent intent = new Intent(SocialMediaLogin.this, MainActivity.class);
				intent.putExtra("appUsername", appUsername.getText().toString());
				intent.putExtra("appPassword", appPassword.getText().toString());
				intent.putExtra("provider", "manual_login");
				startActivity(intent);
			}
		}
		else if(view == createNewAccount)
		{
			Intent createAccount = new Intent(SocialMediaLogin.this, AppSignUp.class);
			startActivity(createAccount);
		}
	}
	
	// To receive the response after authentication
		private final class ResponseListener implements DialogListener {
			@Override
			public void onComplete(Bundle values) {

				Log.d("ShareButton", "Authentication Successful");

				// Get name of provider after authentication
				final String providerName = values.getString(SocialAuthAdapter.PROVIDER);
				Log.d("ShareButton", "Provider Name = " + providerName);
				Toast.makeText(SocialMediaLogin.this, providerName + " connected", Toast.LENGTH_LONG).show();

				adapter.getUserProfileAsync(new ProfileDataListener());
			}

			@Override
			public void onError(SocialAuthError error) {
				Log.d("ShareButton", "Authentication Error: " + error.getMessage());
			}

			@Override
			public void onCancel() {
				Log.d("ShareButton", "Authentication Cancelled");
			}

			@Override
			public void onBack() {
				Log.d("Share-Button", "Dialog Closed by pressing Back Key");
			}

		}
	
	// To receive the profile response after authentication
	private final class ProfileDataListener implements SocialAuthListener<Profile> {

		@Override
		public void onExecute(String provider, Profile t) {

			Log.d("ProcializeAndroidNative", "Receiving Data");
			//mDialog.dismiss();
			Profile profileMap = t;

			//Profile Activity
			/*Intent intent = new Intent(SocialMediaLogin.this, ProfileActivity.class);
			intent.putExtra("provider", provider);
			intent.putExtra("profile", profileMap);
			startActivity(intent);*/
			
			//Event Wall Screen
			/*Intent intent = new Intent(SocialMediaLogin.this, EventWallAcitvity.class);
			startActivity(intent);*/
			
			//Drawer functionality
			Intent intent = new Intent(SocialMediaLogin.this, MainActivity.class);
			intent.putExtra("provider", provider);
			intent.putExtra("profile", profileMap);
			startActivity(intent);
		}

		@Override
		public void onError(SocialAuthError e) {

		}
	}

}
