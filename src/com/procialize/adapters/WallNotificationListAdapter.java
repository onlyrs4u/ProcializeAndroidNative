package com.procialize.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.R;
import com.procialize.customClasses.WallNotifications;
import com.procialize.libraries.ImageLoader;
import com.procialize.utility.Constants;

public class WallNotificationListAdapter extends BaseAdapter{

	private Activity activity;
    private LayoutInflater inflater;
    private List<WallNotifications> wallNotificationList;
    
    Constants constant = new Constants();
    int loader = R.drawable.ic_launcher;
    
    public WallNotificationListAdapter(Activity activity, List<WallNotifications> wallNotificationList) {
        this.activity = activity;
        this.wallNotificationList = wallNotificationList;
    }
 
    @Override
    public int getCount() {
        return wallNotificationList.size();
//    	return 5;
    }
 
    @Override
    public Object getItem(int location) {
        return wallNotificationList.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    public WallNotifications getWallNotificationFromList(int position)
    {
        return wallNotificationList.get(position);
    }
 
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.wall_list_row_for_image, null);
        
        ImageView wall_thumbnail = (ImageView) convertView.findViewById(R.id.wall_sender_thumbnail);
        TextView wallNotificationTextview = (TextView) convertView.findViewById(R.id.wall_notification_text);
 
        WallNotifications wallNotifications = wallNotificationList.get(position);

        // Image url
        String image_url = "";
        ImageLoader imgLoader = new ImageLoader(activity);
        
//        if(!(wallNotifications.getPhoto().equalsIgnoreCase("") || wallNotifications.getPhoto().equalsIgnoreCase(null) || wallNotifications.getPhoto().equalsIgnoreCase("null"))){
        	if(wallNotifications.getObject_type().equalsIgnoreCase("A"))
        		image_url = constant.WEBSERVICE_URL + constant.ATTENDEE_IMAGE_URL + wallNotifications.getPhoto();
        	else if(wallNotifications.getObject_type().equalsIgnoreCase("S"))
        		image_url = constant.WEBSERVICE_URL + constant.SPEAKER_IMAGE_URL + wallNotifications.getPhoto();
        	else if(wallNotifications.getObject_type().equalsIgnoreCase("E"))
        		image_url = constant.WEBSERVICE_URL + constant.EXHIBITOR_IMAGE_URL + wallNotifications.getPhoto();
        	imgLoader.DisplayImage(image_url, loader, wall_thumbnail);
        	
//        }else if(!(wallNotifications.getOrganizer_photo().equalsIgnoreCase("") || wallNotifications.getOrganizer_photo().equalsIgnoreCase(null) || wallNotifications.getOrganizer_photo().equalsIgnoreCase("null"))){
        	if(wallNotifications.getObject_type().equalsIgnoreCase("O"))
        		image_url = constant.WEBSERVICE_URL + constant.ORGANIZER_IMAGE_URL + wallNotifications.getOrganizer_photo();
        	imgLoader.DisplayImage(image_url, loader, wall_thumbnail);
//        }
        
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        String senderFullName = wallNotifications.getFirst_name()+ " "+ wallNotifications.getLast_name();        
        String senderDesignation = wallNotifications.getDesignation();
        String senderCompanyName = wallNotifications.getCompany_name();
        String notificationType = "";
        if(wallNotifications.getNotification_type().equalsIgnoreCase("Sh"))
        	notificationType = activity.getResources().getString(R.string.wall_notification_shared);
        else if(wallNotifications.getNotification_type().equalsIgnoreCase("Sav"))
        	notificationType = activity.getResources().getString(R.string.wall_notification_saved);
        else if(wallNotifications.getNotification_type().equalsIgnoreCase("N"))
        	notificationType = activity.getResources().getString(R.string.wall_notification_notification);
        else if(wallNotifications.getNotification_type().equalsIgnoreCase("download_exe_map"))
        	notificationType = activity.getResources().getString(R.string.wall_notification_download_brochure);
        
