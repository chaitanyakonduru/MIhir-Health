package com.mms.mpc.activities;

import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnCancelListener;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mms.mpc.R;
import com.mms.mpc.custom.Constants;
import com.mms.mpc.custom.Utils;
import com.mms.mpc.model.AuthenticateResponse;
import com.mms.mpc.model.Doctor;
import com.mms.mpc.model.HospitalStaff;
import com.mms.mpc.model.MMSStaff;
import com.mms.mpc.model.Patient;
import com.mms.mpc.model.PatientResponse;
import com.mms.mpc.network.Comm;
import com.mms.mpc.network.NetworkCallback;
import com.mms.mpc.network.SoapServiceManager;

public class LoginActivity extends Activity implements OnClickListener {

	private static final String TAG = "Login Activity";
	public static final String USERNAME = "username";
	public static final String LOGOUTTIME ="logouttime";
	public static final String USERDATA = "userdata";
	public static SharedPreferences mMyPrefs;
	private EditText mUserName;
	private EditText mPassword;
	private CheckBox mrememberMe;
	private AuthenticateResponse authenticate;
	private Boolean rememberme = false;
	private String response;
		public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_login);
		initializeViews();
		
		mMyPrefs = this.getSharedPreferences("userPrefs", MODE_WORLD_WRITEABLE);
		mUserName.setText(mMyPrefs.getString(USERNAME, ""));
		Long logoutTime=mMyPrefs.getLong(LOGOUTTIME, 0L);
		if(logoutTime>System.currentTimeMillis())
		{
			response=mMyPrefs.getString("Response", "");
			if(response.equalsIgnoreCase(""))
			{
			}
			else
			{
				Log.v(TAG,"Persistant Response:::"+ response);
				SoapObject object=new SoapObject("http://mihirmobile.com/MIHIRHealthSrv/", mMyPrefs.getString("Response", ""));
				Log.v(TAG, object.hasProperty("MMS_Staff_Response")+"");
				
			try
			{
			authenticate=Comm.parseAuthenticate(object);
			Log.v(TAG, authenticate.getmUserType()+"");
			ProcessResponseResult(authenticate);
			}
			catch (ClassCastException cce) {
				Log.v(TAG, cce.getMessage());
			}
			catch(Exception e)
			{
				Log.v(TAG, e.getMessage());
			}
		}
		}
		
	}

	private void initializeViews() {

		mUserName = (EditText) findViewById(R.id.login_edittext_username);
		mPassword = (EditText) findViewById(R.id.login_edittext_password);
		mrememberMe = (CheckBox) findViewById(R.id.login_checkbox_rememberme);

		
		findViewById(R.id.login_btn_login).setOnClickListener(
				LoginActivity.this);
		findViewById(R.id.login_btn_forgotpwd).setOnClickListener(
				LoginActivity.this);
		findViewById(R.id.login_btn_register).setOnClickListener(
				LoginActivity.this);
		findViewById(R.id.mms_ad_image).setOnClickListener(this);
	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.login_btn_login:
			SoapServiceManager serviceManager = SoapServiceManager
					.getInstance(LoginActivity.this);
			String userName = mUserName.getText().toString().trim();
			String password = mPassword.getText().toString().trim();
			if (mrememberMe.isChecked()) {
				rememberme = true;
			} else {
				rememberme = false;
			}
			if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {

				Utils.showDialog(Constants.FIELDSREQUIRED, LoginActivity.this,
						false);
			}

			else {
				showDialog(Constants.PROGRESSDIALOG);
				String deviceId = getDeiviceID();
				serviceManager.sendAuthenticateRequest(userName, password,
						deviceId, callback);
			}
			break;

		case R.id.login_btn_forgotpwd:
			Intent intent = new Intent(LoginActivity.this,
					RegisterActivity.class);
			intent.putExtra("from", "forgotpwd");
			startActivity(intent);
			break;

		case R.id.login_btn_register:
			intent = new Intent(LoginActivity.this, RegisterActivity.class);
			intent.putExtra("from", "register");
			startActivity(intent);
			break;
		case R.id.mms_ad_image:
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.mihirmobile.com/")));
			break;
		default:

			break;
		}

	}

	private String getDeiviceID() {

		TelephonyManager manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		String deviceId = manager.getDeviceId();
		return deviceId != null ? deviceId : "";
	}

	final NetworkCallback<Object> callback = new NetworkCallback<Object>() {
		public void onSuccess(Object responseObj) {

			removeDialog(Constants.PROGRESSDIALOG);
			try {

				SoapObject responceObject = (SoapObject) responseObj;
				authenticate=Comm.parseAuthenticate(responceObject);
				int userType = authenticate.getmUserType();
				if(userType!=Constants.INVALIDUSER )
				{
					rememberMe(responceObject);
				}
				ProcessResponseResult(authenticate);
			} catch (ClassCastException cce) {
				Utils.showToast("Unable to process your request",
						LoginActivity.this);
				Log.v(TAG, cce.getMessage());
			} catch (Exception e) {
				Log.v(TAG, e.getMessage());
			}

		}
		
		public void onFailure(String errorMessge) {
			removeDialog(Constants.PROGRESSDIALOG);
			Utils.showToast("Unable to process your request",
					LoginActivity.this);
		}
	};

	NetworkCallback<Object> adcallback = new NetworkCallback<Object>() {

		public void onSuccess(Object object) {

		}
		public void onFailure(String errorMessge) {
			Log.v(TAG, errorMessge);
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case Constants.PROGRESSDIALOG:
			final ProgressDialog progressDialog = new ProgressDialog(
					LoginActivity.this);
			progressDialog.setMessage("Signing in....");
			progressDialog.show();
			progressDialog.setOnCancelListener(new OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					if (!progressDialog.isShowing()) {
						progressDialog.show();
					}
				}
			});
			return progressDialog;
		default:
			break;
		}
		return super.onCreateDialog(id);
	}

	private void rememberMe(SoapObject object) {
		mMyPrefs = this.getSharedPreferences("userPrefs", MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor prefsEditor = mMyPrefs.edit();
		if(rememberme){
		prefsEditor.putString(LoginActivity.USERNAME, mUserName.getText()
				.toString());
		}
		prefsEditor.putLong(LoginActivity.LOGOUTTIME, (System.currentTimeMillis()
				+ (14 * 24 * 60 * 60 * 1000)));
		prefsEditor.putString("Response", object.toString());
		prefsEditor.commit();
	}
	private void ProcessResponseResult(
		AuthenticateResponse authenticate) {
		MihirApp app=(MihirApp) getApplication();
		int userType = authenticate.getmUserType();
		if(authenticate.getmAuthMsg().equalsIgnoreCase(""))
		{
			switch (userType) {
			case Constants.DOCTOR:
				
			Doctor doctor = authenticate.getMdoctor();
			app.setIsloggedin(true);
			app.setCurrentUser(doctor);
			List<Patient> patientList = doctor.getPatientList();
			SelectPatientActivity.setPatientList(patientList);
			startActivity(new Intent(LoginActivity.this,
					SelectPatientActivity.class));
			finish();
			break;
		case Constants.PATIENT:
			PatientResponse patientResponse = authenticate.getPatient();
			app.setCurrentUser(patientResponse);
			app.setIsloggedin(true);
			app.setCurPatient(patientResponse.getPatient());
			startActivity(new Intent(LoginActivity.this,
					HomeActivity.class));
			finish();
			break;
		case Constants.PATIENTCOMPANION:
			PatientResponse companionResponse = authenticate
					.getCompanion();
			app.setCurrentUser(companionResponse);
			app.setIsloggedin(true);
			app.setCurPatient(companionResponse.getPatient());
			startActivity(new Intent(LoginActivity.this,
					HomeActivity.class));
			finish();
			break;
		case Constants.HOSPITALSTAFF:
			HospitalStaff hospitalStaff = authenticate
					.getHospitalStaff();
			app.setCurrentUser(hospitalStaff);
			app.setIsloggedin(true);
			startActivity(new Intent(LoginActivity.this,
					GetPatientDetailsActivity.class));
			finish();
			break;
		case Constants.MMSSTAFF:
			MMSStaff mmsStaff = authenticate.getMmsStaff();
			app.setCurrentUser(mmsStaff);
			app.setIsloggedin(true);
			startActivity(new Intent(LoginActivity.this,
					GetPatientDetailsActivity.class));
			finish();
			break;
		}
		}
		else
		{
			Utils.showDialog(authenticate.getmAuthMsg(),
					LoginActivity.this, false);
			app.setIsloggedin(false);
		}
	}

}