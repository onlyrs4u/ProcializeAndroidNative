package com.procialize.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.procialize.R;
import com.procialize.customClasses.Bookmarked;
import com.procialize.libraries.ImageLoader;
import com.procialize.libraries.MLRoundedImageView;
import com.procialize.utility.Constants;

public class SavedSpeakerListAdapter extends BaseAdapter{

	private Activity activity;
    private LayoutInflater inflater;
    private List<Bookmarked> speakersList;
    
    Constants constant = new Constants();
//  Loader image - will be shown before loading image
    int loader = R.drawable.ic_launcher;
    
    public SavedSpeakerListAdapter(Activity activity, List<Bookmarked> attendeesList) {
        this.activity = activity;
        this.speakersList = attendeesList;
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
 
    public Bookmarked getSpeakerFromList(int position)
    {
        return speakersList.get(position);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.single_list_row, null);

        Typeface typeFace = Typeface.createFromAsset(activity.getAssets(),"fonts/HERO.ttf");
        
        MLRoundedImageView attendee_thumbnail = (MLRoundedImageView) convertView.findViewById(R.id.thumbnail);
        TextView attendee_name = (TextView) convertView.findViewById(R.id.name);
        TextView attendee_designation = (TextView) convertView.findViewById(R.id.designation);
        TextView attendee_comp_name = (TextView) convertView.findViewById(R.id.comp_name);
        TextView attendee_city = (TextView) convertView.findViewById(R.id.city);
 
        Bookmarked exhibitors = speakersList.get(position);
        
        // Image url
        String image_url = "";
//        if(!(exhibitors.getAttendee_image().equalsIgnoreCase("") || exhibitors.getAttendee_image().equalsIgnoreCase(null)))
        		image_url = constant.WEBSERVICE_URL + constant.EXHIBITOR_IMAGE_URL + exhibitors.getReceiver_attendee_image();

        ImageLoader imgLoader = new ImageLoader(activity);

        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView 
        imgLoader.DisplayImage(image_url, loader, attendee_thumbnail);
        
        attendee_name.setText(exhibitors.getReceiver_first_name()+ " "+ exhibitors.getReceiver_last_name());
        attendee_name.setTypeface(typeFace);
         
        attendee_designation.setText(exhibitors.getReceiver_designation());
        attendee_designation.setTypeface(typeFace);
        
        attendee_comp_name.setText(exhibitors.getReceiver_company_name());
        attendee_comp_name.setTypeface(typeFace);
         
        attendee_city.setText(exhibitors.getReceiver_attendee_city());
        attendee_city.setTypeface(typeFace);
 
        return convertView;
    }

}