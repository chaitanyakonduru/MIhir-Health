package com.mms.mpc.custom;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mms.mpc.model.Patient;

public final class Utils {
	
	public static Drawable drawable;

	public static final void showDialog(String msg, final Context context,
			final Boolean tobeFinish) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage(msg);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if (tobeFinish) {
					Activity activity = (Activity) context;
					activity.finish();
					
				}
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	public static final void setActionBar(TextView hospName,TextView patientName,Patient curPatient,ImageView logo)
	{
		hospName.setText(curPatient.getmHospital_Name());
		if(patientName!=null)
		{
		patientName.setText(curPatient.getmPatient_Name());
		}
		
		try {
			drawable=Utils.getImageDrawable(curPatient.getmHospital_Logo());
			if(drawable!=null){
				logo.setImageDrawable(drawable);
			}
		} 
		catch (Exception e) {
			
			
		}
		
	}
	public static final void sendMail(String mailContent, String subject,Context context) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("mail/Html");
		intent.putExtra(Intent.EXTRA_TEXT, mailContent);
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		context.startActivity(Intent.createChooser(intent, "sending..."));
	}
	public static final void showToast(String msg,Context context)
	{
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static  Drawable getImageDrawable(String logo_url) 
	{
		Drawable drawable=null;
		if(Utils.drawable!=null){
			return Utils.drawable;
		}
		if (logo_url != null) {

			try {
				URL url = new URL(logo_url);
				InputStream inputStream = (InputStream) url
						.getContent();
				drawable = Drawable.createFromStream(
						inputStream, "imgsrc");

			} catch (Exception e) {

				drawable = null;
			}
		}
		return drawable;
	}

}
