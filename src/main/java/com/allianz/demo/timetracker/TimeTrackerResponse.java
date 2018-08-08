package com.allianz.demo.timetracker;

import org.json.JSONObject;

public class TimeTrackerResponse {
	
	private String startTime;	
	private String endTime;
	private String email;
	
	public TimeTrackerResponse(JSONObject jsonObject) {
		this.email = jsonObject.getString("email");
		this.startTime = jsonObject.getString("start");
		this.endTime = jsonObject.getString("end");
	}
	
	public TimeTrackerResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
