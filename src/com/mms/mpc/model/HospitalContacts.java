package com.mms.mpc.model;

public class HospitalContacts {

	private String hosipital1="",hosipital2="",hosipitalEmergency1="",hosipitalEmergency2="",ambulance1="",ambulance2="",billingContact="",complaintsContact="";
	public static HospitalContacts hospitalContacts;
	
	public static HospitalContacts getInstance()
	{
		return hospitalContacts==null?hospitalContacts=new HospitalContacts():hospitalContacts;
	}

	public String getHosipital1() {
		return hosipital1;
	}

	public void setHosipital1(String hosipital1) {
		this.hosipital1 = hosipital1;
	}

	public String getHosipital2() {
		return hosipital2;
	}

	public void setHosipital2(String hosipital2) {
		this.hosipital2 = hosipital2;
	}

	public String getHosipitalEmergency1() {
		return hosipitalEmergency1;
	}

	public void setHosipitalEmergency1(String hosipitalEmergency1) {
		this.hosipitalEmergency1 = hosipitalEmergency1;
	}

	public String getHosipitalEmergency2() {
		return hosipitalEmergency2;
	}

	public void setHosipitalEmergency2(String hosipitalEmergency2) {
		this.hosipitalEmergency2 = hosipitalEmergency2;
	}

	public String getAmbulance1() {
		return ambulance1;
	}

	public void setAmbulance1(String ambulance1) {
		this.ambulance1 = ambulance1;
	}

	public String getAmbulance2() {
		return ambulance2;
	}

	public void setAmbulance2(String ambulance2) {
		this.ambulance2 = ambulance2;
	}

	public String getBillingContact() {
		return billingContact;
	}

	public void setBillingContact(String billingContact) {
		this.billingContact = billingContact;
	}

	public String getComplaintsContact() {
		return complaintsContact;
	}

	public void setComplaintsContact(String complaintsContact) {
		this.complaintsContact = complaintsContact;
	}
	
}