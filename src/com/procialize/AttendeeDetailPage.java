package com.procialize;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AttendeeDetailPage extends Activity implements OnClickListener {
	
	TextView attendee_name;
    TextView attendee_designation;
    TextView attendee_comp_name;
    TextView attendee_city;
    
    Button sendMessage;
    Button setMeeting;
    Button saveAttendee;
    Button shareAttendee;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attendees_detail);
		
		Intent getAttendeeInfo = getIntent();
		
        attendee_name = (TextView) findViewById(R.id.attendee_detail_name);
        attendee_name.setText(getAttendeeInfo.getStringExtra("AttendeeName"));
        
        attendee_designation = (TextView) findViewById(R.id.attendee_detail_designation);
        attendee_designation.setText(getAttendeeInfo.getStringExtra("AttendeeDesignation"));
        
        attendee_comp_name = (TextView) findViewById(R.id.attendee_detail_comp_name);
        attendee_comp_name.setText(getAttendeeInfo.getStringExtra("AttendeeCompany"));
        
        attendee_city = (TextView) findViewById(R.id.attendee_detail_city);
        attendee_city.setText(getAttendeeInfo.getStringExtra("AttendeeCity"));
        
        sendMessage = (Button) findViewById(R.id.attendee_detail_send_message);
        setMeeting = (Button) findViewById(R.id.attendee_detail_set_meeting);
        saveAttendee = (Button) findViewById(R.id.attendee_detail_save_attendee);
        shareAttendee = (Button) findViewById(R.id.attendee_detail_share_attendee);
        
        sendMessage.setOnClickListener(this);
        setMeeting.setOnClickListener(this);
        saveAttendee.setOnClickListener(this);
        shareAttendee.setOnClickListener(this);
        
	}
	
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		if(view == sendMessage)
		{
			Toast.makeText(AttendeeDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
		else if(view == setMeeting)
		{
			Toast.makeText(AttendeeDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
		else if(view == saveAttendee)
		{
			Toast.makeText(AttendeeDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
		else if(view == shareAttendee)
		{
			Toast.makeText(AttendeeDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
	}

}
