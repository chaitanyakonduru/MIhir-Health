package com.mms.mpc.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Prescription implements Parcelable{

	private String orderedByDoctor;
	private String validateByStaff;
	private String name;
	private String type;
	private String size;
	private String quantity;
	private String frequency;
	private String noOfDays;
	private String startTime;
	private String instructions;
	

	public Prescription()
	{
		
	}
	public String getOrderedByDoctor() {
		return orderedByDoctor;
	}
	public void setOrderedByDoctor(String orderedByDoctor) {
		this.orderedByDoctor = orderedByDoctor;
	}
	public String getValidateByStaff() {
		return validateByStaff;
	}
	public void setValidateByStaff(String validateByStaff) {
		this.validateByStaff = validateByStaff;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
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
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {

		List<String> ll=new ArrayList<String>();
		ll.add(this.frequency);
		ll.add(this.name);
		ll.add(this.orderedByDoctor);
		ll.add(this.quantity);
		ll.add(this.size);
		ll.add(this.type);
		ll.add(this.validateByStaff);
		ll.add(this.noOfDays);
		ll.add(this.startTime);
		ll.add(this.instructions);
		dest.writeStringList(ll);	
		
	}

	public Prescription(Parcel parcel)
	{
		List<String>list1=new ArrayList<String>();
		parcel.readStringList(list1);
		this.frequency=list1.get(0);
		this.name=list1.get(1);
		this.orderedByDoctor=list1.get(2);
		this.quantity=list1.get(3);
		this.size=list1.get(4);
		this.type=list1.get(5);
		this.validateByStaff=list1.get(6);
		this.noOfDays = list1.get(7);
		this.startTime = list1.get(8);
		this.instructions = list1.get(9);
	}

	public static final Creator<Prescription> CREATOR=new Creator<Prescription>() {

		public Prescription createFromParcel(Parcel source) {
			return new Prescription(source);
		}

		public Prescription[] newArray(int size) {
			return new Prescription[size];
		}
	};
	public static Creator<Prescription> getCreator() {
		return CREATOR;
	}
	

	
	
	
}
