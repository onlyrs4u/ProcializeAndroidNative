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

public class ExhibitorsListAdapter extends BaseAdapter{

	private Activity activity;
    private LayoutInflater inflater;
    private List<Attendees> exhibitorsList;
//    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
 
    public ExhibitorsListAdapter(Activity activity, List<Attendees> attendeesList) {
        this.activity = activity;
        this.exhibitorsList = attendeesList;
    }
 
    @Override
    public int getCount() {
        return exhibitorsList.size();
    }
 
    @Override
    public Object getItem(int location) {
        return exhibitorsList.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    public Attendees getExhibitorFromList(int position)
    {
        return exhibitorsList.get(position);
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
 
        Attendees exhibitors = exhibitorsList.get(position);
 
        // thumbnail image
//        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
         
        attendee_name.setText(exhibitors.getAttendee_first_name()+ " "+ exhibitors.getAttendee_last_name());
         
        attendee_designation.setText(exhibitors.getAttendee_designation());
        
        attendee_comp_name.setText(exhibitors.getAttendee_company_name());
         
        attendee_city.setText(exhibitors.getAttendee_city());
 
        return convertView;
    }

}