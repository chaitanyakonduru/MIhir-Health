package com.mms.mpc.network;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.util.Log;

import com.mms.mpc.custom.Constants;
import com.mms.mpc.model.AuthenticateResponse;
import com.mms.mpc.model.BillingParticualrs;
import com.mms.mpc.model.DietnCare;
import com.mms.mpc.model.DietnCareResponse;
import com.mms.mpc.model.Doctor;
import com.mms.mpc.model.DoctorContacts;
import com.mms.mpc.model.DoctorVisitsResponse;
import com.mms.mpc.model.GetPatientDetailsResponse;
import com.mms.mpc.model.HospitalContacts;
import com.mms.mpc.model.HospitalStaff;
import com.mms.mpc.model.ImpInfoResponse;
import com.mms.mpc.model.LatestDiagnosis;
import com.mms.mpc.model.LatestUpdatesResponse;
import com.mms.mpc.model.MMSStaff;
import com.mms.mpc.model.Patient;
import com.mms.mpc.model.PatientCompanion;
import com.mms.mpc.model.PatientContacts;
import com.mms.mpc.model.PatientCurrentStatus;
import com.mms.mpc.model.PatientResponse;
import com.mms.mpc.model.PaymentInfo;
import com.mms.mpc.model.Prescription;
import com.mms.mpc.model.PrescriptionResponse;
import com.mms.mpc.model.TestsProc;
import com.mms.mpc.model.TestsnProcedureResponse;
import com.mms.mpc.model.VisitingDoctor;
import com.mms.mpc.model.DoctorContacts.DoctorDetails;

public class Comm {
	
	public static final AuthenticateResponse parseAuthenticate(
			final SoapObject object) {
		AuthenticateResponse authenticate = AuthenticateResponse.getInstance();
		Doctor doctor = Doctor.getInstance();
		List<Patient> listPatient = null;
		MMSStaff mmsStaff = MMSStaff.getInstance();
		HospitalStaff hospitalStaff = HospitalStaff.getInstance();
		
		if (object.hasProperty("Authentication_Message")) {
			authenticate.setmUserType(Constants.INVALIDUSER);
			authenticate.setmAuthMsg(object
					.getPropertyAsString("Authentication_Message"));
		}
		else
		{
			authenticate.setmAuthMsg("");
		}
		if (object.hasProperty("Product_Name")) {
			authenticate.setmProductName(object
					.getPropertyAsString("Product_Name"));
		}
		if (object.hasProperty("Doctor_Response")) {
			authenticate.setmUserType(Constants.DOCTOR);
			SoapObject doctorObj = (SoapObject) object
					.getProperty("Doctor_Response");
			if (doctorObj.hasProperty("Doctor_ID")) {
				doctor
						.setmDoctor_ID(doctorObj
								.getPropertyAsString("Doctor_ID"));
			}
			if (doctorObj.hasProperty("Doctor_Name")) {
				doctor.setmDoctor_Name(doctorObj
						.getPropertyAsString("Doctor_Name"));
			}
			if (doctorObj.hasProperty("PatientList")) {
				
				SoapObject patientsObj = (SoapObject) doctorObj
						.getProperty("PatientList");
				int patientCount = patientsObj.getPropertyCount();
				listPatient = new ArrayList<Patient>();
				for (int i = 0; i < patientCount; i++) {
					SoapObject patientObj = (SoapObject) patientsObj
							.getProperty(i);
					listPatient.add(getPatient(patientObj));
				}
				doctor.setPatientList(listPatient);
			}
			authenticate.setMdoctor(doctor);
		}

		if (object.hasProperty("Patient_Response")) {
			authenticate.setmUserType(Constants.PATIENT);
			SoapObject patientResposneObj = (SoapObject) object
					.getProperty("Patient_Response");
			authenticate.setPatient(getPatientResponse(patientResposneObj));
		}
		if (object.hasProperty("Companion_Response")) {
			authenticate.setmUserType(Constants.PATIENTCOMPANION);
			SoapObject companionResponseObj = (SoapObject) object
					.getProperty("Companion_Response");
			authenticate.setCompanion(getPatientResponse(companionResponseObj));
		}
		if (object.hasProperty("HospitalStaff_Response")) {
			authenticate.setmUserType(Constants.HOSPITALSTAFF);
			SoapObject hospitalResponse = (SoapObject) object
					.getProperty("HospitalStaff_Response");

			if (hospitalResponse.hasProperty("Hospital_Staff_ID")) {
				hospitalStaff.setmHospital_Staff_ID(hospitalResponse
						.getPropertyAsString("Hospital_Staff_ID"));
			}
			if (hospitalResponse.hasProperty("Hospital_Staff_Name")) {
				hospitalStaff.setmHospital_Staff_Name(hospitalResponse
						.getPropertyAsString("Hospital_Staff_Name"));
			}
			if (hospitalResponse.hasProperty("Hospital_ID")) {
				hospitalStaff.setmHospital_ID(hospitalResponse
						.getPropertyAsString("Hospital_ID"));
			}

			if (hospitalResponse.hasProperty("Hospital_ID")) {
				hospitalStaff.setmHospital_ID(hospitalResponse
						.getPropertyAsString("Hospital_ID"));
			}
			
			if (hospitalResponse.hasProperty("Hospital_Name")) {
				hospitalStaff.setmHospital_Name(hospitalResponse
						.getPropertyAsString("Hospital_Name"));
			}

			authenticate.setHospitalStaff(hospitalStaff);
		}
		if (object.hasProperty("MMS_Staff_Response")) {
			authenticate.setmUserType(Constants.MMSSTAFF);
			SoapObject mmsstaffResp = (SoapObject) object
					.getProperty("MMS_Staff_Response");
			if (mmsstaffResp.hasProperty("MMS_Staff_Name")) {
				mmsStaff.setmStaff_Name(mmsstaffResp
						.getPropertyAsString("MMS_Staff_Name"));
			}
			if (mmsstaffResp.hasProperty("MMS_Staff_ID")) {
				mmsStaff.setmStaff_ID(mmsstaffResp
						.getPropertyAsString("MMS_Staff_ID"));
			}
			authenticate.setMmsStaff(mmsStaff);

		}

		return authenticate;
	}

