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

public class SavedAttendeeListAdapter extends BaseAdapter{

	private Activity activity;
    private LayoutInflater inflater;
    private List<Bookmarked> bookmarkList;
    
    Constants constant = new Constants();
//    Loader image - will be shown before loading image
    int loader = R.drawable.ic_launcher;
 
    public SavedAttendeeListAdapter(Activity activity, List<Bookmarked> bookmarkedList) {
        this.activity = activity;
        this.bookmarkList = bookmarkedList;
    }
 
    @Override
    public int getCount() {
        return bookmarkList.size();
    }
 
    @Override
    public Object getItem(int location) {
        return bookmarkList.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    public Bookmarked getSavedAttendeeFromList(int position)
    {
        return bookmarkList.get(position);
    }
 
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
 
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
 
        Bookmarked bookmarked = bookmarkList.get(position);
 
        // Image url
        String image_url = "";
//        if(!(attendees.getAttendee_image().equalsIgnoreCase("") || attendees.getAttendee_image().equalsIgnoreCase(null)))
        		image_url = constant.WEBSERVICE_URL + constant.ATTENDEE_IMAGE_URL + bookmarked.getReceiver_attendee_image();
        
        ImageLoader imgLoader = new ImageLoader(activity);
        
        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView 
        imgLoader.DisplayImage(image_url, loader, attendee_thumbnail);
         
        attendee_name.setText(bookmarked.getReceiver_first_name()+ " "+ bookmarked.getReceiver_last_name());
        attendee_name.setTypeface(typeFace);
         
        attendee_designation.setText(bookmarked.getReceiver_designation());
        attendee_designation.setTypeface(typeFace);
        
        attendee_comp_name.setText(bookmarked.getReceiver_company_name());
        attendee_comp_name.setTypeface(typeFace);
         
        attendee_city.setText(bookmarked.getReceiver_attendee_city());
        attendee_city.setTypeface(typeFace);
        
        return convertView;
    }
    
}