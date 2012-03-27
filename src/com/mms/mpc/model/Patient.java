private private package com.mms.mpc.model;

public class Patient {

	private static Patient patient;
	

	private String mPatient_ID;
	private String mpatient_HistoryId;
	private String mPatient_Name;
	private int mHospital_ID;
	private String mHospital_Logo;
	private String mHospital_Name;
	private int mHospital_Admission_Number;
	private String mAdmission_Date;
	private String mDischarge_Date;

	public static void setPatient(Patient patient) {
		Patient.patient = patient;
	}

	public void setmPatient_ID(String mPatientID) {
		mPatient_ID = mPatientID;
	}

	public void setPatient_HistoryId(String patientHistoryId) {
		mpatient_HistoryId = patientHistoryId;
	}

	public void setmPatient_Name(String mPatientName) {
		mPatient_Name = mPatientName;
	}

	public void setmHospital_ID(int mHospitalID) {
		mHospital_ID = mHospitalID;
	}

	public String getmHospital_Logo() {
		return mHospital_Logo;
	}
	
	public void setmHospital_Logo(String mHospital_Logo) {
		this.mHospital_Logo = mHospital_Logo;
	}
	
	public void setmHospital_Name(String mHospitalName) {
		mHospital_Name = mHospitalName;
	}

	public void setmHospital_Admission_Number(int mHospitalAdmissionNumber) {
		mHospital_Admission_Number = mHospitalAdmissionNumber;
	}

	public void setmAdmission_Date(String mAdmissionDate) {
		mAdmission_Date = mAdmissionDate;
	}

	public void setmDischarge_Date(String mDischargeDate) {
		mDischarge_Date = mDischargeDate;
	}
	
	public String getmPatient_ID() {
		return mPatient_ID;
	}

	public String getPatient_HistoryId() {
		return mpatient_HistoryId;
	}
	public String getmPatient_Name() {
		return mPatient_Name;
	}

	public int getmHospital_ID() {
		return mHospital_ID;
	}

	public String getmHospital_Name() {
		return mHospital_Name;
	}

	public int getmHospital_Admission_Number() {
		return mHospital_Admission_Number;
	}

	public String getmAdmission_Date() {
		return mAdmission_Date;
	}

	public String getmDischarge_Date() {
		return mDischarge_Date;
	}

	public String toString() {
		return mPatient_Name;
	}
	
	public Patient()
	{
		
	}
	
	public static Patient getInstance()
	{
		return patient==null?patient=new Patient():patient;
	}

}
