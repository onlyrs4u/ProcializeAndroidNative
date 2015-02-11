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
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.R;
import com.procialize.customClasses.Notifications;

public class WallNotificationListAdapter extends BaseAdapter{

	private Activity activity;
    private LayoutInflater inflater;
    private List<Notifications> wallNotificationList;
//    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
 
    public WallNotificationListAdapter(Activity activity, List<Notifications> wallNotificationList) {
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
    
    public Notifications getWallNotificationFromList(int position)
    {
        return wallNotificationList.get(position);
    }
 
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.wall_list_row_for_image, null);
 
//        if (imageLoader == null)
//            imageLoader = AppController.getInstance().getImageLoader();
//        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView wallNotificationTextview = (TextView) convertView.findViewById(R.id.wall_notification_text);
 
        Notifications wallNotifications = wallNotificationList.get(position);

        // thumbnail image
//        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
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
        else
        	notificationType = activity.getResources().getString(R.string.wall_notification_download_brochure);
        
   		String receiverFullName = wallNotifications.getReceiver_first_name()+ " "+ wallNotifications.getReceiver_last_name();
   		String receiverDesignation = wallNotifications.getReceiver_designation();
   		String receiverCompanyName = wallNotifications.getReceiver_company_name();
   		String finalString = senderFullName+", "+senderDesignation+", "+senderCompanyName+" "+notificationType+" "+receiverFullName+", "+receiverDesignation+", "+receiverCompanyName;
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
   			receiverName = finalString.substring(0, finalString.indexOf("of")+3);
   			strTrailingEnd = finalString.substring(0, (finalString.indexOf(receiverName)));
   		}
   		else if(notificationType.equalsIgnoreCase(activity.getResources().getString(R.string.wall_notification_notification)))
   		{
   			senderName = finalString.substring(0, finalString.indexOf("has"));
   			receiverName = finalString.substring(0, finalString.indexOf("notification"));
   			strTrailingEnd = finalString.substring(0, (finalString.indexOf(receiverName)));
   		}
   		else if(notificationType.equalsIgnoreCase(activity.getResources().getString(R.string.wall_notification_download_brochure)))
   		{
   			senderName = finalString.substring(0, finalString.indexOf("downloaded"));
   			receiverName = finalString.substring(0, finalString.indexOf("brochure"));
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
