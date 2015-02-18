package com.procialize.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.procialize.customClasses.UserNotifications;

public class UserNotificationParser {
	JSONObject jsonObj = null;
	
	// JSON Node names
    private static final String TAG_USER_NOTIFICATION_LIST = "user_notification_list";
    private static final String TAG_USER_RECEIVER_DATA = "receiver_data";
 
    // JSONArray
    JSONArray user_notification_list = null;
    ArrayList<UserNotifications> userNotificationsList;
    UserNotifications userNotifications;
    
	public ArrayList<UserNotifications> userNotification_Parser(String jsonStr) {
		// TODO Auto-generated constructor stub
		
		userNotificationsList = new ArrayList<UserNotifications>();
		
		if (jsonStr != null) {
			try {
				jsonObj = new JSONObject(jsonStr);
				
				// Getting event info JSON Array node
				user_notification_list = jsonObj.getJSONArray(TAG_USER_NOTIFICATION_LIST);
				JSONObject jsonUserNotification = null;
				JSONObject jsonReceiverData = null; 
				
				for (int i = 0; i < user_notification_list.length(); i++) {
					jsonUserNotification = user_notification_list.getJSONObject(i);
					userNotifications = new UserNotifications();
					
					String notificationId = jsonUserNotification.getString("notification_id");
					if(!(notificationId.equalsIgnoreCase("") || notificationId.equalsIgnoreCase(null)))
					{
						userNotifications.setNotification_id(notificationId);	
					}
					String notificationType = jsonUserNotification.getString("notification_type");
					if(!(notificationType.equalsIgnoreCase("") || notificationType.equalsIgnoreCase(null)))
					{
						userNotifications.setNotification_type(notificationType);
					}
					String subjectId = jsonUserNotification.getString("subject_id");
					if(!(subjectId.equalsIgnoreCase("") || subjectId.equalsIgnoreCase(null)))
					{
						userNotifications.setSubject_id(subjectId);
					}
					String subjectType = jsonUserNotification.getString("subject_type");
					if(!(subjectType.equalsIgnoreCase("") || subjectType.equalsIgnoreCase(null)))
					{
						userNotifications.setSubject_type(subjectType);
					}
					String objectId = jsonUserNotification.getString("object_id");
					if(!(objectId.equalsIgnoreCase("") || objectId.equalsIgnoreCase(null)))
					{
						userNotifications.setObject_id(objectId);
					}
					String objectType = jsonUserNotification.getString("object_type");
					if(!(objectType.equalsIgnoreCase("") || objectType.equalsIgnoreCase(null)))
					{
						userNotifications.setObject_type(objectType);
					}
					String read = jsonUserNotification.getString("read");
					if(!(read.equalsIgnoreCase("") || read.equalsIgnoreCase(null)))
					{
						userNotifications.setRead(read);
					}
					String notificationContent = jsonUserNotification.getString("notification_content");
					if(!(notificationContent.equalsIgnoreCase("") || notificationContent.equalsIgnoreCase(null)))
					{
						userNotifications.setNotification_content(notificationContent);
					}
					String meetingId = jsonUserNotification.getString("meeting_id");
					if(!(meetingId.equalsIgnoreCase("") || meetingId.equalsIgnoreCase(null)))
					{
						userNotifications.setMeeting_id(meetingId);
					}
					String messageId = jsonUserNotification.getString("message_id");
					if(!(messageId.equalsIgnoreCase("") || messageId.equalsIgnoreCase(null)))
					{
						userNotifications.setMessage_id(messageId);
					}
					String eventId = jsonUserNotification.getString("event_id");
					if(!(eventId.equalsIgnoreCase("") || eventId.equalsIgnoreCase(null)))
					{
						userNotifications.setEvent_id(eventId);
					}
					String notificationDate = jsonUserNotification.getString("notification_date");
					if(!(notificationDate.equalsIgnoreCase("") || notificationDate.equalsIgnoreCase(null)))
					{
						userNotifications.setNotification_date(notificationDate);
					}
					String userId = jsonUserNotification.getString("user_id");
					if(!(userId.equalsIgnoreCase("") || userId.equalsIgnoreCase(null)))
					{
						userNotifications.setUser_id(userId);	
					}
					String firstName = jsonUserNotification.getString("first_name");
					if(!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null)))
					{
						userNotifications.setFirst_name(firstName);
					}
					String lastName = jsonUserNotification.getString("last_name");
					if(!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null)))
					{
						userNotifications.setLast_name(lastName);
					}
					String typeOfUser = jsonUserNotification.getString("type_of_user");
					if(!(typeOfUser.equalsIgnoreCase("") || typeOfUser.equalsIgnoreCase(null)))
					{
						userNotifications.setType_of_user(typeOfUser);	
					}
					String companyName = jsonUserNotification.getString("company_name");
					if(!(companyName.equalsIgnoreCase("") || companyName.equalsIgnoreCase(null)))
					{
						userNotifications.setCompany_name(companyName);	
					}
					String designation = jsonUserNotification.getString("designation");
					if(!(designation.equalsIgnoreCase("") || designation.equalsIgnoreCase(null)))
					{
						userNotifications.setDesignation(designation);
					}
					String phone = jsonUserNotification.getString("phone");
					if(!(phone.equalsIgnoreCase("") || phone.equalsIgnoreCase(null)))
					{
						userNotifications.setPhone(phone);	
					}
					String photo = jsonUserNotification.getString("photo");
					if(!(photo.equalsIgnoreCase("") || photo.equalsIgnoreCase(null)))
					{
						userNotifications.setPhoto(photo);	
					}
		            String approve = jsonUserNotification.getString("approve");
					if(!(approve.equalsIgnoreCase("") || approve.equalsIgnoreCase(null)))
					{
						userNotifications.setApprove(approve);
					}
					String startTime = jsonUserNotification.getString("start_time");
					if(!(startTime.equalsIgnoreCase("") || startTime.equalsIgnoreCase(null)))
					{
						userNotifications.setStart_time(startTime);	
					}
					String endTime = jsonUserNotification.getString("end_time");
					if(!(endTime.equalsIgnoreCase("") || endTime.equalsIgnoreCase(null)))
					{
						userNotifications.setEnd_time(endTime);
					}
					String eventName = jsonUserNotification.getString("event_name");
					if(!(eventName.equalsIgnoreCase("") || eventName.equalsIgnoreCase(null)))
					{
						userNotifications.setEvent_name(eventName);
					}
					String attendeeId = jsonUserNotification.getString("attendee_id");
					if(!(attendeeId.equalsIgnoreCase("") || attendeeId.equalsIgnoreCase(null)))
					{
						userNotifications.setAttendee_id(attendeeId);	
					}
					String attendeeName = jsonUserNotification.getString("attendee_name");
					if(!(attendeeName.equalsIgnoreCase("") || attendeeName.equalsIgnoreCase(null)))
					{
						userNotifications.setAttendee_name(attendeeName);
					}
					String organizerName = jsonUserNotification.getString("organizer_name");
					if(!(organizerName.equalsIgnoreCase("") || organizerName.equalsIgnoreCase(null)))
					{
						userNotifications.setOrganizer_name(organizerName);
					}
					//Receiver Data
					jsonReceiverData = jsonUserNotification.getJSONObject(TAG_USER_RECEIVER_DATA);
					
