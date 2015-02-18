package com.procialize.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.procialize.customClasses.WallNotifications;

public class WallNotificationsParser {

	JSONObject jsonObj = null;
	
	// JSON Node names
    private static final String TAG_WALL_NOTIFICATION_LIST = "wall_notification_list";
    private static final String TAG_WALL_RECEIVER_DATA = "receiver_data";
 
    // JSONArray
    JSONArray wall_notification_list = null;
    ArrayList<WallNotifications> wallNotificationsList;
    WallNotifications wallNotifications;
    
	public ArrayList<WallNotifications> wallNotification_Parser(String jsonStr) {
		// TODO Auto-generated constructor stub
		
		wallNotificationsList = new ArrayList<WallNotifications>();
		
		if (jsonStr != null) {
			try {
				jsonObj = new JSONObject(jsonStr);
				
				// Getting event info JSON Array node
				wall_notification_list = jsonObj.getJSONArray(TAG_WALL_NOTIFICATION_LIST);
				JSONObject jsonWallNotification = null;
				JSONObject jsonReceiverData = null; 
				
				for (int i = 0; i < wall_notification_list.length(); i++) {
					jsonWallNotification = wall_notification_list.getJSONObject(i);
					wallNotifications = new WallNotifications();
					
					String notificationId = jsonWallNotification.getString("notification_id");
					if(!(notificationId.equalsIgnoreCase("") || notificationId.equalsIgnoreCase(null)))
					{
						wallNotifications.setNotification_id(notificationId);	
					}
					String notificationType = jsonWallNotification.getString("notification_type");
					if(!(notificationType.equalsIgnoreCase("") || notificationType.equalsIgnoreCase(null)))
					{
						wallNotifications.setNotification_type(notificationType);
					}
					String subjectId = jsonWallNotification.getString("subject_id");
					if(!(subjectId.equalsIgnoreCase("") || subjectId.equalsIgnoreCase(null)))
					{
						wallNotifications.setSubject_id(subjectId);
					}
					String subjectType = jsonWallNotification.getString("subject_type");
					if(!(subjectType.equalsIgnoreCase("") || subjectType.equalsIgnoreCase(null)))
					{
						wallNotifications.setSubject_type(subjectType);
					}
					String objectId = jsonWallNotification.getString("object_id");
					if(!(objectId.equalsIgnoreCase("") || objectId.equalsIgnoreCase(null)))
					{
						wallNotifications.setObject_id(objectId);
					}
					String objectType = jsonWallNotification.getString("object_type");
					if(!(objectType.equalsIgnoreCase("") || objectType.equalsIgnoreCase(null)))
					{
						wallNotifications.setObject_type(objectType);
					}
					String notificationContent = jsonWallNotification.getString("notification_content");
					if(!(notificationContent.equalsIgnoreCase("") || notificationContent.equalsIgnoreCase(null)))
					{
						wallNotifications.setNotification_content(notificationContent);
					}
					String eventId = jsonWallNotification.getString("event_id");
					if(!(eventId.equalsIgnoreCase("") || eventId.equalsIgnoreCase(null)))
					{
						wallNotifications.setEvent_id(eventId);
					}
					String announcementId = jsonWallNotification.getString("announcement_id");
					if(!(announcementId.equalsIgnoreCase("") || announcementId.equalsIgnoreCase(null)))
					{
						wallNotifications.setAnnouncement_id(announcementId);
					}
					String notificationDate = jsonWallNotification.getString("notification_date");
					if(!(notificationDate.equalsIgnoreCase("") || notificationDate.equalsIgnoreCase(null)))
					{
						wallNotifications.setNotification_date(notificationDate);
					}
					String userId = jsonWallNotification.getString("user_id");
					if(!(userId.equalsIgnoreCase("") || userId.equalsIgnoreCase(null)))
					{
						wallNotifications.setUser_id(userId);	
					}
					String firstName = jsonWallNotification.getString("first_name");
					if(!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null)))
					{
						wallNotifications.setFirst_name(firstName);
					}
					String lastName = jsonWallNotification.getString("last_name");
					if(!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null)))
					{
						wallNotifications.setLast_name(lastName);
					}
					String typeOfUser = jsonWallNotification.getString("type_of_user");
					if(!(typeOfUser.equalsIgnoreCase("") || typeOfUser.equalsIgnoreCase(null)))
					{
						wallNotifications.setType_of_user(typeOfUser);	
					}
					String companyName = jsonWallNotification.getString("company_name");
					if(!(companyName.equalsIgnoreCase("") || companyName.equalsIgnoreCase(null)))
					{
						wallNotifications.setCompany_name(companyName);	
					}
					String designation = jsonWallNotification.getString("designation");
					if(!(designation.equalsIgnoreCase("") || designation.equalsIgnoreCase(null)))
					{
						wallNotifications.setDesignation(designation);
					}
					String phone = jsonWallNotification.getString("phone");
					if(!(phone.equalsIgnoreCase("") || phone.equalsIgnoreCase(null)))
					{
						wallNotifications.setPhone(phone);	
					}
					String photo = jsonWallNotification.getString("photo");
					if(!(photo.equalsIgnoreCase("") || photo.equalsIgnoreCase(null)))
					{
						wallNotifications.setPhoto(photo);	
					}
					String eventName = jsonWallNotification.getString("event_name");
					if(!(eventName.equalsIgnoreCase("") || eventName.equalsIgnoreCase(null)))
					{
						wallNotifications.setEvent_name(eventName);
					}
					String attendeeId = jsonWallNotification.getString("attendee_id");
					if(!(attendeeId.equalsIgnoreCase("") || attendeeId.equalsIgnoreCase(null)))
					{
						wallNotifications.setAttendee_id(attendeeId);	
					}
					String attendeeName = jsonWallNotification.getString("attendee_name");
					if(!(attendeeName.equalsIgnoreCase("") || attendeeName.equalsIgnoreCase(null)))
					{
						wallNotifications.setAttendee_name(attendeeName);
					}
					String organizerPhoto = jsonWallNotification.getString("organizer_photo");
					if(!(organizerPhoto.equalsIgnoreCase("") || organizerPhoto.equalsIgnoreCase(null)))
					{
						wallNotifications.setOrganizer_photo(organizerPhoto);
					}
					String organizerName = jsonWallNotification.getString("organizer_name");
					if(!(organizerName.equalsIgnoreCase("") || organizerName.equalsIgnoreCase(null)))
					{
						wallNotifications.setOrganizer_name(organizerName);
					}
					//Receiver Data
					jsonReceiverData = jsonWallNotification.getJSONObject(TAG_WALL_RECEIVER_DATA);
					
