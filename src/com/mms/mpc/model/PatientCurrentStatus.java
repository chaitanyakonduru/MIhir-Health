package com.mms.mpc.model;


public class PatientCurrentStatus {

	private String departmentID, departmentName, wardID, wardName, bedID,
			bedName, bedType, roomNumber,roomType, updatingDoctor, proposedDischarage;
	private LatestDiagnosis latestDiagnosis;
	private String mInstructions;
	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}


	public String getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getWardID() {
		return wardID;
	}

	public void setWardID(String wardID) {
		this.wardID = wardID;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getBedID() {
		return bedID;
	}

	public void setBedID(String bedID) {
		this.bedID = bedID;
	}

	public String getBedName() {
		return bedName;
	}

	public void setBedName(String bedName) {
		this.bedName = bedName;
	}

	public String getBedType() {
		return bedType;
	}

	public void setBedType(String bedType) {
		this.bedType = bedType;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getUpdatingDoctor() {
		return updatingDoctor;
	}

	public void setUpdatingDoctor(String updatingDoctor) {
		this.updatingDoctor = updatingDoctor;
	}

	public String getProposedDischarage() {
		return proposedDischarage;
	}

	public void setProposedDischarage(String proposedDischarage) {
		this.proposedDischarage = proposedDischarage;
	}

	public LatestDiagnosis getLatestDiagnosis() {
		return latestDiagnosis;
	}

	public void setLatestDiagnosis(LatestDiagnosis latestDiagnosis) {
		this.latestDiagnosis = latestDiagnosis;
	}
	
	public String getmInstructions() {
		return mInstructions;
	}
	public void setmInstructions(String mInstructions) {
		this.mInstructions = mInstructions;
	}

}
