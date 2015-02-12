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

public class SpeakersListAdapter extends BaseAdapter{

	private Activity activity;
    private LayoutInflater inflater;
    private List<Attendees> speakersList;

    Constants constant = new Constants();
//  Loader image - will be shown before loading image
    int loader = R.drawable.ic_launcher;
  
    public SpeakersListAdapter(Activity activity, List<Attendees> speakersList) {
        this.activity = activity;
        this.speakersList = speakersList;
    }
 
    @Override
    public int getCount() {
        return speakersList.size();
    }
 
    @Override
    public Object getItem(int location) {
        return speakersList.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    public Attendees getSpeakerFromList(int position)
    {
        return speakersList.get(position);
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.single_list_row, null);

        ImageView attendee_thumnail = (ImageView) convertView.findViewById(R.id.thumbnail);
        TextView attendee_name = (TextView) convertView.findViewById(R.id.name);
        TextView attendee_designation = (TextView) convertView.findViewById(R.id.designation);
        TextView attendee_comp_name = (TextView) convertView.findViewById(R.id.comp_name);
        TextView attendee_city = (TextView) convertView.findViewById(R.id.city);
 
        Attendees speakers = speakersList.get(position);
 
//        Image url
        String image_url = "";
        if(!(speakers.getAttendee_image().equalsIgnoreCase("") || speakers.getAttendee_image().equalsIgnoreCase(null)))
        		image_url = constant.WEBSERVICE_URL + constant.ATTENDEE_IMAGE_URL + speakers.getAttendee_image();
        
        ImageLoader imgLoader = new ImageLoader(activity);
        
        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView 
        imgLoader.DisplayImage(image_url, loader, attendee_thumnail);
        
        attendee_name.setText(speakers.getAttendee_first_name()+ " "+ speakers.getAttendee_last_name());
         
        attendee_designation.setText(speakers.getAttendee_designation());
        
        attendee_comp_name.setText(speakers.getAttendee_company_name());
        
        attendee_city.setText(speakers.getAttendee_city());
 
        return convertView;
    }

}
