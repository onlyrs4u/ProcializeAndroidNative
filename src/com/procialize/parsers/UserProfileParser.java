package com.procialize.parsers;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.procialize.customClasses.Profile;
import com.procialize.utility.Constants;

public class UserProfileParser {
	JSONObject userJsonObject = null;
	
	// JSON Node names
    private static final String USER_PROFILE_LIST = "user_data";
 
    ArrayList<Profile> userData;
    Constants constant = new Constants();
    
	public ArrayList<Profile> UserData_Parser(String jsonStr) {
		// TODO Auto-generated constructor stub
		
		userData = new ArrayList<Profile>();
		
		if (jsonStr != null) {
			try {
				userJsonObject = new JSONObject(jsonStr);
				JSONObject user = userJsonObject.getJSONObject(USER_PROFILE_LIST);
				Profile userProfile = new Profile();
				String attendeeId = user.getString("attendee_id");
				if(!(attendeeId.equalsIgnoreCase("") || attendeeId.equalsIgnoreCase(null)))
				{
					userProfile.setProfile_attendee_id(attendeeId);	
				}
				String apiAccessToken = user.getString("api_access_token");
				if(!(apiAccessToken.equalsIgnoreCase("") || apiAccessToken.equalsIgnoreCase(null)))
				{
					userProfile.setApi_access_token(apiAccessToken);
					constant.API_ACCESS_TOKEN = userProfile.getApi_access_token();
				}
				String userEmail = user.getString("email");
				if(!(userEmail.equalsIgnoreCase("") || userEmail.equalsIgnoreCase(null)))
				{
					userProfile.setEmail(userEmail);	
				}
				String userStatus = user.getString("status");
				if(!(userStatus.equalsIgnoreCase("") || userStatus.equalsIgnoreCase(null)))
				{
					userProfile.setStatus(userStatus);	
				}
				String userGCM = user.getString("gcm_reg_id");
				if(!(userGCM.equalsIgnoreCase("") || userGCM.equalsIgnoreCase(null)))
				{
					userProfile.setGcm_reg_id(userGCM);	
				}
				String mobileOS = user.getString("mobile_os");
				if(!(mobileOS.equalsIgnoreCase("") || mobileOS.equalsIgnoreCase(null)))
				{
					userProfile.setMobile_os(mobileOS);	
				}
				String firstName = user.getString("first_name");
				if(!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null)))
				{
					userProfile.setFirst_name(firstName);	
				}
				String lastName = user.getString("last_name");
				if(!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null)))
				{
					userProfile.setLast_name(lastName);	
				}
				String linkedIn = user.getString("linkden");
				if(!(linkedIn.equalsIgnoreCase("") || linkedIn.equalsIgnoreCase(null)))
				{
					userProfile.setLinkedin_(linkedIn);	
				}
				String facebook = user.getString("facebook");
				if(!(facebook.equalsIgnoreCase("") || facebook.equalsIgnoreCase(null)))
				{
					userProfile.setFacebook(facebook);	
				}
				String companyName = user.getString("company_name");
				if(!(companyName.equalsIgnoreCase("") || companyName.equalsIgnoreCase(null)))
				{
					userProfile.setCompany_name(companyName);	
				}
				String designation = user.getString("designation");
				if(!(designation.equalsIgnoreCase("") || designation.equalsIgnoreCase(null)))
				{
					userProfile.setDesignation(designation);	
				}
				String photo = user.getString("photo");
				if(!(photo.equalsIgnoreCase("") || photo.equalsIgnoreCase(null)))
				{
					userProfile.setProfile_image(photo);	
				}
				String description = user.getString("description");
				if(!(description.equalsIgnoreCase("") || description.equalsIgnoreCase(null)))
				{
					userProfile.setDescription(description);	
				}
				String city = user.getString("city");
				if(!(city.equalsIgnoreCase("") || city.equalsIgnoreCase(null)))
				{
					userProfile.setCity(city);	
				}
				String country = user.getString("country");
				if(!(country.equalsIgnoreCase("") || country.equalsIgnoreCase(null)))
				{
					userProfile.setCountry(country);	
				}
				String userIndustry = user.getString("attendee_industry");
				if(!(userIndustry.equalsIgnoreCase("") || userIndustry.equalsIgnoreCase(null)))
				{
					userProfile.setIndustry(userIndustry);	
				}
				String userFunctionality = user.getString("attendee_functionality");
				if(!(userFunctionality.equalsIgnoreCase("") || userFunctionality.equalsIgnoreCase(null)))
				{
					userProfile.setFunctionality(userFunctionality);	
				}
				String userPhone = user.getString("attendee_phone");
				if(!(userPhone.equalsIgnoreCase("") || userPhone.equalsIgnoreCase(null)))
				{
					userProfile.setPhone_number(userPhone);	
				}
				String userMobile = user.getString("attendee_mobile");
				if(!(userMobile.equalsIgnoreCase("") || userMobile.equalsIgnoreCase(null)))
				{
					userProfile.setMobile_number(userMobile);	
				}
				
				userData.add(userProfile);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
		}
		return userData;
	}
}
