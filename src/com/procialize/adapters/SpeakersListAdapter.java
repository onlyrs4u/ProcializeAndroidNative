package com.procialize.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.procialize.R;
import com.procialize.customClasses.Attendees;

public class SpeakersListAdapter extends BaseAdapter{

	private Activity activity;
    private LayoutInflater inflater;
    private List<Attendees> speakersList;
//    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
 
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
 
//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView attendee_name = (TextView) convertView.findViewById(R.id.name);
        TextView attendee_designation = (TextView) convertView.findViewById(R.id.designation);
        TextView attendee_comp_name = (TextView) convertView.findViewById(R.id.comp_name);
        TextView attendee_city = (TextView) convertView.findViewById(R.id.city);
 
        Attendees attendees = speakersList.get(position);
 
        // thumbnail image
//        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
         
        attendee_name.setText(attendees.getAttendee_first_name()+ " "+ attendees.getAttendee_last_name());
         
        attendee_designation.setText(attendees.getAttendee_designation());
        
        attendee_comp_name.setText(attendees.getAttendee_company_name());
         
        attendee_city.setText(attendees.getAttendee_city());
 
        return convertView;
    }

}
