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
import com.mms.mpc.model.Prescription;
import com.mms.mpc.model.TestsProc;

public class PrescriptionDetailsActivity extends Activity implements OnClickListener{
	
	private static final String TAG = null;
	private TextView prescriptionName;
	private TextView prescriptionNameHeader;
	private TextView prescriptiontype;
	private TextView prescriptionsize;
	private TextView prescriptionquantity;
	private TextView prescriptionfrequency;
	private TextView prescriptionorderedBy;
	private TextView prescriptionvalidatedBy;
	private Object object;
	private TextView prescriptionperformTime;
	private TextView recurrenceTime;
	private TextView specialInstructions;
	private TextView patientName;
	private TextView hospitalName;
	private TextView noOfDays;
	private TextView startTime;
	private Patient curPatient;
	private ImageView schoolLogo;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_prescription_details);
		initializeViews();
		MihirApp app=(MihirApp)getApplication();
		curPatient=app.getCurPatient();
		Utils.setActionBar(hospitalName,patientName,curPatient,schoolLogo);
		Bundle bundle=getIntent().getExtras();
		
		if(bundle!=null)
		{
			object=bundle.getParcelable("object");
			if(object instanceof Prescription)
			{
				try
				{
				Prescription prescription=(Prescription) object;
				findViewById(R.id.prescription_det_trow_recurence).setVisibility(View.GONE);
//				findViewById(R.id.prescription_details_trow_ordertime).setVisibility(View.VISIBLE);
				findViewById(R.id.prescritpion_det_trow_perfrom_time).setVisibility(View.GONE);
				findViewById(R.id.prescription_det_trow_size).setVisibility(View.VISIBLE);
				findViewById(R.id.prescription_det_trow_qty).setVisibility(View.VISIBLE);
				findViewById(R.id.prescription_det_trow_validateBy).setVisibility(View.GONE);
				findViewById(R.id.prescription_det_trow_noOfDays).setVisibility(View.VISIBLE);
				findViewById(R.id.prescription_det_trow_startTime).setVisibility(View.VISIBLE);
				prescriptionName.setText(prescription.getName());
				prescriptionfrequency.setText(prescription.getFrequency());
				prescriptionorderedBy.setText(prescription.getOrderedByDoctor());
				prescriptionquantity.setText(prescription.getQuantity());
				prescriptionsize.setText(prescription.getSize());
				prescriptiontype.setText(prescription.getType());
				specialInstructions.setText(prescription.getInstructions());
				prescriptionvalidatedBy.setText(prescription.getValidateByStaff());
				noOfDays.setText(prescription.getNoOfDays());
				startTime.setText(prescription.getStartTime());
				}
				catch(Exception e)
				{
					Log.v(TAG, e.getMessage());
				}
				
			}
			else if(object instanceof TestsProc)
			{
				try
				{
				TestsProc proc=(TestsProc) object;
				prescriptionName.setText(proc.getName());
				prescriptionNameHeader.setText("Test/Procedure Name");
				findViewById(R.id.prescription_det_trow_recurence).setVisibility(View.VISIBLE);
//				findViewById(R.id.prescription_details_trow_ordertime).setVisibility(View.GONE);
				findViewById(R.id.prescritpion_det_trow_perfrom_time).setVisibility(View.VISIBLE);
				findViewById(R.id.prescription_det_trow_size).setVisibility(View.GONE);
				findViewById(R.id.prescription_det_trow_qty).setVisibility(View.GONE);
				findViewById(R.id.prescription_det_trow_validateBy).setVisibility(View.GONE);
				findViewById(R.id.prescription_det_trow_noOfDays).setVisibility(View.GONE);
				findViewById(R.id.prescription_det_trow_startTime).setVisibility(View.GONE);
				prescriptionfrequency.setText(proc.getFrequency());
				prescriptionorderedBy.setText(proc.getOrderedBy());
//				prescriptionOrderTime.setText(proc.getOrderTime());
				prescriptionperformTime.setText(proc.getPerformTime());
				recurrenceTime.setText(proc.getRecurrenceTimes());
				specialInstructions.setText(proc.getSpecialInstructions());
				prescriptionvalidatedBy.setText(proc.getValidatedBy());
				prescriptiontype.setText(proc.getType());
				Log.v(TAG,proc.getFrequency());
				}
				
				catch(Exception e)
				{
					Log.v(TAG, e.getMessage());
				}
			}
		}
		

	}

	private void initializeViews() {
		schoolLogo = (ImageView) findViewById(R.id.school_logo);
		prescriptionName=(TextView)findViewById(R.id.prescription_details_prescname);
		prescriptionNameHeader = (TextView)findViewById(R.id.prescription_details_prescnameheader);
		prescriptiontype=(TextView)findViewById(R.id.prescription_details_type);
		prescriptionsize=(TextView)findViewById(R.id.prescription_details_size);
		prescriptionquantity=(TextView)findViewById(R.id.prescription_details_quantity);
		prescriptionfrequency=(TextView)findViewById(R.id.prescription_details_frequency);
		prescriptionorderedBy=(TextView)findViewById(R.id.prescription_details_ordered_by);
		prescriptionvalidatedBy=(TextView)findViewById(R.id.prescription_details_validatedby);
//		prescriptionOrderTime=(TextView)findViewById(R.id.prescription_details_ordertime);
		prescriptionperformTime=(TextView)findViewById(R.id.prescription_details_performtime);
		recurrenceTime=(TextView)findViewById(R.id.prescription_details_recurrence_times);
		specialInstructions=(TextView)findViewById(R.id.prescription_details_instructions);
		patientName=(TextView)findViewById(R.id.action_bar_tv_patient_name);
		hospitalName=(TextView)findViewById(R.id.action_tv_hospital_name);
		noOfDays = (TextView) findViewById(R.id.prescription_details_noOfdays);
		startTime = (TextView) findViewById(R.id.prescription_details_startTime);
		findViewById(R.id.sharebutton).setOnClickListener(PrescriptionDetailsActivity.this);
		findViewById(R.id.mms_ad_image).setOnClickListener(this);
	}
		

