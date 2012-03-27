package com.mms.mpc.model;

public class PatientResponse {
	private static PatientResponse patientResponse;
	private Patient patient;
	private PatientCompanion companion;
	
	
	public PatientResponse()
	{
	
	}
	
	public static PatientResponse getInstance()
	{
		return patientResponse==null?patientResponse=new PatientResponse():patientResponse;
	}
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public PatientCompanion getCompanion() {
		return companion;
	}
	public void setCompanion(PatientCompanion companion) {
		this.companion = companion;
	}
	 
	
	

}
