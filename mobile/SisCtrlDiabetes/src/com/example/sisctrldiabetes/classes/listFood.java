package com.example.sisctrldiabetes.classes;


import java.util.ArrayList;

public class listFood {
	
	private Boolean success = false;
	private ArrayList<Food> data;
	private String errorMessage;
	private String updateMessage;
	
	public listFood() {
		
	}
	
	public String getUpdateMessage() {
		return updateMessage;
	}
	
	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public ArrayList<Food> getData() {
		return data;
	}

	public void setData(ArrayList<Food> data) {
		this.data = data;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
