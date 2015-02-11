package com.procialize;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SpeakerDetailPage extends Activity implements OnClickListener {
	
	TextView speaker_name;
    TextView speaker_designation;
    TextView speaker_comp_name;
    TextView speaker_city;
    
    Button sendMessage;
    Button setMeeting;
    Button saveAttendee;
    Button shareAttendee;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speakers_detail);
		
		Intent getSpeakerInfo = getIntent();
		
		speaker_name = (TextView) findViewById(R.id.speaker_detail_name);
		speaker_name.setText(getSpeakerInfo.getStringExtra("SpeakerName"));
        
		speaker_designation = (TextView) findViewById(R.id.speaker_detail_designation);
		speaker_designation.setText(getSpeakerInfo.getStringExtra("SpeakerDesignation"));
        
		speaker_comp_name = (TextView) findViewById(R.id.speaker_detail_comp_name);
		speaker_comp_name.setText(getSpeakerInfo.getStringExtra("SpeakerCompany"));
        
		speaker_city = (TextView) findViewById(R.id.speaker_detail_city);
		speaker_city.setText(getSpeakerInfo.getStringExtra("SpeakerCity"));
        
        sendMessage = (Button) findViewById(R.id.speaker_detail_send_message);
        setMeeting = (Button) findViewById(R.id.speaker_detail_set_meeting);
        saveAttendee = (Button) findViewById(R.id.speaker_detail_save_attendee);
        shareAttendee = (Button) findViewById(R.id.speaker_detail_share_attendee);
        
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
			Toast.makeText(SpeakerDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
		else if(view == setMeeting)
		{
			Toast.makeText(SpeakerDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
		else if(view == saveAttendee)
		{
			Toast.makeText(SpeakerDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
		else if(view == shareAttendee)
		{
			Toast.makeText(SpeakerDetailPage.this, "Coming soon", Toast.LENGTH_SHORT).show();
		}
	}

}