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
import com.mms.mpc.model.Patient;
import com.mms.mpc.model.VisitingDoctor;

public class PhysicianVisitsDescriptionActivity extends Activity implements OnClickListener{
	
	
	private static final String TAG = "PhysicianVisitsDescriptionActivity";
	private TextView doctorName;
	private TextView visitedTime;
	private TextView notes;
	private VisitingDoctor doctor;
	private TextView patientName;
	private TextView hospitalName;
	private Patient curPatient;
	private ImageView schoolLogo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_doctor_visits_description);
		initializeViews();
		MihirApp app=(MihirApp)getApplication();
		curPatient=app.getCurPatient();
		Utils.setActionBar(hospitalName,patientName,curPatient,schoolLogo);
		Bundle bundle=getIntent().getExtras();
		if(bundle!=null)
		{
			doctor=bundle.getParcelable("visitdoctor");
		}
		doctorName.setText(doctor.getmDoctorName());
		visitedTime.setText(doctor.getmVisitDateandTime());
		notes.setText(doctor.getMvisitNotes());
	}

	private void initializeViews() {
		schoolLogo = (ImageView) findViewById(R.id.school_logo);
		doctorName=(TextView)findViewById(R.id.doc_visit_desc_doctorname);
		visitedTime=(TextView)findViewById(R.id.doc_visit_desc_visitedtime);
		notes=(TextView)findViewById(R.id.doc_visit_desc_notes);
		patientName=(TextView)findViewById(R.id.action_bar_tv_patient_name);
		hospitalName=(TextView)findViewById(R.id.action_tv_hospital_name);
		findViewById(R.id.sharebutton).setOnClickListener(PhysicianVisitsDescriptionActivity.this);
		findViewById(R.id.mms_ad_image).setOnClickListener(this);
	}

	public void onClick(View v) {
		if(v.getId()==R.id.sharebutton)
		{
			try
			{
			StringBuilder mailContent=new StringBuilder("");
			mailContent.append("Doctor Name :"+doctor.getmDoctorName()+"\n")
			.append("Visited Date and Time :"+doctor.getmVisitDateandTime()+"\n")
			.append("Visited Notes:"+doctor.getMvisitNotes()+"\n");
			String subject=curPatient.getmPatient_Name()+"'s Physician Latest Visit Description";
			Utils.sendMail(mailContent.toString(), subject, PhysicianVisitsDescriptionActivity.this);
			}
			
			catch(Exception e)
			{
				Log.v(TAG, e.getMessage());
			}
		}else if(v.getId() == R.id.mms_ad_image){
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.mihirmobile.com/")));
		}
	}

}
