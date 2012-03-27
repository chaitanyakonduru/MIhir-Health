package com.mms.mpc.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import android.content.Context;

public final class SoapServiceManager {

	 private final static String NAMESPACE =
	 "http://mihirmobile.com/MIHIRHealthSrv/";
	
	private static final String SOAP_ACTION_REGISTRATION = "http://mihirmobile.com/MIHIRHealthSrv/Register";
	private static final String SOAP_ACTION_FORGOTPASSWORD = "http://mihirmobile.com/MIHIRHealthSrv/ForgotPassword";
	private static final String SOAP_ACTION_CHANGEPASSWORD = "http://mihirmobile.com/MIHIRHealthSrv/ResetPassword";
	private static final String SOAP_ACTION_AUTHENTICATE = "http://mihirmobile.com/MIHIRHealthSrv/Authenticate";
	private static final String SOAP_ACTION_GETPATIENTDETAILS = "http://mihirmobile.com/MIHIRHealthSrv/GetPatient";
	private static final String SOAP_ACTION_DOCTOR_VISITS = "http://mihirmobile.com/MIHIRHealthSrv/DoctorVisits";
	private static final String SOAP_ACTION_PRESCRIPTIONS = "http://mihirmobile.com/MIHIRHealthSrv/Prescriptions";
	private static final String SOAP_ACTION_TESTSNPROCEDURES = "http://mihirmobile.com/MIHIRHealthSrv/TestsAndProcedures";
	private static final String SOAP_ACTION_DIETNCARE = "http://mihirmobile.com/MIHIRHealthSrv/DietAndCare";
	private static final String SOAP_ACTION_IMPINFO = "http://mihirmobile.com/MIHIRHealthSrv/ImportantInfo";
	private static final String SOAP_ACTION_LATESTUPDATES="http://mihirmobile.com/MIHIRHealthSrv/LatestObservations";
	
	
	private static SoapServiceManager serviceManager;
	private static ExecutorService executorService;

	public static SoapServiceManager getInstance(Context context) {
		return serviceManager == null ? serviceManager = new SoapServiceManager(
				context)
				: serviceManager;
	}
	public SoapServiceManager(Context context) {
		executorService = Executors.newCachedThreadPool(new NamedThreadFactory(
				"mihir"));
	}

	public void sendLatestUpdatesRequest(String patientHistoryId,NetworkCallback<Object> callback)
	{
		String methodName = "LatestObservations";
		final String actionName = SOAP_ACTION_LATESTUPDATES;
		final SoapObject requestObj = new SoapObject(NAMESPACE, methodName);

		PropertyInfo info = new PropertyInfo();
		info.setNamespace(NAMESPACE);
		info.setName("Patient_History_ID");
		info.setValue(patientHistoryId);
		requestObj.addProperty(info);
		final MihirHandler handler = new MihirHandler(callback);

		executorService.execute(new Runnable() {

			public void run() {
				SoapConn.callWebService(actionName, requestObj, handler);
			}
		});


	}
	public void sendDoctorVisitsRequest(String patientHistoryId,
			NetworkCallback<Object> callback) {
		String methodName = "DoctorVisits";
		final String actionName = SOAP_ACTION_DOCTOR_VISITS;
		final SoapObject requestObj = new SoapObject(NAMESPACE, methodName);

		PropertyInfo info = new PropertyInfo();
		info.setNamespace(NAMESPACE);
		info.setName("Patient_History_ID");
		info.setValue(patientHistoryId);
		requestObj.addProperty(info);
		final MihirHandler handler = new MihirHandler(callback);

		executorService.execute(new Runnable() {

			public void run() {
				SoapConn.callWebService(actionName, requestObj, handler);
			}
		});

	}

	public  void sendTestsnProceduresRequest(String patientHistoryId,
			NetworkCallback<Object> callback) {
		String methodName = "TestsAndProcedures";
		final String actionName = SOAP_ACTION_TESTSNPROCEDURES;
		final SoapObject requestObj = new SoapObject(NAMESPACE, methodName);

		PropertyInfo info = new PropertyInfo();
		info.setNamespace(NAMESPACE);
		info.setName("Patient_History_ID");
		info.setValue(patientHistoryId);
		requestObj.addProperty(info);
		final MihirHandler handler = new MihirHandler(callback);
		executorService.execute(new Runnable() {

			public void run() {
				SoapConn.callWebService(actionName, requestObj, handler);
			}
		});

	}

	public void sendDietnCareRequest(String patientHistoryId,
			NetworkCallback<Object> callback) {
		String methodName = "DietAndCare";
		final String actionName = SOAP_ACTION_DIETNCARE;
		final SoapObject requestObj = new SoapObject(NAMESPACE, methodName);

		PropertyInfo info = new PropertyInfo();
		info.setNamespace(NAMESPACE);
		info.setName("Patient_History_ID");
		info.setValue(patientHistoryId);
		requestObj.addProperty(info);
		final MihirHandler handler = new MihirHandler(callback);
		executorService.execute(new Runnable() {

			public void run() {
				SoapConn.callWebService(actionName, requestObj, handler);
			}
		});

	}

