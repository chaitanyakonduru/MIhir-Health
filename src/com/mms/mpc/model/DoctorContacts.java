package com.mms.mpc.model;

public class DoctorContacts {
	private DoctorDetails primaryDoctor;
	private DoctorDetails referncingDoctor;
	public static DoctorContacts doctorContacts;
	
	public static DoctorContacts getInstance()
	{
		return doctorContacts==null?doctorContacts=new DoctorContacts():doctorContacts;
	}
	public DoctorDetails getPrimaryDoctor() {
		return primaryDoctor;
	}
	public void setPrimaryDoctor(DoctorDetails primaryDoctor) {
		this.primaryDoctor = primaryDoctor;
	}
	public DoctorDetails getReferncingDoctor() {
		return referncingDoctor;
	}
	public void setReferncingDoctor(DoctorDetails referncingDoctor) {
		this.referncingDoctor = referncingDoctor;
	}

	public static class DoctorDetails
	{
		private String doctorName="",contact="";

		public String getDoctorName() {
			return doctorName;
		}

		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}

		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
		}
		
	}
}
