package com.procialize.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.procialize.R;
import com.procialize.customClasses.UserProfile;
import com.procialize.utility.Constants;

public class CustomSpinnerAdapter extends ArrayAdapter<UserProfile>{

	Context context;
	int layoutResID;
//	List<Profile> spinnerData;
	List<UserProfile> profileData;
//	public ListView myCustomItem;
	Constants constant = new Constants();
	
	/*public CustomSpinnerAdapter(Context context, int layoutResourceID, int textViewResourceId, List<Profile> spinnerDataList) {
		super(context, layoutResourceID, textViewResourceId, spinnerDataList);
		
		this.context = context;
		this.layoutResID = layoutResourceID;
		this.profileData = spinnerDataList;
		
	}*/
	
	/*public CustomSpinnerAdapter(Context context, int layoutResourceID, List<Profile> spinnerDataList, ListView drawerItem0) {
		super(context, layoutResourceID, spinnerDataList);
		
		myCustomItem = new ListView(context);
		this.context = context;
		this.layoutResID = layoutResourceID;
		this.profileData = spinnerDataList;
		this.myCustomItem = drawerItem0;
	}*/
	
	public CustomSpinnerAdapter(Context context, int layoutResourceID, List<UserProfile> spinnerDataList) {
		super(context, layoutResourceID, spinnerDataList);
		
		this.context = context;
		this.layoutResID = layoutResourceID;
		this.profileData = spinnerDataList;
		
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		SpinnerHolder holder;
		
		if(row == null) {
			LayoutInflater inflater=((Activity)context).getLayoutInflater();
			
			row = inflater.inflate(layoutResID, parent, false);
			holder = new SpinnerHolder();
			
			holder.userImage = (ImageView)row.findViewById(R.id.left_pic);
			holder.name = (TextView)row.findViewById(R.id.text_main_name);
			holder.designation = (TextView)row.findViewById(R.id.sub_text_email);
			holder.company = (TextView)row.findViewById(R.id.sub_text_company);
			
			row.setTag(holder);
		}
		else {
			holder=(SpinnerHolder)row.getTag();
		}
		UserProfile spinnerItem = null;
		if(!constant.USER_PROFILE_DB_LIST.isEmpty())
			spinnerItem = constant.USER_PROFILE_DB_LIST.get(position);
		else
			spinnerItem = profileData.get(position);
		
//		holder.userImage.setImageDrawable(row.getResources().getDrawable(spinnerItem.getDrawableResID()));
		holder.name.setText(spinnerItem.getFirst_name()+" "+spinnerItem.getLast_name());
		holder.designation.setText(spinnerItem.getDesignation());
		holder.company.setText(spinnerItem.getCompany_name());
		
		return row;
		
	}
	
	private static class SpinnerHolder
	{
		ImageView userImage;
		TextView  name, designation, company;
		
	}

}
