package com.mms.mpc.model;

public class HospitalStaff {

	private static final String DELIM_COMMA = ",";
	public static StringBuilder builder = new StringBuilder();
	
	private String mHospital_Staff_ID;
	private String mHospital_Staff_Name;
	private String mHospital_ID;
	private String mHospital_Name;
	public static HospitalStaff hospitalStaff;
	
	public HospitalStaff()
	{
		
	}
	
	public static HospitalStaff getInstance()
	{
		return hospitalStaff==null?hospitalStaff=new HospitalStaff():hospitalStaff;
	}
	public HospitalStaff(String mHospitalStaffID, String mHospitalStaffName,
			String mHospitalID, String mHospitalName) {
		super();
		mHospital_Staff_ID = mHospitalStaffID;
		mHospital_Staff_Name = mHospitalStaffName;
		mHospital_ID = mHospitalID;
		mHospital_Name = mHospitalName;
		catchData();
	}
	
	private void catchData() {
		builder.append(mHospital_Staff_ID);
		builder.append(DELIM_COMMA);
		
		builder.append(mHospital_Staff_Name);
		builder.append(DELIM_COMMA);
		
		builder.append(mHospital_ID);
		builder.append(DELIM_COMMA);
		
		builder.append(mHospital_Name);
		builder.append(DELIM_COMMA);
	}

	public void setmHospital_Staff_ID(String mHospitalStaffID) {
		mHospital_Staff_ID = mHospitalStaffID;
	}
	public void setmHospital_Staff_Name(String mHospitalStaffName) {
		mHospital_Staff_Name = mHospitalStaffName;
	}
	public void setmHospital_ID(String mHospitalID) {
		mHospital_ID = mHospitalID;
	}
	public void setmHospital_Name(String mHospitalName) {
		mHospital_Name = mHospitalName;
	}
	
	public String getmHospital_Staff_ID() {
		return mHospital_Staff_ID;
	}
	public String getmHospital_Staff_Name() {
		return mHospital_Staff_Name;
	}
	public String getmHospital_ID() {
		return mHospital_ID;
	}
	public String getmHospital_Name() {
		return mHospital_Name;
	}
	
	

}