					String receiverTypeOfUser = jsonReceiverData.getString("type_of_user");
					if(!(receiverTypeOfUser.equalsIgnoreCase("") || receiverTypeOfUser.equalsIgnoreCase(null)))
					{
						userNotifications.setReceiver_type_of_user(receiverTypeOfUser);
					}
					if(receiverTypeOfUser.equalsIgnoreCase("O"))
					{
						String receiverUserID = jsonReceiverData.getString("user_id");
						if(!(receiverUserID.equalsIgnoreCase("") || receiverUserID.equalsIgnoreCase(null)))
						{
							userNotifications.setReceiver_user_id(receiverUserID);
						}
						String receiverFirstName = jsonReceiverData.getString("first_name");
						if(!(receiverFirstName.equalsIgnoreCase("") || receiverFirstName.equalsIgnoreCase(null)))
						{
							userNotifications.setReceiver_first_name(receiverFirstName);
						}
						String receiverLastName = jsonReceiverData.getString("last_name");
						if(!(receiverLastName.equalsIgnoreCase("") || receiverLastName.equalsIgnoreCase(null)))
						{
							userNotifications.setReceiver_last_name(receiverLastName);
						}
					}else{
						String receiverUserID = jsonReceiverData.getString("user_id");
						if(!(receiverUserID.equalsIgnoreCase("") || receiverUserID.equalsIgnoreCase(null)))
						{
							userNotifications.setReceiver_user_id(receiverUserID);
						}
						String receiverFirstName = jsonReceiverData.getString("first_name");
						if(!(receiverFirstName.equalsIgnoreCase("") || receiverFirstName.equalsIgnoreCase(null)))
						{
							userNotifications.setReceiver_first_name(receiverFirstName);
						}
						String receiverLastName = jsonReceiverData.getString("last_name");
						if(!(receiverLastName.equalsIgnoreCase("") || receiverLastName.equalsIgnoreCase(null)))
						{
							userNotifications.setReceiver_last_name(receiverLastName);
						}
						String receiverCompanyName = jsonReceiverData.getString("company_name");
						if(!(receiverCompanyName.equalsIgnoreCase("") || receiverCompanyName.equalsIgnoreCase(null)))
						{
							userNotifications.setReceiver_company_name(receiverCompanyName);
						}
						String receiverDesignation = jsonReceiverData.getString("designation");
						if(!(receiverDesignation.equalsIgnoreCase("") || receiverDesignation.equalsIgnoreCase(null)))
						{
							userNotifications.setReceiver_designation(receiverDesignation);
						}
						String receiverPhone = jsonReceiverData.getString("phone");
						if(!(receiverPhone.equalsIgnoreCase("") || receiverPhone.equalsIgnoreCase(null)))
						{
							userNotifications.setReceiver_phone(receiverPhone);
						}
						String receiverPhoto = jsonReceiverData.getString("photo");
						if(!(receiverPhoto.equalsIgnoreCase("") || receiverPhoto.equalsIgnoreCase(null)))
						{
							userNotifications.setReceiver_photo(receiverPhoto);
						}
						String receiverAttendeeID = jsonReceiverData.getString("attendee_id");
						if(!(receiverAttendeeID.equalsIgnoreCase("") || receiverAttendeeID.equalsIgnoreCase(null)))
						{
							userNotifications.setReceiver_attendee_id(receiverAttendeeID);
						}
						String receiverAttendeeType = jsonReceiverData.getString("attendee_type");
						if(!(receiverAttendeeType.equalsIgnoreCase("") || receiverAttendeeType.equalsIgnoreCase(null)))
						{
							userNotifications.setReceiver_attendee_type(receiverAttendeeType);
						}	
					}
					
					userNotificationsList.add(userNotifications);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any data from the url");
		}
		return userNotificationsList;
	}
}
