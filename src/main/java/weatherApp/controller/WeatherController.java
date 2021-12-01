package weatherApp.controller;


import java.io.IOException;

import javax.xml.ws.RequestWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class WeatherController {

	private OkHttpClient client;
	private Response response;
	private String city;
	private String unit;
	
	private JSONObject getWeather() throws JSONException{
		client = new OkHttpClient();
		
		Request request = new Request
				.Builder() .url("http://api.openweathermap.org/data/2.5/weather?q="+getCity()+"&units="+getUnit()+"&appid=908e83ada5d6312d5bb8a40033d8ea3a")
				.build();
		try {
			response = client.newCall(request).execute();
		    return new JSONObject(response.body().string());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    JSONObject jo = null;
	try {
		jo = new JSONObject(response.body().toString());
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return jo;
	}

	public JSONArray getWeatherArray() throws JSONException{
		JSONArray weatherArray = getWeather().getJSONArray("weather");
		return weatherArray;
		
	}
	
	public JSONObject getMainObject() throws JSONException {
		JSONObject main = getWeather().getJSONObject("main");
		return main;
	}
  
	public JSONObject getWindObject() throws JSONException {
        JSONObject wind = getWeather().getJSONObject("wind");
        return wind;
        }
    public JSONObject getSysObject() throws JSONException{
        JSONObject sys = getWeather().getJSONObject("sys");
        return sys;
        } 


	//public JSONObject get
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	
	
	
}
