package com.procialize.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.procialize.customClasses.Bookmarked;

public class BookmarksParser {
	JSONObject jsonObj = null;
	JSONObject userJsonObject = null;
	
	// JSON Node names
    private static final String TAG_BOOKMARK_LIST = "saved_profile";
    private static final String TAG_BOOKMARK_RECEIVER_DATA = "receiver_data";
 
    // JSONArray
    JSONArray bookmark_list = null;
    ArrayList<Bookmarked> bookmarksList;
    Bookmarked bookmarks;
    
	public ArrayList<Bookmarked> BookmarksInfo_Parser(String jsonStr) {
		// TODO Auto-generated constructor stub
		
		bookmarksList = new ArrayList<Bookmarked>();
		
		if (jsonStr != null) {
			try {
				jsonObj = new JSONObject(jsonStr);
				
				// Getting bookmark info JSON Array node
				bookmark_list = jsonObj.getJSONArray(TAG_BOOKMARK_LIST);
				JSONObject jsonBookmark = null;
				JSONObject jsonReceiverData = null; 
				
				for (int i = 0; i < bookmark_list.length(); i++) {
					jsonBookmark = bookmark_list.getJSONObject(i);
					bookmarks = new Bookmarked();
					
					String NotificationID = jsonBookmark.getString("notification_id");
					if(!(NotificationID.equalsIgnoreCase("") || NotificationID.equalsIgnoreCase(null)))
					{
						bookmarks.setNotification_id(NotificationID);	
					}
					String notificationType = jsonBookmark.getString("notification_type");
					if(!(notificationType.equalsIgnoreCase("") || notificationType.equalsIgnoreCase(null)))
					{
						bookmarks.setNotification_type(notificationType);
					}
					String subjectID = jsonBookmark.getString("subject_id");
					if(!(subjectID.equalsIgnoreCase("") || subjectID.equalsIgnoreCase(null)))
					{
						bookmarks.setSubject_id(subjectID);
					}
					String subjectType = jsonBookmark.getString("subject_type");
					if(!(subjectType.equalsIgnoreCase("") || subjectType.equalsIgnoreCase(null)))
					{
						bookmarks.setSubject_type(subjectType);
					}
					String objectID = jsonBookmark.getString("object_id");
					if(!(objectID.equalsIgnoreCase("") || objectID.equalsIgnoreCase(null)))
					{
						bookmarks.setObject_id(objectID);
					}
					String objectType = jsonBookmark.getString("object_type");
					if(!(objectType.equalsIgnoreCase("") || objectType.equalsIgnoreCase(null)))
					{
						bookmarks.setObject_type(objectType);
					}
					String read = jsonBookmark.getString("read");
					if(!(read.equalsIgnoreCase("") || read.equalsIgnoreCase(null)))
					{
						bookmarks.setRead(read);
					}
					String notificationContent = jsonBookmark.getString("notification_content");
					if(!(notificationContent.equalsIgnoreCase("") || notificationContent.equalsIgnoreCase(null)))
					{
						bookmarks.setNotification_content(notificationContent);
					}
					String meetingID = jsonBookmark.getString("meeting_id");
					if(!(meetingID.equalsIgnoreCase("") || meetingID.equalsIgnoreCase(null)))
					{
						bookmarks.setMeeting_id(meetingID);
					}
					String messageID = jsonBookmark.getString("message_id");
					if(!(messageID.equalsIgnoreCase("") || messageID.equalsIgnoreCase(null)))
					{
						bookmarks.setMessage_id(messageID);
					}
					String eventID = jsonBookmark.getString("event_id");
					if(!(eventID.equalsIgnoreCase("") || eventID.equalsIgnoreCase(null)))
					{
						bookmarks.setEvent_id(eventID);
					}
					String notificationDate = jsonBookmark.getString("notification_date");
					if(!(notificationDate.equalsIgnoreCase("") || notificationDate.equalsIgnoreCase(null)))
					{
						bookmarks.setNotification_date(notificationDate);
					}
					String userID = jsonBookmark.getString("user_id");
					if(!(userID.equalsIgnoreCase("") || userID.equalsIgnoreCase(null)))
					{
						bookmarks.setUser_id(userID);	
					}
					String firstName = jsonBookmark.getString("first_name");
					if(!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null)))
					{
						bookmarks.setFirst_name(firstName);
					}
					String lastName = jsonBookmark.getString("last_name");
					if(!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null)))
					{
						bookmarks.setLast_name(lastName);
					}
					String TypeOfUser = jsonBookmark.getString("type_of_user");
					if(!(TypeOfUser.equalsIgnoreCase("") || TypeOfUser.equalsIgnoreCase(null)))
					{
						bookmarks.setType_of_user(TypeOfUser);	
					}
					String companyName = jsonBookmark.getString("company_name");
					if(!(companyName.equalsIgnoreCase("") || companyName.equalsIgnoreCase(null)))
					{
						bookmarks.setCompany_name(companyName);	
					}
					String designation = jsonBookmark.getString("designation");
					if(!(designation.equalsIgnoreCase("") || designation.equalsIgnoreCase(null)))
					{
						bookmarks.setDesignation(designation);
					}
					String phone = jsonBookmark.getString("phone");
					if(!(phone.equalsIgnoreCase("") || phone.equalsIgnoreCase(null)))
					{
						bookmarks.setPhone(phone);	
					}
					String fullname = jsonBookmark.getString("name");
					if(!(fullname.equalsIgnoreCase("") || fullname.equalsIgnoreCase(null)))
					{
						bookmarks.setFull_name(fullname);
					}
					String eventName = jsonBookmark.getString("event_name");
					if(!(eventName.equalsIgnoreCase("") || eventName.equalsIgnoreCase(null)))
					{
						bookmarks.setEvent_name(eventName);	
					}
					//Receiver Data
					jsonReceiverData = jsonBookmark.getJSONObject(TAG_BOOKMARK_RECEIVER_DATA);
					
