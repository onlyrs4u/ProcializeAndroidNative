package com.procialize.customClasses;

public class DrawerItem {

	String ItemName;
	int imgResID;
	String title;
	int titleImgResId;
	
	boolean isSpinner;

	public DrawerItem(int titleImgResId, String title) {
		this.titleImgResId = titleImgResId;
		this.title = title;
	}
	
	public DrawerItem(String itemName, int imgResID) {
		ItemName = itemName;
		this.imgResID = imgResID;
	}

	public DrawerItem(boolean isSpinner) {
		this(null, 0);
		this.isSpinner = isSpinner;
	}

	public DrawerItem(String title) {
		this(null, 0);
		this.title = title;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public int getImgResID() {
		return imgResID;
	}

	public void setImgResID(int imgResID) {
		this.imgResID = imgResID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTitleImgResId() {
		return titleImgResId;
	}

	public void setTitleImgResId(int titleImgResId) {
		this.titleImgResId = titleImgResId;
	}

	public boolean isSpinner() {
		return isSpinner;
	}

	
}
