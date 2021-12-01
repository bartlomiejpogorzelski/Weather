package weatherApp.view;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.stereotype.Component;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import weatherApp.controller.WeatherController;


@SpringUI(path = "")
public class MainView extends UI {
	private VerticalLayout mainLayout;
	private Button searchButton ;
	private TextField cityText;
	@Autowired
	private WeatherController weatherController;
	private NativeSelect<String> unitSelect ;
	private Label location;
	private Label weatherMin;
    private Label weatherMax;
	private HorizontalLayout mainDescriptionLayout;
	private Label weatherDescription;
	private Label pressureLabel;
	private Label humidityLabel;
	private Label windSpeedLabel;
	private Label feelsLike;
	private HorizontalLayout dashboardLayout;
	private VerticalLayout descriptionLayout ;
	private HorizontalLayout footer;
	private Label currentTemp;
	
	
	@Override
	protected void init(VaadinRequest request) {
		mainLayout();
		setHeader();
		setLogo();
		setForm();
		dashboardTitle();
		dashboardDetails();
		
		footer();
		searchButton.addClickListener(clickEvent -> {
	           if (!cityText.getValue().equals("")){
	               try {
	            	 updateScreen();
	               } catch (JSONException e) {
	                   e.printStackTrace();
	               }
	           }else
	               Notification.show("Please Enter The City");
	        });
		
	}
	
	private void mainLayout() {
		mainLayout = new VerticalLayout();
		mainLayout.setMargin(true);
		//mainLayout.setSizeFull();
		mainLayout.setWidth("100%");
	    mainLayout.setSpacing(true);
		mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		mainLayout.setStyleName("BackColorGrey");
		setContent(mainLayout);
	}
	
	private void setHeader(){
		HorizontalLayout header = new HorizontalLayout();
		header.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		Label title = new Label("Simple Weather APP");
		title.addStyleName(ValoTheme.LABEL_BOLD);
		
		header.addComponent(title);		
		mainLayout.addComponents(header);
	}
	
	private void setLogo(){
		HorizontalLayout logo = new HorizontalLayout();
		logo.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		logo.setWidth("200px");
		logo.setHeight("200px");
		Image img = new Image(null, new ClassResource("/logo.jpg"));
		
		logo.addComponent(img);
		
		mainLayout.addComponents(logo);
	}
	
	private void setForm(){
		HorizontalLayout form = new HorizontalLayout();
		form.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		form.setMargin(true);
		form.setSpacing(true);
		
		unitSelect = new NativeSelect<>();
		unitSelect.setHeight("60px");
		ArrayList<String> items = new ArrayList<>();
		items.add("C");
		items.add("F");
		
		unitSelect.setItems(items);
		unitSelect.setValue(items.get(0));
		
		cityText = new TextField();
		cityText.setWidth("70px");
		
		
		searchButton = new Button();
		searchButton.setIcon(VaadinIcons.SEARCH);
		
		
		form.addComponents(cityText);
		form.addComponents(unitSelect);
		form.addComponents(searchButton);
		mainLayout.addComponents(form);
		
	}
	
	private void dashboardTitle() {
		dashboardLayout = new HorizontalLayout();
		dashboardLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		
		location = new Label("City to choose");
		location.addStyleName(ValoTheme.LABEL_H2);
        location.addStyleName(ValoTheme.LABEL_LIGHT);
        
        currentTemp = new Label("---");
        currentTemp.setStyleName(ValoTheme.LABEL_BOLD);
        currentTemp.setStyleName(ValoTheme.LABEL_H1);
        
        dashboardLayout.addComponents(location,currentTemp);
        mainLayout.addComponents(dashboardLayout);
	}
	
	 private void dashboardDetails(){
	        mainDescriptionLayout = new HorizontalLayout();
	        mainDescriptionLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

	        descriptionLayout = new VerticalLayout();
	        descriptionLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

	        
	        weatherDescription = new Label("Description: Clear Skies");
	        weatherDescription.setStyleName(ValoTheme.LABEL_SUCCESS);
	        descriptionLayout.addComponents(weatherDescription);

	       
	        weatherMin = new Label("Min tempreture:10");
	        descriptionLayout.addComponents(weatherMin);
	     
	        weatherMax = new Label("Max temp:22");
	        descriptionLayout.addComponents(weatherMax);

	        // Pressure, humidity, wind, 

	        VerticalLayout pressureLayout = new VerticalLayout();
	        pressureLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

	        pressureLabel = new Label("Pressure:123Pa");
	        pressureLayout.addComponents(pressureLabel);

//	        humidityLabel = new Label("Humidity:34");
//	        pressureLayout.addComponents(humidityLabel);

	        windSpeedLabel = new Label("124/hr");
	        pressureLayout.addComponents(windSpeedLabel);

//	        feelsLike = new Label("FeelsLike:");
//	        pressureLayout.addComponents(feelsLike);




	        mainDescriptionLayout.addComponents(descriptionLayout, pressureLayout);

	    }
	 
	    private void footer(){
	        footer = new HorizontalLayout();
	        footer.setDefaultComponentAlignment(Alignment.BOTTOM_CENTER);
	        footer.setSpacing(true);
	        footer.setMargin(true);
	        footer.setWidth("100%");
	        footer.setHeight("40px");
	        Label description = new Label();
	        description.setValue("Weather App by BP ");
	        footer.addComponents(description);
	        mainLayout.addComponents(footer);
	    }
	    
	private void updateScreen() throws JSONException {
		
		
		String city = cityText.getValue();
		
		try{
			weatherController.setCity(city);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		location.setValue("Weather in the city: "+city);
		
		
		if(unitSelect.getValue().equals("C")){
			weatherController.setUnit("metric");
		}else {
			weatherController.setUnit("imperials");
		}		
		JSONObject mainObject = weatherController.getMainObject();
	    int temp = mainObject.getInt("temp");
	    currentTemp.setValue(temp+ unitSelect.getValue());
	    
	    String weatherDescriptionNew = null;
	    JSONArray jsonA = weatherController.getWeatherArray();
	    for(int i=0;i<jsonA.length(); i++ ){
	    	weatherDescriptionNew = jsonA.getJSONObject(i).getString("description"); 
	    }
	    
	    
		weatherMin.setValue("min temp: " + weatherController.getMainObject().getInt("temp_min")+ unitSelect.getValue());
		weatherMax.setValue("max temp: " + weatherController.getMainObject().getInt("temp_max")+ unitSelect.getValue() );		
		
		pressureLabel.setValue("Pressure: "+weatherController.getMainObject().getInt("pressure"));
	     
		weatherDescription.setValue("Description "+ weatherDescriptionNew);
	  //  humidityLabel.setValue("Humidity: "+weatherController.getMainObject().getInt("humidity"));
	   windSpeedLabel.setValue("Wind: "+weatherController.getWindObject().getInt("speed")+"m/s");
	     
		mainLayout.addComponents(dashboardLayout,mainDescriptionLayout, footer);
	}
	 
	
	 


}
