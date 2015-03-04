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
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.database.DBHelper;
import com.procialize.libraries.MLRoundedImageView;

public class SocialMediaLogin extends Activity implements OnClickListener{
	
	// SocialAuth Components
	SocialAuthAdapter adapter;
	
	//Android components
	private TextView EventNameText;
	private TextView EventDateText;
	
	private ImageView facebookLoginBtn;
	private ImageView linkedInLoginBtn;
	private MLRoundedImageView loginBtn;
	private MLRoundedImageView SignUpBtn;
	
	private MLRoundedImageView loginSubmitBtn;
	private MLRoundedImageView loginCancelBtn;;
	
	private EditText appUsername;
	private EditText appPassword;
	
	private DBHelper procializeDB;
	private RelativeLayout loginRL;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.social_media_login_screen);
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
		
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/HERO.ttf");
		//Declaring elements
		EventNameText = (TextView) findViewById(R.id.event_name);
		EventNameText.setTypeface(typeFace);
		EventDateText = (TextView) findViewById(R.id.event_date);
		EventDateText.setTypeface(typeFace);
		
		facebookLoginBtn = (ImageView)findViewById(R.id.fb_login);
		linkedInLoginBtn = (ImageView)findViewById(R.id.linkedin_login);
		loginBtn = (MLRoundedImageView)findViewById(R.id.login_btn);
		SignUpBtn = (MLRoundedImageView)findViewById(R.id.sign_up_btn);
		
		loginSubmitBtn = (MLRoundedImageView)findViewById(R.id.submit_btn);
		loginCancelBtn = (MLRoundedImageView)findViewById(R.id.cancel_btn);
		
		loginRL = (RelativeLayout) findViewById(R.id.manual_login_layout);
		
		appUsername = (EditText)findViewById(R.id.app_username);
		appUsername.setTypeface(typeFace);
		appPassword = (EditText)findViewById(R.id.app_password);
		appPassword.setTypeface(typeFace);
		
		// Applying listeners to elements
		facebookLoginBtn.setOnClickListener(this);
		linkedInLoginBtn.setOnClickListener(this);
		SignUpBtn.setOnClickListener(this);
		loginBtn.setOnClickListener(this);
		
		loginSubmitBtn.setOnClickListener(this);
		loginCancelBtn.setOnClickListener(this);
		
		procializeDB = new DBHelper(this);
		SQLiteDatabase db = procializeDB.getReadableDatabase();
		
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view == facebookLoginBtn)
		{
			// Add it to Library
			adapter = new SocialAuthAdapter(new ResponseListener());
			// Add providers
			adapter.addProvider(Provider.FACEBOOK, R.drawable.facebook);
			// Enable Provider
			adapter.enable(facebookLoginBtn);
		}
		else if(view == linkedInLoginBtn)
		{
			// Add it to Library
			adapter = new SocialAuthAdapter(new ResponseListener());
			// Add providers
			adapter.addProvider(Provider.LINKEDIN, R.drawable.linkedin);
			// Enable Provider
			adapter.enable(linkedInLoginBtn);
		}
		else if(view == loginBtn)
		{
			facebookLoginBtn.setVisibility(View.GONE);
			linkedInLoginBtn.setVisibility(View.GONE);
			loginBtn.setVisibility(View.GONE); 
			SignUpBtn.setVisibility(View.GONE);
			SlideToAbove();
		}
		else if(view == SignUpBtn)
		{
			Intent createAccount = new Intent(SocialMediaLogin.this, AppSignUp.class);
			startActivity(createAccount);
		}
		else if(view == loginSubmitBtn)
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
		else if(view == loginCancelBtn)
		{
			SlideToDown();
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
	
	public void SlideToAbove() {
	    Animation slide = null;
	    loginRL.setVisibility(View.VISIBLE);
	   
	    slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
	            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
	            1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
	    
	    slide.setDuration(1000);
	    slide.setFillAfter(true);
	    slide.setFillEnabled(true);
	    loginRL.startAnimation(slide);

	    slide.setAnimationListener(new AnimationListener() {
	    	@Override
	        public void onAnimationStart(Animation animation) {
	        	
	        }
	        @Override
	        public void onAnimationRepeat(Animation animation) {
	        	
	        }
	        @Override
	        public void onAnimationEnd(Animation animation) {
	        	loginRL.clearAnimation();
	        	
	        	RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(loginRL.getWidth(), loginRL.getHeight());
	            // lp.setMargins(0, 0, 0, 0);
	            lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	            loginRL.setLayoutParams(lp);
	        }
	    });
	}
	
	public void SlideToDown() {
	    Animation slide = null;
	    slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
	            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
	            0.0f, Animation.RELATIVE_TO_SELF, 2.0f);

	    slide.setDuration(1000);
	    slide.setFillAfter(true);
	    slide.setFillEnabled(true);
	    loginRL.startAnimation(slide);

	    slide.setAnimationListener(new AnimationListener() {
	        @Override
	        public void onAnimationStart(Animation animation) {

	        }
	        @Override
	        public void onAnimationRepeat(Animation animation) {
	        	
	        }
	        @Override
	        public void onAnimationEnd(Animation animation) {
	        	loginRL.clearAnimation();

	            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(loginRL.getWidth(), loginRL.getHeight());
	            lp.setMargins(0, loginRL.getWidth(), 0, 0);
	            lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	            loginRL.setLayoutParams(lp);
	            
	            loginRL.setVisibility(View.GONE);
				facebookLoginBtn.setVisibility(View.VISIBLE);
				linkedInLoginBtn.setVisibility(View.VISIBLE);
				loginBtn.setVisibility(View.VISIBLE);
				SignUpBtn.setVisibility(View.VISIBLE);
	        }
	    });
	}

}
