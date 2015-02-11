package com.procialize.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.procialize.R;
import com.procialize.customClasses.Attendees;

public class AttendeesListAdapter extends BaseAdapter{

	private Activity activity;
    private LayoutInflater inflater;
    private List<Attendees> attendeesList;
//    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
 
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
 
//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        RelativeLayout single_relative_layout = (RelativeLayout) convertView.findViewById(R.id.single_relative_layout);
        TextView attendee_name = (TextView) convertView.findViewById(R.id.name);
        TextView attendee_designation = (TextView) convertView.findViewById(R.id.designation);
        TextView attendee_comp_name = (TextView) convertView.findViewById(R.id.comp_name);
        TextView attendee_city = (TextView) convertView.findViewById(R.id.city);
 
        Attendees attendees = attendeesList.get(position);
 
        // thumbnail image
//        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
         
        attendee_name.setText(attendees.getAttendee_first_name()+ " "+ attendees.getAttendee_last_name());
         
        attendee_designation.setText(attendees.getAttendee_designation());
        
        attendee_comp_name.setText(attendees.getAttendee_company_name());
         
        attendee_city.setText(attendees.getAttendee_city());
        
        /*convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				Intent attendeeDetail = new Intent(activity, AttendeeDetailPage.class);
				activity.startActivity(attendeeDetail);
			}
		});*/
 
        return convertView;
    }
}
