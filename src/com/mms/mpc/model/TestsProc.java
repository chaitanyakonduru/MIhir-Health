package com.mms.mpc.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;


public class TestsProc implements Parcelable {
	public String getOrderedBy() {
		return OrderedBy;
	}
	
	public TestsProc()
	{
		
	}
	public void setOrderedBy(String orderedBy) {
		OrderedBy = orderedBy;
	}
	public String getValidatedBy() {
		return ValidatedBy;
	}
	public void setValidatedBy(String validatedBy) {
		ValidatedBy = validatedBy;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getOrderTime() {
		return OrderTime;
	}
	public void setOrderTime(String orderTime) {
		OrderTime = orderTime;
	}
	public String getPerformTime() {
		return PerformTime;
	}
	public void setPerformTime(String performTime) {
		PerformTime = performTime;
	}
	public String getFrequency() {
		return Frequency;
	}
	public void setFrequency(String frequency) {
		Frequency = frequency;
	}
	public String getRecurrenceTimes() {
		return recurrenceTimes;
	}
	public void setRecurrenceTimes(String recurrenceTimes) {
		this.recurrenceTimes = recurrenceTimes;
	}
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {

		List<String> ll=new ArrayList<String>();
		ll.add(this.Frequency);
		ll.add(this.Name);
		ll.add(this.OrderedBy);
		ll.add(this.OrderTime);
		ll.add(this.PerformTime);
		ll.add(this.recurrenceTimes);
		ll.add(this.Type);
		ll.add(this.specialInstructions);
		ll.add(this.ValidatedBy);
		
		dest.writeStringList(ll);	
		
	}

	public TestsProc(Parcel parcel)
	{
		List<String>list1=new ArrayList<String>();
		parcel.readStringList(list1);
		this.Frequency=list1.get(0);
		this.Name=list1.get(1);
		this.OrderedBy=list1.get(2);
		this.OrderTime=list1.get(3);
		this.PerformTime=list1.get(4);
		this.recurrenceTimes=list1.get(5);
		this.Type=list1.get(6);
		this.specialInstructions=list1.get(7);
		this.ValidatedBy=list1.get(8);
		
	}

	public static final Creator<TestsProc> CREATOR=new Creator<TestsProc>() {

		public TestsProc createFromParcel(Parcel source) {
			return new TestsProc(source);
		}

		public TestsProc[] newArray(int size) {
			return new TestsProc[size];
		}
	};
	public static Creator<TestsProc> getCreator() {
		return CREATOR;
	}
	

	
	
	private String OrderedBy;
	private String ValidatedBy;
	private String Name;
	private String Type;
	private String OrderTime;
	private String PerformTime;
	private String Frequency;
	private String recurrenceTimes;
	private String specialInstructions;
	
}
