package com.mms.mpc.model;

public class PaymentInfo {
	
	private String currentBilledAmount,Discounts,PaidAmount,PaidDate;

	public String getCurrentBilledAmount() {
		return currentBilledAmount;
	}

	public void setCurrentBilledAmount(String currentBilledAmount) {
		this.currentBilledAmount = currentBilledAmount;
	}

	public String getDiscounts() {
		return Discounts;
	}

	public void setDiscounts(String discounts) {
		Discounts = discounts;
	}

	public String getPaidAmount() {
		return PaidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		PaidAmount = paidAmount;
	}

	public String getPaidDate() {
		return PaidDate;
	}

	public void setPaidDate(String paidDate) {
		PaidDate = paidDate;
	}
	

}