	private static PatientResponse getPatientResponse(
			final SoapObject patientRespobject) {

		PatientResponse patientResponse = PatientResponse.getInstance();
		if (patientRespobject.hasProperty("Patient")) {
			SoapObject patientObj = (SoapObject) patientRespobject
					.getProperty("Patient");
			patientResponse.setPatient(getPatient(patientObj));
		}
		if (patientRespobject.hasProperty("Patient_Companion")) {
			PatientCompanion companion = PatientCompanion.getInstance();
			SoapObject companionObj = (SoapObject) patientRespobject
					.getProperty("Patient_Companion");
			if (companionObj.hasProperty("Companion_ID")) {
				companion.setmCompanion_Id(companionObj
						.getPropertyAsString("Companion_ID"));
			}
			if (companionObj.hasProperty("Companion_Name")) {
				companion.setmCompanion_Name(companionObj
						.getPropertyAsString("Companion_Name"));
			}
			if (companionObj.hasProperty("Companion_Relation")) {
				companion.setmCompanion_Relation(companionObj
						.getPropertyAsString("Companion_Relation"));
			}
			patientResponse.setCompanion(companion);

		}
		return patientResponse;
	}

	public static DoctorVisitsResponse parseVisitResponse(SoapObject object) {
		DoctorVisitsResponse doctorVisitsResp = DoctorVisitsResponse.getInstance();
		VisitingDoctor visitingDoctor;
		List<VisitingDoctor> visitList;
		if (object.hasProperty("PatientName")) {
			doctorVisitsResp.setmPatientName(object
					.getPropertyAsString("PatientName"));
		}
		if (object.hasProperty("HospitalName")) {
			doctorVisitsResp.setmHospitalName(object
					.getPropertyAsString("HospitalName"));
		}

		if (object.hasProperty("VisitsList")) {
			SoapObject visitListSoapObj = (SoapObject) object
					.getProperty("VisitsList");
			int visitsCount = visitListSoapObj.getPropertyCount();
			visitList = new ArrayList<VisitingDoctor>();
			for (int i = 0; i < visitsCount; i++) {
				visitingDoctor = new VisitingDoctor();
				SoapObject visitingDoctorSoapObj = (SoapObject) visitListSoapObj
						.getProperty(i);

				if (visitingDoctorSoapObj.hasProperty("DoctorName")) {
					visitingDoctor.setmDoctorName(visitingDoctorSoapObj
							.getPropertyAsString("DoctorName"));
				}
				if (visitingDoctorSoapObj.hasProperty("VisitDateandTime")) {
					visitingDoctor.setmVisitDateandTime(visitingDoctorSoapObj
							.getPropertyAsString("VisitDateandTime"));
				}
				if (visitingDoctorSoapObj.hasProperty("visitNotes")) {
					visitingDoctor.setMvisitNotes(visitingDoctorSoapObj
							.getPropertyAsString("visitNotes"));
				}
				visitList.add(visitingDoctor);
			}
			doctorVisitsResp.setVisitDoctorList(visitList);
		}
		if (object.hasProperty("DoctorvisitsRespMSG")) {
			doctorVisitsResp.setDoctorVisitResponseMsg(object
					.getPropertyAsString("DoctorvisitsRespMSG"));
		} else {
			doctorVisitsResp.setDoctorVisitResponseMsg("");
		}

		return doctorVisitsResp;
	}

