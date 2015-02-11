package com.procialize.customClasses;

public class SpinnerItem {

	int drawableResID;
	String name;
	String email;
	String company;

	public SpinnerItem(int drawableResID, String name, String email, String company) {
		super();
		this.drawableResID = drawableResID;
		this.name = name;
		this.email = email;
		this.company = company;
	}
	
	public int getDrawableResID() {
		return drawableResID;
	}

	public void setDrawableResID(int drawableResID) {
		this.drawableResID = drawableResID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	
}
