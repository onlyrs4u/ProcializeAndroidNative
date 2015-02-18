package com.procialize.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.procialize.R;
import com.procialize.customClasses.Attendees;
import com.procialize.libraries.ImageLoader;
import com.procialize.utility.Constants;

public class AttendeesListAdapter extends BaseAdapter{

	private Activity activity;
    private LayoutInflater inflater;
    private List<Attendees> attendeesList;
    
    Constants constant = new Constants();
//    Loader image - will be shown before loading image
    int loader = R.drawable.ic_launcher;
 
    public AttendeesListAdapter(Activity activity, List<Attendees> attendeesList) {
        this.activity = activity;
        this.attendeesList = attendeesList;
    }
 
    @Override
    public int getCount() {
        return attendeesList.size();
    }
 
    @Override
    public Object getItem(int location) {
        return attendeesList.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    public Attendees getAttendeeFromList(int position)
    {
        return attendeesList.get(position);
    }
 
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.single_list_row, null);
 
        ImageView attendee_thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
        TextView attendee_name = (TextView) convertView.findViewById(R.id.name);
        TextView attendee_designation = (TextView) convertView.findViewById(R.id.designation);
        TextView attendee_comp_name = (TextView) convertView.findViewById(R.id.comp_name);
        TextView attendee_city = (TextView) convertView.findViewById(R.id.city);
 
        Attendees attendees = attendeesList.get(position);
 
        // Image url
        String image_url = "";
        if(!(attendees.getAttendee_image().equalsIgnoreCase("") || attendees.getAttendee_image().equalsIgnoreCase(null)))
        		image_url = constant.WEBSERVICE_URL + constant.ATTENDEE_IMAGE_URL + attendees.getAttendee_image();
        
        ImageLoader imgLoader = new ImageLoader(activity);
        
        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView 
        imgLoader.DisplayImage(image_url, loader, attendee_thumbnail);
         
        attendee_name.setText(attendees.getAttendee_first_name()+ " "+ attendees.getAttendee_last_name());
         
        attendee_designation.setText(attendees.getAttendee_designation());
        
        attendee_comp_name.setText(attendees.getAttendee_company_name());
         
        attendee_city.setText(attendees.getAttendee_city());
        
        return convertView;
    }
}