	public static PrescriptionResponse parsePrescription(SoapObject object) {
		PrescriptionResponse prescriptionResp = PrescriptionResponse.getInstance();
		List<Prescription> prescriptionList;
		if (object.hasProperty("PatientName")) {
			prescriptionResp.setPatientName(object
					.getPropertyAsString("PatientName"));
		}
		if (object.hasProperty("HosipitalName")) {
			prescriptionResp.setHospitalName(object
					.getPropertyAsString("HosipitalName"));
		}
		if (object.hasProperty("PrescriptionsList")) {
			prescriptionList = new ArrayList<Prescription>();
			SoapObject prescriptionListSoapObj = (SoapObject) object
					.getProperty("PrescriptionsList");
			int prescriptionsCount = prescriptionListSoapObj.getPropertyCount();
			for (int i = 0; i < prescriptionsCount; i++) {
				SoapObject prescriptionSoapObj = (SoapObject) prescriptionListSoapObj
						.getProperty(i);
				prescriptionList.add(getPrescription(prescriptionSoapObj));
			}
			prescriptionResp.setErrormsg("");
			prescriptionResp.setPrescriptionList(prescriptionList);
		} 
		if(object.hasProperty("PrescriptionsRespeMSG"))
		{
			prescriptionResp.setErrormsg(object.getPropertyAsString("PrescriptionsRespeMSG"));
		}
		else
		{
			prescriptionResp.setErrormsg("");
		}

		return prescriptionResp;

	}

	private static Prescription getPrescription(SoapObject prescriptionSoapObj) {
		Prescription prescription = new Prescription();
		if (prescriptionSoapObj.hasProperty("OrderedByDoctor")) {
			prescription.setOrderedByDoctor(prescriptionSoapObj
					.getPropertyAsString("OrderedByDoctor"));
		}
		if (prescriptionSoapObj.hasProperty("ValidateByStaff")) {
			prescription.setValidateByStaff(prescriptionSoapObj
					.getPropertyAsString("ValidateByStaff"));
		}
		if (prescriptionSoapObj.hasProperty("Name")) {
			prescription.setName(prescriptionSoapObj
					.getPropertyAsString("Name"));
		}
		if (prescriptionSoapObj.hasProperty("Type")) {
			prescription.setType(prescriptionSoapObj
					.getPropertyAsString("Type"));
		}
		if (prescriptionSoapObj.hasProperty("Size")) {
			prescription.setSize(prescriptionSoapObj
					.getPropertyAsString("Size"));
		}
		if (prescriptionSoapObj.hasProperty("Quantity")) {
			prescription.setQuantity(prescriptionSoapObj
					.getPropertyAsString("Quantity"));
		}
		if (prescriptionSoapObj.hasProperty("Frequency")) {
			prescription.setFrequency(prescriptionSoapObj
					.getPropertyAsString("Frequency"));
		}
		if (prescriptionSoapObj.hasProperty("NoOfDays")) {
			prescription.setNoOfDays(prescriptionSoapObj
					.getPropertyAsString("NoOfDays"));
		}
		if (prescriptionSoapObj.hasProperty("StartTime")) {
			prescription.setStartTime(prescriptionSoapObj
					.getPropertyAsString("StartTime"));
		}
		if (prescriptionSoapObj.hasProperty("Special_Instructions")) {
			prescription.setInstructions(prescriptionSoapObj
					.getPropertyAsString("Special_Instructions"));
		}
		return prescription;
	}

