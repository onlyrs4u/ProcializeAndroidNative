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
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.customClasses.DataWrapper;
import com.procialize.customClasses.UserProfile;
import com.procialize.database.DBHelper;
import com.procialize.network.ServiceHandler;
import com.procialize.parsers.UserProfileParser;
import com.procialize.utility.Constants;

public class EditProfileActivity extends Activity implements OnClickListener{
	
	private TextView editProfileLabel;
	private Button chooseFileBtn;
	private ImageView selected_Image;
	private EditText firstNameEdit;
	private EditText lastNameEdit;
	private EditText designationEdit;
	private EditText companyNameEdit;
	private EditText descriptionEdit;
	private EditText cityEdit;
	private EditText mobileEdit;
	private EditText phoneEdit;
	private Button SaveBtn;
	
	String api_access_token_ = "";
	String user_registration_url = "";
	Constants constant;
//	private AutoCompleteTextView industry_dropdown;
	private static int RESULT_LOAD_IMAGE = 1;
	
	private ProgressDialog pDialog;
	private ArrayList<UserProfile> myProfile = new ArrayList<UserProfile>();
	
	String fname = "";
	String lname = "";
	String designation = "";
	String company_name = "";
	String description = "";
	String city = "";
	String mobile = "";
	String phone = "";
	
	DBHelper helper;
	private UserProfileParser userParser;
	ArrayList<UserProfile> userData;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.edit_profile_screen);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.custom_title);
		
//		myProfile = (ArrayList<UserProfile>) getIntent().getExtras().getSerializable("userProfile_Array");
		DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("userProfile_Array");
		myProfile = dw.getUserData();
		
		helper = new DBHelper(this);
		
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/HERO.ttf");
		
		constant = new Constants();
		user_registration_url = constant.WEBSERVICE_URL + constant.WEBSERVICE_FOLDER + constant.NEW_REGISTRATION;
		
		//Declaring elements
		editProfileLabel = (TextView) findViewById(R.id.edit_profile);
		editProfileLabel.setTypeface(typeFace);
		chooseFileBtn = (Button)findViewById(R.id.choose_file_button);
		chooseFileBtn.setTypeface(typeFace);
		firstNameEdit = (EditText) findViewById(R.id.edit_first_name_edittext);
		firstNameEdit.setTypeface(typeFace);
		lastNameEdit = (EditText) findViewById(R.id.edit_last_name_edittext);
		lastNameEdit.setTypeface(typeFace);
		designationEdit = (EditText) findViewById(R.id.edit_designation_edittext);
		designationEdit.setTypeface(typeFace);
		companyNameEdit = (EditText) findViewById(R.id.edit_company_name_edittext);
		companyNameEdit.setTypeface(typeFace);
		descriptionEdit = (EditText) findViewById(R.id.edit_description_edittext);
		descriptionEdit.setTypeface(typeFace);
		cityEdit = (EditText) findViewById(R.id.edit_city_edittext);
		cityEdit.setTypeface(typeFace);
		mobileEdit = (EditText) findViewById(R.id.edit_mobile_edittext);
		mobileEdit.setTypeface(typeFace);
		phoneEdit = (EditText) findViewById(R.id.edit_phone_edittext);
		phoneEdit.setTypeface(typeFace);
		SaveBtn = (Button)findViewById(R.id.save_button);
		SaveBtn.setTypeface(typeFace);
		
		api_access_token_ = Constants.API_ACCESS_TOKEN;
		//Applying listener to the elements
		chooseFileBtn.setOnClickListener(this);
		SaveBtn.setOnClickListener(this);
		
		setRespectiveValues(myProfile);
		
//		industry_dropdown = (AutoCompleteTextView) findViewById(R.id.industry_dropdown);
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, WORLDCUP2010);
//		industry_dropdown.setAdapter(adapter);
		userData = new ArrayList<UserProfile>();
		
	}

	public void setRespectiveValues(ArrayList<UserProfile> profile)
	{
		for(int i=0; i<profile.size(); i++)
		{
			fname = profile.get(i).getFirst_name();
			lname = profile.get(i).getLast_name();
			designation = profile.get(i).getDesignation();
			company_name = profile.get(i).getCompany_name();
			description = profile.get(i).getDescription();
			city = profile.get(i).getCity();
			mobile = profile.get(i).getMobile_number();
			phone = profile.get(i).getPhone_number();
			
			if(!(fname.equalsIgnoreCase("") || fname.equalsIgnoreCase(null)))
			{
				firstNameEdit.setText(fname);
			}
			if(!(lname.equalsIgnoreCase("") || lname.equalsIgnoreCase(null)))
			{
				lastNameEdit.setText(lname);
			}
			if(!(designation.equalsIgnoreCase("") || designation.equalsIgnoreCase(null)))
			{
				designationEdit.setText(designation);
			}
			if(!(company_name.equalsIgnoreCase("") || company_name.equalsIgnoreCase(null)))
			{
				companyNameEdit.setText(company_name);
			}
			if(!(description.equalsIgnoreCase("") || description.equalsIgnoreCase(null)))
			{
				descriptionEdit.setText(description);
			}
			if(!(city.equalsIgnoreCase("") || city.equalsIgnoreCase(null)))
			{
				cityEdit.setText(city);
			}
			if(!(mobile.equalsIgnoreCase("") || mobile.equalsIgnoreCase(null)))
			{
				mobileEdit.setText(mobile);
			}
			if(!(phone.equalsIgnoreCase("") || phone.equalsIgnoreCase(null)))
			{
				phoneEdit.setText(phone);
			}
		}
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view == chooseFileBtn)
		{
			Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(i, RESULT_LOAD_IMAGE);
		}else if(view == SaveBtn)
		{
			new editRegisterUser().execute();
		}
	}
		
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	         
		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
	 
			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();
	 
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
	             
			selected_Image = (ImageView) findViewById(R.id.selected_image);
			selected_Image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
		}
	}
		
	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class editRegisterUser extends AsyncTask<Void, Void, Void> {
			
		JSONObject jsonObj = null;
		String err = "";
		String message = "";
			
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(EditProfileActivity.this);
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
			nameValuePair.add(new BasicNameValuePair("first_name", firstNameEdit.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("last_name", lastNameEdit.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("designation", designationEdit.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("company", companyNameEdit.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("description", descriptionEdit.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("city", cityEdit.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("mobile", mobileEdit.getText().toString()));
			nameValuePair.add(new BasicNameValuePair("phone", phoneEdit.getText().toString()));
			
			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(user_registration_url, ServiceHandler.POST, nameValuePair);
			
			Log.d("User Registration Response: ", "> " + jsonStr);
			
			if (jsonStr != null) {
				try {
					jsonObj = new JSONObject(jsonStr);
					err = jsonObj.getString("error");
					message = jsonObj.getString("msg");
						
					//User Profile Parser
					userParser = new UserProfileParser();
					userData = userParser.UserData_Parser(jsonStr);

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
			
			helper.clearUserProfileTable();
				
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
				
			Log.d("Created URL : ", ">>>>> " + user_registration_url);
			Toast.makeText(EditProfileActivity.this, ""+message, Toast.LENGTH_LONG).show();
				
			/*if(err.equalsIgnoreCase("success"))
			{
				Toast.makeText(EditProfileActivity.this, ""+err, Toast.LENGTH_LONG).show();
			}*/
		}
	}

}
