package com.procialize.adapters;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.R;
import com.procialize.customClasses.DrawerItem;
import com.procialize.customClasses.UserProfile;

public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

	Context context;
	List<DrawerItem> drawerItemList;
	ArrayList<UserProfile> myUserList;
	int layoutResID;
	
	public CustomDrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems) {
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.drawerItemList = listItems;
		this.layoutResID = layoutResourceID;
	}
	
	public CustomDrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems, ArrayList<UserProfile> userList) {
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.layoutResID = layoutResourceID;
		this.drawerItemList = listItems;
		this.myUserList = userList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub 
		DrawerItemHolder drawerHolder;
		View view = convertView;

		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			drawerHolder = new DrawerItemHolder();

			view = inflater.inflate(layoutResID, parent, false);
			drawerHolder.ItemName = (TextView) view.findViewById(R.id.drawer_itemName);
			drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);

			drawerHolder.spinner = (ListView) view.findViewById(R.id.drawerSpinner);

			drawerHolder.title = (TextView) view.findViewById(R.id.drawerTitle);
			drawerHolder.titleIcon = (ImageView) view.findViewById(R.id.drawerIcon);

			drawerHolder.headerLayout = (LinearLayout) view.findViewById(R.id.headerLayout);
			drawerHolder.itemLayout = (LinearLayout) view.findViewById(R.id.itemLayout);
			drawerHolder.spinnerLayout = (LinearLayout) view.findViewById(R.id.spinnerLayout);
			
			/*drawerHolder.userImage=(ImageView)view.findViewById(R.id.left_pic);
			drawerHolder.name=(TextView)view.findViewById(R.id.text_main_name);
			drawerHolder.email=(TextView)view.findViewById(R.id.sub_text_email);
			drawerHolder.company=(TextView)view.findViewById(R.id.sub_text_company);*/

			view.setTag(drawerHolder);

		} else {
			drawerHolder = (DrawerItemHolder) view.getTag();

		}
		
		DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

		if (dItem.isSpinner()) {
			drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.spinnerLayout.setVisibility(LinearLayout.VISIBLE);

			ArrayList<UserProfile> userList = null;
			userList = new ArrayList<UserProfile>();
			if(this.myUserList != null){
				userList = this.myUserList;
			}else{
				userList.add(new UserProfile(R.drawable.user1, "Loggedin fname", "Loggedin lname", "user designation", "company name"));
			}
			drawerHolder.spinner.invalidate();
			CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(context, R.layout.custom_spinner_item, userList);
			drawerHolder.spinner.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			
			drawerHolder.spinner.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Toast.makeText(context, "Edit Profile", Toast.LENGTH_SHORT).show();
					/*Intent edit_profile = new Intent(context, EditProfileActivity.class);
					edit_profile.putExtra("userProfile_Array", myUserList);
					context.startActivity(edit_profile);*/
				}
			});
			/*drawerHolder.spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					// TODO Auto-generated method stub
					Toast.makeText(context, "User Changed", Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});*/
			
		} else if (dItem.getTitle() != null && dItem.getTitleImgResId() != 0) {
			drawerHolder.headerLayout.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.spinnerLayout.setVisibility(LinearLayout.INVISIBLE);
			
			drawerHolder.titleIcon.setImageDrawable(view.getResources().getDrawable(dItem.getTitleImgResId()));
			drawerHolder.title.setText(dItem.getTitle());
			Log.d("Getview","Passed4");
		} else {
			
			drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.spinnerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);

			drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(dItem.getImgResID()));
			drawerHolder.ItemName.setText(dItem.getItemName());
			Log.d("Getview","Passed5");
		}
		return view;
	}

	private static class DrawerItemHolder {
		TextView ItemName, title;
		ImageView titleIcon;
		ImageView icon;
		LinearLayout headerLayout, itemLayout, spinnerLayout;
		ImageView userImage;
		TextView  name,email,company;
		ListView spinner;
	}
}