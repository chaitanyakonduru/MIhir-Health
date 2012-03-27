package com.mms.mpc.model;

import java.util.Collections;
import java.util.List;

public class BillingParticualrs {
	private static BillingParticualrs billingParticulars;
	private List<PaymentInfo> paymentInfo;

	public List<PaymentInfo> getPaymentInfo() {
		return Collections.unmodifiableList(paymentInfo);
	}

	public void setPaymentInfo(List<PaymentInfo> paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public static BillingParticualrs getInstance()
	{
		return billingParticulars==null?billingParticulars=new BillingParticualrs():billingParticulars;
	}

 }
