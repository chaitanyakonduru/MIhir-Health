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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.mms.mpc.R;
import com.mms.mpc.custom.Constants;
import com.mms.mpc.custom.PrescriptionTestProcAdapter;
import com.mms.mpc.custom.Utils;
import com.mms.mpc.model.Patient;
import com.mms.mpc.model.Prescription;
import com.mms.mpc.model.PrescriptionResponse;
import com.mms.mpc.model.TestsProc;
import com.mms.mpc.model.TestsnProcedureResponse;
import com.mms.mpc.network.Comm;
import com.mms.mpc.network.NetworkCallback;
import com.mms.mpc.network.SoapServiceManager;

public class PrescriptionsActivity extends TabActivity implements
		OnTabChangeListener, OnItemClickListener {

	protected static final String TAG = "Prescription Activity";
	private TabHost tabHost;
	private TextView hospitalName;
	private SoapServiceManager manager;
	private ListView prescriptionListView;
	private ListView procedureListView;
	private TextView patientName;
	private Patient curPatient;
	private ImageView schoolLogo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_prescriptions_test);
		initializeViews();
		manager = SoapServiceManager.getInstance(PrescriptionsActivity.this);
		MihirApp app = (MihirApp) getApplication();
		curPatient = app.getCurPatient();
		Utils.setActionBar(hospitalName, patientName, curPatient,schoolLogo);
		tabHost = getTabHost();
		setUpTab("Prescriptions", R.id.prescription_layout_prescriptions);
		setUpTab("Tests/Procedures ", R.id.prescriptions_layout_tests);
		tabHost.setCurrentTab(0);
		if (tabHost.getCurrentTab() == 0) {
			showDialog(Constants.PROGRESSDIALOG);
			manager.sendPrescriptionsRequest(curPatient.getPatient_HistoryId(),
					prescriptioncallback);
		}
		tabHost.setOnTabChangedListener(this);

	}

	private void initializeViews() {
		schoolLogo = (ImageView) findViewById(R.id.school_logo);
		hospitalName = (TextView) findViewById(R.id.action_tv_hospital_name);
		prescriptionListView = (ListView) findViewById(R.id.prescriptions_list);
		procedureListView = (ListView) findViewById(R.id.proc_list);
		patientName = (TextView) findViewById(R.id.action_bar_tv_patient_name);
		findViewById(R.id.mms_ad_image).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.mihirmobile.com/")));
			}
		});
	}

	private void setUpTab(String tag, int resId) {
		TabSpec spec = tabHost.newTabSpec(tag).setContent(resId).setIndicator(
				makeTab(tag));
		tabHost.addTab(spec);
	}

	private View makeTab(String text) {
		int bgId;
		if ("Prescriptions".equalsIgnoreCase(text)) {
			bgId = R.drawable.left_tab_indicator;
		} else {
			bgId = R.drawable.right_tab_indicator;
		}

		View tabView = LayoutInflater.from(PrescriptionsActivity.this).inflate(
				R.layout.tab_indicator, null);
		tabView.setBackgroundResource(bgId);
		TextView textView = (TextView) tabView.findViewById(R.id.text);
		textView.setText(text);
		return tabView;
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case Constants.PROGRESSDIALOG:
			final ProgressDialog progressDialog = new ProgressDialog(
					PrescriptionsActivity.this);
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

	final NetworkCallback<Object> prescriptioncallback = new NetworkCallback<Object>() {

		public void onSuccess(Object object) {
			removeDialog(Constants.PROGRESSDIALOG);
			try
			{
			PrescriptionResponse prescriptionResponse = Comm
					.parsePrescription((SoapObject) object);
			if (prescriptionResponse.getErrormsg().equals("")) {
				final List<Prescription> prescriptionList = prescriptionResponse
						.getPrescriptionList();

				prescriptionListView
						.setAdapter(new PrescriptionTestProcAdapter(
								PrescriptionsActivity.this, -1,
								prescriptionList));
				prescriptionListView
						.setOnItemClickListener(new OnItemClickListener() {

							public void onItemClick(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								Prescription prescription = prescriptionList
										.get(arg2);
								Intent intent = new Intent(
										PrescriptionsActivity.this,
										PrescriptionDetailsActivity.class);
								intent.putExtra("object", prescription);
								startActivity(intent);
							}

						});
			}

			else {
				Utils.showDialog(prescriptionResponse.getErrormsg(),
						PrescriptionsActivity.this, false);
			}
			}
			catch(ClassCastException cce)
			{
				Utils.showToast("Unable to process your request", PrescriptionsActivity.this);
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
	final NetworkCallback<Object> testscallback = new NetworkCallback<Object>() {

		public void onSuccess(Object object) {
			removeDialog(Constants.PROGRESSDIALOG);
			Log.v(TAG, object.toString());
			try
			{
			TestsnProcedureResponse procedureResponse = Comm
					.parseTestsnProcedure((SoapObject) object);
			

			if (procedureResponse.getErrorMsg().equals("")) {
				final List<TestsProc> procList = procedureResponse.getTestsnProcedureslist();
						
				procedureListView.setAdapter(new PrescriptionTestProcAdapter(
						PrescriptionsActivity.this, -1, procList));
				procedureListView
						.setOnItemClickListener(new OnItemClickListener() {

							public void onItemClick(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {
								TestsProc testsproc = procList.get(arg2);
								Intent intent = new Intent(
										PrescriptionsActivity.this,
										PrescriptionDetailsActivity.class);
								intent.putExtra("object", testsproc);
								startActivity(intent);

							}

						});
				/*final List<TestsProc> testsList = procedureResponse
						.getTestslist();
				testsListView.setAdapter(new PrescriptionTestProcAdapter(
						PrescriptionsActivity.this, -1, testsList));
				testsListView.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						TestsProc tests = testsList.get(arg2);
						Intent intent = new Intent(PrescriptionsActivity.this,
								PrescriptionDetailsActivity.class);
						intent.putExtra("object", tests);
						startActivity(intent);

					}

				});*/
			} else {
				Utils.showDialog(procedureResponse.getErrorMsg(),
						PrescriptionsActivity.this, false);
			}
			}
			catch (Exception e) {
			Utils.showToast("Unable to process your request", PrescriptionsActivity.this);
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
	public void onTabChanged(String tabId) {
		if (tabHost.getCurrentTab() == 0) {
			showDialog(Constants.PROGRESSDIALOG);
			manager.sendPrescriptionsRequest(curPatient.getPatient_HistoryId(),
					prescriptioncallback);
		}
		if (tabHost.getCurrentTab() == 1) {
			showDialog(Constants.PROGRESSDIALOG);
			manager.sendTestsnProceduresRequest(curPatient
					.getPatient_HistoryId(), testscallback);
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}

}
