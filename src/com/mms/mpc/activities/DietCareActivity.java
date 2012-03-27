package com.mms.mpc.activities;

import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnCancelListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.mms.mpc.R;
import com.mms.mpc.custom.Constants;
import com.mms.mpc.custom.Utils;
import com.mms.mpc.model.DietnCare;
import com.mms.mpc.model.DietnCareResponse;
import com.mms.mpc.model.Patient;
import com.mms.mpc.network.Comm;
import com.mms.mpc.network.NetworkCallback;
import com.mms.mpc.network.SoapServiceManager;

public class DietCareActivity extends TabActivity implements
		OnTabChangeListener,OnClickListener {
	protected static final String TAG = "DietCareActivity";

	private SoapServiceManager manager;
	private TextView hospitalName;

	private TabHost tabHost;
	private ListView dietListView;
	private ListView careListView;
	private Patient curPatient;

	private TextView patientName;

	private ImageView schoolLogo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_diet_care);
		initializeViews();
		MihirApp app = (MihirApp) getApplication();
		curPatient = app.getCurPatient();
		Utils.setActionBar(hospitalName, patientName, curPatient,schoolLogo);
		tabHost = getTabHost();
		setUpTab("Diet", R.id.diet_info);
		setUpTab("Care", R.id.care_info);
		manager = SoapServiceManager.getInstance(DietCareActivity.this);
		showDialog(Constants.PROGRESSDIALOG);
		manager.sendDietnCareRequest(curPatient.getPatient_HistoryId(),
				callback);
	}

	private void setUpTab(String tag, int resId) {
		TabSpec spec = tabHost.newTabSpec(tag).setContent(resId).setIndicator(
				makeTab(tag));
		tabHost.addTab(spec);
	}

	private View makeTab(String tag) {
		int bgId;
		if ("Billing Info".equalsIgnoreCase(tag)) {
			bgId = R.drawable.left_tab_indicator;
		} else {
			bgId = R.drawable.right_tab_indicator;
		}

		View tabView = LayoutInflater.from(DietCareActivity.this).inflate(
				R.layout.tab_indicator, null);
		tabView.setBackgroundResource(bgId);
		TextView tabText = (TextView) tabView.findViewById(R.id.text);
		tabText.setText(tag);
		return tabView;
	}

	private void initializeViews() {
		schoolLogo = (ImageView) findViewById(R.id.school_logo);
		hospitalName = (TextView) findViewById(R.id.action_tv_hospital_name);
		dietListView = (ListView) findViewById(R.id.diet_list);
		careListView = (ListView) findViewById(R.id.care_list);
		patientName = (TextView) findViewById(R.id.action_bar_tv_patient_name);
		hospitalName = (TextView) findViewById(R.id.action_tv_hospital_name);
		findViewById(R.id.mms_ad_image).setOnClickListener(this);

	}

	NetworkCallback<Object> callback = new NetworkCallback<Object>() {

		public void onSuccess(Object object) {
			removeDialog(Constants.PROGRESSDIALOG);

			try
			{
				Log.v(TAG, object.toString());
			DietnCareResponse dietnCareResponse = Comm
					.parseDietnCareResp((SoapObject) object);
			Log.v(TAG, dietnCareResponse.getHospitalName());
			final List<DietnCare> dietList = dietnCareResponse.getDietList();
			if(dietList != null && dietList.size()>0){
			dietListView.setAdapter(new ArrayAdapter<DietnCare>(
					DietCareActivity.this, R.layout.layout_dietcarelisttext,
					dietList));
			
			dietListView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					DietnCare diet = dietList.get(arg2);
					Intent intent = new Intent(DietCareActivity.this,
							DietCareDetailsActivity.class);
					intent.putExtra("myobject", diet);
					startActivity(intent);
				}
			});
			}else{
			}
			final List<DietnCare> careList = dietnCareResponse.getCareList();
			
			careListView.setAdapter(new ArrayAdapter<DietnCare>(
					DietCareActivity.this, R.layout.layout_dietcarelisttext,
					careList));
			careListView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					DietnCare care = careList.get(arg2);
					Intent intent = new Intent(DietCareActivity.this,
							DietCareDetailsActivity.class);
					intent.putExtra("myobject", care);
					startActivity(intent);
				}
			});
			}
			catch(ClassCastException cce)
			{
				cce.printStackTrace();
				Utils.showToast("Unable to process your request", DietCareActivity.this);
				Log.v(TAG,""+ cce.toString());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		public void onFailure(String errorMessge) {
			removeDialog(Constants.PROGRESSDIALOG);
			Log.v(TAG, errorMessge);
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
					DietCareActivity.this);
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

	public void onTabChanged(String tabId) {

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