public void onClick(View v) {
	if(v.getId()==R.id.sharebutton)
	{
		StringBuilder mailContent = null;
		String subject="";
		if(object instanceof Prescription)
		{
			mailContent=new StringBuilder("");
			Prescription prescription=(Prescription) object;
			mailContent.append("PrescriptionName:\t"+prescription.getName())
			.append("\n")
			.append("Prescriptionfrequency:\t"+prescription.getFrequency())
			.append("\n")
			.append("PrescriptionorderedBy:\t"+prescription.getOrderedByDoctor())
			.append("\n")
			.append("Prescriptionquantity:\t"+prescription.getQuantity())
			.append("\n")
			.append("Prescriptionsize:\t"+prescription.getSize())
			.append("\n")
			.append("Prescriptiontype:\t"+prescription.getType())
			.append("\n")
			.append("PrescriptionvalidatedBy:\t"+prescription.getValidateByStaff())
			.append("\n")
			.append("NO of days:\t"+prescription.getNoOfDays())
			.append("\n")
			.append("start Time:\t"+prescription.getStartTime())
			.append("\n");
			subject=curPatient.getmPatient_Name()+"'s Prescriptions Description";
		}
		else if(object instanceof TestsProc)
		{
			mailContent=new StringBuilder("");
			TestsProc proc=(TestsProc) object;
			mailContent.append("Prescriptionfrequency:\t"+proc.getFrequency())
			.append("\n")
			.append("PrescriptionorderedBy:\t"+proc.getOrderedBy())
			.append("\n")
			.append("PrescriptionOrderTime:\t"+proc.getOrderTime())
			.append("\n")
			.append("PrescriptionperformTime:\t"+proc.getPerformTime())
			.append("\n")
			.append("RecurrenceTime:\t"+proc.getRecurrenceTimes())
			.append("\n")
			.append("SpecialInstructions:\t"+proc.getSpecialInstructions())
			.append("\n")
			.append("PrescriptionvalidatedBy:\t"+proc.getValidatedBy())
			.append("\n")
			.append("Prescriptiontype:\t"+proc.getType())
			.append("\n");
			subject=curPatient.getmPatient_Name()+"'s Tests and Procedure Description";
		}
		
		Utils.sendMail(mailContent.toString(), subject, PrescriptionDetailsActivity.this);
		
	}else if(v.getId() == R.id.mms_ad_image){
		startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.mihirmobile.com/")));
	}
}
}

