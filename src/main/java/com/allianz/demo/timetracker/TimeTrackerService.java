package com.allianz.demo.timetracker;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class TimeTrackerService {
	public ArrayList<TimeTrackerResponse> getData(String email, String start, String end) {

		try {
			final HttpResponse<JsonNode> res = Unirest.put("http://172.17.218.177:8080/records?email=" + email.trim())
					.header("Content-Type", "application/json; charset=UTF-8").body("").asJson();

			JSONArray jsonArray = res.getBody().getArray();
			ArrayList<TimeTrackerResponse> records = new ArrayList<>();
			for (int i = 0; i < jsonArray.length(); i++) {
				if (jsonArray.isNull(i))
					continue;
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				records.add((new TimeTrackerResponse(jsonObject)));
			}
			return records;
		}

		catch (final UnirestException | NullPointerException | JSONException e) {
			Logger.getLogger(TimeTrackerController.class.getName()).log(Level.SEVERE, "error fetching records", e);
		}

		return null;
	}

	public boolean saveData(String email, String start, String end) {
		try {
			TimeTrackerResponse obj = new TimeTrackerResponse();
			obj.setEmail(email);
			obj.setEndTime(start);
			obj.setStartTime(end);
			final HttpResponse<JsonNode> res = Unirest.post("http://172.17.218.177:8080/records")
					.field("email", obj.getEmail()).field("start", obj.getStartTime()).field("end", obj.getEndTime())
					.asJson();

			if (res.getStatus() == 200) {
				return true;
			}

		} catch (final UnirestException | NullPointerException | JSONException e) {
			Logger.getLogger(TimeTrackerController.class.getName()).log(Level.SEVERE, "error on saving record", e);
		}
		return false;
	}
}
