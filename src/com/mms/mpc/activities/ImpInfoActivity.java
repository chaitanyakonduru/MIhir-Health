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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.mms.mpc.R;
import com.mms.mpc.custom.Constants;
import com.mms.mpc.custom.PaymentInfoAdapter;
import com.mms.mpc.custom.Utils;
import com.mms.mpc.model.DoctorContacts;
import com.mms.mpc.model.HospitalContacts;
import com.mms.mpc.model.ImpInfoResponse;
import com.mms.mpc.model.Patient;
import com.mms.mpc.model.PatientContacts;
import com.mms.mpc.model.PaymentInfo;
import com.mms.mpc.network.Comm;
import com.mms.mpc.network.NetworkCallback;
import com.mms.mpc.network.SoapServiceManager;

public class ImpInfoActivity extends TabActivity implements OnClickListener {

	protected static final String TAG = "ImpInfoActivity";
	private TabHost tabHost;
	private TextView hospitalName;
	private SoapServiceManager manager;
	private ListView payInfoListView;
	private TextView primaryDocName;
	private TextView primaryDocCont;
	private TextView hospitalContHosp1;
	private TextView hospitalContHosp2;
	private TextView hospitalContEmer1;
	private TextView hospitalContEmer2;
	private TextView hospitalContAmb1;
	private TextView hospitalContAmb2;
	private TextView hospitalContBilling;
	private TextView hospitalContCompl;
	private TextView patientContEmer1;
	private TextView patientContEmer2;
	private TextView referenceDocName;
	private TextView referenceDocCont;
	private TextView patientName;

