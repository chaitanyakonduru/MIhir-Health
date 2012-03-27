package com.mms.mpc.model;

import java.util.Collections;
import java.util.List;

public class TestsnProcedureResponse {
	
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public List<TestsProc> getTestsnProcedureslist() {
		return Collections.unmodifiableList(testsProcedureslist);
	}
	public void setTestsnProcedureslist(List<TestsProc> testslist) {
		this.testsProcedureslist = testslist;
	}
/*	public List<TestsProc> getProclist() {
		return proclist;
	}
	public void setProclist(List<TestsProc> proclist) {
		this.proclist = proclist;
	}*/
	public static TestsnProcedureResponse testsnProcedureResp;
	private String PatientName;
	private String hospitalName;
	private List<TestsProc> testsProcedureslist;
//	private List<TestsProc> proclist;
	private String errorMsg;
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public static TestsnProcedureResponse getInstance()
	{
		return testsnProcedureResp==null?testsnProcedureResp=new TestsnProcedureResponse():testsnProcedureResp;
	}
}