	public  void sendImpInfoRequest(String patientHistoryId,
			NetworkCallback<Object> callback) {
		String methodName = "ImportantInfo";
		final String actionName = SOAP_ACTION_IMPINFO;
		final SoapObject requestObj = new SoapObject(NAMESPACE, methodName);

		PropertyInfo info = new PropertyInfo();
		info.setNamespace(NAMESPACE);
		info.setName("Patient_History_ID");
		info.setValue(patientHistoryId);
		requestObj.addProperty(info);
		final MihirHandler handler = new MihirHandler(callback);
		executorService.execute(new Runnable() {

			public void run() {
				SoapConn.callWebService(actionName, requestObj, handler);
			}
		});

	}
	
	

	public  void sendPrescriptionsRequest(String patientHistoryId,
			NetworkCallback<Object> callback) {

		String methodName = "Prescriptions";
		final String actionName = SOAP_ACTION_PRESCRIPTIONS;
		final SoapObject requestObj = new SoapObject(NAMESPACE, methodName);

		PropertyInfo info = new PropertyInfo();
		info.setNamespace(NAMESPACE);
		info.setName("Patient_History_ID");
		info.setValue(patientHistoryId);
		requestObj.addProperty(info);
		final MihirHandler handler = new MihirHandler(callback);
		executorService.execute(new Runnable() {
			public void run() {
				SoapConn.callWebService(actionName, requestObj, handler);
			}
		});

	}

	public void sendgetPatientDetailsRequest(String patientId,
			NetworkCallback<Object> callback) {
		String methodName = "GetPatient_Request";
		final String actionName = SOAP_ACTION_GETPATIENTDETAILS;
		final SoapObject requestObj = new SoapObject(NAMESPACE, methodName);

		PropertyInfo info = new PropertyInfo();
		info.setNamespace(NAMESPACE);
		info.setName("Patient_ID");
		info.setValue(Integer.parseInt(patientId));
		requestObj.addProperty(info);
		final MihirHandler handler = new MihirHandler(callback);
		executorService.execute(new Runnable() {
			public void run() {
				SoapConn.callWebService(actionName, requestObj, handler);
			}
		});

	}
	
	
	public void sendregisterRequest(String mmsId,
			NetworkCallback<Object> callback) {

		String methodName = "Registration_Request";
		final String actionName = SOAP_ACTION_REGISTRATION;
		final SoapObject request = new SoapObject(NAMESPACE, methodName);

		PropertyInfo info = new PropertyInfo();
		info.namespace = NAMESPACE;
		info.name = "MMS_ID";
		info.setValue(mmsId);
		request.addProperty(info);
		final MihirHandler handler = new MihirHandler(callback);
		executorService.execute(new Runnable() {
			public void run() {

				SoapConn.callWebService(actionName, request, handler);
			}
		});
	}

	public  void sendforgotPasswordRequest(String mmsId,
			NetworkCallback<Object> callback) {
		String methodName = "Forgot_Password_Request";
		final String actionName = SOAP_ACTION_FORGOTPASSWORD;
		final SoapObject request = new SoapObject(NAMESPACE, methodName);

		PropertyInfo info = new PropertyInfo();
		info.namespace = NAMESPACE;
		info.name = "MMS_ID";
		info.setValue(mmsId);
		request.addProperty(info);
		final MihirHandler handler = new MihirHandler(callback);
		executorService.execute(new Runnable() {
			public void run() {
				SoapConn.callWebService(actionName, request, handler);
			}
		});

	}

	public void sendchangePasswordRequest(String mmsId, String oldPwd,
			String newPwd, NetworkCallback<Object> callback) {
		String methodName = "Reset_Password_Request";
		final String actionName = SOAP_ACTION_CHANGEPASSWORD;
		final SoapObject request = new SoapObject(NAMESPACE, methodName);

		PropertyInfo info = new PropertyInfo();
		info.namespace = NAMESPACE;
		info.name = "MMS_ID";
		info.setValue(mmsId);
		request.addProperty(info);

		info = new PropertyInfo();
		info.namespace = NAMESPACE;
		info.name = "Old_Password";
		info.setValue(oldPwd);
		request.addProperty(info);

		info = new PropertyInfo();
		info.namespace = NAMESPACE;
		info.name = "New_Password";
		info.setValue(newPwd);
		request.addProperty(info);
		final MihirHandler handler = new MihirHandler(callback);

		executorService.execute(new Runnable() {
			public void run() {
				SoapConn.callWebService(actionName, request, handler);
			}
		});
	}

	public void sendAuthenticateRequest(String mmsId, String password,
			String deviceId, NetworkCallback<Object> callback) {

		// String methodName = "Authentication_Request";
		String methodName = "Authenticate";

		final String actionName = SOAP_ACTION_AUTHENTICATE;
		final SoapObject requestObject = new SoapObject(NAMESPACE, methodName);

		PropertyInfo info = new PropertyInfo();
		info.namespace = NAMESPACE;
		info.name = "MMS_ID";
		info.setValue(mmsId);
		requestObject.addProperty(info);

		info = new PropertyInfo();
		info.namespace = NAMESPACE;
		info.name = "User_Password";
		info.setValue(password);
		requestObject.addProperty(info);

		info = new PropertyInfo();
		info.namespace = NAMESPACE;
		info.name = "Device_ID";
		info.setValue(deviceId);
		requestObject.addProperty(info);

		info = new PropertyInfo();
		info.namespace = NAMESPACE;
		info.name = "Device_OS";
		info.setValue(2);
		requestObject.addProperty(info);

		final MihirHandler mihirHandler = new MihirHandler(callback);

		executorService.execute(new Runnable() {

			public void run() {
				SoapConn
						.callWebService(actionName, requestObject, mihirHandler);
			}
		});
	}

}
