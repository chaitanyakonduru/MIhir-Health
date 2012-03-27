package com.mms.mpc.model;

import java.util.Collections;
import java.util.List;

public class DoctorVisitsResponse {

	private static DoctorVisitsResponse doctorVisitsResp;
	private String mPatientName;
	private String mHospitalName;
	private List<VisitingDoctor> visitDoctorList;
	private String doctorVisitResponseMsg="";
	
	public static DoctorVisitsResponse getInstance()
	{
		return doctorVisitsResp==null?doctorVisitsResp=new DoctorVisitsResponse():doctorVisitsResp;
	}

	public String getDoctorVisitResponseMsg() {
		return doctorVisitResponseMsg;
	}
	public void setDoctorVisitResponseMsg(String doctorVisitResponseMsg) {
		this.doctorVisitResponseMsg = doctorVisitResponseMsg;
	}
	public String getmPatientName() {
		return mPatientName;
	}
	public void setmPatientName(String mPatientName) {
		this.mPatientName = mPatientName;
	}
	public String getmHospitalName() {
		return mHospitalName;
	}
	public void setmHospitalName(String mHospitalName) {
		this.mHospitalName = mHospitalName;
	}
	public List<VisitingDoctor> getVisitDoctorList() {
		return Collections.unmodifiableList(visitDoctorList);
	}
	public void setVisitDoctorList(List<VisitingDoctor> visitDoctorList) {
		this.visitDoctorList = visitDoctorList;
	}
	public static DoctorVisitsResponse getDoctorVisitsResp() {
		return doctorVisitsResp;
	}
	public static void setDoctorVisitsResp(DoctorVisitsResponse doctorVisitsResp) {
		DoctorVisitsResponse.doctorVisitsResp = doctorVisitsResp;
	}
	
}
