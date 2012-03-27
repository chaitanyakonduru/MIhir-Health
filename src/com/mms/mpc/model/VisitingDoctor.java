package com.mms.mpc.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class VisitingDoctor implements Parcelable{
	private String mDoctorName;
	private String mVisitDateandTime;
	private String mvisitNotes;

	public VisitingDoctor()
	{
		
	}
	public String getmDoctorName() {
		return mDoctorName;
	}
	public void setmDoctorName(String mDoctorName) {
		this.mDoctorName = mDoctorName;
	}
	public String getmVisitDateandTime() {
		return mVisitDateandTime;
	}
	public void setmVisitDateandTime(String mVisitDateandTime) {
		this.mVisitDateandTime = mVisitDateandTime;
	}
	public String getMvisitNotes() {
		return mvisitNotes;
	}
	public void setMvisitNotes(String mvisitNotes) {
		this.mvisitNotes = mvisitNotes;
	}
	
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {

		List<String> ll=new ArrayList<String>();
		ll.add(this.mDoctorName);
		ll.add(this.mVisitDateandTime);
		ll.add(this.mvisitNotes);
		
		dest.writeStringList(ll);	
		
	}

	public VisitingDoctor(Parcel parcel)
	{
		List<String>list1=new ArrayList<String>();
		parcel.readStringList(list1);
		this.mDoctorName=list1.get(0);
		this.mVisitDateandTime=list1.get(1);
		this.mvisitNotes=list1.get(2);
		
	}

	public static final Creator<VisitingDoctor> CREATOR=new Creator<VisitingDoctor>() {

		public VisitingDoctor createFromParcel(Parcel source) {
			return new VisitingDoctor(source);
		}

		public VisitingDoctor[] newArray(int size) {
			return new VisitingDoctor[size];
		}
	};
	public static Creator<VisitingDoctor> getCreator() {
		return CREATOR;
	}

}
