package com.mms.mpc.model;

import java.util.Collections;
import java.util.List;

public class Doctor {

	private static Doctor doctor;
	private String mDoctor_ID;
	private String mDoctor_Name;
	private List<Patient> patientList;

	public Doctor(String mDoctorID, String mDoctorName,
			List<Patient> patientList) {
		super();
		mDoctor_ID = mDoctorID;
		mDoctor_Name = mDoctorName;
		this.patientList = patientList;
	}

	public Doctor() {

	}

	public static Doctor getInstance() {
		return doctor == null ? doctor = new Doctor() : doctor;
	}

	public void setmDoctor_ID(String mDoctorID) {
		mDoctor_ID = mDoctorID;
	}

	public void setmDoctor_Name(String mDoctorName) {
		mDoctor_Name = mDoctorName;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}

	public String getmDoctor_ID() {
		return mDoctor_ID;
	}

	public String getmDoctor_Name() {
		return mDoctor_Name;
	}

	public List<Patient> getPatientList() {
		return Collections.unmodifiableList(patientList);
	}

}