   		String receiverFullName = wallNotifications.getReceiver_first_name()+ " "+ wallNotifications.getReceiver_last_name();
   		String receiverDesignation = wallNotifications.getReceiver_designation();
   		String receiverCompanyName = wallNotifications.getReceiver_company_name();
   		String finalString = "";
   		if(wallNotifications.getNotification_type().equalsIgnoreCase("Sh") || wallNotifications.getNotification_type().equalsIgnoreCase("Sav")){
   			finalString = senderFullName+", "+senderDesignation+", "+senderCompanyName+" "+notificationType+" "+receiverFullName+", "+receiverDesignation+", "+receiverCompanyName;
   		}else if(wallNotifications.getNotification_type().equalsIgnoreCase("download_exe_map")){
   			receiverFullName = wallNotifications.getReceiver_first_name();
   			finalString = senderFullName+", "+senderDesignation+", "+senderCompanyName+" "+notificationType+" "+receiverFullName;
   		}else if(wallNotifications.getNotification_type().equalsIgnoreCase("N")){
   			String organizerName = wallNotifications.getOrganizer_name();
   			String notificationContent = wallNotifications.getNotification_content();
   			finalString = organizerName+" "+notificationType+" "+notificationContent;
   		}
   			
   		String senderName = "";
   		String receiverName = "";
   		String strTrailingEnd = "";
   		
   		if(notificationType.equalsIgnoreCase(activity.getResources().getString(R.string.wall_notification_shared)))
   		{
   			senderName = finalString.substring(0, finalString.indexOf("shared"));
   			receiverName = finalString.substring(finalString.lastIndexOf("of")+3);
   			strTrailingEnd = finalString.substring(0, (finalString.indexOf(receiverName)));
   		}
   		else if(notificationType.equalsIgnoreCase(activity.getResources().getString(R.string.wall_notification_saved)))
   		{
   			senderName = finalString.substring(0, finalString.indexOf("saved"));
   			receiverName = finalString.substring(finalString.lastIndexOf("of")+3);
   			strTrailingEnd = finalString.substring(0, (finalString.indexOf(receiverName)));
   		}
   		else if(notificationType.equalsIgnoreCase(activity.getResources().getString(R.string.wall_notification_notification)))
   		{
   			senderName = finalString.substring(0, finalString.indexOf("has"));
   			receiverName = finalString.substring(finalString.lastIndexOf("notification")+13);
   			strTrailingEnd = finalString.substring(0, (finalString.indexOf(receiverName)));
   		}
   		else if(notificationType.equalsIgnoreCase(activity.getResources().getString(R.string.wall_notification_download_brochure)))
   		{
   			senderName = finalString.substring(0, finalString.indexOf("downloaded"));
   			receiverName = finalString.substring(finalString.lastIndexOf("of")+3);
   			strTrailingEnd = finalString.substring(0, (finalString.indexOf(receiverName)));
   		}
   		
   		stringBuilder.append(finalString);
   		
   		stringBuilder.setSpan(new NonUnderlinedClickableSpan() {
			@Override
			public void onClick(View widget) {
				Toast.makeText(activity, "Clicked senderName", Toast.LENGTH_LONG).show();
			}
		},0, senderName.length()-1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
   		
   		stringBuilder.setSpan(new NonUnderlinedClickableSpan() {
			@Override
			public void onClick(View widget) {
				Toast.makeText(activity, "Clicked receiverName", Toast.LENGTH_LONG).show();
			}
		}, strTrailingEnd.length(), strTrailingEnd.length() + receiverName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
   		
   		wallNotificationTextview.setText(stringBuilder);
   		wallNotificationTextview.setMovementMethod(LinkMovementMethod.getInstance());
   		
        return convertView;
    }
    
    public class NonUnderlinedClickableSpan extends ClickableSpan {
    	  
		@Override
		public void updateDrawState(TextPaint ds) {
			ds.setColor(Color.MAGENTA);
			ds.setUnderlineText(false);
			ds.setTypeface(Typeface.DEFAULT_BOLD);
		}
		@Override
		public void onClick(View v) {

		}
	}
    
}
