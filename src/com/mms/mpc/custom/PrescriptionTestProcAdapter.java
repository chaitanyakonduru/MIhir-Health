package com.mms.mpc.custom;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import com.mms.mpc.R;
import com.mms.mpc.model.Prescription;
import com.mms.mpc.model.TestsProc;

@SuppressWarnings("unchecked")
public class PrescriptionTestProcAdapter extends ArrayAdapter {

	private List items;
	private LayoutInflater inflater;

	public PrescriptionTestProcAdapter(Context context, int textViewResourceId,
			List items) {
		super(context, textViewResourceId, items);
		this.items = items;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public int getPosition(Object item) {
		return items.indexOf(item);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.prescriptions_list_layout,
					null);
			holder = new ViewHolder();
			holder.tests_type = (TableRow) convertView.findViewById(R.id.prescription_ll_tablerow_type);
			holder.textName = (TextView) convertView
					.findViewById(R.id.prescription_textname);
			holder.textValue = (TextView) convertView
					.findViewById(R.id.prescrption_text_value);
			holder.freqvalue = (TextView) convertView
					.findViewById(R.id.presc_ll_freq);
			holder.quantity = (TextView) convertView
					.findViewById(R.id.presc_ll_qty);
			holder.type = (TextView) convertView
					.findViewById(R.id.presc_ll_type);
			holder.prescription_Qty=(TableRow)convertView.findViewById(R.id.prescription_ll_tablerow_quantity);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final Object object = items.get(position);
		if (object instanceof Prescription) {
			
			Prescription prescription = (Prescription) object;
			holder.prescription_Qty.setVisibility(View.VISIBLE);
			holder.tests_type.setVisibility(View.GONE);
			holder.quantity.setText(prescription.getQuantity());
			holder.textValue.setText(prescription.getName());
			holder.freqvalue.setText(prescription.getFrequency());
		} else if (object instanceof TestsProc) {
			TestsProc testsProc = (TestsProc) object;
			holder.prescription_Qty.setVisibility(View.GONE);
			holder.tests_type.setVisibility(View.VISIBLE);
			holder.textName.setText("Test/Procedure Name");
			holder.type.setText(testsProc.getType());
			holder.textValue.setText(testsProc.getName());
			holder.freqvalue.setText(testsProc.getFrequency());
		}
		
		return convertView;
	}

	private static class ViewHolder {
		private TextView textName;
		private TextView textValue;
		private TextView quantity;
		private TextView freqvalue;
		private TextView type;
		private TableRow prescription_Qty;
		private TableRow tests_type;
	}
}
