package com.mms.mpc.network;

@SuppressWarnings("hiding")
public interface NetworkCallback<Object> {
	
	void onSuccess(Object object);
	void onFailure(String errorMessge);
}
