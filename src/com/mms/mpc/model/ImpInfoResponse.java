package com.mms.mpc.model;

public class ImpInfoResponse {
	public BillingParticualrs getBillparticulars() {
		return billparticulars;
	}
	public void setBillparticulars(BillingParticualrs billparticulars) {
		this.billparticulars = billparticulars;
	}
	public DoctorContacts getDoctorContacts() {
		return doctorContacts;
	}
	public void setDoctorContacts(DoctorContacts doctorContacts) {
		this.doctorContacts = doctorContacts;
	}
	public HospitalContacts getHospitalContacts() {
		return hospitalContacts;
	}
	public void setHospitalContacts(HospitalContacts hospitalContacts) {
		this.hospitalContacts = hospitalContacts;
	}
	public PatientContacts getPatientContacts() {
		return patientContacts;
	}
	public void setPatientContacts(PatientContacts patientContacts) {
		this.patientContacts = patientContacts;
	}
	public static ImpInfoResponse impInfoResponse;
	private BillingParticualrs billparticulars;
	private DoctorContacts doctorContacts;
	private HospitalContacts hospitalContacts;
	private PatientContacts patientContacts;
	private String responseMsg;
	public String getResponseMsg() {
		return responseMsg;
	}
	
	public static ImpInfoResponse getInstance()
	{
		return impInfoResponse==null?impInfoResponse=new ImpInfoResponse():impInfoResponse;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	
	

}
