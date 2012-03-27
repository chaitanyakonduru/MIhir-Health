package com.mms.mpc.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.mms.mpc.R;
import com.mms.mpc.custom.Utils;
import com.mms.mpc.model.DietnCare;
import com.mms.mpc.model.Patient;

public class DietCareDetailsActivity extends Activity implements
		OnClickListener {
	private static final String TAG = null;
	private TextView doctorName;
	private TextView starttime;
	private TextView instructions;
	private TextView recurrence;
	private Patient curPatient;
	private TextView patientName;
	private TextView hospitalName;
	private DietnCare dietnCare;
	private ImageView schoolLogo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_dietcare_details);
		initializeViews();
		MihirApp app = (MihirApp) getApplication();
		curPatient = app.getCurPatient();
		Utils.setActionBar(hospitalName, patientName, curPatient, schoolLogo);
		Bundle bundle = getIntent().getExtras();

		if (bundle != null) {
			dietnCare = bundle.getParcelable("myobject");
			doctorName.setText(dietnCare.getOrderedByDoctor());
			starttime.setText(dietnCare.getStartTime());
			instructions.setText(dietnCare.getInstructions());
			recurrence.setText(dietnCare.getRecurrence());
		}

	}

	private void initializeViews() {
		schoolLogo = (ImageView) findViewById(R.id.school_logo);
		doctorName = (TextView) findViewById(R.id.dietcare_details_orderby);
		patientName = (TextView) findViewById(R.id.action_bar_tv_patient_name);
		hospitalName = (TextView) findViewById(R.id.action_tv_hospital_name);
		starttime = (TextView) findViewById(R.id.dietcare_details_starttime);
		instructions = (TextView) findViewById(R.id.dietcare_details_instructions);
		recurrence = (TextView) findViewById(R.id.dietcare_details_details_recurrence);
		findViewById(R.id.sharebutton).setOnClickListener(
				DietCareDetailsActivity.this);
		findViewById(R.id.mms_ad_image).setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sharebutton:

			if (dietnCare != null) {
				try {
					StringBuilder builder = new StringBuilder();
					builder.append(
							"Instructions :\t" + dietnCare.getInstructions()
									+ "\n")
							.append("Ordered BY Doctor:\t"
									+ dietnCare.getOrderedByDoctor() + "\n")
							.append("Recurrence :\t"
									+ dietnCare.getRecurrence() + "\n")
							.append("Start Time:\t" + dietnCare.getStartTime()
									+ "\n");
					String subject = curPatient.getmPatient_Name() + "'s   "
							+ "Diet and Care Description Details";
					Utils.sendMail(builder.toString(), subject,
							DietCareDetailsActivity.this);
				} catch (Exception e) {
					Log.v(TAG, e.getMessage());
				}
			}

			break;
		case R.id.mms_ad_image:
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.mihirmobile.com/")));
			break;

		default:
			break;
		}
	}

}