	public static GetPatientDetailsResponse getPatientDetails(SoapObject object) {
		GetPatientDetailsResponse detailsResponse=GetPatientDetailsResponse.getInstance();
		
		if (object.hasProperty("PatientDetails")) {
			SoapObject patientDetails = (SoapObject) object
					.getProperty("PatientDetails");
			if (patientDetails.hasProperty("Patient")) {
				detailsResponse.setPatient(getPatient((SoapObject) patientDetails
						.getProperty("Patient")));
			}
		}
		if(object.hasProperty("GetPatientRespMSG"))
		{
			detailsResponse.setGetPatientRespMsg(object.getPropertyAsString("GetPatientRespMSG"));
		}
		else
		{
			detailsResponse.setGetPatientRespMsg("");
		}
		return detailsResponse;
	}

	public static Patient getPatient(SoapObject patientObj) {
		Patient patient;
		patient = new Patient();
		

		if (patientObj.hasProperty("Patient_ID")) {
			patient
					.setmPatient_ID(patientObj
							.getPropertyAsString("Patient_ID"));
		}
		if (patientObj.hasProperty("Patient_History_ID")) {
			patient.setPatient_HistoryId(patientObj
					.getPropertyAsString("Patient_History_ID"));
		}
		if (patientObj.hasProperty("Patient_Name")) {
			patient.setmPatient_Name(patientObj
					.getPropertyAsString("Patient_Name"));
		}
		if (patientObj.hasProperty("Hosiptal_ID")) {
			patient.setmHospital_ID(Integer.parseInt(patientObj
					.getPropertyAsString("Hosiptal_ID")));
		}
		if (patientObj.hasProperty("Hospital_Logo")) {
			patient.setmHospital_Logo(patientObj
					.getPropertyAsString("Hospital_Logo"));
		}
		if (patientObj.hasProperty("Hospital_Name")) {
			patient.setmHospital_Name(patientObj
					.getPropertyAsString("Hospital_Name"));
		}
		if (patientObj.hasProperty("Hospital_Admission_Number")) {
			patient.setmHospital_Admission_Number(Integer.parseInt(patientObj
					.getPropertyAsString("Hospital_Admission_Number")));
		}
		if (patientObj.hasProperty("Admission_Date")) {
			patient.setmAdmission_Date(patientObj
					.getPropertyAsString("Admission_Date"));
		}
		if (patientObj.hasProperty("Discharge_Date")) {
			patient.setmDischarge_Date(patientObj
					.getPropertyAsString("Discharge_Date"));

		}

		return patient;

	}

