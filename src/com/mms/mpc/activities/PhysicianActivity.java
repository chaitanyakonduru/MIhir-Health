package com.mms.mpc.activities;

import java.util.List;

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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.mms.mpc.R;
import com.mms.mpc.custom.Constants;
import com.mms.mpc.custom.Utils;
import com.mms.mpc.custom.VisitingDoctorAdapter;
import com.mms.mpc.model.DoctorVisitsResponse;
import com.mms.mpc.model.Patient;
import com.mms.mpc.model.VisitingDoctor;
import com.mms.mpc.network.Comm;
import com.mms.mpc.network.NetworkCallback;
import com.mms.mpc.network.SoapServiceManager;

public class PhysicianActivity extends Activity implements
		OnItemClickListener,OnClickListener {

	protected static final String TAG = "In DoctorVisits Activity";
	private SoapServiceManager manager;
	private TextView hospitalName;
	private ListView listView;
	List<VisitingDoctor> vistitDoctorList;
	private TextView emptytextView;
	private TextView patientName;
	private Patient curPatient;
	private ImageView logo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_doctor_visits);
		initializeViews();
		MihirApp app = (MihirApp) getApplication();
		curPatient = app.getCurPatient();
		Utils.setActionBar(hospitalName, patientName, curPatient,logo);
		manager = SoapServiceManager.getInstance(PhysicianActivity.this);
		showDialog(Constants.PROGRESSDIALOG);
		manager.sendDoctorVisitsRequest(curPatient
				.getPatient_HistoryId(), callback);
	}

	private void initializeViews() {
		logo = (ImageView) findViewById(R.id.school_logo);
		listView = (ListView) findViewById(android.R.id.list);
		listView.setOnItemClickListener(this);
		hospitalName = (TextView) findViewById(R.id.action_tv_hospital_name);
		emptytextView = (TextView) findViewById(android.R.id.empty);
		patientName = (TextView) findViewById(R.id.action_bar_tv_patient_name);
		hospitalName = (TextView) findViewById(R.id.action_tv_hospital_name);
		findViewById(R.id.mms_ad_image).setOnClickListener(this);
	}

	final NetworkCallback<Object> callback = new NetworkCallback<Object>() {

		public void onSuccess(Object object) {
			removeDialog(Constants.PROGRESSDIALOG);
			try
			{
			DoctorVisitsResponse visitsResponse = Comm
					.parseVisitResponse((SoapObject) object);
			String responseMsg = visitsResponse.getDoctorVisitResponseMsg();
			if ((responseMsg.equals(""))) {

				hospitalName.setText(visitsResponse.getmHospitalName());
				vistitDoctorList = visitsResponse.getVisitDoctorList();

				if (vistitDoctorList != null && vistitDoctorList.size() > 0) {
					listView.setAdapter(new VisitingDoctorAdapter(
							PhysicianActivity.this, 0, vistitDoctorList));
				} else {
					listView.setVisibility(View.GONE);
					emptytextView.setVisibility(View.VISIBLE);
					listView.setEmptyView(emptytextView);
				}
			} else {
				Utils.showDialog(responseMsg, PhysicianActivity.this, true);
			}
			}
			catch(ClassCastException cce)
			{
				Utils.showToast("Unable to process your request", PhysicianActivity.this);
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

		}

		public void onFailure(String errorMessge) {

		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case Constants.PROGRESSDIALOG:
			final ProgressDialog progressDialog = new ProgressDialog(
					PhysicianActivity.this);
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
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {

		VisitingDoctor doctor = vistitDoctorList.get(position);
		Intent intent = new Intent(PhysicianActivity.this,
				PhysicianVisitsDescriptionActivity.class);
		intent.putExtra("visitdoctor", doctor);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mms_ad_image:
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.mihirmobile.com/")));
			break;

		default:
			break;
		}
	}

}
