package com.mms.mpc.model;

public class MMSStaff {
	
	private String mStaff_Name;
	private String mStaff_ID;
	public static MMSStaff mmsStaff;

	public void setmStaff_Name(String mStaffName) {
		mStaff_Name = mStaffName;
	}
	public void setmStaff_ID(String mStaffID) {
		mStaff_ID = mStaffID;
	}
	
	public MMSStaff()
	{
		
	}
	
	public static MMSStaff getInstance()
	{
		return mmsStaff==null?mmsStaff=new MMSStaff():mmsStaff;
	}
	public String getmStaff_Name() {
		return mStaff_Name;
	}
	public String getmStaff_ID() {
		return mStaff_ID;
	}
	
	
}