	public static final LatestUpdatesResponse parseLatestUpdatesResponse(
			SoapObject object) {
		LatestUpdatesResponse latestUpdatesResponse = LatestUpdatesResponse.getInstance();
		PatientCurrentStatus currentStatus;

		if (object.hasProperty("PatientName")) {
			latestUpdatesResponse.setPatientName(object
					.getPropertyAsString("PatientName"));
		}
		if (object.hasProperty("HosipitalName")) {
			latestUpdatesResponse.setHosipitalName(object
					.getPropertyAsString("HosipitalName"));
		}

		if (object.hasProperty("PatientCurrentStatus")) {
			currentStatus = new PatientCurrentStatus();
			SoapObject patientCurrentStatObject = (SoapObject) object
					.getProperty("PatientCurrentStatus");
			if (patientCurrentStatObject.hasProperty("DepartmentID")) {
				currentStatus.setDepartmentID(patientCurrentStatObject
						.getPropertyAsString("DepartmentID"));
			}
			if (patientCurrentStatObject.hasProperty("DepartmentName")) {
				currentStatus.setDepartmentName(patientCurrentStatObject
						.getPropertyAsString("DepartmentName"));
			}
			if (patientCurrentStatObject.hasProperty("WardID")) {
				currentStatus.setWardID(patientCurrentStatObject
						.getPropertyAsString("WardID"));
			}
			if (patientCurrentStatObject.hasProperty("WardName")) {
				currentStatus.setWardName(patientCurrentStatObject
						.getPropertyAsString("WardName"));
			}

			if (patientCurrentStatObject.hasProperty("BedID")) {
				currentStatus.setBedID(patientCurrentStatObject
						.getPropertyAsString("BedID"));
			}
			if (patientCurrentStatObject.hasProperty("BedName")) {
				currentStatus.setBedName(patientCurrentStatObject
						.getPropertyAsString("BedName"));
			}

			if (patientCurrentStatObject.hasProperty("BedType")) {
				currentStatus.setBedType(patientCurrentStatObject
						.getPropertyAsString("BedType"));
			}
			if (patientCurrentStatObject.hasProperty("RoomType")) {
				currentStatus.setRoomType(patientCurrentStatObject
						.getPropertyAsString("RoomType"));
			}
			if (patientCurrentStatObject.hasProperty("RoomNumber")) {
				currentStatus.setRoomNumber(patientCurrentStatObject
						.getPropertyAsString("RoomNumber"));
			}
			if (patientCurrentStatObject.hasProperty("LatestInstructions")) {
				Log.v("Instruct", ""+patientCurrentStatObject
						.getPropertyAsString("LatestInstructions"));
				currentStatus.setmInstructions(patientCurrentStatObject
						.getPropertyAsString("LatestInstructions"));
			}
			if (patientCurrentStatObject.hasProperty("LatestDiagnosis")) {

				LatestDiagnosis diagnosis = new LatestDiagnosis();
				SoapObject latestDiagnosisSoapObj = (SoapObject) patientCurrentStatObject
						.getProperty("LatestDiagnosis");
				diagnosis = new LatestDiagnosis();
				if (latestDiagnosisSoapObj.hasProperty("Long")) {
					diagnosis.setMlong(latestDiagnosisSoapObj
							.getPropertyAsString("Long"));
				}
				if (latestDiagnosisSoapObj.hasProperty("Short")) {
					diagnosis.setMshort(latestDiagnosisSoapObj
							.getPropertyAsString("Short"));
				}
				currentStatus.setLatestDiagnosis(diagnosis);
			}

			if (patientCurrentStatObject.hasProperty("UpdatingDoctor")) {
				currentStatus.setUpdatingDoctor(patientCurrentStatObject
						.getPropertyAsString("UpdatingDoctor"));
			}
			if (patientCurrentStatObject.hasProperty("ProposedDischarage")) {
				currentStatus.setProposedDischarage(patientCurrentStatObject
						.getPropertyAsString("ProposedDischarage"));
			}

			latestUpdatesResponse.setPatientCurrentStatus(currentStatus);
		}

		if (object.hasProperty("LatestRespMSG")) {
			Log.v("LatestrespMSG", object
					.getPropertyAsString("LatestRespMSG"));
			latestUpdatesResponse.setLatestRespMSG(object
					.getPropertyAsString("LatestRespMSG"));
		}

		return latestUpdatesResponse;
	}

	public static final ImpInfoResponse parseImpInfoResponse(SoapObject object) {
		ImpInfoResponse impInfoResponse = ImpInfoResponse.getInstance();
		if (object.hasProperty("BillParticulars")) {
			SoapObject billingSoapObj = (SoapObject) object
					.getProperty("BillParticulars");
			impInfoResponse
					.setBillparticulars(getBillingPaticulars(billingSoapObj));

		}
		if (object.hasProperty("DoctorContacts")) {
			SoapObject doctorContSoapObj = (SoapObject) object
					.getProperty("DoctorContacts");
			impInfoResponse
					.setDoctorContacts(getDoctorContacts(doctorContSoapObj));
		}
		if (object.hasProperty("HosipitalContacts")) {
			SoapObject hospitalContSoapObj = (SoapObject) object
					.getProperty("HosipitalContacts");
			impInfoResponse
					.setHospitalContacts(getHospitalContacts(hospitalContSoapObj));
		}
		if (object.hasProperty("PatientContacts")) {
			SoapObject patientContSoapObj = (SoapObject) object
					.getProperty("PatientContacts");
			impInfoResponse
					.setPatientContacts(getPatientContacts(patientContSoapObj));

		}
		if (object.hasProperty("ImportantInfoRespeMSG")) {
			impInfoResponse.setResponseMsg(object
					.getPropertyAsString("ImportantInfoRespeMSG"));
		} else {
			impInfoResponse.setResponseMsg("");
		}
		return impInfoResponse;
	}

