package com.procialize.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.procialize.customClasses.Attendees;
import com.procialize.customClasses.Events;

public class AttendeesParser {
	JSONObject jsonObj = null;
	JSONObject userJsonObject = null;
	
	// JSON Node names
    private static final String TAG_EVENT_LIST = "event_list";
    private static final String TAG_SPEAKERS_LIST = "speaker_list";
    private static final String TAG_ATTENDEES_LIST = "attendee_list";
    private static final String TAG_EXHIBITORS_LIST = "exhibitor_list";
 
    // JSONArray
    JSONArray event_list = null;
    JSONArray speakers_list = null;
    JSONArray attendees_list = null;
    JSONArray exhibitors_list = null;
    
    ArrayList<Attendees> attendeesList;
    
    Events events;
	Attendees attendees;
    
	public ArrayList<Attendees> Attendee_Parser(String jsonStr) {
		// TODO Auto-generated constructor stub
		
		attendeesList = new ArrayList<Attendees>();
		
		if (jsonStr != null) {
			try {
				jsonObj = new JSONObject(jsonStr);
				
				// Getting event info JSON Array node
				event_list = jsonObj.getJSONArray(TAG_EVENT_LIST);
				JSONObject jsonEvent = null;
				
				for (int i = 0; i < event_list.length(); i++) {
					jsonEvent = event_list.getJSONObject(i);
										
					// Getting speakers list JSON Array node
					speakers_list = jsonEvent.getJSONArray(TAG_SPEAKERS_LIST);
					JSONObject jsonSpeaker = null;
					
					for (int j = 0; j < speakers_list.length(); j++) {
						jsonSpeaker = speakers_list.getJSONObject(j);
						attendees = new Attendees();
						
						String attendeeId = jsonSpeaker.getString("attendee_id");
						if(!(attendeeId.equalsIgnoreCase("") || attendeeId.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_id(attendeeId);
						}
						String attendeeIndustry = jsonSpeaker.getString("attendee_industry");
						if(!(attendeeIndustry.equalsIgnoreCase("") || attendeeIndustry.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_industry(attendeeIndustry);
						}
						String attendeeFunctionality = jsonSpeaker.getString("attendee_functionality");
						if(!(attendeeFunctionality.equalsIgnoreCase("") || attendeeFunctionality.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_functionality(attendeeFunctionality);
						}
						String passcode = jsonSpeaker.getString("passcode");
						if(!(passcode.equalsIgnoreCase("") || passcode.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_passcode(passcode);
						}
						String attendee_status = jsonSpeaker.getString("attendee_status");
						if(!(attendee_status.equalsIgnoreCase("") || attendee_status.equalsIgnoreCase(null)))
						{
							attendees.setStatus(attendee_status);
						}
						String attendeeImage = jsonSpeaker.getString("attendee_image");
						if(!(attendeeImage.equalsIgnoreCase("") || attendeeImage.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_image(attendeeImage);	
						}
						String attendeeLocation = jsonSpeaker.getString("attendee_location");
						if(!(attendeeLocation.equalsIgnoreCase("") || attendeeLocation.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_location(attendeeLocation);
						}
						String attendeeCity = jsonSpeaker.getString("attendee_city");
						if(!(attendeeCity.equalsIgnoreCase("") || attendeeCity.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_city(attendeeCity);
						}
						String attendeeCountry = jsonSpeaker.getString("attendee_country");
						if(!(attendeeCountry.equalsIgnoreCase("") || attendeeCountry.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_country(attendeeCountry);
						}
						String attendeeDesc = jsonSpeaker.getString("attendee_description");
						if(!(attendeeDesc.equalsIgnoreCase("") || attendeeDesc.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_description(attendeeDesc);
						}
						String attendeeLinkedIn = jsonSpeaker.getString("attendee_linkdin");
						if(!(attendeeLinkedIn.equalsIgnoreCase("") || attendeeLinkedIn.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_linkedin_id(attendeeLinkedIn);
						}
						String attendeeFacebook = jsonSpeaker.getString("attendee_facebook");
						if(!(attendeeFacebook.equalsIgnoreCase("") || attendeeFacebook.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_facebook_id(attendeeFacebook);
						}
						String attendeeType = jsonSpeaker.getString("attendee_type");
						if(!(attendeeType.equalsIgnoreCase("") || attendeeType.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_type(attendeeType);
						}
						String firstName = jsonSpeaker.getString("first_name");
						if(!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_first_name(firstName);
						}
						String lastName = jsonSpeaker.getString("last_name");
						if(!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_last_name(lastName);
						}
						String GCMReg = jsonSpeaker.getString("gcm_reg_id");
						if(!(GCMReg.equalsIgnoreCase("") || GCMReg.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_gcm_registration_id(GCMReg);
						}
						String mobileOS = jsonSpeaker.getString("mobile_os");
						if(!(mobileOS.equalsIgnoreCase("") || mobileOS.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_mobile_os(mobileOS);
						}
						String attendeeEmail = jsonSpeaker.getString("attendee_email");
						if(!(attendeeEmail.equalsIgnoreCase("") || attendeeEmail.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_email(attendeeEmail);	
						}
						String attendeeCompany = jsonSpeaker.getString("attendee_company");
						if(!(attendeeCompany.equalsIgnoreCase("") || attendeeCompany.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_company_name(attendeeCompany);
						}
						String attendeeDesignation = jsonSpeaker.getString("attendee_designation");
						if(!(attendeeDesignation.equalsIgnoreCase("") || attendeeDesignation.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_designation(attendeeDesignation);
						}
						String attendeePhone = jsonSpeaker.getString("attendee_phone");
						if(!(attendeePhone.equalsIgnoreCase("") || attendeePhone.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_phone_number(attendeePhone);
						}
						
						attendeesList.add(attendees);
					}
					
					// Getting attendees list JSON Array node
					attendees_list = jsonEvent.getJSONArray(TAG_ATTENDEES_LIST);
					JSONObject jsonAttendee = null;
					
					for (int j = 0; j < attendees_list.length(); j++) {
						jsonAttendee = attendees_list.getJSONObject(j);
						attendees = new Attendees();
						
						String attendeeIndustry = jsonAttendee.getString("attendee_industry");
						if(!(attendeeIndustry.equalsIgnoreCase("") || attendeeIndustry.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_industry(attendeeIndustry);
						}
						String attendeeFunctionality = jsonAttendee.getString("attendee_functionality");
						if(!(attendeeFunctionality.equalsIgnoreCase("") || attendeeFunctionality.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_functionality(attendeeFunctionality);
						}
						String attendeeId = jsonAttendee.getString("attendee_id");
						if(!(attendeeId.equalsIgnoreCase("") || attendeeId.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_id(attendeeId);
						}
						String passcode = jsonAttendee.getString("passcode");
						if(!(passcode.equalsIgnoreCase("") || passcode.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_passcode(passcode);
						}
						String attendeeImage = jsonAttendee.getString("attendee_image");
						if(!(attendeeImage.equalsIgnoreCase("") || attendeeImage.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_image(attendeeImage);	
						}
						String attendee_status = jsonAttendee.getString("attendee_status");
						if(!(attendee_status.equalsIgnoreCase("") || attendee_status.equalsIgnoreCase(null)))
						{
							attendees.setStatus(attendee_status);
						}
						String attendeeLocation = jsonAttendee.getString("attendee_location");
						if(!(attendeeLocation.equalsIgnoreCase("") || attendeeLocation.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_location(attendeeLocation);
						}
						String attendeeCity = jsonAttendee.getString("attendee_city");
						if(!(attendeeCity.equalsIgnoreCase("") || attendeeCity.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_city(attendeeCity);
						}
						String attendeeCountry = jsonAttendee.getString("attendee_country");
						if(!(attendeeCountry.equalsIgnoreCase("") || attendeeCountry.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_country(attendeeCountry);
						}
						String attendeeDesc = jsonAttendee.getString("attendee_description");
						if(!(attendeeDesc.equalsIgnoreCase("") || attendeeDesc.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_description(attendeeDesc);
						}
						String attendeeLinkedIn = jsonAttendee.getString("attendee_linkdin");
						if(!(attendeeLinkedIn.equalsIgnoreCase("") || attendeeLinkedIn.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_linkedin_id(attendeeLinkedIn);
						}
						String attendeeFacebook = jsonAttendee.getString("attendee_facebook");
						if(!(attendeeFacebook.equalsIgnoreCase("") || attendeeFacebook.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_facebook_id(attendeeFacebook);
						}
						String attendeeType = jsonAttendee.getString("attendee_type");
						if(!(attendeeType.equalsIgnoreCase("") || attendeeType.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_type(attendeeType);
						}
						String firstName = jsonAttendee.getString("first_name");
						if(!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_first_name(firstName);
						}
						String lastName = jsonAttendee.getString("last_name");
						if(!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_last_name(lastName);
						}
						String GCMReg = jsonAttendee.getString("gcm_reg_id");
						if(!(GCMReg.equalsIgnoreCase("") || GCMReg.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_gcm_registration_id(GCMReg);
						}
						String mobileOS = jsonAttendee.getString("mobile_os");
						if(!(mobileOS.equalsIgnoreCase("") || mobileOS.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_mobile_os(mobileOS);
						}
						String attendeeEmail = jsonAttendee.getString("attendee_email");
						if(!(attendeeEmail.equalsIgnoreCase("") || attendeeEmail.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_email(attendeeEmail);	
						}
						String attendeeCompany = jsonAttendee.getString("attendee_company");
						if(!(attendeeCompany.equalsIgnoreCase("") || attendeeCompany.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_company_name(attendeeCompany);
						}
						String attendeeDesignation = jsonAttendee.getString("attendee_designation");
						if(!(attendeeDesignation.equalsIgnoreCase("") || attendeeDesignation.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_designation(attendeeDesignation);
						}
						String attendeePhone = jsonAttendee.getString("attendee_phone");
						if(!(attendeePhone.equalsIgnoreCase("") || attendeePhone.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_phone_number(attendeePhone);
						}
						
						attendeesList.add(attendees);
					}

					// Getting exhibitors list JSON Array node
					exhibitors_list = jsonEvent.getJSONArray(TAG_EXHIBITORS_LIST);
					JSONObject jsonExhibitor = null;
					
					for (int j = 0; j < exhibitors_list.length(); j++) {
						jsonExhibitor = exhibitors_list.getJSONObject(j);
						attendees = new Attendees();
						
						String attendeeFeatured = jsonExhibitor.getString("attendee_featured");
						if(!(attendeeFeatured.equalsIgnoreCase("") || attendeeFeatured.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_featured(attendeeFeatured);
						}
						String attendeeStallNumber = jsonExhibitor.getString("stall_number");
						if(!(attendeeStallNumber.equalsIgnoreCase("") || attendeeStallNumber.equalsIgnoreCase(null)))
						{
							attendees.setStall_number(attendeeStallNumber);
						}
						String attendeeDesc = jsonExhibitor.getString("attendee_description");
						if(!(attendeeDesc.equalsIgnoreCase("") || attendeeDesc.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_description(attendeeDesc);
						}
						String attendee_status = jsonExhibitor.getString("attendee_status");
						if(!(attendee_status.equalsIgnoreCase("") || attendee_status.equalsIgnoreCase(null)))
						{
							attendees.setStatus(attendee_status);
						}
						String attendeeWebsite = jsonExhibitor.getString("exhibitor_website");
						if(!(attendeeWebsite.equalsIgnoreCase("") || attendeeWebsite.equalsIgnoreCase(null)))
						{
							attendees.setExhibitor_website(attendeeWebsite);
						}
						String attendeeFacebook = jsonExhibitor.getString("attendee_facebook");
						if(!(attendeeFacebook.equalsIgnoreCase("") || attendeeFacebook.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_facebook_id(attendeeFacebook);
						}
						String attendeeLinkedIn = jsonExhibitor.getString("attendee_linkdin");
						if(!(attendeeLinkedIn.equalsIgnoreCase("") || attendeeLinkedIn.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_linkedin_id(attendeeLinkedIn);
						}
						String attendeeCity = jsonExhibitor.getString("attendee_city");
						if(!(attendeeCity.equalsIgnoreCase("") || attendeeCity.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_city(attendeeCity);
						}
						String attendeeCountry = jsonExhibitor.getString("attendee_country");
						if(!(attendeeCountry.equalsIgnoreCase("") || attendeeCountry.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_country(attendeeCountry);
						}
						String attendeeLocation = jsonExhibitor.getString("attendee_location");
						if(!(attendeeLocation.equalsIgnoreCase("") || attendeeLocation.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_location(attendeeLocation);
						}
						String attendeeImage = jsonExhibitor.getString("attendee_image");
						if(!(attendeeImage.equalsIgnoreCase("") || attendeeImage.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_image(attendeeImage);	
						}
						String attendeeImage1 = jsonExhibitor.getString("image1");
						if(!(attendeeImage1.equalsIgnoreCase("") || attendeeImage1.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_image_1(attendeeImage1);	
						}
						String attendeeImage2 = jsonExhibitor.getString("image1");
						if(!(attendeeImage2.equalsIgnoreCase("") || attendeeImage2.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_image_2(attendeeImage2);	
						}
						String brochure = jsonExhibitor.getString("brochure");
						if(!(brochure.equalsIgnoreCase("") || brochure.equalsIgnoreCase(null)))
						{
							attendees.setBrochure(brochure);
						}
						String firstName = jsonExhibitor.getString("first_name");
						if(!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_first_name(firstName);
						}
						String lastName = jsonExhibitor.getString("last_name");
						if(!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_last_name(lastName);
						}
						String attendeeEmail = jsonExhibitor.getString("attendee_email");
						if(!(attendeeEmail.equalsIgnoreCase("") || attendeeEmail.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_email(attendeeEmail);	
						}
						String attendeePhone = jsonExhibitor.getString("attendee_phone");
						if(!(attendeePhone.equalsIgnoreCase("") || attendeePhone.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_phone_number(attendeePhone);
						}
						String attendeeMobile = jsonExhibitor.getString("mobile");
						if(!(attendeeMobile.equalsIgnoreCase("") || attendeeMobile.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_mobile_number(attendeeMobile);
						}
						String GCMReg = jsonExhibitor.getString("gcm_reg_id");
						if(!(GCMReg.equalsIgnoreCase("") || GCMReg.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_gcm_registration_id(GCMReg);
						}
						String mobileOS = jsonExhibitor.getString("mobile_os");
						if(!(mobileOS.equalsIgnoreCase("") || mobileOS.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_mobile_os(mobileOS);
						}
						String attendeeId = jsonExhibitor.getString("attendee_id");
						if(!(attendeeId.equalsIgnoreCase("") || attendeeId.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_id(attendeeId);
						}
						String attendeeType = jsonExhibitor.getString("attendee_type");
						if(!(attendeeType.equalsIgnoreCase("") || attendeeType.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_type(attendeeType);
						}
						String attendeeIndustry = jsonExhibitor.getString("attendee_industry");
						if(!(attendeeIndustry.equalsIgnoreCase("") || attendeeIndustry.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_industry(attendeeIndustry);
						}
						String attendeeCompany = jsonExhibitor.getString("attendee_company");
						if(!(attendeeCompany.equalsIgnoreCase("") || attendeeCompany.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_company_name(attendeeCompany);
						}
						String attendeeDesignation = jsonExhibitor.getString("attendee_designation");
						if(!(attendeeDesignation.equalsIgnoreCase("") || attendeeDesignation.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_designation(attendeeDesignation);
						}
						String attendeeFunctionality = jsonExhibitor.getString("attendee_functionality");
						if(!(attendeeFunctionality.equalsIgnoreCase("") || attendeeFunctionality.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_functionality(attendeeFunctionality);
						}
						
						
						/*String passcode = jsonExhibitor.getString("passcode");
						if(!(passcode.equalsIgnoreCase("") || passcode.equalsIgnoreCase(null)))
						{
							attendees.setAttendee_passcode(passcode);
						}*/
						
						attendeesList.add(attendees);
					}
				}
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any data from the url");
		}
		return attendeesList;
	}
}
