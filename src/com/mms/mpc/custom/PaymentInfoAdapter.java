package com.mms.mpc.custom;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mms.mpc.R;
import com.mms.mpc.model.PaymentInfo;

public class PaymentInfoAdapter extends ArrayAdapter<PaymentInfo> {
	
	private List<PaymentInfo> items;
	private LayoutInflater inflater;

	public PaymentInfoAdapter(Context context, int textViewResourceId, List<PaymentInfo> items) {
		super(context, textViewResourceId, items);
		this.items = items;
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return items.size();
	}
	
	
	@Override
	public int getPosition(PaymentInfo item) {
		return items.indexOf(item);
	}
	
	@Override
	public PaymentInfo getItem(int position) {
		return items.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.paymentinfo_list_layout, null);
			holder = new ViewHolder();
			holder.currentBilledAmt = (TextView)convertView.findViewById(R.id.payinfo_ll_currentbillamt);
			holder.discount= (TextView)convertView.findViewById(R.id.payinfo_discount);
			holder.paidAmt=(TextView)convertView.findViewById(R.id.payinfo_paidamt);
			holder.paidDate=(TextView)convertView.findViewById(R.id.payinfo_paiddate);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		PaymentInfo payInfo= items.get(position);
		holder.currentBilledAmt.setText(payInfo.getCurrentBilledAmount());
		holder.discount.setText(payInfo.getDiscounts());
		holder.paidAmt.setText(payInfo.getPaidAmount());
		holder.paidDate.setText(payInfo.getPaidDate());
		
		return convertView;
	}
	
	private static class ViewHolder {
		private TextView currentBilledAmt;
		private TextView discount;
		private TextView paidAmt;
		private TextView paidDate;
	}
}
