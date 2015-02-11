package com.procialize;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ExhibitorDetailPage extends Activity implements OnClickListener {
	
	TextView exhibitor_name;
    TextView exhibitor_designation;
    TextView exhibitor_comp_name;
    TextView exhibitor_city;
    
    Button sendMessage;
    Button setMeeting;
    Button saveAttendee;
    Button shareAttendee;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exhibitors_detail);
		
		Intent getExhibitorInfo = getIntent();
		
		exhibitor_name = (TextView) findViewById(R.id.exhibitor_detail_name);
		exhibitor_name.setText(getExhibitorInfo.getStringExtra("ExhibitorName"));
        
		exhibitor_designation = (TextView) findViewById(R.id.exhibitor_detail_designation);
		exhibitor_designation.setText(getExhibitorInfo.getStringExtra("ExhibitorDesignation"));
        
		exhibitor_comp_name = (TextView) findViewById(R.id.exhibitor_detail_comp_name);
		exhibitor_comp_name.setText(getExhibitorInfo.getStringExtra("ExhibitorCompany"));
        
		exhibitor_city = (TextView) findViewById(R.id.exhibitor_detail_city);
		exhibitor_city.setText(getExhibitorInfo.getStringExtra("ExhibitorCity"));
        
        sendMessage = (Button) findViewById(R.id.exhibitor_detail_send_message);
        setMeeting = (Button) findViewById(R.id.exhibitor_detail_set_meeting);
        saveAttendee = (Button) findViewById(R.id.exhibitor_detail_save_attendee);
        shareAttendee = (Button) findViewById(R.id.exhibitor_detail_share_attendee);
        
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
			Toast.makeText(ExhibitorDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
		else if(view == setMeeting)
		{
			Toast.makeText(ExhibitorDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
		else if(view == saveAttendee)
		{
			Toast.makeText(ExhibitorDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
		else if(view == shareAttendee)
		{
			Toast.makeText(ExhibitorDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
	}

}