	private static PatientContacts getPatientContacts(
			SoapObject patientContSoapObj) {
		PatientContacts patientContacts = PatientContacts.getInstance();
		if (patientContSoapObj.hasProperty("PatientEmergency1")) {
			patientContacts.setPatientEmergency1(patientContSoapObj
					.getPropertyAsString("PatientEmergency1"));
		}
		if (patientContSoapObj.hasProperty("PatientEmergency2")) {
			patientContacts.setPatientEmergency2(patientContSoapObj
					.getPropertyAsString("PatientEmergency2"));
		}
		return patientContacts;
	}

	private static HospitalContacts getHospitalContacts(
			SoapObject hospitalContSoapObj) {
		HospitalContacts hospitalContacts = HospitalContacts.getInstance();
		if (hospitalContSoapObj.hasProperty("Hosipital1")) {
			hospitalContacts.setHosipital1(hospitalContSoapObj
					.getPropertyAsString("Hosipital1"));
		}
		if (hospitalContSoapObj.hasProperty("Hosipital2")) {
			hospitalContacts.setHosipital2(hospitalContSoapObj
					.getPropertyAsString("Hosipital2"));
		}
		if (hospitalContSoapObj.hasProperty("HosipitalEmergency1")) {
			hospitalContacts.setHosipitalEmergency1(hospitalContSoapObj
					.getPropertyAsString("HosipitalEmergency1"));
		}
		if (hospitalContSoapObj.hasProperty("HosipitalEmergency2")) {
			hospitalContacts.setHosipitalEmergency2(hospitalContSoapObj
					.getPropertyAsString("HosipitalEmergency2"));
		}
		if (hospitalContSoapObj.hasProperty("Ambulance1")) {
			hospitalContacts.setAmbulance1(hospitalContSoapObj
					.getPropertyAsString("Ambulance1"));
		}
		if (hospitalContSoapObj.hasProperty("Ambulance2")) {
			hospitalContacts.setAmbulance2(hospitalContSoapObj
					.getPropertyAsString("Ambulance2"));
		}
		if (hospitalContSoapObj.hasProperty("BillingContact")) {
			hospitalContacts.setBillingContact(hospitalContSoapObj
					.getPropertyAsString("BillingContact"));
		}
		if (hospitalContSoapObj.hasProperty("ComplaintsContact")) {
			hospitalContacts.setComplaintsContact(hospitalContSoapObj
					.getPropertyAsString("ComplaintsContact"));
		}

		return hospitalContacts;
	}

	private static DoctorContacts getDoctorContacts(SoapObject doctorContSoapObj) {
		DoctorContacts doctorContacts = DoctorContacts.getInstance();
		if (doctorContSoapObj.hasProperty("PrimaryDoctor")) {
			SoapObject primaryDoctorSoapObj = (SoapObject) doctorContSoapObj
					.getProperty("PrimaryDoctor");
			DoctorContacts.DoctorDetails primaryDoctor = new DoctorDetails();
			if (primaryDoctorSoapObj.hasProperty("DoctorName")) {
				primaryDoctor.setDoctorName(primaryDoctorSoapObj
						.getPropertyAsString("DoctorName"));
			}
			if (primaryDoctorSoapObj.hasProperty("Contact")) {
				primaryDoctor.setContact(primaryDoctorSoapObj
						.getPropertyAsString("Contact"));
			}
			doctorContacts.setPrimaryDoctor(primaryDoctor);

		}
		if (doctorContSoapObj.hasProperty("ReferringDoctor")) {
			SoapObject refferingDoctorSoapObj = (SoapObject) doctorContSoapObj
					.getProperty("ReferringDoctor");
			DoctorDetails referenceDoctor = new DoctorDetails();
			if (refferingDoctorSoapObj.hasProperty("DoctorName")) {
				referenceDoctor.setDoctorName(refferingDoctorSoapObj
						.getPropertyAsString("DoctorName"));
			}
			if (refferingDoctorSoapObj.hasProperty("Contact")) {
				referenceDoctor.setContact(refferingDoctorSoapObj
						.getPropertyAsString("Contact"));
			}
			doctorContacts.setReferncingDoctor(referenceDoctor);
		}
		return doctorContacts;
	}