					String receiverUserID = jsonReceiverData.getString("user_id");
					if(!(receiverUserID.equalsIgnoreCase("") || receiverUserID.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_user_id(receiverUserID);
					}
					String exhibitorEmail = jsonReceiverData.getString("exhibitor_email");
					if(!(exhibitorEmail.equalsIgnoreCase("") || exhibitorEmail.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_exhibitor_email(exhibitorEmail);
					}
					String receiverFirstName = jsonReceiverData.getString("first_name");
					if(!(receiverFirstName.equalsIgnoreCase("") || receiverFirstName.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_first_name(receiverFirstName);
					}
					String receiverLastName = jsonReceiverData.getString("last_name");
					if(!(receiverLastName.equalsIgnoreCase("") || receiverLastName.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_last_name(receiverLastName);
					}
					String receiverTypeOfUser = jsonReceiverData.getString("type_of_user");
					if(!(receiverTypeOfUser.equalsIgnoreCase("") || receiverTypeOfUser.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_type_of_user(receiverTypeOfUser);
					}
					String receiverTargetID = jsonReceiverData.getString("target_id");
					if(!(receiverTargetID.equalsIgnoreCase("") || receiverTargetID.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_target_id(receiverTargetID);
					}
					String receiverAttendeeType = jsonReceiverData.getString("attendee_type");
					if(!(receiverAttendeeType.equalsIgnoreCase("") || receiverAttendeeType.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_attendee_type(receiverAttendeeType);
					}
					String receiverFullName = jsonReceiverData.getString("name");
					if(!(receiverFullName.equalsIgnoreCase("") || receiverFullName.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_full_name(receiverFullName);
					}
//					String receiverAttendeeImage = jsonReceiverData.getString("attendee_image");
//					if(!(receiverAttendeeImage.equalsIgnoreCase("") || receiverAttendeeImage.equalsIgnoreCase(null)))
//					{
//						bookmarks.setReceiver_attendee_image(receiverAttendeeImage);
//					}
					String receiverAttendeeLocation = jsonReceiverData.getString("attendee_location");
					if(!(receiverAttendeeLocation.equalsIgnoreCase("") || receiverAttendeeLocation.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_attendee_location(receiverAttendeeLocation);
					}
					String receiverAttendeeCity = jsonReceiverData.getString("attendee_city");
					if(!(receiverAttendeeCity.equalsIgnoreCase("") || receiverAttendeeCity.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_attendee_city(receiverAttendeeCity);
					}
					String receiverAttendeeCountry = jsonReceiverData.getString("attendee_country");
					if(!(receiverAttendeeCountry.equalsIgnoreCase("") || receiverAttendeeCountry.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_attendee_country(receiverAttendeeCountry);
					}
					String receiverCompanyName = jsonReceiverData.getString("company_name");
					if(!(receiverCompanyName.equalsIgnoreCase("") || receiverCompanyName.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_company_name(receiverCompanyName);
					}
					String receiverDesignation = jsonReceiverData.getString("designation");
					if(!(receiverDesignation.equalsIgnoreCase("") || receiverDesignation.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_designation(receiverDesignation);
					}
					String receiverPhone = jsonReceiverData.getString("phone");
					if(!(receiverPhone.equalsIgnoreCase("") || receiverPhone.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_phone(receiverPhone);
					}
					String receiverAttendeeID = jsonReceiverData.getString("attendee_id");
					if(!(receiverAttendeeID.equalsIgnoreCase("") || receiverAttendeeID.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_attendee_id(receiverAttendeeID);
					}
					String receiverAttendeeIndustry = jsonReceiverData.getString("attendee_industry");
					if(!(receiverAttendeeIndustry.equalsIgnoreCase("") || receiverAttendeeIndustry.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_attendee_industry(receiverAttendeeIndustry);
					}
					String receiverAttendeeFunctionality = jsonReceiverData.getString("attendee_functionality");
					if(!(receiverAttendeeFunctionality.equalsIgnoreCase("") || receiverAttendeeFunctionality.equalsIgnoreCase(null)))
					{
						bookmarks.setReceiver_attendee_functionality(receiverAttendeeFunctionality);
					}
					
					bookmarksList.add(bookmarks);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
		}
		return bookmarksList;
	}
}
