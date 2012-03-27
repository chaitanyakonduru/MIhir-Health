package com.mms.mpc.model;

import com.mms.mpc.custom.Constants;

public class AuthenticateResponse {

	
	private static AuthenticateResponse authenticate;
	private int mUserType=Constants.INVALIDUSER;
	private String mErrMsg;
	private String mProductName;
	private Doctor mdoctor;
	private PatientResponse patientResponse,companionResponse;
	private MMSStaff mmsStaff;
	private HospitalStaff hospitalStaff;
	
//	public AuthenticateResponse(int mUserType, String mAuthMsg, String mProductName,
//			Doctor mdoctor, PatientResponse patientResponse, MMSStaff mmsStaff,
//			PatientResponse companionResponse, HospitalStaff hospitalStaff) {
//		super();
//		this.mUserType = mUserType;
//		this.mAuthMsg = mAuthMsg;
//		this.mProductName = mProductName;
//		this.mdoctor = mdoctor;
//		this.patientResponse = patientResponse;
//		this.mmsStaff = mmsStaff;
//		this.companionResponse = companionResponse;
//		this.hospitalStaff = hospitalStaff;
//	}
	
	
	public void setmUserType(int mUserType) {
		this.mUserType = mUserType;
	}
	public void setmAuthMsg(String mAuthMsg) {
		this.mErrMsg = mAuthMsg;
	}
	public void setmProductName(String mProductName) {
		this.mProductName = mProductName;
	}
	public void setMdoctor(Doctor mdoctor) {
		this.mdoctor = mdoctor;
	}
	public void setPatient(PatientResponse patientResponse) {
		this.patientResponse = patientResponse;
	}
	public void setMmsStaff(MMSStaff mmsStaff) {
		this.mmsStaff = mmsStaff;
	}
	public void setCompanion(PatientResponse companionResponse) {
		this.companionResponse = companionResponse;
	}
	public void setHospitalStaff(HospitalStaff hospitalStaff) {
		this.hospitalStaff = hospitalStaff;
	}
	
	public int getmUserType() {
		return mUserType;
	}
	
	public String getmAuthMsg() {
		return mErrMsg;
	}
	
	public String getmProductName() {
		return mProductName;
	}
	public Doctor getMdoctor() {
		return mdoctor;
	}
	
	public PatientResponse getPatient() {
		return patientResponse;
	}
	
	public MMSStaff getMmsStaff() {
		return mmsStaff;
	}
	
	public PatientResponse getCompanion() {
		return companionResponse;
	}
	
	public HospitalStaff getHospitalStaff() {
		return hospitalStaff;
	}
	
	public static AuthenticateResponse getInstance()
	{
		return authenticate==null?authenticate=new AuthenticateResponse():authenticate;
	}

	
	
}
