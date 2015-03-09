package com.procialize.customClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class DataWrapper implements Serializable {

	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<UserProfile> myUserData;

	   public DataWrapper(ArrayList<UserProfile> data) {
	      this.myUserData = data;
	   }

	   public ArrayList<UserProfile> getUserData() {
	      return this.myUserData;
	   }

	}