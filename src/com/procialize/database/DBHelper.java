package com.procialize.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.procialize.customClasses.Attendees;
import com.procialize.customClasses.Events;
import com.procialize.customClasses.Notifications;
import com.procialize.customClasses.Profile;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "ProcializeDatabase.db";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Events Table
	public static final String EVENTS_TABLE_NAME = "EVENT";

	public static final String EVENT_ID = "EVENT_ID";
	public static final String EVENT_NAME = "EVENT_NAME";
	public static final String EVENT_DESCRIPTION = "EVENT_DESCRIPTION";
	public static final String FEATURED = "FEATURED";
	public static final String EVENT_START = "EVENT_START";
	public static final String EVENT_END = "EVENT_END";
	public static final String EVENT_LOCATION = "EVENT_LOCATION";
	public static final String EVENT_CITY = "EVENT_CITY";
	public static final String EVENT_COUNRTY = "EVENT_COUNRTY";
	public static final String EVENT_LATITUDE = "EVENT_LATITUDE";
	public static final String EVENT_LONGITUDE = "EVENT_LONGITUDE";
	public static final String EVENT_WEBSITE = "EVENT_WEBSITE";
	public static final String EVENT_LINKEDIN_PAGE_LINK = "EVENT_LINKEDIN_PAGE_LINK";
	public static final String EVENT_TWITTER_PAGE_LINK = "EVENT_TWITTER_PAGE_LINK";
	public static final String EVENT_FACEBOOK_PAGE_LINK = "EVENT_FACEBOOK_PAGE_LINK";
	public static final String EVENT_LOGO = "EVENT_LOGO";
	public static final String EVENT_IMAGE_1 = "EVENT_IMAGE_1";
	public static final String EVENT_IMAGE_2 = "EVENT_IMAGE_2";
	public static final String EVENT_IMAGE_3 = "EVENT_IMAGE_3";
	public static final String EVENT_CONTACT_NAME = "EVENT_CONTACT_NAME";
	public static final String EVENT_EMAIL = "EVENT_EMAIL";
	public static final String FLOOR_PLAN = "FLOOR_PLAN";
	public static final String ORGANIZER_ID = "ORGANIZER_ID";
	public static final String EVENT_ORGANIZER = "EVENT_ORGANIZER";
	public static final String ORGANIZER_PHOTO = "ORGANIZER_PHOTO";
	public static final String EVENT_INDUSTRY = "EVENT_INDUSTRY";
	public static final String EVENT_FUNCTIONALITY = "EVENT_FUNCTIONALITY";

	// Attendees Table
	public static final String ATTENDEES_TABLE_NAME = "ATTENDEES";

	public static final String ATTENDEE_ID = "ATTENDEE_ID";
	public static final String ATTENDEE_FIRST_NAME = "ATTENDEE_FIRST_NAME";
	public static final String ATTENDEE_LAST_NAME = "ATTENDEE_LAST_NAME";
	public static final String ATTENDEE_TYPE = "ATTENDEE_TYPE";// EXHIBITOR - E,
																// ATTENDEE - A,
																// SPEAKER - S
	public static final String ATTENDEE_INDUSTRY = "ATTENDEE_INDUSTRY";
	public static final String ATTENDEE_FUNCTIONALITY = "ATTENDEE_FUNCTIONALITY";
	public static final String ATTENDEE_EMAIL = "ATTENDEE_EMAIL";
	public static final String ATTENDEE_COMPANY_NAME = "ATTENDEE_COMPANY_NAME";
	public static final String ATTENDEE_DESIGNATION = "ATTENDEE_DESIGNATION";
	public static final String ATTENDEE_PHONE_NUMBER = "ATTENDEE_PHONE_NUMBER";
	public static final String ATTENDEE_IMAGE = "ATTENDEE_IMAGE";// PROFILE PIC
	public static final String ATTENDEE_LOCATION = "ATTENDEE_LOCATION";
	public static final String ATTENDEE_CITY = "ATTENDEE_CITY";
	public static final String ATTENDEE_COUNTRY = "ATTENDEE_COUNTRY";
	public static final String ATTENDEE_DESCRIPTION = "ATTENDEE_DESCRIPTION";
	public static final String ATTENDEE_LINKEDIN_ID = "ATTENDEE_LINKEDIN_ID";// LINKEDIN ID
	public static final String ATTENDEE_FACEBOOK_ID = "ATTENDEE_FACEBOOK_ID";// FB_ID
	public static final String ATTENDEE_PASSCODE = "ATTENDEE_PASSCODE";
	public static final String GCM_REGISTRATION_NUMBER = "GCM_REGISTRATION_NUMBER";
	public static final String ATTENDEE_MOBILE_OS = "ATTENDEE_MOBILE_OS";
	public static final String STATUS = "STATUS";
	public static final String ATTENDEE_FEATURED = "ATTENDEE_FEATURED";
	public static final String STALL_NUMBER = "STALL_NUMBER";
	public static final String EXHIBITOR_WEBSITE = "EXHIBITOR_WEBSITE";
	public static final String ATTENDEE_IMAGE_1 = "ATTENDEE_IMAGE_1";
	public static final String ATTENDEE_IMAGE_2 = "ATTENDEE_IMAGE_2";
	public static final String BROCHURE = "BROCHURE";
	public static final String ATTENDEE_MOBILE_NUMBER = "ATTENDEE_MOBILE_NUMBER";

	// My Profile Table
	public static final String PROFILE_TABLE_NAME = "PROFILE";

	public static final String PROFILE_ATTENDEE_ID = "PROFILE_ATTENDEE_ID";
	public static final String PROFILE_API_ACCESS_TOKEN = "PROFILE_API_ACCESS_TOKEN";
	public static final String PROFILE_EMAIL = "PROFILE_EMAIL";
	public static final String PROFILE_STATUS = "PROFILE_STATUS";
	public static final String PROFILE_GCM_REGISTRATION_ID = "PROFILE_GCM_REGISTRATION_ID";
	public static final String PROFILE_MOBILE_OS = "PROFILE_MOBILE_OS";
	public static final String PROFILE_FIRST_NAME = "PROFILE_FIRST_NAME";
	public static final String PROFILE_LAST_NAME = "PROFILE_LAST_NAME";
	public static final String PROFILE_LINKEDIN = "PROFILE_LINKEDIN";
	public static final String PROFILE_FACEBOOK = "PROFILE_FACEBOOK";
	public static final String PROFILE_COMPANY_NAME = "PROFILE_COMPANY_NAME";
	public static final String PROFILE_DESIGNATION = "PROFILE_DESIGNATION";
	public static final String PROFILE_IMAGE = "PROFILE_IMAGE";
	public static final String PROFILE_DESCRIPTION = "PROFILE_DESCRIPTION";
	public static final String PROFILE_CITY = "PROFILE_CITY";
	public static final String PROFILE_COUNRTY = "PROFILE_COUNRTY";
	public static final String PROFILE_INDUSTRY = "PROFILE_INDUSTRY";
	public static final String PROFILE_FUNCTIONALITY = "PROFILE_FUNCTIONALITY";
	public static final String PROFILE_PHONE_NUMBER = "PROFILE_PHONE_NUMBER";
	public static final String PROFILE_MOBILE_NUMBER = "PROFILE_MOBILE_NUMBER";
	
	// Wall Notification Table
	public static final String WALL_NOTIFICATION_TABLE_NAME = "WALL_NOTIFICATIONS";
	
	public static final String WALL_NOTIFICATION_ID = "WALL_NOTIFICATION_ID";
	public static final String WALL_NOTIFICATION_TYPE = "WALL_NOTIFICATION_TYPE";
	public static final String WALL_NOTIFICATION_SUBJECT_ID = "WALL_NOTIFICATION_SUBJECT_ID";
	public static final String WALL_NOTIFICATION_SUBJECT_TYPE = "WALL_NOTIFICATION_SUBJECT_TYPE";
	public static final String WALL_NOTIFICATION_OBJECT_ID = "WALL_NOTIFICATION_OBJECT_ID";
	public static final String WALL_NOTIFICATION_OBJECT_TYPE = "WALL_NOTIFICATION_OBJECT_TYPE";
	public static final String WALL_NOTIFICATION_READ = "WALL_NOTIFICATION_READ";
	public static final String WALL_NOTIFICATION_CONTENT = "WALL_NOTIFICATION_CONTENT";
	public static final String WALL_NOTIFICATION_MEETING_ID = "WALL_NOTIFICATION_MEETING_ID";
	public static final String WALL_NOTIFICATION_MESSAGE_ID = "WALL_NOTIFICATION_MESSAGE_ID";
	public static final String WALL_NOTIFICATION_EVENT_ID = "WALL_NOTIFICATION_EVENT_ID";
	public static final String WALL_NOTIFICATION_DATE = "WALL_NOTIFICATION_DATE";
	public static final String WALL_NOTIFICATION_USER_ID = "WALL_NOTIFICATION_USER_ID";
	public static final String WALL_NOTIFICATION_FIRST_NAME = "WALL_NOTIFICATION_FIRST_NAME";
	public static final String WALL_NOTIFICATION_LAST_NAME = "WALL_NOTIFICATION_LAST_NAME";
	public static final String WALL_NOTIFICATION_TYPE_OF_USER = "WALL_NOTIFICATION_TYPE_OF_USER";
	public static final String WALL_NOTIFICATION_COMPANY_NAME = "WALL_NOTIFICATION_COMPANY_NAME";
	public static final String WALL_NOTIFICATION_DESIGNATION = "WALL_NOTIFICATION_DESIGNATION";
	public static final String WALL_NOTIFICATION_PHONE_NUMBER = "WALL_NOTIFICATION_PHONE_NUMBER";
	public static final String WALL_NOTIFICATION_PHOTO = "WALL_NOTIFICATION_PHOTO";
	public static final String WALL_NOTIFICATION_EVENT_NAME = "WALL_NOTIFICATION_EVENT_NAME";
	public static final String WALL_NOTIFICATION_ATTENDEE_ID = "WALL_NOTIFICATION_ATTENDEE_ID";
	public static final String WALL_NOTIFICATION_ATTENDEE_NAME = "WALL_NOTIFICATION_ATTENDEE_NAME";
	public static final String WALL_NOTIFICATION_ORGANIZER_NAME = "WALL_NOTIFICATION_ORGANIZER_NAME";
	//Wall Notification Receiver Data
	public static final String WALL_RECEIVER_USER_ID = "WALL_RECEIVER_USER_ID";
	public static final String WALL_RECEIVER_FIRST_NAME = "WALL_RECEIVER_FIRST_NAME";
	public static final String WALL_RECEIVER_LAST_NAME = "WALL_RECEIVER_LAST_NAME";
	public static final String WALL_RECEIVER_TYPE_OF_USER = "WALL_RECEIVER_TYPE_OF_USER";
	public static final String WALL_RECEIVER_COMPANY_NAME = "WALL_RECEIVER_COMPANY_NAME";
	public static final String WALL_RECEIVER_DESIGNATION = "WALL_RECEIVER_DESIGNATION";
	public static final String WALL_RECEIVER_PHONE = "WALL_RECEIVER_PHONE";
	public static final String WALL_RECEIVER_PHOTO = "WALL_RECEIVER_PHOTO";
	public static final String WALL_RECEIVER_ATTENDEE_ID = "WALL_RECEIVER_ATTENDEE_ID";
	public static final String WALL_RECEIVER_ATTENDEE_TYPE = "WALL_RECEIVER_ATTENDEE_TYPE";
	
	// User Notification Table
	public static final String USER_NOTIFICATION_TABLE_NAME = "USER_NOTIFICATIONS";

	public static final String USER_NOTIFICATION_ID = "USER_NOTIFICATION_ID";
	public static final String USER_NOTIFICATION_TYPE = "USER_NOTIFICATION_TYPE";
	public static final String USER_NOTIFICATION_SUBJECT_ID = "USER_NOTIFICATION_SUBJECT_ID";
	public static final String USER_NOTIFICATION_SUBJECT_TYPE = "USER_NOTIFICATION_SUBJECT_TYPE";
	public static final String USER_NOTIFICATION_OBJECT_ID = "USER_NOTIFICATION_OBJECT_ID";
	public static final String USER_NOTIFICATION_OBJECT_TYPE = "USER_NOTIFICATION_OBJECT_TYPE";
	public static final String USER_NOTIFICATION_READ = "USER_NOTIFICATION_READ";
	public static final String USER_NOTIFICATION_CONTENT = "USER_NOTIFICATION_CONTENT";
	public static final String USER_NOTIFICATION_MEETING_ID = "USER_NOTIFICATION_MEETING_ID";
	public static final String USER_NOTIFICATION_MESSAGE_ID = "USER_NOTIFICATION_MESSAGE_ID";
	public static final String USER_NOTIFICATION_EVENT_ID = "USER_NOTIFICATION_EVENT_ID";
	public static final String USER_NOTIFICATION_DATE = "USER_NOTIFICATION_DATE";
	public static final String USER_NOTIFICATION_USER_ID = "USER_NOTIFICATION_USER_ID";
	public static final String USER_NOTIFICATION_FIRST_NAME = "USER_NOTIFICATION_FIRST_NAME";
	public static final String USER_NOTIFICATION_LAST_NAME = "USER_NOTIFICATION_LAST_NAME";
	public static final String USER_NOTIFICATION_TYPE_OF_USER = "USER_NOTIFICATION_TYPE_OF_USER";
	public static final String USER_NOTIFICATION_COMPANY_NAME = "USER_NOTIFICATION_COMPANY_NAME";
	public static final String USER_NOTIFICATION_DESIGNATION = "USER_NOTIFICATION_DESIGNATION";
	public static final String USER_NOTIFICATION_PHONE_NUMBER = "USER_NOTIFICATION_PHONE_NUMBER";
	public static final String USER_NOTIFICATION_PHOTO = "USER_NOTIFICATION_PHOTO";
	public static final String USER_NOTIFICATION_APPROVE = "USER_NOTIFICATION_APPROVE";
	public static final String USER_NOTIFICATION_START_TIME = "USER_NOTIFICATION_START_TIME";
	public static final String USER_NOTIFICATION_END_TIME = "USER_NOTIFICATION_END_TIME";
	public static final String USER_NOTIFICATION_EVENT_NAME = "USER_NOTIFICATION_EVENT_NAME";
	public static final String USER_NOTIFICATION_ATTENDEE_ID = "USER_NOTIFICATION_ATTENDEE_ID";
	public static final String USER_NOTIFICATION_ATTENDEE_NAME = "USER_NOTIFICATION_ATTENDEE_NAME";
	public static final String USER_NOTIFICATION_ORGANIZER_NAME = "USER_NOTIFICATION_ORGANIZER_NAME";
	// Wall Notification Receiver Data
	public static final String USER_RECEIVER_USER_ID = "USER_RECEIVER_USER_ID";
	public static final String USER_RECEIVER_FIRST_NAME = "USER_RECEIVER_FIRST_NAME";
	public static final String USER_RECEIVER_LAST_NAME = "USER_RECEIVER_LAST_NAME";
	public static final String USER_RECEIVER_TYPE_OF_USER = "USER_RECEIVER_TYPE_OF_USER";
	public static final String USER_RECEIVER_COMPANY_NAME = "USER_RECEIVER_COMPANY_NAME";
	public static final String USER_RECEIVER_DESIGNATION = "USER_RECEIVER_DESIGNATION";
	public static final String USER_RECEIVER_PHONE = "USER_RECEIVER_PHONE";
	public static final String USER_RECEIVER_PHOTO = "USER_RECEIVER_PHOTO";
	public static final String USER_RECEIVER_ATTENDEE_ID = "USER_RECEIVER_ATTENDEE_ID";
	public static final String USER_RECEIVER_ATTENDEE_TYPE = "USER_RECEIVER_ATTENDEE_TYPE";	
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.d("DB", "DB Created");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// Creating events table
		db.execSQL("create table "+ EVENTS_TABLE_NAME
				+ "("
				+ EVENT_ID+" text, "+EVENT_NAME+" text, "+EVENT_DESCRIPTION+" text, "+FEATURED+" text, "+EVENT_START+" text, "+EVENT_END+" text, "
				+ EVENT_LOCATION+" text, "+EVENT_CITY+" text, "+EVENT_COUNRTY+" text, "+EVENT_LATITUDE+" text, "+EVENT_LONGITUDE+" text, "+EVENT_WEBSITE+" text, "
				+ EVENT_LINKEDIN_PAGE_LINK+" text, "+EVENT_TWITTER_PAGE_LINK+" text, "+EVENT_FACEBOOK_PAGE_LINK+" text, "+EVENT_LOGO+" text, "+EVENT_IMAGE_1+" text, "
				+ EVENT_IMAGE_2+" text, "+EVENT_IMAGE_3+" text, "+EVENT_CONTACT_NAME+" text, "+EVENT_EMAIL+" text, "+FLOOR_PLAN+" text, "+ORGANIZER_ID+" text, "
				+ EVENT_ORGANIZER+" text, "+ORGANIZER_PHOTO+" text, "+EVENT_INDUSTRY+" text, "+EVENT_FUNCTIONALITY+" text)");

		// Creating attendees table
		db.execSQL("create table " + ATTENDEES_TABLE_NAME
				+ "("
				+ ATTENDEE_ID+" text, "+ATTENDEE_FIRST_NAME+" text, "+ATTENDEE_LAST_NAME+" text, "+ATTENDEE_TYPE+" text, "+ATTENDEE_INDUSTRY+" text, "
				+ ATTENDEE_FUNCTIONALITY+" text, "+ATTENDEE_EMAIL+" text, "+ATTENDEE_COMPANY_NAME+" text, "+ATTENDEE_DESIGNATION+" text, "
				+ ATTENDEE_PHONE_NUMBER+" text, "+ATTENDEE_IMAGE+" text, "+ATTENDEE_LOCATION+" text, "+ATTENDEE_CITY+" text, "+ATTENDEE_COUNTRY+" text, "
				+ ATTENDEE_DESCRIPTION+" text, "+ATTENDEE_LINKEDIN_ID+" text, "+ATTENDEE_FACEBOOK_ID+" text, "+ATTENDEE_PASSCODE+" text, "
				+ GCM_REGISTRATION_NUMBER+" text, "+ATTENDEE_MOBILE_OS+" text, "+STATUS+" text, "+ATTENDEE_FEATURED+" text, "+STALL_NUMBER+" text, "
				+ EXHIBITOR_WEBSITE+" text, "+ATTENDEE_IMAGE_1+" text, "+ATTENDEE_IMAGE_2+" text, "+BROCHURE+" text, "+ATTENDEE_MOBILE_NUMBER+" text)");

		// Creating profile table		
		db.execSQL("create table " + PROFILE_TABLE_NAME  
				+ "("
				+ PROFILE_ATTENDEE_ID+" text, "+PROFILE_API_ACCESS_TOKEN+" text, "+PROFILE_EMAIL+" text, "+PROFILE_STATUS+" text, "+PROFILE_GCM_REGISTRATION_ID+" text, " 
				+ PROFILE_MOBILE_OS+" text, "+PROFILE_FIRST_NAME+" text, "+PROFILE_LAST_NAME+" text, "+PROFILE_LINKEDIN+" text, "+PROFILE_FACEBOOK+" text, "
				+ PROFILE_COMPANY_NAME+" text, "+PROFILE_DESIGNATION+" text, "+PROFILE_IMAGE+" text, "+PROFILE_DESCRIPTION+" text, "+PROFILE_CITY+" text, "
				+ PROFILE_COUNRTY+" text, "+PROFILE_INDUSTRY+" text, "+PROFILE_FUNCTIONALITY+" text, "+PROFILE_PHONE_NUMBER+" text, "
				+ PROFILE_MOBILE_NUMBER+" text)");
		
		// Creating wall notification table		
		db.execSQL("create table " + WALL_NOTIFICATION_TABLE_NAME 
				+ "("
				+ WALL_NOTIFICATION_ID+" text, "+WALL_NOTIFICATION_TYPE+" text, "+WALL_NOTIFICATION_SUBJECT_ID+" text, "+WALL_NOTIFICATION_SUBJECT_TYPE+" text, "
				+ WALL_NOTIFICATION_OBJECT_ID+" text, "+WALL_NOTIFICATION_OBJECT_TYPE+" text, "+WALL_NOTIFICATION_READ+" text, "+WALL_NOTIFICATION_CONTENT+" text, "
				+ WALL_NOTIFICATION_MEETING_ID+" text, "+WALL_NOTIFICATION_MESSAGE_ID+" text, "+WALL_NOTIFICATION_EVENT_ID+" text, "+WALL_NOTIFICATION_DATE+" text, "
				+ WALL_NOTIFICATION_USER_ID+" text, "+WALL_NOTIFICATION_FIRST_NAME+" text, "+WALL_NOTIFICATION_LAST_NAME+" text, "+WALL_NOTIFICATION_TYPE_OF_USER+" text, "
				+ WALL_NOTIFICATION_COMPANY_NAME+" text, "+WALL_NOTIFICATION_DESIGNATION+" text, "+WALL_NOTIFICATION_PHONE_NUMBER+" text, "+WALL_NOTIFICATION_PHOTO+" text, "
				+ WALL_NOTIFICATION_EVENT_NAME+" text, "+WALL_NOTIFICATION_ATTENDEE_ID+" text, "+WALL_NOTIFICATION_ATTENDEE_NAME+" text, "+WALL_NOTIFICATION_ORGANIZER_NAME+" text, "
				+ WALL_RECEIVER_USER_ID+" text, "+WALL_RECEIVER_FIRST_NAME+" text, "+WALL_RECEIVER_LAST_NAME+" text, "+WALL_RECEIVER_TYPE_OF_USER+" text, "+WALL_RECEIVER_COMPANY_NAME+" text, "
				+ WALL_RECEIVER_DESIGNATION+" text, "+WALL_RECEIVER_PHONE+" text, "+WALL_RECEIVER_PHOTO+" text, "+WALL_RECEIVER_ATTENDEE_ID+" text, "+WALL_RECEIVER_ATTENDEE_TYPE+" text)");

		// Creating user notification table		
		db.execSQL("create table " + USER_NOTIFICATION_TABLE_NAME 
				+ "("
				+ USER_NOTIFICATION_ID+" text, "+USER_NOTIFICATION_TYPE+" text, "+USER_NOTIFICATION_SUBJECT_ID+" text, "+USER_NOTIFICATION_SUBJECT_TYPE+" text, "
				+ USER_NOTIFICATION_OBJECT_ID+" text, "+USER_NOTIFICATION_OBJECT_TYPE+" text, "+USER_NOTIFICATION_READ+" text, "+USER_NOTIFICATION_CONTENT+" text, "
				+ USER_NOTIFICATION_MEETING_ID+" text, "+USER_NOTIFICATION_MESSAGE_ID+" text, "+USER_NOTIFICATION_EVENT_ID+" text, "+USER_NOTIFICATION_DATE+" text, "
				+ USER_NOTIFICATION_USER_ID+" text, "+USER_NOTIFICATION_FIRST_NAME+" text, "+USER_NOTIFICATION_LAST_NAME+" text, "+USER_NOTIFICATION_TYPE_OF_USER+" text, "
				+ USER_NOTIFICATION_COMPANY_NAME+" text, "+USER_NOTIFICATION_DESIGNATION+" text, "+USER_NOTIFICATION_PHONE_NUMBER+" text, "+USER_NOTIFICATION_PHOTO+" text, "
				+ USER_NOTIFICATION_APPROVE+" text, "+USER_NOTIFICATION_START_TIME+" text, "+USER_NOTIFICATION_END_TIME+" text, "+USER_NOTIFICATION_EVENT_NAME+" text, "
				+ USER_NOTIFICATION_ATTENDEE_ID+" text, "+USER_NOTIFICATION_ATTENDEE_NAME+" text, "+USER_NOTIFICATION_ORGANIZER_NAME+" text, "+USER_RECEIVER_USER_ID+" text, "
				+ USER_RECEIVER_FIRST_NAME+" text, "+USER_RECEIVER_LAST_NAME+" text, "+USER_RECEIVER_TYPE_OF_USER+" text, "+USER_RECEIVER_COMPANY_NAME+" text, "
				+ USER_RECEIVER_DESIGNATION+" text, "+USER_RECEIVER_PHONE+" text, "+USER_RECEIVER_PHOTO+" text, "+USER_RECEIVER_ATTENDEE_ID+" text, "
				+ USER_RECEIVER_ATTENDEE_TYPE+" text)");

		Log.d("DB", "Database created with five tables");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+EVENTS_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "+ATTENDEES_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "+PROFILE_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "+WALL_NOTIFICATION_TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS "+USER_NOTIFICATION_TABLE_NAME);

		onCreate(db);
	}

	// Open SQLite DB
	public DBHelper open(SQLiteDatabase db) throws SQLException {
		db = this.getWritableDatabase();
		return this;
	}

	// Close SQLite DB
	/*
	 * public void close(SQLiteDatabase db) { db.close(); }
	 */

	public void insertEventInfo(ArrayList<Events> eventsList, SQLiteDatabase db) {
		db = this.getWritableDatabase();
		ContentValues contentValues;

		try {
			for (int i = 0; i < eventsList.size(); i++) {
				contentValues = new ContentValues();

				String eventID = eventsList.get(i).getEvent_id();
				if (!(eventID.equalsIgnoreCase("") || eventID.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_ID, eventID);
				}
				String eventName = eventsList.get(i).getEvent_name();
				if (!(eventName.equalsIgnoreCase("") || eventName.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_NAME, eventName);
				}
				String eventDesc = eventsList.get(i).getEvent_description();
				if (!(eventDesc.equalsIgnoreCase("") || eventDesc.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_DESCRIPTION, eventDesc);
				}
				String featured = eventsList.get(i).getFeatured();
				if (!(featured.equalsIgnoreCase("") || featured.equalsIgnoreCase(null))) {
					contentValues.put(FEATURED, featured);
				}
				String eventStart = eventsList.get(i).getEvent_start();
				if (!(eventStart.equalsIgnoreCase("") || eventStart.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_START, eventStart);
				}
				String eventEnd = eventsList.get(i).getEvent_end();
				if (!(eventEnd.equalsIgnoreCase("") || eventEnd.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_END, eventEnd);
				}
				String eventLocation = eventsList.get(i).getEvent_location();
				if (!(eventLocation.equalsIgnoreCase("") || eventLocation.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_LOCATION, eventLocation);
				}
				String eventCity = eventsList.get(i).getEvent_city();
				if (!(eventCity.equalsIgnoreCase("") || eventCity.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_CITY, eventCity);
				}
				String eventCountry = eventsList.get(i).getEvent_country();
				if (!(eventCountry.equalsIgnoreCase("") || eventCountry.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_COUNRTY, eventCountry);
				}
				String eventLatitude = eventsList.get(i).getEvent_latitude();
				if (!(eventLatitude.equalsIgnoreCase("") || eventLatitude.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_LATITUDE, eventLatitude);
				}
				String eventLongitude = eventsList.get(i).getEvent_longitude();
				if (!(eventLongitude.equalsIgnoreCase("") || eventLongitude.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_LONGITUDE, eventLongitude);
				}
				String website = eventsList.get(i).getEvent_website();
				if (!(website.equalsIgnoreCase("") || website.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_WEBSITE, website);
				}
				String eventLinkedIn = eventsList.get(i).getLinkedin_page_url();
				if (!(eventLinkedIn.equalsIgnoreCase("") || eventLinkedIn.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_LINKEDIN_PAGE_LINK, eventLinkedIn);
				}
				String eventTwitter = eventsList.get(i).getTwitter_page_url();
				if (!(eventTwitter.equalsIgnoreCase("") || eventTwitter.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_TWITTER_PAGE_LINK, eventTwitter);
				}
				String eventFacebook = eventsList.get(i).getFacebook_page_url();
				if (!(eventFacebook.equalsIgnoreCase("") || eventFacebook.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_FACEBOOK_PAGE_LINK, eventFacebook);
				}
				String eventLogo = eventsList.get(i).getEvent_logo();
				if(!(eventLogo.equalsIgnoreCase("") || eventLogo.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_LOGO, eventLogo); 
				} 
				String eventImage1 = eventsList.get(i).getEvent_image_1();
				if(!(eventImage1.equalsIgnoreCase("") || eventImage1.equalsIgnoreCase(null) || eventImage1.equalsIgnoreCase("null"))) {
					contentValues.put(EVENT_IMAGE_1, eventImage1); 
				} 
				/*String eventImage2 = eventsList.get(i).getEvent_image_2();
				if(!(eventImage2.equalsIgnoreCase("") || eventImage2.equalsIgnoreCase(null) || eventImage2.equalsIgnoreCase("null"))) {
					contentValues.put(EVENT_IMAGE_2, eventImage2); 
				} */
				/*String eventImage3 = eventsList.get(i).getEvent_image_3();
				if(!(eventImage3.equalsIgnoreCase("") || eventImage3.equalsIgnoreCase(null) || eventImage3.equalsIgnoreCase("null"))) {
					contentValues.put(EVENT_IMAGE_3, eventImage3); 
				}*/
				String eventContactName = eventsList.get(i).getEvent_contact_name();
				if (!(eventContactName.equalsIgnoreCase("") || eventContactName.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_CONTACT_NAME, eventContactName);
				}
				String eventContactEmail = eventsList.get(i).getEvent_contact_email();
				if (!(eventContactEmail.equalsIgnoreCase("") || eventContactEmail.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_EMAIL, eventContactEmail);
				}
				String floorPlan = eventsList.get(i).getEvent_floor_plan();
				if (!(floorPlan.equalsIgnoreCase("") || floorPlan.equalsIgnoreCase(null))) {
					contentValues.put(FLOOR_PLAN, floorPlan);
				}
				String organizerId = eventsList.get(i).getEvent_organizer_id();
				if (!(organizerId.equalsIgnoreCase("") || organizerId.equalsIgnoreCase(null))) {
					contentValues.put(ORGANIZER_ID, organizerId);
				}
				String eventOrganizer = eventsList.get(i).getEvent_organizer();
				if (!(eventOrganizer.equalsIgnoreCase("") || eventOrganizer.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_ORGANIZER, eventOrganizer);
				}
				String organizerPhoto = eventsList.get(i).getOrganizer_photo();
				if(!(organizerPhoto.equalsIgnoreCase("") || organizerPhoto.equalsIgnoreCase(null))) {
					contentValues.put(ORGANIZER_PHOTO, organizerPhoto); 
				}
				String eventIndustry = eventsList.get(i).getEvent_industry();
				if (!(eventIndustry.equalsIgnoreCase("") || eventIndustry.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_INDUSTRY, eventIndustry);
				}
				String eventFunctionality = eventsList.get(i).getEvent_functionality();
				if (!(eventFunctionality.equalsIgnoreCase("") || eventFunctionality.equalsIgnoreCase(null))) {
					contentValues.put(EVENT_FUNCTIONALITY, eventFunctionality);
				}

				db.insert(EVENTS_TABLE_NAME, null, contentValues);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	public void insertSpeakersInfo(ArrayList<Attendees> speakersList, SQLiteDatabase db) {
		db = this.getWritableDatabase();
		ContentValues contentValues;

		try {
			for (int i = 0; i < speakersList.size(); i++) {
				contentValues = new ContentValues();

				if(speakersList.get(i).getAttendee_type().equalsIgnoreCase("S"))
				{
					String attendeeId = speakersList.get(i).getAttendee_id();
					if(!(attendeeId.equalsIgnoreCase("") || attendeeId.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_ID, attendeeId);
					}
					String attendeeIndustry = speakersList.get(i).getAttendee_industry();
					if(!(attendeeIndustry.equalsIgnoreCase("") || attendeeIndustry.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_INDUSTRY, attendeeIndustry);
					}
					String attendeeFunctionality = speakersList.get(i).getAttendee_functionality();
					if(!(attendeeFunctionality.equalsIgnoreCase("") || attendeeFunctionality.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_FUNCTIONALITY, attendeeFunctionality);
					}
					String passcode = speakersList.get(i).getAttendee_passcode();
					if(!(passcode.equalsIgnoreCase("") || passcode.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_PASSCODE, passcode);
					}
					String attendee_status = speakersList.get(i).getStatus();
					if(!(attendee_status.equalsIgnoreCase("") || attendee_status.equalsIgnoreCase(null)))
					{
						contentValues.put(STATUS, attendee_status);
					}
					String attendeeImage = speakersList.get(i).getAttendee_image();
					if(!(attendeeImage.equalsIgnoreCase("") || attendeeImage.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_IMAGE, attendeeImage);
					}
					String attendeeLocation = speakersList.get(i).getAttendee_location();
					if(!(attendeeLocation.equalsIgnoreCase("") || attendeeLocation.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_LOCATION, attendeeLocation);
					}
					String attendeeCity = speakersList.get(i).getAttendee_city();
					if(!(attendeeCity.equalsIgnoreCase("") || attendeeCity.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_CITY, attendeeCity);
					}
					String attendeeCountry = speakersList.get(i).getAttendee_country();
					if(!(attendeeCountry.equalsIgnoreCase("") || attendeeCountry.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_COUNTRY, attendeeCountry);
					}
					String attendeeDesc = speakersList.get(i).getAttendee_description();
					if(!(attendeeDesc.equalsIgnoreCase("") || attendeeDesc.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_DESCRIPTION, attendeeDesc);
					}
					String attendeeLinkedIn = speakersList.get(i).getAttendee_linkedin_id();
					if(!(attendeeLinkedIn.equalsIgnoreCase("") || attendeeLinkedIn.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_LINKEDIN_ID, attendeeLinkedIn);
					}
					String attendeeFacebook = speakersList.get(i).getAttendee_facebook_id();
					if(!(attendeeFacebook.equalsIgnoreCase("") || attendeeFacebook.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_FACEBOOK_ID, attendeeFacebook);
					}
					String attendeeType = speakersList.get(i).getAttendee_type();
					if(!(attendeeType.equalsIgnoreCase("") || attendeeType.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_TYPE, attendeeType);
					}
					String firstName = speakersList.get(i).getAttendee_first_name();
					if(!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_FIRST_NAME, firstName);
					}
					String lastName = speakersList.get(i).getAttendee_last_name();
					if(!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_LAST_NAME, lastName);
					}
					String GCMReg = speakersList.get(i).getAttendee_gcm_registration_id();
					if(!(GCMReg.equalsIgnoreCase("") || GCMReg.equalsIgnoreCase(null)))
					{
						contentValues.put(GCM_REGISTRATION_NUMBER, GCMReg);
					}
					String mobileOS = speakersList.get(i).getAttendee_mobile_os();
					if(!(mobileOS.equalsIgnoreCase("") || mobileOS.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_MOBILE_OS, mobileOS);
					}
					String attendeeEmail = speakersList.get(i).getAttendee_email();
					if(!(attendeeEmail.equalsIgnoreCase("") || attendeeEmail.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_EMAIL, attendeeEmail);
					}
					String attendeeCompany = speakersList.get(i).getAttendee_company_name();
					if(!(attendeeCompany.equalsIgnoreCase("") || attendeeCompany.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_COMPANY_NAME, attendeeCompany);
					}
					String attendeeDesignation = speakersList.get(i).getAttendee_designation();
					if(!(attendeeDesignation.equalsIgnoreCase("") || attendeeDesignation.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_DESIGNATION, attendeeDesignation);
					}
					String attendeePhone = speakersList.get(i).getAttendee_phone_number();
					if(!(attendeePhone.equalsIgnoreCase("") || attendeePhone.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_PHONE_NUMBER, attendeePhone);
					}
					db.insert(ATTENDEES_TABLE_NAME, null, contentValues);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public void insertAttendeesInfo(ArrayList<Attendees> attendeesList, SQLiteDatabase db) {
		db = this.getWritableDatabase();
		ContentValues contentValues;

		try {
			for (int i = 0; i < attendeesList.size(); i++) {
				contentValues = new ContentValues();

				if(attendeesList.get(i).getAttendee_type().equalsIgnoreCase("A"))
				{
					String attendeeIndustry = attendeesList.get(i).getAttendee_industry();
					if(!(attendeeIndustry.equalsIgnoreCase("") || attendeeIndustry.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_INDUSTRY, attendeeIndustry);
					}
					String attendeeFunctionality = attendeesList.get(i).getAttendee_functionality();
					if(!(attendeeFunctionality.equalsIgnoreCase("") || attendeeFunctionality.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_FUNCTIONALITY, attendeeFunctionality);
					}
					String attendeeId = attendeesList.get(i).getAttendee_id();
					if(!(attendeeId.equalsIgnoreCase("") || attendeeId.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_ID, attendeeId);
					}
					
					String passcode = attendeesList.get(i).getAttendee_passcode();
					if(!(passcode.equalsIgnoreCase("") || passcode.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_PASSCODE, passcode);
					}
					String attendeeImage = attendeesList.get(i).getAttendee_image();
					if(!(attendeeImage.equalsIgnoreCase("") || attendeeImage.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_IMAGE, attendeeImage);
					}
					String attendee_status = attendeesList.get(i).getStatus();
					if(!(attendee_status.equalsIgnoreCase("") || attendee_status.equalsIgnoreCase(null)))
					{
						contentValues.put(STATUS, attendee_status);
					}
					String attendeeLocation = attendeesList.get(i).getAttendee_location();
					if(!(attendeeLocation.equalsIgnoreCase("") || attendeeLocation.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_LOCATION, attendeeLocation);
					}
					String attendeeCity = attendeesList.get(i).getAttendee_city();
					if(!(attendeeCity.equalsIgnoreCase("") || attendeeCity.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_CITY, attendeeCity);
					}
					String attendeeCountry = attendeesList.get(i).getAttendee_country();
					if(!(attendeeCountry.equalsIgnoreCase("") || attendeeCountry.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_COUNTRY, attendeeCountry);
					}
					String attendeeDesc = attendeesList.get(i).getAttendee_description();
					if(!(attendeeDesc.equalsIgnoreCase("") || attendeeDesc.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_DESCRIPTION, attendeeDesc);
					}
					String attendeeLinkedIn = attendeesList.get(i).getAttendee_linkedin_id();
					if(!(attendeeLinkedIn.equalsIgnoreCase("") || attendeeLinkedIn.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_LINKEDIN_ID, attendeeLinkedIn);
					}
					String attendeeFacebook = attendeesList.get(i).getAttendee_facebook_id();
					if(!(attendeeFacebook.equalsIgnoreCase("") || attendeeFacebook.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_FACEBOOK_ID, attendeeFacebook);
					}
					String attendeeType = attendeesList.get(i).getAttendee_type();
					if(!(attendeeType.equalsIgnoreCase("") || attendeeType.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_TYPE, attendeeType);
					}
					String firstName = attendeesList.get(i).getAttendee_first_name();
					if(!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_FIRST_NAME, firstName);
					}
					String lastName = attendeesList.get(i).getAttendee_last_name();
					if(!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_LAST_NAME, lastName);
					}
					String GCMReg = attendeesList.get(i).getAttendee_gcm_registration_id();
					if(!(GCMReg.equalsIgnoreCase("") || GCMReg.equalsIgnoreCase(null)))
					{
						contentValues.put(GCM_REGISTRATION_NUMBER, GCMReg);
					}
					String mobileOS = attendeesList.get(i).getAttendee_mobile_os();
					if(!(mobileOS.equalsIgnoreCase("") || mobileOS.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_MOBILE_OS, mobileOS);
					}
					String attendeeEmail = attendeesList.get(i).getAttendee_email();
					if(!(attendeeEmail.equalsIgnoreCase("") || attendeeEmail.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_EMAIL, attendeeEmail);
					}
					String attendeeCompany = attendeesList.get(i).getAttendee_company_name();
					if(!(attendeeCompany.equalsIgnoreCase("") || attendeeCompany.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_COMPANY_NAME, attendeeCompany);
					}
					String attendeeDesignation = attendeesList.get(i).getAttendee_designation();
					if(!(attendeeDesignation.equalsIgnoreCase("") || attendeeDesignation.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_DESIGNATION, attendeeDesignation);
					}
					String attendeePhone = attendeesList.get(i).getAttendee_phone_number();
					if(!(attendeePhone.equalsIgnoreCase("") || attendeePhone.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_PHONE_NUMBER, attendeePhone);
					}
					db.insert(ATTENDEES_TABLE_NAME, null, contentValues);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}

	public void insertExhibitorsInfo(ArrayList<Attendees> exhibitorsList, SQLiteDatabase db) {
		db = this.getWritableDatabase();
		ContentValues contentValues;

		try {
			for (int i = 0; i < exhibitorsList.size(); i++) {
				contentValues = new ContentValues();

				if(exhibitorsList.get(i).getAttendee_type().equalsIgnoreCase("E"))
				{
					String attendeeFeatured = exhibitorsList.get(i).getAttendee_featured();
					if(!(attendeeFeatured.equalsIgnoreCase("") || attendeeFeatured.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_FEATURED, attendeeFeatured);
					}
					String attendeeStallNumber = exhibitorsList.get(i).getStall_number();
					if(!(attendeeStallNumber.equalsIgnoreCase("") || attendeeStallNumber.equalsIgnoreCase(null)))
					{
						contentValues.put(STALL_NUMBER, attendeeStallNumber);
					}
					String attendeeDesc = exhibitorsList.get(i).getAttendee_description();
					if(!(attendeeDesc.equalsIgnoreCase("") || attendeeDesc.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_DESCRIPTION, attendeeDesc);
					}
					String attendee_status = exhibitorsList.get(i).getStatus();
					if(!(attendee_status.equalsIgnoreCase("") || attendee_status.equalsIgnoreCase(null)))
					{
						contentValues.put(STATUS, attendee_status);
					}
					String website = exhibitorsList.get(i).getExhibitor_website();
					if(!(website.equalsIgnoreCase("") || website.equalsIgnoreCase(null)))
					{
						contentValues.put(EXHIBITOR_WEBSITE, website);
					}
					String attendeeFacebook = exhibitorsList.get(i).getAttendee_facebook_id();
					if(!(attendeeFacebook.equalsIgnoreCase("") || attendeeFacebook.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_FACEBOOK_ID, attendeeFacebook);
					}
					String attendeeLinkedIn = exhibitorsList.get(i).getAttendee_linkedin_id();
					if(!(attendeeLinkedIn.equalsIgnoreCase("") || attendeeLinkedIn.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_LINKEDIN_ID, attendeeLinkedIn);
					}
					String attendeeCity = exhibitorsList.get(i).getAttendee_city();
					if(!(attendeeCity.equalsIgnoreCase("") || attendeeCity.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_CITY, attendeeCity);
					}
					String attendeeCountry = exhibitorsList.get(i).getAttendee_country();
					if(!(attendeeCountry.equalsIgnoreCase("") || attendeeCountry.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_COUNTRY, attendeeCountry);
					}
					String attendeeLocation = exhibitorsList.get(i).getAttendee_location();
					if(!(attendeeLocation.equalsIgnoreCase("") || attendeeLocation.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_LOCATION, attendeeLocation);
					}
					String attendeeImage = exhibitorsList.get(i).getAttendee_image();
					if(!(attendeeImage.equalsIgnoreCase("") || attendeeImage.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_IMAGE, attendeeImage);
					}
					String attendeeImage1 = exhibitorsList.get(i).getAttendee_image_1();
					if(!(attendeeImage1.equalsIgnoreCase("") || attendeeImage1.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_IMAGE_1, attendeeImage1);
					}
					String attendeeImage2 = exhibitorsList.get(i).getAttendee_image_2();
					if(!(attendeeImage2.equalsIgnoreCase("") || attendeeImage2.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_IMAGE_2, attendeeImage2);
					}
					String brochure = exhibitorsList.get(i).getBrochure();
					if(!(brochure.equalsIgnoreCase("") || brochure.equalsIgnoreCase(null)))
					{
						contentValues.put(BROCHURE, brochure);
					}
					String firstName = exhibitorsList.get(i).getAttendee_first_name();
					if(!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_FIRST_NAME, firstName);
					}
					String lastName = exhibitorsList.get(i).getAttendee_last_name();
					if(!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_LAST_NAME, lastName);
					}
					String attendeeEmail = exhibitorsList.get(i).getAttendee_email();
					if(!(attendeeEmail.equalsIgnoreCase("") || attendeeEmail.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_EMAIL, attendeeEmail);
					}
					String attendeePhone = exhibitorsList.get(i).getAttendee_phone_number();
					if(!(attendeePhone.equalsIgnoreCase("") || attendeePhone.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_PHONE_NUMBER, attendeePhone);
					}
					String attendeeMobile = exhibitorsList.get(i).getAttendee_mobile_number();
					if(!(attendeeMobile.equalsIgnoreCase("") || attendeeMobile.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_MOBILE_NUMBER, attendeeMobile);
					}
					String GCMReg = exhibitorsList.get(i).getAttendee_gcm_registration_id();
					if(!(GCMReg.equalsIgnoreCase("") || GCMReg.equalsIgnoreCase(null)))
					{
						contentValues.put(GCM_REGISTRATION_NUMBER, GCMReg);
					}
					String mobileOS = exhibitorsList.get(i).getAttendee_mobile_os();
					if(!(mobileOS.equalsIgnoreCase("") || mobileOS.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_MOBILE_OS, mobileOS);
					}
					String attendeeId = exhibitorsList.get(i).getAttendee_id();
					if(!(attendeeId.equalsIgnoreCase("") || attendeeId.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_ID, attendeeId);
					}
					String attendeeType = exhibitorsList.get(i).getAttendee_type();
					if(!(attendeeType.equalsIgnoreCase("") || attendeeType.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_TYPE, attendeeType);
					}
					String attendeeIndustry = exhibitorsList.get(i).getAttendee_industry();
					if(!(attendeeIndustry.equalsIgnoreCase("") || attendeeIndustry.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_INDUSTRY, attendeeIndustry);
					}
					String attendeeCompany = exhibitorsList.get(i).getAttendee_company_name();
					if(!(attendeeCompany.equalsIgnoreCase("") || attendeeCompany.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_COMPANY_NAME, attendeeCompany);
					}
					String attendeeDesignation = exhibitorsList.get(i).getAttendee_designation();
					if(!(attendeeDesignation.equalsIgnoreCase("") || attendeeDesignation.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_DESIGNATION, attendeeDesignation);
					}
					String attendeeFunctionality = exhibitorsList.get(i).getAttendee_functionality();
					if(!(attendeeFunctionality.equalsIgnoreCase("") || attendeeFunctionality.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_FUNCTIONALITY, attendeeFunctionality);
					}
					/*String passcode = exhibitorsList.get(i).getAttendee_passcode();
					if(!(passcode.equalsIgnoreCase("") || passcode.equalsIgnoreCase(null)))
					{
						contentValues.put(ATTENDEE_PASSCODE, passcode);
					}
					*/
					db.insert(ATTENDEES_TABLE_NAME, null, contentValues);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public void insertUserData(ArrayList<Profile> userDataList, SQLiteDatabase db) {
		db = this.getWritableDatabase();
		ContentValues contentValues;

		try {
			for (int i = 0; i < userDataList.size(); i++) {
				contentValues = new ContentValues();

				String attendeeId = userDataList.get(i).getProfile_attendee_id();
				if(!(attendeeId.equalsIgnoreCase("") || attendeeId.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_ATTENDEE_ID, attendeeId);
				}
				String apiAccessToken = userDataList.get(i).getApi_access_token();
				if(!(apiAccessToken.equalsIgnoreCase("") || apiAccessToken.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_API_ACCESS_TOKEN, apiAccessToken);
				}
				String userEmail = userDataList.get(i).getEmail();
				if(!(userEmail.equalsIgnoreCase("") || userEmail.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_EMAIL, userEmail);
				}
				String userStatus = userDataList.get(i).getStatus();
				if(!(userStatus.equalsIgnoreCase("") || userStatus.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_STATUS, userStatus);
				}
				String userGCM = userDataList.get(i).getGcm_reg_id();
				if(!(userGCM.equalsIgnoreCase("") || userGCM.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_GCM_REGISTRATION_ID, userGCM);
				}
				String mobileOS = userDataList.get(i).getMobile_os();
				if(!(mobileOS.equalsIgnoreCase("") || mobileOS.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_MOBILE_OS, mobileOS);
				}
				String firstName = userDataList.get(i).getFirst_name();
				if(!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_FIRST_NAME, firstName);
				}
				String lastName = userDataList.get(i).getLast_name();
				if(!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_LAST_NAME, lastName);
				}
				String linkedIn = userDataList.get(i).getLinkedin_();
				if(!(linkedIn.equalsIgnoreCase("") || linkedIn.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_LINKEDIN, linkedIn);
				}
				String facebook = userDataList.get(i).getFacebook();
				if(!(facebook.equalsIgnoreCase("") || facebook.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_FACEBOOK, facebook);
				}
				String companyName = userDataList.get(i).getCompany_name();
				if(!(companyName.equalsIgnoreCase("") || companyName.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_COMPANY_NAME, companyName);
				}
				String designation = userDataList.get(i).getDesignation();
				if(!(designation.equalsIgnoreCase("") || designation.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_DESIGNATION, designation);
				}
				String photo = userDataList.get(i).getProfile_image();
				if(!(photo.equalsIgnoreCase("") || photo.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_IMAGE, photo);
				}
				String description = userDataList.get(i).getDescription();
				if(!(description.equalsIgnoreCase("") || description.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_DESCRIPTION, description);
				}
				String city = userDataList.get(i).getCity();
				if(!(city.equalsIgnoreCase("") || city.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_CITY, city);
				}
				String country = userDataList.get(i).getCountry();
				if(!(country.equalsIgnoreCase("") || country.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_COUNRTY, country);
				}
				String userIndustry = userDataList.get(i).getIndustry();
				if(!(userIndustry.equalsIgnoreCase("") || userIndustry.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_INDUSTRY, userIndustry);
				}
				String userFunctionality = userDataList.get(i).getFunctionality();
				if(!(userFunctionality.equalsIgnoreCase("") || userFunctionality.equalsIgnoreCase(null)))
				{
					contentValues.put(PROFILE_FUNCTIONALITY, userFunctionality);
				}
				/*String userPhone = userDataList.get(i).getPhone_number();
				if(!(userPhone.equalsIgnoreCase("") || userPhone.equalsIgnoreCase(null) || userPhone.equalsIgnoreCase("null")))
				{
					contentValues.put(PROFILE_PHONE_NUMBER, userPhone);
				}*/
				/*String userMobile = userDataList.get(i).getMobile_number();
				if(!(userMobile.equalsIgnoreCase("") || userMobile.equalsIgnoreCase(null) || userMobile.equalsIgnoreCase("null")))
				{
					contentValues.put(PROFILE_MOBILE_NUMBER, userMobile);
				}*/
			
				db.insert(PROFILE_TABLE_NAME, null, contentValues);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public void insertWallNotificationData(ArrayList<Notifications> wallNotificationsList, SQLiteDatabase db) {
		db = this.getWritableDatabase();
		ContentValues contentValues;

		try {
			for (int i = 0; i < wallNotificationsList.size(); i++) {
				contentValues = new ContentValues();

				String notificationId = wallNotificationsList.get(i).getNotification_id();
				if (!(notificationId.equalsIgnoreCase("") || notificationId.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_ID, notificationId);
				}
				String notificationType = wallNotificationsList.get(i).getNotification_type();
				if (!(notificationType.equalsIgnoreCase("") || notificationType.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_TYPE, notificationType);
				}
				String subjectId = wallNotificationsList.get(i).getSubject_id();
				if (!(subjectId.equalsIgnoreCase("") || subjectId.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_SUBJECT_ID, subjectId);
				}
				String subjectType = wallNotificationsList.get(i).getSubject_type();
				if (!(subjectType.equalsIgnoreCase("") || subjectType.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_SUBJECT_TYPE, subjectType);
				}
				String objectId = wallNotificationsList.get(i).getObject_id();
				if (!(objectId.equalsIgnoreCase("") || objectId.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_OBJECT_ID, objectId);
				}
				String objectType = wallNotificationsList.get(i).getObject_type();
				if (!(objectType.equalsIgnoreCase("") || objectType.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_OBJECT_TYPE, objectType);
				}
				String read = wallNotificationsList.get(i).getRead();
				if (!(read.equalsIgnoreCase("") || read.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_READ, read);
				}
				String notificationContent = wallNotificationsList.get(i).getNotification_content();
				if (!(notificationContent.equalsIgnoreCase("") || notificationContent.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_CONTENT, notificationContent);
				}
				String meetingId = wallNotificationsList.get(i).getMeeting_id();
				if (!(meetingId.equalsIgnoreCase("") || meetingId.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_MEETING_ID, meetingId);
				}
				String messageId = wallNotificationsList.get(i).getMessage_id();
				if (!(messageId.equalsIgnoreCase("") || messageId.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_MESSAGE_ID, messageId);
				}
				String eventId = wallNotificationsList.get(i).getEvent_id();
				if (!(eventId.equalsIgnoreCase("") || eventId.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_EVENT_ID, eventId);
				}
				String notificationDate = wallNotificationsList.get(i).getNotification_date();
				if (!(notificationDate.equalsIgnoreCase("") || notificationDate.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_DATE, notificationDate);
				}
				String userId = wallNotificationsList.get(i).getUser_id();
				if (!(userId.equalsIgnoreCase("") || userId.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_USER_ID, userId);
				}
				String firstName = wallNotificationsList.get(i).getFirst_name();
				if (!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_FIRST_NAME, firstName);
				}
				String lastName = wallNotificationsList.get(i).getLast_name();
				if (!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_LAST_NAME, lastName);
				}
				String typeOfUser = wallNotificationsList.get(i).getType_of_user();
				if(!(typeOfUser.equalsIgnoreCase("") || typeOfUser.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_TYPE_OF_USER, typeOfUser); 
				} 
				String companyName = wallNotificationsList.get(i).getCompany_name();
				if(!(companyName.equalsIgnoreCase("") || companyName.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_COMPANY_NAME, companyName); 
				} 
				String designation = wallNotificationsList.get(i).getDesignation();
				if(!(designation.equalsIgnoreCase("") || designation.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_DESIGNATION, designation); 
				} 
				String phone = wallNotificationsList.get(i).getPhone();
				if(!(phone.equalsIgnoreCase("") || phone.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_PHONE_NUMBER, phone); 
				}
				/*String photo = wallNotificationsList.get(i).getPhoto();
				if(!(photo.equalsIgnoreCase("") || photo.equalsIgnoreCase(null) || photo.equalsIgnoreCase("null")))
				{	
					contentValues.put(WALL_NOTIFICATION_PHOTO, photo); 
				}*/
				String eventName = wallNotificationsList.get(i).getEvent_name();
				if (!(eventName.equalsIgnoreCase("") || eventName.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_EVENT_NAME, eventName);
				}
				String attendeeId = wallNotificationsList.get(i).getAttendee_id();
				if (!(attendeeId.equalsIgnoreCase("") || attendeeId.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_ATTENDEE_ID, attendeeId);
				}
				String attendeeName = wallNotificationsList.get(i).getAttendee_name();
				if (!(attendeeName.equalsIgnoreCase("") || attendeeName.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_ATTENDEE_NAME, attendeeName);
				}
				String organizerName = wallNotificationsList.get(i).getOrganizer_name();
				if (!(organizerName.equalsIgnoreCase("") || organizerName.equalsIgnoreCase(null))) {
					contentValues.put(WALL_NOTIFICATION_ORGANIZER_NAME, organizerName);
				}
				//Receiver Data Insert
				String receiverUserId = wallNotificationsList.get(i).getReceiver_user_id();
				if (!(receiverUserId.equalsIgnoreCase("") || receiverUserId.equalsIgnoreCase(null))) {
					contentValues.put(WALL_RECEIVER_USER_ID, receiverUserId);
				}
				String receiverFname = wallNotificationsList.get(i).getReceiver_first_name();
				if (!(receiverFname.equalsIgnoreCase("") || receiverFname.equalsIgnoreCase(null))) {
					contentValues.put(WALL_RECEIVER_FIRST_NAME, receiverFname);
				}
				String receiverLname = wallNotificationsList.get(i).getReceiver_last_name();
				if (!(receiverLname.equalsIgnoreCase("") || receiverLname.equalsIgnoreCase(null))) {
					contentValues.put(WALL_RECEIVER_LAST_NAME, receiverLname);
				}
				String receiverTypeOfuser = wallNotificationsList.get(i).getReceiver_type_of_user();
				if (!(receiverTypeOfuser.equalsIgnoreCase("") || receiverTypeOfuser.equalsIgnoreCase(null))) {
					contentValues.put(WALL_RECEIVER_TYPE_OF_USER, receiverTypeOfuser);
				}
				String receiverCompanyName = wallNotificationsList.get(i).getReceiver_company_name();
				if (!(receiverCompanyName.equalsIgnoreCase("") || receiverCompanyName.equalsIgnoreCase(null))) {
					contentValues.put(WALL_RECEIVER_COMPANY_NAME, receiverCompanyName);
				}
				String receiverDesignation = wallNotificationsList.get(i).getReceiver_designation();
				if (!(receiverDesignation.equalsIgnoreCase("") || receiverDesignation.equalsIgnoreCase(null))) {
					contentValues.put(WALL_RECEIVER_DESIGNATION, receiverDesignation);
				}
				String receiverPhone = wallNotificationsList.get(i).getReceiver_phone();
				if (!(receiverPhone.equalsIgnoreCase("") || receiverPhone.equalsIgnoreCase(null))) {
					contentValues.put(WALL_RECEIVER_PHONE, receiverPhone);
				}
				/*String receiverPhoto = wallNotificationsList.get(i).getReceiver_photo();
				if (!(receiverPhoto.equalsIgnoreCase("") || receiverPhoto.equalsIgnoreCase(null))) {
					contentValues.put(WALL_RECEIVER_PHOTO, receiverPhoto);
				}*/
				String receiverAttendeeId = wallNotificationsList.get(i).getReceiver_attendee_id();
				if (!(receiverAttendeeId.equalsIgnoreCase("") || receiverAttendeeId.equalsIgnoreCase(null))) {
					contentValues.put(WALL_RECEIVER_ATTENDEE_ID, receiverAttendeeId);
				}
				String receiverAttendeeType = wallNotificationsList.get(i).getReceiver_attendee_type();
				if (!(receiverAttendeeType.equalsIgnoreCase("") || receiverAttendeeType.equalsIgnoreCase(null))) {
					contentValues.put(WALL_RECEIVER_ATTENDEE_TYPE, receiverAttendeeType);
				}
				
				db.insert(WALL_NOTIFICATION_TABLE_NAME, null, contentValues);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public void insertUserNotificationData(ArrayList<Notifications> userNotificationsList, SQLiteDatabase db) {
		db = this.getWritableDatabase();
		ContentValues contentValues;

		try {
			for (int i = 0; i < userNotificationsList.size(); i++) {
				contentValues = new ContentValues();

				String notificationId = userNotificationsList.get(i).getNotification_id();
				if (!(notificationId.equalsIgnoreCase("") || notificationId.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_ID, notificationId);
				}
				String notificationType = userNotificationsList.get(i).getNotification_type();
				if (!(notificationType.equalsIgnoreCase("") || notificationType.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_TYPE, notificationType);
				}
				String subjectId = userNotificationsList.get(i).getSubject_id();
				if (!(subjectId.equalsIgnoreCase("") || subjectId.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_SUBJECT_ID, subjectId);
				}
				String subjectType = userNotificationsList.get(i).getSubject_type();
				if (!(subjectType.equalsIgnoreCase("") || subjectType.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_SUBJECT_TYPE, subjectType);
				}
				String objectId = userNotificationsList.get(i).getObject_id();
				if (!(objectId.equalsIgnoreCase("") || objectId.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_OBJECT_ID, objectId);
				}
				String objectType = userNotificationsList.get(i).getObject_type();
				if (!(objectType.equalsIgnoreCase("") || objectType.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_OBJECT_TYPE, objectType);
				}
				String read = userNotificationsList.get(i).getRead();
				if (!(read.equalsIgnoreCase("") || read.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_READ, read);
				}
				String notificationContent = userNotificationsList.get(i).getNotification_content();
				if (!(notificationContent.equalsIgnoreCase("") || notificationContent.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_CONTENT, notificationContent);
				}
				String meetingId = userNotificationsList.get(i).getMeeting_id();
				if (!(meetingId.equalsIgnoreCase("") || meetingId.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_MEETING_ID, meetingId);
				}
				String messageId = userNotificationsList.get(i).getMessage_id();
				if (!(messageId.equalsIgnoreCase("") || messageId.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_MESSAGE_ID, messageId);
				}
				String eventId = userNotificationsList.get(i).getEvent_id();
				if (!(eventId.equalsIgnoreCase("") || eventId.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_EVENT_ID, eventId);
				}
				String notificationDate = userNotificationsList.get(i).getNotification_date();
				if (!(notificationDate.equalsIgnoreCase("") || notificationDate.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_DATE, notificationDate);
				}
				String userId = userNotificationsList.get(i).getUser_id();
				if (!(userId.equalsIgnoreCase("") || userId.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_USER_ID, userId);
				}
				String firstName = userNotificationsList.get(i).getFirst_name();
				if (!(firstName.equalsIgnoreCase("") || firstName.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_FIRST_NAME, firstName);
				}
				String lastName = userNotificationsList.get(i).getLast_name();
				if (!(lastName.equalsIgnoreCase("") || lastName.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_LAST_NAME, lastName);
				}
				String typeOfUser = userNotificationsList.get(i).getType_of_user();
				if(!(typeOfUser.equalsIgnoreCase("") || typeOfUser.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_TYPE_OF_USER, typeOfUser); 
				} 
				String companyName = userNotificationsList.get(i).getCompany_name();
				if(!(companyName.equalsIgnoreCase("") || companyName.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_COMPANY_NAME, companyName); 
				} 
				String designation = userNotificationsList.get(i).getDesignation();
				if(!(designation.equalsIgnoreCase("") || designation.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_DESIGNATION, designation); 
				} 
				String phone = userNotificationsList.get(i).getPhone();
				if(!(phone.equalsIgnoreCase("") || phone.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_PHONE_NUMBER, phone); 
				}
				/*String photo = userNotificationsList.get(i).getPhoto();
				if(!(photo.equalsIgnoreCase("") || photo.equalsIgnoreCase(null) || photo.equalsIgnoreCase("null")))
				{	
					contentValues.put(USER_NOTIFICATION_PHOTO, photo); 
				}*/
				String approve = userNotificationsList.get(i).getEvent_name();
				if (!(approve.equalsIgnoreCase("") || approve.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_APPROVE, approve);
				}
				String startTime = userNotificationsList.get(i).getAttendee_id();
				if (!(startTime.equalsIgnoreCase("") || startTime.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_START_TIME, startTime);
				}
				String endTime = userNotificationsList.get(i).getAttendee_name();
				if (!(endTime.equalsIgnoreCase("") || endTime.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_END_TIME, endTime);
				}
				String eventName = userNotificationsList.get(i).getEvent_name();
				if (!(eventName.equalsIgnoreCase("") || eventName.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_EVENT_NAME, eventName);
				}
				String attendeeId = userNotificationsList.get(i).getAttendee_id();
				if (!(attendeeId.equalsIgnoreCase("") || attendeeId.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_ATTENDEE_ID, attendeeId);
				}
				String attendeeName = userNotificationsList.get(i).getAttendee_name();
				if (!(attendeeName.equalsIgnoreCase("") || attendeeName.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_ATTENDEE_NAME, attendeeName);
				}
				String organizerName = userNotificationsList.get(i).getOrganizer_name();
				if (!(organizerName.equalsIgnoreCase("") || organizerName.equalsIgnoreCase(null))) {
					contentValues.put(USER_NOTIFICATION_ORGANIZER_NAME, organizerName);
				}
				//Receiver Data Insert
				String receiverUserId = userNotificationsList.get(i).getReceiver_user_id();
				if (!(receiverUserId.equalsIgnoreCase("") || receiverUserId.equalsIgnoreCase(null))) {
					contentValues.put(USER_RECEIVER_USER_ID, receiverUserId);
				}
				String receiverFname = userNotificationsList.get(i).getReceiver_first_name();
				if (!(receiverFname.equalsIgnoreCase("") || receiverFname.equalsIgnoreCase(null))) {
					contentValues.put(USER_RECEIVER_FIRST_NAME, receiverFname);
				}
				String receiverLname = userNotificationsList.get(i).getReceiver_last_name();
				if (!(receiverLname.equalsIgnoreCase("") || receiverLname.equalsIgnoreCase(null))) {
					contentValues.put(USER_RECEIVER_LAST_NAME, receiverLname);
				}
				String receiverTypeOfuser = userNotificationsList.get(i).getReceiver_type_of_user();
				if (!(receiverTypeOfuser.equalsIgnoreCase("") || receiverTypeOfuser.equalsIgnoreCase(null))) {
					contentValues.put(USER_RECEIVER_TYPE_OF_USER, receiverTypeOfuser);
				}
				String receiverCompanyName = userNotificationsList.get(i).getReceiver_company_name();
				if (!(receiverCompanyName.equalsIgnoreCase("") || receiverCompanyName.equalsIgnoreCase(null))) {
					contentValues.put(USER_RECEIVER_COMPANY_NAME, receiverCompanyName);
				}
				String receiverDesignation = userNotificationsList.get(i).getReceiver_designation();
				if (!(receiverDesignation.equalsIgnoreCase("") || receiverDesignation.equalsIgnoreCase(null))) {
					contentValues.put(USER_RECEIVER_DESIGNATION, receiverDesignation);
				}
				String receiverPhone = userNotificationsList.get(i).getReceiver_phone();
				if (!(receiverPhone.equalsIgnoreCase("") || receiverPhone.equalsIgnoreCase(null))) {
					contentValues.put(USER_RECEIVER_PHONE, receiverPhone);
				}
				/*String receiverPhoto = userNotificationsList.get(i).getReceiver_photo();
				if (!(receiverPhoto.equalsIgnoreCase("") || receiverPhoto.equalsIgnoreCase(null))) {
					contentValues.put(USER_RECEIVER_PHOTO, receiverPhoto);
				}*/
				String receiverAttendeeId = userNotificationsList.get(i).getReceiver_attendee_id();
				if (!(receiverAttendeeId.equalsIgnoreCase("") || receiverAttendeeId.equalsIgnoreCase(null))) {
					contentValues.put(USER_RECEIVER_ATTENDEE_ID, receiverAttendeeId);
				}
				String receiverAttendeeType = userNotificationsList.get(i).getReceiver_attendee_type();
				if (!(receiverAttendeeType.equalsIgnoreCase("") || receiverAttendeeType.equalsIgnoreCase(null))) {
					contentValues.put(USER_RECEIVER_ATTENDEE_TYPE, receiverAttendeeType);
				}
				
				db.insert(USER_NOTIFICATION_TABLE_NAME, null, contentValues);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
	public Cursor getData(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from contacts where id=" + id + "",
				null);
		return res;
	}

	public int numberOfRows() {
		SQLiteDatabase db = this.getReadableDatabase();
		int numRows = (int) DatabaseUtils.queryNumEntries(db, EVENTS_TABLE_NAME);
		return numRows;
	}
	
	//Get Attendees List
    public List<Attendees> getAttendeesList(){
        String selectQuery = "select "+ATTENDEE_FIRST_NAME+", "+ATTENDEE_LAST_NAME+", "+ATTENDEE_DESIGNATION+", "+ATTENDEE_COMPANY_NAME+", "+ATTENDEE_CITY+
				" from "+ATTENDEES_TABLE_NAME+" where "+ATTENDEE_TYPE+" =\'A\'";
        
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Attendees> attendeeList = new ArrayList<Attendees>();
        if (cursor.moveToFirst()) {
            do {
            	Attendees attendeesList = new Attendees();
            	attendeesList.setAttendee_first_name(cursor.getString(0));
            	attendeesList.setAttendee_last_name(cursor.getString(1));
            	attendeesList.setAttendee_designation(cursor.getString(2));
            	attendeesList.setAttendee_company_name(cursor.getString(3));
            	attendeesList.setAttendee_city(cursor.getString(4));
                attendeeList.add(attendeesList);
            } while (cursor.moveToNext());
        }
        db.close();
        return attendeeList;
    }
    
    //Get Exhibitors List
    public List<Attendees> getExhibitorsList(){
        String selectQuery = "select "+ATTENDEE_FIRST_NAME+", "+ATTENDEE_LAST_NAME+", "+ATTENDEE_DESIGNATION+", "+ATTENDEE_COMPANY_NAME+", "+ATTENDEE_CITY+
				" from "+ATTENDEES_TABLE_NAME+" where "+ATTENDEE_TYPE+" =\'E\'";
        
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Attendees> exhibitorList = new ArrayList<Attendees>();
        if (cursor.moveToFirst()) {
            do {
            	Attendees exhibitorsList = new Attendees();
            	exhibitorsList.setAttendee_first_name(cursor.getString(0));
            	exhibitorsList.setAttendee_last_name(cursor.getString(1));
            	exhibitorsList.setAttendee_designation(cursor.getString(2));
            	exhibitorsList.setAttendee_company_name(cursor.getString(3));
                exhibitorsList.setAttendee_city(cursor.getString(4));
                exhibitorList.add(exhibitorsList);
            } while (cursor.moveToNext());
        }
        db.close();
        return exhibitorList;
    }
    
    //Get Speakers List
    public List<Attendees> getSpeakersList(){
        String selectQuery = "select "+ATTENDEE_FIRST_NAME+", "+ATTENDEE_LAST_NAME+", "+ATTENDEE_DESIGNATION+", "+ATTENDEE_COMPANY_NAME+", "+ATTENDEE_CITY+
				" from "+ATTENDEES_TABLE_NAME+" where "+ATTENDEE_TYPE+" =\'S\'";
        
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Attendees> speakerList = new ArrayList<Attendees>();
        if (cursor.moveToFirst()) {
            do {
            	Attendees speakersList = new Attendees();
            	speakersList.setAttendee_first_name(cursor.getString(0));
            	speakersList.setAttendee_last_name(cursor.getString(1));
            	speakersList.setAttendee_designation(cursor.getString(2));
            	speakersList.setAttendee_company_name(cursor.getString(3));
            	speakersList.setAttendee_city(cursor.getString(4));
                speakerList.add(speakersList);
            } while (cursor.moveToNext());
        }
        db.close();
        return speakerList;
    }
    
    //get Attendee Details
    public List<Attendees> getAttendeeDetails(){
        String selectQuery = "select * from "+ATTENDEES_TABLE_NAME+" where "+ATTENDEE_TYPE+" =\'A\'";
        
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Attendees> attendeeList = new ArrayList<Attendees>();
        if (cursor.moveToFirst()) {
            do {
            	Attendees attendeesList = new Attendees();
            	attendeesList.setAttendee_id(cursor.getString(0));
            	attendeesList.setAttendee_first_name(cursor.getString(1));
            	attendeesList.setAttendee_last_name(cursor.getString(2));
            	attendeesList.setAttendee_type(cursor.getString(3));
            	attendeesList.setAttendee_industry(cursor.getString(4));
            	
            	attendeesList.setAttendee_functionality(cursor.getString(5));
            	attendeesList.setAttendee_email(cursor.getString(6));
            	attendeesList.setAttendee_company_name(cursor.getString(7));
            	attendeesList.setAttendee_designation(cursor.getString(8));
            	attendeesList.setAttendee_phone_number(cursor.getString(9));
            	
            	attendeesList.setAttendee_image(cursor.getString(10));
            	attendeesList.setAttendee_location(cursor.getString(11));
            	attendeesList.setAttendee_city(cursor.getString(12));
            	attendeesList.setAttendee_country(cursor.getString(13));
            	attendeesList.setAttendee_description(cursor.getString(14));
            	
            	attendeesList.setAttendee_linkedin_id(cursor.getString(15));
            	attendeesList.setAttendee_facebook_id(cursor.getString(16));
            	attendeesList.setAttendee_passcode(cursor.getString(17));
            	attendeesList.setAttendee_gcm_registration_id(cursor.getString(18));
            	attendeesList.setAttendee_mobile_os(cursor.getString(19));
            	
            	attendeesList.setStatus(cursor.getString(20));
            	attendeesList.setAttendee_featured(cursor.getString(21));
            	attendeesList.setStall_number(cursor.getString(22));
            	attendeesList.setExhibitor_website(cursor.getString(23));
            	attendeesList.setAttendee_image_1(cursor.getString(24));
            	
            	attendeesList.setAttendee_image_2(cursor.getString(25));
            	attendeesList.setBrochure(cursor.getString(26));
            	attendeesList.setAttendee_mobile_number(cursor.getString(27));
            	
                attendeeList.add(attendeesList);
            } while (cursor.moveToNext());
        }
        db.close(); 
        return attendeeList;
    }
    
  //get Attendee Details
    public List<Events> getEventInfo(){
        String selectQuery = "select * from "+EVENTS_TABLE_NAME;
        
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Events> eventInfo = new ArrayList<Events>();
        if (cursor.moveToFirst()) {
            do {
            	Events eventInfoListing = new Events();
            	eventInfoListing.setEvent_id(cursor.getString(0));
            	eventInfoListing.setEvent_name(cursor.getString(1));
            	eventInfoListing.setEvent_description(cursor.getString(2));
            	eventInfoListing.setFeatured(cursor.getString(3));
            	eventInfoListing.setEvent_start(cursor.getString(4));
            	
            	eventInfoListing.setEvent_end(cursor.getString(5));
            	eventInfoListing.setEvent_location(cursor.getString(6));
            	eventInfoListing.setEvent_city(cursor.getString(7));
            	eventInfoListing.setEvent_country(cursor.getString(8));
            	eventInfoListing.setEvent_latitude(cursor.getString(9));
            	
            	eventInfoListing.setEvent_longitude(cursor.getString(10));
            	eventInfoListing.setEvent_website(cursor.getString(11));
            	eventInfoListing.setLinkedin_page_url(cursor.getString(12));
            	eventInfoListing.setTwitter_page_url(cursor.getString(13));
            	eventInfoListing.setFacebook_page_url(cursor.getString(14));
            	
            	eventInfoListing.setEvent_logo(cursor.getString(15));
            	eventInfoListing.setEvent_image_1(cursor.getString(16));
            	eventInfoListing.setEvent_image_2(cursor.getString(17));
            	eventInfoListing.setEvent_image_3(cursor.getString(18));
            	eventInfoListing.setEvent_contact_name(cursor.getString(19));
            	
            	eventInfoListing.setEvent_contact_email(cursor.getString(20));
            	eventInfoListing.setEvent_floor_plan(cursor.getString(21));
            	eventInfoListing.setEvent_organizer_id(cursor.getString(22));
            	eventInfoListing.setEvent_organizer(cursor.getString(23));
            	eventInfoListing.setOrganizer_photo(cursor.getString(24));
            	
            	eventInfoListing.setEvent_industry(cursor.getString(25));
            	eventInfoListing.setEvent_functionality(cursor.getString(26));
            	
            	eventInfo.add(eventInfoListing);
            } while (cursor.moveToNext());
        }
        db.close();
        return eventInfo;
    }
    
  //Get Wall Notification List
    public List<Notifications> getWallNotifications(){
        String selectQuery = "select "+WALL_NOTIFICATION_FIRST_NAME+", "+WALL_NOTIFICATION_LAST_NAME+", "+WALL_NOTIFICATION_DESIGNATION+", "+
        					WALL_NOTIFICATION_COMPANY_NAME+", "+WALL_NOTIFICATION_TYPE+", "+WALL_RECEIVER_FIRST_NAME+", "+WALL_RECEIVER_LAST_NAME+", "+
        					WALL_RECEIVER_DESIGNATION+", "+WALL_RECEIVER_COMPANY_NAME+" from "+WALL_NOTIFICATION_TABLE_NAME;
        
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<Notifications> wallNotificationList = new ArrayList<Notifications>();
        if (cursor.moveToFirst()) {
            do {
            	Notifications wallNotificationsList = new Notifications();
            	wallNotificationsList.setFirst_name(cursor.getString(0));
            	wallNotificationsList.setLast_name(cursor.getString(1));
            	wallNotificationsList.setDesignation(cursor.getString(2));
            	wallNotificationsList.setCompany_name(cursor.getString(3));
            	wallNotificationsList.setNotification_type(cursor.getString(4));
            	wallNotificationsList.setReceiver_first_name(cursor.getString(5));
            	wallNotificationsList.setReceiver_last_name(cursor.getString(6));
            	wallNotificationsList.setReceiver_designation(cursor.getString(7));
            	wallNotificationsList.setReceiver_company_name(cursor.getString(8));
            	
            	wallNotificationList.add(wallNotificationsList);
            } while (cursor.moveToNext());
        }
        db.close();
        return wallNotificationList;
    }
    

	/*
	 * public boolean updateContact (Integer id, String name, String phone,
	 * String email, String street,String place) { SQLiteDatabase db =
	 * this.getWritableDatabase(); ContentValues contentValues = new
	 * ContentValues(); contentValues.put("name", name);
	 * contentValues.put("phone", phone); contentValues.put("email", email);
	 * contentValues.put("street", street); contentValues.put("place", place);
	 * db.update("contacts", contentValues, "id = ? ", new String[] {
	 * Integer.toString(id) } ); return true; }
	 * 
	 * public Integer deleteContact (Integer id) { SQLiteDatabase db =
	 * this.getWritableDatabase(); return db.delete("contacts", "id = ? ", new
	 * String[] { Integer.toString(id) }); } public ArrayList getAllCotacts() {
	 * ArrayList array_list = new ArrayList(); //hp = new HashMap();
	 * SQLiteDatabase db = this.getReadableDatabase(); Cursor res = db.rawQuery(
	 * "select * from contacts", null ); res.moveToFirst();
	 * while(res.isAfterLast() == false){
	 * array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
	 * res.moveToNext(); } return array_list; }
	 */

}