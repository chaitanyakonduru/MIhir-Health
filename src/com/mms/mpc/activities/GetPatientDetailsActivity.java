package com.mms.mpc.activities;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.mms.mpc.R;
import com.mms.mpc.custom.Constants;
import com.mms.mpc.custom.Utils;
import com.mms.mpc.model.GetPatientDetailsResponse;
import com.mms.mpc.model.HospitalStaff;
import com.mms.mpc.model.MMSStaff;
import com.mms.mpc.model.Patient;
import com.mms.mpc.network.Comm;
import com.mms.mpc.network.NetworkCallback;
import com.mms.mpc.network.SoapServiceManager;

public class GetPatientDetailsActivity extends Activity implements OnClickListener{
	protected static final String TAG = "GetPatientDetailsActivity";

	private EditText patientId;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_getpatient);
		initializeViews();
		
	}

	private void initializeViews() {
		patientId=(EditText)findViewById(R.id.getpatient_edittext_patientid);
		findViewById(R.id.getPatient_btn_getdetails).setOnClickListener(this);
		findViewById(R.id.mms_ad_image).setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.getPatient_btn_getdetails:

			SoapServiceManager manager=SoapServiceManager.getInstance(GetPatientDetailsActivity.this);
			if(TextUtils.isEmpty(patientId.getText().toString()))
			{
				patientId.setError("Patient Id is required");
			}
			else
			{
				showDialog(Constants.PROGRESSDIALOG);
				
				manager.sendgetPatientDetailsRequest(patientId.getText().toString(), callback);
			}
		
			break;
		case R.id.mms_ad_image:
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.mihirmobile.com/")));
			break;

		default:
			break;
		}
	}
	
final NetworkCallback<Object> callback=new NetworkCallback<Object>() {
		public void onSuccess(Object object) {
		removeDialog(Constants.PROGRESSDIALOG);
		MihirApp app = (MihirApp) getApplication();
		try
		{
		GetPatientDetailsResponse detailsResponse=Comm.getPatientDetails((SoapObject)object);
		if(detailsResponse.getGetPatientRespMsg().equals(""))
		{
			Patient patient=detailsResponse.getPatient();
			app.setCurPatient(patient);
			startActivity(new Intent(GetPatientDetailsActivity.this,HomeActivity.class));
		}
		else
		{
			Utils.showDialog(detailsResponse.getGetPatientRespMsg(), GetPatientDetailsActivity.this, false);
		}
		}
		catch(ClassCastException cce)
		{
			Utils.showToast("Unable to process your request", GetPatientDetailsActivity.this);
			Log.v(TAG, cce.getMessage());
		}
		catch(Exception e)
		{
			Log.v(TAG, e.getMessage());
		}
		}
		
		public void onFailure(String errorMessge) {
			removeDialog(Constants.PROGRESSDIALOG);
		}
	};
	
	final NetworkCallback<Object> adcallback=new NetworkCallback<Object>() {
		
		public void onSuccess(Object object) {
			
		}
		
		public void onFailure(String errorMessge) {
			
		}
	};

	
	
	@Override
	protected Dialog onCreateDialog(int id) {


		switch (id) {
		case Constants.PROGRESSDIALOG:
			final ProgressDialog progressDialog = new ProgressDialog(
					GetPatientDetailsActivity.this);
			progressDialog.setMessage("Please wait....");
			progressDialog.show();
			progressDialog.setOnCancelListener(new OnCancelListener() {
				
				@Override
				public void onCancel(DialogInterface dialog) {
					if(!progressDialog.isShowing())
					{
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
	

	protected void onRestart() {
		MihirApp app=(MihirApp) getApplication();
		if(!app.isIsloggedin())
		{
			finish();
		}
		Object currentUserObject=app.getCurrentUser();
		
		if((currentUserObject instanceof HospitalStaff) || (currentUserObject instanceof MMSStaff))
		{
		}
		else
		{
			finish();
		}
	Log.v(TAG, "OnRestart");
		super.onRestart();
	}
	@Override
	protected void onResume() {
	Log.v(TAG, "OnResume");
		super.onResume();
	}
	
	
	protected void onStart() {
	Log.v(TAG,"On Start");		super.onStart();
	}
	
	
	protected void onStop() {
	Log.v(TAG, "On Stop");
		super.onStop();
	}
	@Override
	protected void onPause() {
	Log.v(TAG, "On Pause");
		super.onPause();
	}


}
