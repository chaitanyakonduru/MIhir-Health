package com.mms.mpc.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mms.mpc.R;
import com.mms.mpc.custom.Utils;
import com.mms.mpc.model.Patient;

public class HomeActivity extends Activity implements OnClickListener {
	private static final String TAG = "Home Activity";
	private TextView hospitalName;
	private ImageView hospitalLogo;
	private Button singout;
	private MihirApp app;
	private Patient curPatient;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homelayout);
		app = (MihirApp) getApplication();
		curPatient = app.getCurPatient();
		initViews();
		Utils.setActionBar(hospitalName, null, curPatient, hospitalLogo);
		
		try {
			Log.v(TAG, curPatient.getPatient_HistoryId());
		} 
		catch (Exception e) {
			Log.v(TAG, "Something wrong");
		}
	}

	private void initViews() {

		findViewById(R.id.home_tv_latestupdates).setOnClickListener(
				HomeActivity.this);
		findViewById(R.id.home_tv_doctor_visits).setOnClickListener(
				HomeActivity.this);
		findViewById(R.id.home_tv_prescriptions).setOnClickListener(
				HomeActivity.this);
		findViewById(R.id.home_tv_diet_care).setOnClickListener(
				HomeActivity.this);
		findViewById(R.id.home_tv_myaccount).setOnClickListener(
				HomeActivity.this);
		findViewById(R.id.home_tv_imp_info).setOnClickListener(
				HomeActivity.this);
		findViewById(R.id.mms_ad_image).setOnClickListener(this);
		singout = (Button) findViewById(R.id.action_bar_signout);
		singout.setVisibility(View.VISIBLE);
		singout.setOnClickListener(HomeActivity.this);
		
		hospitalName = (TextView) findViewById(R.id.action_tv_hospital_name);
		hospitalName.setText(curPatient.getmHospital_Name());
		hospitalLogo = (ImageView) findViewById(R.id.school_logo);
	}

	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.home_tv_latestupdates:

			startActivity(new Intent(HomeActivity.this,
					LatestUpdatesActivity.class));
			break;
		case R.id.home_tv_doctor_visits:

			startActivity(new Intent(HomeActivity.this, PhysicianActivity.class));
			break;
		case R.id.home_tv_prescriptions:

			startActivity(new Intent(HomeActivity.this,
					PrescriptionsActivity.class));
			break;
		case R.id.home_tv_diet_care:

			startActivity(new Intent(HomeActivity.this, DietCareActivity.class));
			break;
		case R.id.home_tv_myaccount:

			startActivity(new Intent(HomeActivity.this, MyAccountActivity.class));
			break;
		case R.id.home_tv_imp_info:
			startActivity(new Intent(HomeActivity.this, ImpInfoActivity.class));
			break;
		case R.id.action_bar_signout:
			clearPreferences();
			app.setCurPatient(null);
			app.setIsloggedin(false);
			Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
			break;
		case R.id.mms_ad_image:
			startActivity(new Intent(Intent.ACTION_VIEW, Uri
					.parse("http://www.mihirmobile.com/")));
			break;
		default:
			break;
		}

	}

	private void clearPreferences() {
		LoginActivity.mMyPrefs = this.getSharedPreferences("userPrefs",
				MODE_WORLD_WRITEABLE);
		SharedPreferences.Editor prefsEditor = LoginActivity.mMyPrefs.edit();
		prefsEditor.putLong(LoginActivity.LOGOUTTIME, 0L);
		prefsEditor.putString("Response", "");
		prefsEditor.commit();
	}

	protected void onRestart() {
		Log.v(TAG, "OnREstart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.v(TAG, "OnResume");
		super.onResume();
	}

	protected void onStart() {
		Log.v(TAG, "On Start");
		super.onStart();
	}

	@Override
	protected void onPause() {
		Log.v(TAG, "On Pause");
		super.onPause();
	}

}