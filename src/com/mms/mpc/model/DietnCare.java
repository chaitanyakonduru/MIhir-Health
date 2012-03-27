package com.mms.mpc.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class DietnCare implements Parcelable{
	
	public String getOrderedByDoctor() {
		return orderedByDoctor;
	}
	public void setOrderedByDoctor(String orderedByDoctor) {
		this.orderedByDoctor = orderedByDoctor;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getRecurrence() {
		return recurrence;
	}
	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}
	
	public int describeContents() {
		return 0;
	}
	public DietnCare()
	{
		
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {

		List<String> ll=new ArrayList<String>();
		ll.add(this.instructions);
		ll.add(this.orderedByDoctor);
		ll.add(this.recurrence);
		ll.add(this.startTime);
		dest.writeStringList(ll);	
		
	}

	public DietnCare(Parcel parcel)
	{
		List<String>list1=new ArrayList<String>();
		parcel.readStringList(list1);
		this.instructions=list1.get(0);
		this.orderedByDoctor=list1.get(1);
		this.recurrence=list1.get(2);
		this.startTime=list1.get(3);
	}

	public static final Creator<DietnCare> CREATOR=new Creator<DietnCare>() {

		public DietnCare createFromParcel(Parcel source) {
			return new DietnCare(source);
		}

		public DietnCare[] newArray(int size) {
			return new DietnCare[size];
		}
	};
	public static Creator<DietnCare> getCreator() {
		return CREATOR;
	}
	
	
	
	public String toString() {
	
		return this.instructions;
	}
	private String startTime;
	private String instructions;
	private String recurrence;
	private String orderedByDoctor;
	
}
