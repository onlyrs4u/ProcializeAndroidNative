package com.procialize.customClasses;

import java.io.Serializable;

public class UserProfile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int drawableResID;
	
	private String profile_attendee_id = "";
	private String api_access_token = "";
	private String email = "";
	private String status = "";
	private String password = "";
	private String gcm_reg_id = "";
	private String mobile_os = "";
	private String user_ID = "";
	private String first_name = "";
	private String last_name = "";
	private String linkedin_= "";
	private String facebook = "";
	private String company_name = "";
	private String designation = "";
	private String profile_image;
	private String description = "";
	private String city = "";
	private String country = "";
	private String industry = "";
	private String functionality = "";
	private String mobile_number;
	private String phone_number;
	
	public UserProfile(int drawableResID, String fname, String lname, String designation, String company) {
		super();
		this.drawableResID = drawableResID;
		this.first_name = fname;
		this.last_name = lname;
		this.designation = designation;
		this.company_name = company;
	}
	
	public UserProfile() {
		// TODO Auto-generated constructor stub
	}

	public String getProfile_attendee_id() {
		return profile_attendee_id;
	}
	public void setProfile_attendee_id(String profile_attendee_id) {
		this.profile_attendee_id = profile_attendee_id;
	}
	public String getApi_access_token() {
		return api_access_token;
	}
	public void setApi_access_token(String api_access_token) {
		this.api_access_token = api_access_token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGcm_reg_id() {
		return gcm_reg_id;
	}
	public void setGcm_reg_id(String gcm_reg_id) {
		this.gcm_reg_id = gcm_reg_id;
	}
	public String getMobile_os() {
		return mobile_os;
	}
	public void setMobile_os(String mobile_os) {
		this.mobile_os = mobile_os;
	}
	public String getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getLinkedin_() {
		return linkedin_;
	}
	public void setLinkedin_(String linkedin_) {
		this.linkedin_ = linkedin_;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getProfile_image() {
		return profile_image;
	}
	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getFunctionality() {
		return functionality;
	}
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	
	
}
