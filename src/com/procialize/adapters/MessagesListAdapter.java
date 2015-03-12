package com.procialize.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.R;
import com.procialize.customClasses.UserNotifications;
import com.procialize.utility.Constants;

public class MessagesListAdapter extends BaseAdapter{

	private Activity activity;
    private LayoutInflater inflater;
    private List<UserNotifications> messagesList;
    
    Constants constant = new Constants();
 
    public MessagesListAdapter(Activity activity, List<UserNotifications> userMessagesList) {
        this.activity = activity;
        this.messagesList = userMessagesList;
    }
 
    @Override
    public int getCount() {
        return messagesList.size();
    }
 
    @Override
    public Object getItem(int location) {
        return messagesList.get(location);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    public UserNotifications getMessagesFromList(int position)
    {
        return messagesList.get(position);
    }
 
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
 
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.messages_single_item, null);
        
        Typeface typeFace = Typeface.createFromAsset(activity.getAssets(),"fonts/HERO.ttf");
        
        TextView notification_content = (TextView) convertView.findViewById(R.id.content);
        TextView notification_date = (TextView) convertView.findViewById(R.id.date);
        TextView notification_sender_name = (TextView) convertView.findViewById(R.id.sender_name);
        Button reply_button = (Button) convertView.findViewById(R.id.reply_button);
 
        UserNotifications notifications = messagesList.get(position);
        
        notification_content.setText(notifications.getNotification_content());
        notification_content.setTypeface(typeFace);
        
        notification_date.setText(notifications.getNotification_date());
        notification_date.setTypeface(typeFace);
        
        notification_sender_name.setText(notifications.getFirst_name()+ " "+ notifications.getLast_name());
        notification_sender_name.setTypeface(typeFace);
            
        reply_button.setTypeface(typeFace);
        reply_button.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		Toast.makeText(activity, "Nahi deta reply ...", Toast.LENGTH_LONG).show();
        	}
        });

        return convertView;
    }
    
}