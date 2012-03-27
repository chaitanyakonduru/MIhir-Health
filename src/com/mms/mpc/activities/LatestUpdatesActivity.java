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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.mms.mpc.R;
import com.mms.mpc.custom.Constants;
import com.mms.mpc.custom.Utils;
import com.mms.mpc.model.LatestUpdatesResponse;
import com.mms.mpc.model.Patient;
import com.mms.mpc.model.PatientCurrentStatus;
import com.mms.mpc.network.Comm;
import com.mms.mpc.network.NetworkCallback;
import com.mms.mpc.network.SoapServiceManager;

public class LatestUpdatesActivity extends Activity implements OnClickListener {

	protected static final String TAG = "LatestUpdatesActivity";
	private TextView hospitalName;
	private TextView deptName;
	private TextView wardName;
	private TextView bedType;
	private TextView roomType;
	private TextView roomNo;
	private TextView mlong;
	private TextView mshort;
	private TextView mInstructions;
	private TextView updatingDoctor;
	private TextView proposedDischarge;
	private TextView bedName;
	private TextView patientName;
	private Patient curPatient;
	protected PatientCurrentStatus currentStatus;
	private ImageView schoolLogo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SoapServiceManager manager = SoapServiceManager
				.getInstance(LatestUpdatesActivity.this);
		setContentView(R.layout.layout_latest_updates);
		initializeViews();
		MihirApp app = (MihirApp) getApplication();
		curPatient = app.getCurPatient();
		Utils.setActionBar(hospitalName, patientName, curPatient,schoolLogo);
		manager = SoapServiceManager.getInstance(LatestUpdatesActivity.this);
		showDialog(Constants.PROGRESSDIALOG);
		manager.sendLatestUpdatesRequest(curPatient.getPatient_HistoryId(),
				callback);
		// Utils.setActionBar(hospitalName);
		
	}

	private void initializeViews() {
		schoolLogo = (ImageView) findViewById(R.id.school_logo);
		hospitalName = (TextView) findViewById(R.id.action_tv_hospital_name);
		deptName = (TextView) findViewById(R.id.latestupdates_deptname);
		wardName = (TextView) findViewById(R.id.latestupdates_wardname);
		bedType = (TextView) findViewById(R.id.latestupdates_bedtype);
		bedName = (TextView) findViewById(R.id.latestupdates_bedname);
		roomType = (TextView) findViewById(R.id.latestupdates_room_type);
		roomNo = (TextView) findViewById(R.id.latestupdates_room_no);
		mlong = (TextView) findViewById(R.id.latestupdates_long);
		mshort = (TextView) findViewById(R.id.latestupdates_short);
		mInstructions = (TextView) findViewById(R.id.latestupdates_instrucations);
		updatingDoctor = (TextView) findViewById(R.id.latestupdates_updating_doctor);
		proposedDischarge = (TextView) findViewById(R.id.latestupdates_propsed_discharge);
		patientName = (TextView) findViewById(R.id.action_bar_tv_patient_name);
		findViewById(R.id.sharebutton).setOnClickListener(
				LatestUpdatesActivity.this);
		findViewById(R.id.mms_ad_image).setOnClickListener(this);

	}

	final NetworkCallback<Object> callback = new NetworkCallback<Object>() {


		public void onSuccess(Object object) {
			removeDialog(Constants.PROGRESSDIALOG);
			try
			{
			LatestUpdatesResponse latestUpdatesResponse = Comm
					.parseLatestUpdatesResponse((SoapObject) object);
			if (latestUpdatesResponse.getLatestRespMSG().equals("")) {
				currentStatus = latestUpdatesResponse
						.getPatientCurrentStatus();
				if (currentStatus != null) {
					deptName.setText(currentStatus.getDepartmentName());
					wardName.setText(currentStatus.getWardName());
					bedType.setText(currentStatus.getBedType());
					bedName.setText(currentStatus.getBedName());
					roomType.setText(currentStatus.getRoomType());
					roomNo.setText(currentStatus.getRoomNumber());
					mlong.setText(currentStatus.getLatestDiagnosis().getMlong());
					mshort.setText(currentStatus.getLatestDiagnosis()
							.getMshort());
					mInstructions.setText(currentStatus.getmInstructions());
					updatingDoctor.setText(currentStatus.getUpdatingDoctor());
					proposedDischarge.setText(currentStatus
							.getProposedDischarage());
				}
			} else {
				Log.v("LatestErrorMessage", latestUpdatesResponse.getLatestRespMSG());
				Utils.showDialog(latestUpdatesResponse.getLatestRespMSG(),
						LatestUpdatesActivity.this, true);
			}
			}
			catch(ClassCastException cce)
			{
				Utils.showToast("Unable to process your request", LatestUpdatesActivity.this);
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

	final NetworkCallback<Object> adcallback = new NetworkCallback<Object>() {

		public void onSuccess(Object object) {
			removeDialog(Constants.PROGRESSDIALOG);

		}

		public void onFailure(String errorMessge) {
			removeDialog(Constants.PROGRESSDIALOG);
			Log.v(TAG, errorMessge);
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case Constants.PROGRESSDIALOG:
			final ProgressDialog progressDialog = new ProgressDialog(
					LatestUpdatesActivity.this);
			progressDialog.setMessage("Please wait....");
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

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.sharebutton)
		{
			StringBuilder mailContent=new StringBuilder("");
			mailContent.append("Bed Id:\t"+currentStatus.getBedID()+"\n")
			.append("Bed Name:\t"+currentStatus.getBedName()+"\n")
			.append("Bed Type:\t"+currentStatus.getBedType()+"\n")
			.append("Department ID:\t"+currentStatus.getDepartmentID()+"\n")
			.append("Department Name:\t"+currentStatus.getDepartmentName()+"\n")
			.append("Proposed Discharge Date:\t"+currentStatus.getProposedDischarage()+"\n")
			.append("Room Number:\t"+currentStatus.getRoomNumber()+"\n")
			.append("Room Type:\t"+currentStatus.getRoomType()+"\n")
			.append("Updating Doctor:\t"+currentStatus.getUpdatingDoctor()+"\n")
			.append("Ward ID:\t"+currentStatus.getWardID()+"\n")
			.append("Ward Name:\t"+currentStatus.getWardName()+"\n")
			.append("Latest Diagnosis Long:\t"+currentStatus.getLatestDiagnosis().getMlong()+"\n")
			.append("Latest Diagnosis Short:\t"+currentStatus.getLatestDiagnosis().getMshort()+"\n");
			String subject=curPatient.getmPatient_Name()+"'s Current Status Information";
			Utils.sendMail(mailContent.toString(), subject, LatestUpdatesActivity.this);
		}else if(v.getId() == R.id.mms_ad_image){
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.mihirmobile.com/")));
		}
		
	}
}