	private List<PaymentInfo> paymentInfoList;
	private Patient curPatient;
	protected DoctorContacts doctorContacts;
	protected HospitalContacts hospitalContacts;
	protected PatientContacts patientContacts;
	private ImageView schoolLogo;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_billing_contact_info);
		intializeViews();
		tabHost = getTabHost();
		setUpTab("Bills", R.id.billing_info);
		setUpTab("IMP.CONTACTS", R.id.contact_info);
		MihirApp app = (MihirApp) getApplication();
		curPatient = app.getCurPatient();

		Utils.setActionBar(hospitalName, patientName, curPatient, schoolLogo);
		manager = SoapServiceManager.getInstance(ImpInfoActivity.this);
		showDialog(Constants.PROGRESSDIALOG);
		manager.sendImpInfoRequest(curPatient.getPatient_HistoryId(), callback);

	}

	private void intializeViews() {
		schoolLogo = (ImageView) findViewById(R.id.school_logo);
		hospitalName = (TextView) findViewById(R.id.action_tv_hospital_name);
		payInfoListView = (ListView) findViewById(R.id.paymentlist);
		primaryDocName = (TextView) findViewById(R.id.primary_doc_name);
		primaryDocCont = (TextView) findViewById(R.id.primary_doc_contact);
		referenceDocName = (TextView) findViewById(R.id.refering_doc_name);
		referenceDocCont = (TextView) findViewById(R.id.refering_doc_contact);
		hospitalContHosp1 = (TextView) findViewById(R.id.hospital_cont_hosp1);
		hospitalContHosp2 = (TextView) findViewById(R.id.hospital_cont_hosp2);
		hospitalContEmer1 = (TextView) findViewById(R.id.hospital_cont_emer1);
		hospitalContEmer2 = (TextView) findViewById(R.id.hospital_cont_emer2);
		hospitalContAmb1 = (TextView) findViewById(R.id.hospital_cont_amb1);
		hospitalContAmb2 = (TextView) findViewById(R.id.hospital_cont_amb2);
		hospitalContBilling = (TextView) findViewById(R.id.hospital_cont_billing);
		hospitalContCompl = (TextView) findViewById(R.id.hospital_cont_complaint);
		patientContEmer1 = (TextView) findViewById(R.id.patient_cont_emer1);
		patientContEmer2 = (TextView) findViewById(R.id.patient_cont_emer2);
		patientName = (TextView) findViewById(R.id.action_bar_tv_patient_name);
		findViewById(R.id.sharebutton).setOnClickListener(ImpInfoActivity.this);
		findViewById(R.id.mms_ad_image).setOnClickListener(this);
	}

	private void setUpTab(String tag, int resId) {
		TabSpec spec = tabHost.newTabSpec(tag).setContent(resId)
				.setIndicator(makeTab(tag));
		tabHost.addTab(spec);
	}

	private View makeTab(String tag) {
		int bgId;
		if ("Billing Info".equalsIgnoreCase(tag)) {
			bgId = R.drawable.left_tab_indicator;
		} else {
			bgId = R.drawable.right_tab_indicator;
		}

		View tabView = LayoutInflater.from(ImpInfoActivity.this).inflate(
				R.layout.tab_indicator, null);
		tabView.setBackgroundResource(bgId);
		TextView tabText = (TextView) tabView.findViewById(R.id.text);
		tabText.setText(tag);
		return tabView;
	}

	final NetworkCallback<Object> callback = new NetworkCallback<Object>() {

		public void onSuccess(Object object) {
			removeDialog(Constants.PROGRESSDIALOG);
			try {
				ImpInfoResponse infoResponse = Comm
						.parseImpInfoResponse((SoapObject) object);
				if (infoResponse.getResponseMsg().equals("")) {
					paymentInfoList = infoResponse.getBillparticulars()
							.getPaymentInfo();
					if (paymentInfoList != null && paymentInfoList.size() > 0) {
						payInfoListView.setAdapter(new PaymentInfoAdapter(
								ImpInfoActivity.this, -1, paymentInfoList));
					}

					doctorContacts = infoResponse.getDoctorContacts();
					if (doctorContacts != null) {

						primaryDocName.setText(doctorContacts
								.getPrimaryDoctor().getDoctorName());
						primaryDocCont.setText(doctorContacts
								.getPrimaryDoctor().getContact());
						primaryDocCont.setOnClickListener(ImpInfoActivity.this);
						referenceDocName.setText(doctorContacts
								.getReferncingDoctor().getDoctorName());
						referenceDocCont.setText(doctorContacts
								.getReferncingDoctor().getContact());
						referenceDocCont
								.setOnClickListener(ImpInfoActivity.this);
					}
					hospitalContacts = infoResponse.getHospitalContacts();
					if (hospitalContacts != null) {
						hospitalContHosp1.setText(hospitalContacts
								.getHosipital1());
						hospitalContHosp1
								.setOnClickListener(ImpInfoActivity.this);
						hospitalContHosp2.setText(hospitalContacts
								.getHosipital2());
						hospitalContHosp2
								.setOnClickListener(ImpInfoActivity.this);
						hospitalContEmer1.setText(hospitalContacts
								.getHosipitalEmergency1());
						hospitalContEmer1
								.setOnClickListener(ImpInfoActivity.this);
						hospitalContEmer2.setText(hospitalContacts
								.getHosipitalEmergency2());
						hospitalContEmer2
								.setOnClickListener(ImpInfoActivity.this);
						hospitalContAmb1.setText(hospitalContacts
								.getAmbulance1());
						hospitalContAmb1
								.setOnClickListener(ImpInfoActivity.this);
						hospitalContAmb2.setText(hospitalContacts
								.getAmbulance2());
						hospitalContAmb2
								.setOnClickListener(ImpInfoActivity.this);
						hospitalContBilling.setText(hospitalContacts
								.getBillingContact());
						hospitalContBilling
								.setOnClickListener(ImpInfoActivity.this);
						hospitalContCompl.setText(hospitalContacts
								.getComplaintsContact());
						hospitalContCompl
								.setOnClickListener(ImpInfoActivity.this);
					}

					patientContacts = infoResponse.getPatientContacts();
					if (patientContacts != null) {
						patientContEmer1.setText(patientContacts
								.getPatientEmergency1());
						patientContEmer1
								.setOnClickListener(ImpInfoActivity.this);
						patientContEmer2.setText(patientContacts
								.getPatientEmergency2());
						patientContEmer2
								.setOnClickListener(ImpInfoActivity.this);
					}
				} else {
					Utils.showDialog(infoResponse.getResponseMsg(),
							ImpInfoActivity.this, true);
				}
			} catch (ClassCastException cce) {
				Utils.showToast("Unable to process your request",
						ImpInfoActivity.this);
				Log.v(TAG, cce.getMessage());
			} catch (Exception e) {
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
					ImpInfoActivity.this);
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
		switch (v.getId()) {
		case R.id.sharebutton:

			StringBuilder mailContent = new StringBuilder("");
			String subject = "";
			if (tabHost.getCurrentTab() == 0) {

				for (PaymentInfo info : paymentInfoList) {
					mailContent
							.append("Paid Amount: \t" + info.getPaidAmount()
									+ "\n")
							.append("Paid Date :\t" + info.getPaidDate() + "\n")
							.append("Current Billed Amount :\t"
									+ info.getCurrentBilledAmount() + "\n")
							.append("Discounts (If any): \t"
									+ info.getDiscounts() + "\n");
				}
				subject = curPatient.getmPatient_Name()
						+ "'s Payment Information";
				Utils.sendMail(mailContent.toString(), subject,
						ImpInfoActivity.this);
			} else if (tabHost.getCurrentTab() == 1) {
				mailContent = new StringBuilder("");
				mailContent
						.append("Hospital Contacts \n")
						.append("Hospital 1 :\t"
								+ hospitalContacts.getHosipital1() + "\n")
						.append("Hospital 2 :\t"
								+ hospitalContacts.getHosipital2() + "\n")
						.append("Hospital Emergency1 :\t"
								+ hospitalContacts.getHosipitalEmergency1()
								+ "\n")
						.append("Hospital Emergency2 :\t"
								+ hospitalContacts.getHosipitalEmergency2()
								+ "\n")
						.append("Ambulance 1 :\t"
								+ hospitalContacts.getAmbulance1() + "\n")
						.append("Ambulance 2 :\t"
								+ hospitalContacts.getAmbulance2() + "\n")
						.append("Billing Contacts :\t"
								+ hospitalContacts.getBillingContact() + "\n")
						.append("Complaint Contacts :\t"
								+ hospitalContacts.getComplaintsContact()
								+ "\n")
						.append("Doctor Contacts\n")
						.append("Primary Doctor Details\n")
						.append("Primary Doctor Name \t"
								+ doctorContacts.getPrimaryDoctor()
										.getDoctorName() + "\n")
						.append("Primary Doctor Contact \t"
								+ doctorContacts.getPrimaryDoctor()
										.getContact() + "\n")
						.append("Reference Doctor Details\n")
						.append("Reference Doctor Name \t"
								+ doctorContacts.getReferncingDoctor()
										.getDoctorName() + "\n")
						.append("Referemnce Doctor Contact \t"
								+ doctorContacts.getReferncingDoctor()
										.getContact() + "\n")
						.append("Patient Contacts\n")
						.append("Patient Emergency1 \t"
								+ patientContacts.getPatientEmergency1() + "\n")
						.append("Patient Emergency2 \t"
								+ patientContacts.getPatientEmergency2() + "\n");

				String subject1 = curPatient.getmPatient_Name() + "'s"
						+ "Emergency Numbers";
				Utils.sendMail(mailContent.toString(), subject1,
						ImpInfoActivity.this);

			}
			break;

		case R.id.hospital_cont_amb1:
			String text = hospitalContAmb1.getText().toString();
			if (isNumber(text)) {
				makeCall(text);
			}
			break;
		case R.id.hospital_cont_amb2:
			text = hospitalContAmb2.getText().toString();
			if (isNumber(text)) {
				makeCall(text);
			}
			break;
		case R.id.hospital_cont_billing:
			text = hospitalContBilling.getText().toString();
			if (isNumber(text)) {
				makeCall(text);
			}
			break;
		case R.id.hospital_cont_complaint:
			text = hospitalContCompl.getText().toString();
			if (isNumber(text)) {
				makeCall(text);
			}
			break;
		case R.id.hospital_cont_emer1:
			text = hospitalContEmer1.getText().toString();
			if (isNumber(text)) {
				makeCall(text);
			}
			break;
		case R.id.hospital_cont_emer2:
			text = hospitalContEmer2.getText().toString();
			if (isNumber(text)) {
				makeCall(text);
			}
			break;
		case R.id.hospital_cont_hosp1:
			text = hospitalContHosp1.getText().toString();
			if (isNumber(text)) {
				makeCall(text);
			}
			break;

		case R.id.hospital_cont_hosp2:
			text = hospitalContHosp2.getText().toString();
			if (isNumber(text)) {
				makeCall(text);
			}
			break;
		case R.id.primary_doc_contact:
			text = primaryDocCont.getText().toString();
			if (isNumber(text)) {
				makeCall(text);
			}
			break;
		case R.id.refering_doc_contact:
			text = referenceDocCont.getText().toString();
			if (isNumber(text)) {
				makeCall(text);
			}
			break;
		case R.id.patient_cont_emer1:
			text = patientContEmer1.getText().toString();
			if (isNumber(text)) {
				makeCall(text);
			}
			break;
		case R.id.patient_cont_emer2:
			text = patientContEmer2.getText().toString();
			if (isNumber(text)) {
				makeCall(text);
			} else {
				Toast.makeText(ImpInfoActivity.this,
						"Unable to make a call to this number",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.mms_ad_image:
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.mihirmobile.com/")));
			break;

		default:
			break;

		}

	}

	private void makeCall(String text) {
		startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + text)));
	}

	private Boolean isNumber(String text) {
		return TextUtils.isDigitsOnly(text);
	}

}
