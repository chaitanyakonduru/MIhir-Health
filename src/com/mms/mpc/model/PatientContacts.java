package com.mms.mpc.model;

public class PatientContacts {
	private String patientEmergency1="",patientEmergency2="";
	public static PatientContacts patientContacts;
	
	public static PatientContacts getInstance()
	{
		return patientContacts==null?patientContacts=new PatientContacts():patientContacts;
	}
	public String getPatientEmergency1() {
		return patientEmergency1;
	}

	public void setPatientEmergency1(String patientEmergency1) {
		this.patientEmergency1 = patientEmergency1;
	}

	public String getPatientEmergency2() {
		return patientEmergency2;
	}

	public void setPatientEmergency2(String patientEmergency2) {
		this.patientEmergency2 = patientEmergency2;
	}
}