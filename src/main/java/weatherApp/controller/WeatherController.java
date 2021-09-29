package weatherApp.controller;


import java.io.IOException;

import javax.xml.ws.RequestWrapper;

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
	
	private JSONObject getWeather(){
		client = new OkHttpClient();
		
		Request request = new Request
				.Builder().url("http://api.openweathermap.org/data/2.5/weather?q=Londyn&appid=908e83ada5d6312d5bb8a40033d8ea3a")
				.build();
		try {
			response = client.newCall(request).execute();
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
	
}
