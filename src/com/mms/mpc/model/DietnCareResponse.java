package com.mms.mpc.model;

import java.util.List;

public class DietnCareResponse {
	
	
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
	public List<DietnCare> getDietList() {
		return dietList;
	}
	public void setDietList(List<DietnCare> dietList) {
		this.dietList = dietList;
	}
	public List<DietnCare> getCareList() {
		return careList;
	}
	public void setCareList(List<DietnCare> careList) {
		this.careList = careList;
	}
	private String patientName;
	private String hospitalName;
	private List<DietnCare> dietList;
	private List<DietnCare> careList;
	private String errorMsg;
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	

}
