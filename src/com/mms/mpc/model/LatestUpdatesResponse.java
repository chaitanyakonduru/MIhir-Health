package com.mms.mpc.model;


public class LatestUpdatesResponse {
	public static LatestUpdatesResponse latestUpdatesResponse;
	private String patientName;
	private String hosipitalName;
	private PatientCurrentStatus patientCurrentStatus;
	private String latestRespMSG="";
	public String getPatientName() {
		return patientName;
	}
	
	
	public static LatestUpdatesResponse getInstance()
	{
		return latestUpdatesResponse==null?latestUpdatesResponse=new LatestUpdatesResponse():latestUpdatesResponse;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getHosipitalName() {
		return hosipitalName;
	}
	public void setHosipitalName(String hosipitalName) {
		this.hosipitalName = hosipitalName;
	}
	public PatientCurrentStatus getPatientCurrentStatus() {
		return patientCurrentStatus;
	}
	public void setPatientCurrentStatus(
			PatientCurrentStatus patientCurrentStatus) {
		this.patientCurrentStatus = patientCurrentStatus;
	}
	public String getLatestRespMSG() {
		return latestRespMSG;
	}
	public void setLatestRespMSG(String latestRespMSG) {
		this.latestRespMSG = latestRespMSG;
	}
	
	
}
