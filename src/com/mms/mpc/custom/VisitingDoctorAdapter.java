package com.mms.mpc.custom;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mms.mpc.R;
import com.mms.mpc.model.VisitingDoctor;

public class VisitingDoctorAdapter extends ArrayAdapter<VisitingDoctor> {

	private static final String TAG = "VisitingDoctorAdapter";
	private List<VisitingDoctor> items;
	private LayoutInflater inflater;

	public VisitingDoctorAdapter(Context context, int textViewResourceId,
			List<VisitingDoctor> items) {
		super(context, textViewResourceId, items);
		this.items = items;
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return items.size();
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.doctor_visit_list_layout,
					null);
			holder = new ViewHolder();
			holder.doctorName = (TextView) convertView
					.findViewById(R.id.doctor_visit_doctorname);
			holder.visitNotesText = (TextView) convertView
					.findViewById(R.id.doctor_visit_notes);
			holder.date=(TextView)convertView.findViewById(R.id.doctor_visit_date);
			convertView.setTag(holder);
		} else {
			
			holder = (ViewHolder) convertView.getTag();
		}

		VisitingDoctor visitingDoctor = items.get(position);
		Log.v(TAG, visitingDoctor.getmVisitDateandTime());
		holder.doctorName.setText(visitingDoctor.getmDoctorName());
		holder.visitNotesText.setText(visitingDoctor.getMvisitNotes());
		holder.date.setText(visitingDoctor.getmVisitDateandTime());
		return convertView;
	}

	private static class ViewHolder {
		private TextView date;
		private TextView visitNotesText;
		private TextView doctorName;
	}
}
