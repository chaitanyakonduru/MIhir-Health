package com.mms.mpc.model;

public class GetPatientDetailsResponse {

	private Patient patient;
	private String getPatientRespMsg;
	private static GetPatientDetailsResponse detailsResponse;
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getGetPatientRespMsg() {
		return getPatientRespMsg;
	}
	public void setGetPatientRespMsg(String getPatientRespMsg) {
		this.getPatientRespMsg = getPatientRespMsg;
	}
	
	public static GetPatientDetailsResponse getInstance()
	{
		return detailsResponse==null?detailsResponse=new GetPatientDetailsResponse():detailsResponse;
	}
	

}
