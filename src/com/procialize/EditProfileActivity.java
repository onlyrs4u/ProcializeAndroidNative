package com.procialize;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

public class EditProfileActivity extends Activity implements OnClickListener{
	
	private Button chooseFileBtn;
	private ImageView selected_Image;
	private AutoCompleteTextView industry_dropdown;
	private static int RESULT_LOAD_IMAGE = 1;
	
	static final String[] WORLDCUP2010 = new String[] {
		"Algeria",  "Argentina", "Australia", 
		"Brazil", "Cote d'Ivoire", "Cameroon", 
		"Chile", "Costa Rica", "Denmark", 
		"England", "France", "Germany",
		"Ghana",  "Greece", "Honduras",
		"Italy",  "Japan", "Netherlands",
		"New Zealand", "Nigeria", "North Korea",
		"Paraguay", "Portugal","Serbia",
		"Slovakia", "Slovenia", "South Africa",  
		"South Korea",  "Spain", "Switzerland",    
		"United States", "Uruguay" };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_profile_screen);
		
		//String[] countries = getResources().  getStringArray(R.array.list_of_countries);
		//ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,countries);
		
		//Declaring elements
		chooseFileBtn = (Button)findViewById(R.id.choose_file_button);
		
		//Applying listener to the elements
		chooseFileBtn.setOnClickListener(this);
		
		industry_dropdown = (AutoCompleteTextView) findViewById(R.id.industry_dropdown);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, WORLDCUP2010);
		industry_dropdown.setAdapter(adapter);
		
	}
		

		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			if(view == chooseFileBtn)
			{
				Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 
                startActivityForResult(i, RESULT_LOAD_IMAGE);
			}
		}
		
		@Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	         
	        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	            Uri selectedImage = data.getData();
	            String[] filePathColumn = { MediaStore.Images.Media.DATA };
	 
	            Cursor cursor = getContentResolver().query(selectedImage,
	                    filePathColumn, null, null, null);
	            cursor.moveToFirst();
	 
	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String picturePath = cursor.getString(columnIndex);
	            cursor.close();
	             
	            //ImageView imageView = (ImageView) findViewById(R.id.imgView);
	            selected_Image = (ImageView) findViewById(R.id.selected_image);
	            selected_Image.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	         
	        }
	     
	     
	    }

}
