package com.procialize.parsers;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.procialize.customClasses.Events;

public class EventInfoParser {

	JSONObject jsonObj = null;
	JSONObject userJsonObject = null;
	
	// JSON Node names
    private static final String TAG_EVENT_LIST = "event_list";
 
    // JSONArray
    JSONArray event_list = null;
    ArrayList<Events> eventsList;
    Events events;
    
	public ArrayList<Events> EventInfo_Parser(String jsonStr) {
		// TODO Auto-generated constructor stub
		
		eventsList = new ArrayList<Events>();
		
		if (jsonStr != null) {
			try {
				jsonObj = new JSONObject(jsonStr);
				
				// Getting event info JSON Array node
				event_list = jsonObj.getJSONArray(TAG_EVENT_LIST);
				JSONObject jsonEvent = null;
				
				for (int i = 0; i < event_list.length(); i++) {
					jsonEvent = event_list.getJSONObject(i);
					events = new Events();
					
					String eventID = jsonEvent.getString("event_id");
					if(!(eventID.equalsIgnoreCase("") || eventID.equalsIgnoreCase(null)))
					{
						events.setEvent_id(eventID);	
					}
					String eventName = jsonEvent.getString("event_name");
					if(!(eventName.equalsIgnoreCase("") || eventName.equalsIgnoreCase(null)))
					{
						events.setEvent_name(eventName);
					}
					String eventDesc = jsonEvent.getString("event_description");
					if(!(eventDesc.equalsIgnoreCase("") || eventDesc.equalsIgnoreCase(null)))
					{
						events.setEvent_description(eventDesc);
					}
					String featured = jsonEvent.getString("featured");
					if(!(featured.equalsIgnoreCase("") || featured.equalsIgnoreCase(null)))
					{
						events.setFeatured(featured);
					}
					String eventStart = jsonEvent.getString("event_start");
					if(!(eventStart.equalsIgnoreCase("") || eventStart.equalsIgnoreCase(null)))
					{
						events.setEvent_start(eventStart);
					}
					String eventEnd = jsonEvent.getString("event_end");
					if(!(eventEnd.equalsIgnoreCase("") || eventEnd.equalsIgnoreCase(null)))
					{
						events.setEvent_end(eventEnd);
					}
					String eventLocation = jsonEvent.getString("event_location");
					if(!(eventLocation.equalsIgnoreCase("") || eventLocation.equalsIgnoreCase(null)))
					{
						events.setEvent_location(eventLocation);
					}
					String eventCity = jsonEvent.getString("event_city");
					if(!(eventCity.equalsIgnoreCase("") || eventCity.equalsIgnoreCase(null)))
					{
						events.setEvent_city(eventCity);
					}
					String eventCountry = jsonEvent.getString("event_country");
					if(!(eventCountry.equalsIgnoreCase("") || eventCountry.equalsIgnoreCase(null)))
					{
						events.setEvent_country(eventCountry);
					}
					String eventLatitude = jsonEvent.getString("event_latitude");
					if(!(eventLatitude.equalsIgnoreCase("") || eventLatitude.equalsIgnoreCase(null)))
					{
						events.setEvent_latitude(eventLatitude);
					}
					String eventLongitude = jsonEvent.getString("event_longitude");
					if(!(eventLongitude.equalsIgnoreCase("") || eventLongitude.equalsIgnoreCase(null)))
					{
						events.setEvent_longitude(eventLongitude);
					}
					String website = jsonEvent.getString("website");
					if(!(website.equalsIgnoreCase("") || website.equalsIgnoreCase(null)))
					{
						events.setEvent_website(website);
					}
					String eventLinkedIn = jsonEvent.getString("linkden");
					if(!(eventLinkedIn.equalsIgnoreCase("") || eventLinkedIn.equalsIgnoreCase(null)))
					{
						events.setLinkedin_page_url(eventLinkedIn);	
					}
					String eventTwitter = jsonEvent.getString("twitter");
					if(!(eventTwitter.equalsIgnoreCase("") || eventTwitter.equalsIgnoreCase(null)))
					{
						events.setTwitter_page_url(eventTwitter);
					}
					String eventFacebook = jsonEvent.getString("facebook");
					if(!(eventFacebook.equalsIgnoreCase("") || eventFacebook.equalsIgnoreCase(null)))
					{
						events.setFacebook_page_url(eventFacebook);
					}
					String eventLogo = jsonEvent.getString("event_logo");
					if(!(eventLogo.equalsIgnoreCase("") || eventLogo.equalsIgnoreCase(null)))
					{
						events.setEvent_logo(eventLogo);	
					}
					String eventImage1 = jsonEvent.getString("image1");
					if(!(eventImage1.equalsIgnoreCase("") || eventImage1.equalsIgnoreCase(null)))
					{
						events.setEvent_image_1(eventImage1);	
					}
					String eventImage2 = jsonEvent.getString("image2");
					if(!(eventImage2.equalsIgnoreCase("") || eventImage2.equalsIgnoreCase(null)))
					{
						events.setEvent_image_2(eventImage2);
					}
					String eventImage3 = jsonEvent.getString("image3");
					if(!(eventImage3.equalsIgnoreCase("") || eventImage3.equalsIgnoreCase(null)))
					{
						events.setEvent_image_3(eventImage3);	
					}
					String eventContactName = jsonEvent.getString("contact_name");
					if(!(eventContactName.equalsIgnoreCase("") || eventContactName.equalsIgnoreCase(null)))
					{
						events.setEvent_contact_name(eventContactName);
					}
					String eventContactEmail = jsonEvent.getString("contact_email");
					if(!(eventContactEmail.equalsIgnoreCase("") || eventContactEmail.equalsIgnoreCase(null)))
					{
						events.setEvent_contact_email(eventContactEmail);	
					}
					String floorPlan = jsonEvent.getString("floor_plan");
					if(!(floorPlan.equalsIgnoreCase("") || floorPlan.equalsIgnoreCase(null)))
					{
						events.setEvent_floor_plan(floorPlan);
					}
					String organizerId = jsonEvent.getString("organizer_id");
					if(!(organizerId.equalsIgnoreCase("") || organizerId.equalsIgnoreCase(null)))
					{
						events.setEvent_organizer_id(organizerId);
					}
					String eventOrganizer = jsonEvent.getString("event_organizer");
					if(!(eventOrganizer.equalsIgnoreCase("") || eventOrganizer.equalsIgnoreCase(null)))
					{
						events.setEvent_organizer(eventOrganizer);
					}
					String organizerPhoto = jsonEvent.getString("organiser_photo");
					if(!(organizerPhoto.equalsIgnoreCase("") || organizerPhoto.equalsIgnoreCase(null)))
					{
						events.setOrganizer_photo(organizerPhoto);
					}
					String eventIndustry = jsonEvent.getString("event_industry");
					if(!(eventIndustry.equalsIgnoreCase("") || eventIndustry.equalsIgnoreCase(null)))
					{
						events.setEvent_industry(eventIndustry);
					}
					String eventFunctionality = jsonEvent.getString("event_functionality");
					if(!(eventFunctionality.equalsIgnoreCase("") || eventFunctionality.equalsIgnoreCase(null)))
					{
						events.setEvent_functionality(eventFunctionality);	
					}
					eventsList.add(events);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
		}
		return eventsList;
	}
}
