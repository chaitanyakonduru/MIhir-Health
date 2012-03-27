package com.mms.mpc.model;

public class PatientCompanion {

	private String mCompanion_Id;
	private String mCompanion_Name;
	private String mCompanion_Relation;
	private static PatientCompanion companion;
	
	public static PatientCompanion getInstance()
	{
	return companion==null?companion=new PatientCompanion():companion;	
	}
	public PatientCompanion()
	{
		
	}
	public void setmCompanion_Id(String mCompanionId) {
		mCompanion_Id = mCompanionId;
	}

	public void setmCompanion_Name(String mCompanionName) {
		mCompanion_Name = mCompanionName;
	}

	public void setmCompanion_Relation(String mCompanionRelation) {
		mCompanion_Relation = mCompanionRelation;
	}


	public PatientCompanion(String mCompanionId, String mCompanionName,
			String mCompanionRelation) {
		super();
		mCompanion_Id = mCompanionId;
		mCompanion_Name = mCompanionName;
		mCompanion_Relation = mCompanionRelation;
	}

	public String getmCompanion_Id() {
		return mCompanion_Id;
	}

	public String getmCompanion_Name() {
		return mCompanion_Name;
	}

	public String getmCompanion_Relation() {
		return mCompanion_Relation;
	}

}