	private static BillingParticualrs getBillingPaticulars(
			SoapObject billingSoapObj) {
		BillingParticualrs billingParticualrs = BillingParticualrs.getInstance();
		PaymentInfo paymentInfo;
		List<PaymentInfo> paymentInfoList = new ArrayList<PaymentInfo>();
		int paymentInfoCount = billingSoapObj.getPropertyCount();
		for (int i = 0; i < paymentInfoCount; i++) {
			paymentInfo = new PaymentInfo();
			SoapObject paymentInfoSoapObj = (SoapObject) billingSoapObj
					.getProperty(i);
			if (paymentInfoSoapObj.hasProperty("CurrentBilledAmount")) {
				paymentInfo.setCurrentBilledAmount(paymentInfoSoapObj
						.getPropertyAsString("CurrentBilledAmount"));
			}
			if (paymentInfoSoapObj.hasProperty("Discounts")) {
				paymentInfo.setDiscounts(paymentInfoSoapObj
						.getPropertyAsString("Discounts"));
			}
			if (paymentInfoSoapObj.hasProperty("PaidAmount")) {
				paymentInfo.setPaidAmount(paymentInfoSoapObj
						.getPropertyAsString("PaidAmount"));
			}
			if (paymentInfoSoapObj.hasProperty("PaidDate")) {
				paymentInfo.setPaidDate(paymentInfoSoapObj
						.getPropertyAsString("PaidDate"));
			}
			paymentInfoList.add(paymentInfo);

		}
		billingParticualrs.setPaymentInfo(paymentInfoList);

		return billingParticualrs;
	}

	public static TestsnProcedureResponse parseTestsnProcedure(SoapObject object) {
		TestsnProcedureResponse testsnProcedureResp = TestsnProcedureResponse.getInstance();
		List<TestsProc> testsProcList;
		testsProcList = new ArrayList<TestsProc>();
		if (object.hasProperty("PatientName")) {
			testsnProcedureResp.setPatientName(object
					.getPropertyAsString("PatientName"));
		}
		if (object.hasProperty("HosipitalName")) {
			testsnProcedureResp.setHospitalName(object
					.getPropertyAsString("HosipitalName"));
		}
		if (object.hasProperty("TestsLists")) {
		
			SoapObject testsListSoapObj = (SoapObject) object
					.getProperty("TestsLists");
			int count = testsListSoapObj.getPropertyCount();
			for (int i = 0; i < count; i++) {
				SoapObject testsProcSoapObj = (SoapObject) testsListSoapObj
						.getProperty(i);
				testsProcList.add(getTestsProc(testsProcSoapObj));

			}
			
		}
		if (object.hasProperty("ProceduresList")) {
			SoapObject procListSoapObj = (SoapObject) object
					.getProperty("ProceduresList");
			int count = procListSoapObj.getPropertyCount();
			for (int i = 0; i < count; i++) {
				SoapObject testsProcSoapObj = (SoapObject) procListSoapObj
						.getProperty(i);
				testsProcList.add(getTestsProc(testsProcSoapObj));
			}
//			testsnProcedureResp.setProclist(testsProcList);
			testsnProcedureResp.setTestsnProcedureslist(testsProcList);
		}
		if (object.hasProperty("ErrorRespeMSG")) {
			testsnProcedureResp.setErrorMsg(object
					.getPropertyAsString("ErrorRespeMSG"));
		} else {
			testsnProcedureResp.setErrorMsg("");
		}

		return testsnProcedureResp;
	}

	private static TestsProc getTestsProc(SoapObject testsProcSoapObj) {
		TestsProc proc = new TestsProc();
		if (testsProcSoapObj.hasProperty("OrderedBy")) {
			proc
					.setOrderedBy(testsProcSoapObj
							.getPropertyAsString("OrderedBy"));
		}
		if (testsProcSoapObj.hasProperty("ValidatedBy")) {
			proc.setValidatedBy(testsProcSoapObj
					.getPropertyAsString("ValidatedBy"));
		}
		if (testsProcSoapObj.hasProperty("Name")) {
			proc.setName(testsProcSoapObj.getPropertyAsString("Name"));
		}
		if (testsProcSoapObj.hasProperty("Type")) {
			proc.setType(testsProcSoapObj.getPropertyAsString("Type"));
		}
		if (testsProcSoapObj.hasProperty("OrderTime")) {
			proc
					.setOrderTime(testsProcSoapObj
							.getPropertyAsString("OrderTime"));
		}
		if (testsProcSoapObj.hasProperty("PerformTime")) {
			proc.setPerformTime(testsProcSoapObj
					.getPropertyAsString("PerformTime"));
		}
		if (testsProcSoapObj.hasProperty("Frequency")) {
			proc
					.setFrequency(testsProcSoapObj
							.getPropertyAsString("Frequency"));
		}
		if (testsProcSoapObj.hasProperty("RecurrenceTimes")) {
			proc.setRecurrenceTimes(testsProcSoapObj
					.getPropertyAsString("RecurrenceTimes"));
		}
		if (testsProcSoapObj.hasProperty("SpecialInstructions")) {
			proc.setSpecialInstructions(testsProcSoapObj
					.getPropertyAsString("SpecialInstructions"));
		}
		return proc;

	}

