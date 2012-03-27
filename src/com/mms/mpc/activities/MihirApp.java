package com.mms.mpc.activities;

import android.app.Application;

import com.mms.mpc.model.Patient;

public final class MihirApp extends Application {
	
	
	private Patient curPatientInfo;
	private Object currentUser;
	
	public Object getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Object currentUser) {
		this.currentUser = currentUser;
	}

	private boolean isloggedin=false;
	private static MihirApp app;
	public boolean isIsloggedin() {
		return isloggedin;
	}

	public static MihirApp getInstance()
	{
		return app==null?app=new MihirApp():app;
	}
	
	public void setIsloggedin(boolean isloggedin) {
		
		this.isloggedin = isloggedin;
	}

	public  Patient getCurPatient() {
		return curPatientInfo;
	}

	public void setCurPatient( Patient patient) {
		curPatientInfo = patient;
	}
	
	
	

}
