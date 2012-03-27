package com.mms.mpc.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.mms.mpc.R;
import com.mms.mpc.model.Doctor;
import com.mms.mpc.model.Patient;
import com.mms.mpc.network.NetworkCallback;

public class SelectPatientActivity extends Activity implements
		OnItemClickListener {

	private static final String TAG = "SelectPatientActivity";
	private ListView listView;
	private static List<Patient> patientList;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_patients_list);
		initializeViews();
		
		if(patientList!=null)
		{
			listView.setAdapter(new ArrayAdapter<Patient>(
				SelectPatientActivity.this,
				android.R.layout.simple_list_item_1, patientList));
		}
	}

	public static void setPatientList(List<Patient> patientlist) {
		if (patientlist != null) {
			
			Log.v(TAG, String.valueOf(patientlist.size()));
			patientList = patientlist;
		}

	}
	private void initializeViews() {

		listView = (ListView) findViewById(android.R.id.list);
		listView.setOnItemClickListener(SelectPatientActivity.this);
findViewById(R.id.mms_ad_image).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.mihirmobile.com/")));
			}
		});
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		MihirApp app = (MihirApp) getApplication();
		Patient patient = patientList.get(arg2);
		Log.v(TAG, patient.getPatient_HistoryId());
		app.setCurPatient(patient);
		startActivity(new Intent(SelectPatientActivity.this, HomeActivity.class));
	}
	protected void onDestroy() {
		super.onDestroy();
		if (patientList != null) {
			patientList = null;
		}
	}

	
	NetworkCallback<Object> adcallback = new NetworkCallback<Object>() {
		public void onSuccess(Object object) {
			
		}

		public void onFailure(String errorMessge) {
			Log.v(TAG, errorMessge);
		}
	};
	
	protected void onRestart() {
		MihirApp app=(MihirApp) getApplication();
		if(!app.isIsloggedin())
		{
			finish();
		}
		Object currentUserObject=app.getCurrentUser();
		if(!(currentUserObject instanceof Doctor))
		{
			finish();
		}
	Log.v(TAG, "OnRestart");
		super.onRestart();
	}
}