	public static final DietnCareResponse parseDietnCareResp(SoapObject object) {
		
		DietnCareResponse dietnCareResponse = new DietnCareResponse();
		List<DietnCare> dietList = new ArrayList<DietnCare>();
		List<DietnCare> careList = new ArrayList<DietnCare>();

		if (object.hasProperty("PatientName")) {
			dietnCareResponse.setPatientName(object
					.getPropertyAsString("PatientName"));
		}
		if (object.hasProperty("HosipitalName")) {
			dietnCareResponse.setHospitalName(object
					.getPropertyAsString("HosipitalName"));
		}
		if (object.hasProperty("DietList")) {
			SoapObject dietListSoapObj = (SoapObject) object
					.getProperty("DietList");
			dietList = new ArrayList<DietnCare>();
			int count = dietListSoapObj.getPropertyCount();
			for (int i = 0; i < count; i++) {
				SoapObject dietnCareSoapObj = (SoapObject) dietListSoapObj
						.getProperty(i);
				dietList.add(getDietnCare(dietnCareSoapObj));
			}
			dietnCareResponse.setDietList(dietList);
		}

		if (object.hasProperty("OrderedCareList")) {
			SoapObject careListSoapObj = (SoapObject) object
					.getProperty("OrderedCareList");
			careList = new ArrayList<DietnCare>();
			int count = careListSoapObj.getPropertyCount();
			for (int i = 0; i < count; i++) {
				SoapObject dietnCareSoapObj = (SoapObject) careListSoapObj
						.getProperty(i);
				careList.add(getDietnCare(dietnCareSoapObj));
			}
			dietnCareResponse.setCareList(careList);
		}
		if (object.hasProperty("DietAndCareRespeMSG")) {
			dietnCareResponse.setErrorMsg(object
					.getPropertyAsString("DietAndCareRespeMSG"));
		} else {
			dietnCareResponse.setErrorMsg("");
		}
		return dietnCareResponse;
	}

	private static DietnCare getDietnCare(SoapObject dietnCareSoapObj) {
		DietnCare dietnCare = new DietnCare();
		if (dietnCareSoapObj.hasProperty("OrderedByDoctor")) {
			dietnCare.setOrderedByDoctor(dietnCareSoapObj
					.getPropertyAsString("OrderedByDoctor"));
		}
		if (dietnCareSoapObj.hasProperty("StartTime")) {
			dietnCare.setStartTime(dietnCareSoapObj
					.getPropertyAsString("StartTime"));
		}
		if (dietnCareSoapObj.hasProperty("Instructions")) {
			dietnCare.setInstructions(dietnCareSoapObj
					.getPropertyAsString("Instructions"));
		}
		if (dietnCareSoapObj.hasProperty("Recurrence")) {
			dietnCare.setRecurrence(dietnCareSoapObj
					.getPropertyAsString("Recurrence"));
		}
		return dietnCare;
	}

	public static final String parseRegister(SoapObject object) {
		String message = "";
		if (object.hasProperty("Registration_MSG")) {
			message = object.getPropertyAsString("Registration_MSG");
		} else if (object.hasProperty("Forgot_Password_MSG")) {
			message = object.getPropertyAsString("Forgot_Password_MSG");
		}
		return message;
	}

	public static final String parseChangePwd(SoapObject object) {
		String message = "";
		if (object.hasProperty("Reset_Password_MSG")) {
			message = object.getPropertyAsString("Reset_Password_MSG");
		}

		return message;
	}

}
