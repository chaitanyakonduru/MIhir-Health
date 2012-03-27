package com.mms.mpc.model;

import java.util.Collections;
import java.util.List;

public class PrescriptionResponse {
	
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public List<Prescription> getPrescriptionList() {
		return Collections.unmodifiableList(prescriptionList);
	}
	public void setPrescriptionList(List<Prescription> prescriptionList) {
		this.prescriptionList = prescriptionList;
	}
	
	private String patientName;
	private String hospitalName;
	private  String errormsg;
	
	public  String getErrormsg() {
		return errormsg;
	}
	public  void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	private List<Prescription> prescriptionList;
	private static PrescriptionResponse prescriptionResp;
	
	public static PrescriptionResponse getInstance()
	{
		return prescriptionResp==null?prescriptionResp=new PrescriptionResponse():prescriptionResp;
	}
	
	

}