					String receiverUserID = jsonReceiverData.getString("user_id");
					if(!(receiverUserID.equalsIgnoreCase("") || receiverUserID.equalsIgnoreCase(null)))
					{
						wallNotifications.setReceiver_user_id(receiverUserID);
					}
					String receiverFirstName = jsonReceiverData.getString("first_name");
					if(!(receiverFirstName.equalsIgnoreCase("") || receiverFirstName.equalsIgnoreCase(null)))
					{
						wallNotifications.setReceiver_first_name(receiverFirstName);
					}
					String receiverLastName = jsonReceiverData.getString("last_name");
					if(!(receiverLastName.equalsIgnoreCase("") || receiverLastName.equalsIgnoreCase(null)))
					{
						wallNotifications.setReceiver_last_name(receiverLastName);
					}
					String receiverTypeOfUser = jsonReceiverData.getString("type_of_user");
					if(!(receiverTypeOfUser.equalsIgnoreCase("") || receiverTypeOfUser.equalsIgnoreCase(null)))
					{
						wallNotifications.setReceiver_type_of_user(receiverTypeOfUser);
					}
					String receiverCompanyName = jsonReceiverData.getString("company_name");
					if(!(receiverCompanyName.equalsIgnoreCase("") || receiverCompanyName.equalsIgnoreCase(null)))
					{
						wallNotifications.setReceiver_company_name(receiverCompanyName);
					}
					String receiverDesignation = jsonReceiverData.getString("designation");
					if(!(receiverDesignation.equalsIgnoreCase("") || receiverDesignation.equalsIgnoreCase(null)))
					{
						wallNotifications.setReceiver_designation(receiverDesignation);
					}
					String receiverAttendeeID = jsonReceiverData.getString("attendee_id");
					if(!(receiverAttendeeID.equalsIgnoreCase("") || receiverAttendeeID.equalsIgnoreCase(null)))
					{
						wallNotifications.setReceiver_attendee_id(receiverAttendeeID);
					}
					String receiverAttendeeType = jsonReceiverData.getString("attendee_type");
					if(!(receiverAttendeeType.equalsIgnoreCase("") || receiverAttendeeType.equalsIgnoreCase(null)))
					{
						wallNotifications.setReceiver_attendee_type(receiverAttendeeType);
					}
					
					wallNotificationsList.add(wallNotifications);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
		}
		return wallNotificationsList;
	}
}
