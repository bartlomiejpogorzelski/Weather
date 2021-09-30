package weatherApp.view;

import java.util.ArrayList;

import org.springframework.beans.factory.parsing.Location;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI(path = "")
public class MainView extends UI {
	private VerticalLayout mainLayout;
	@Override
	protected void init(VaadinRequest request) {
		mainLayout();
		setHeader();
		setLogo();
		setForm();
		dashboardTitle();
		
	}
	
	private void mainLayout() {
		mainLayout = new VerticalLayout();
		mainLayout.setMargin(true);
		mainLayout.setSizeFull();
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
		
		NativeSelect<String> unitSelect = new NativeSelect<>();
		unitSelect.setHeight("60px");
		ArrayList<String> items = new ArrayList<>();
		items.add("C");
		items.add("F");
		
		unitSelect.setItems(items);
		unitSelect.setValue(items.get(0));
		
		TextField cityText = new TextField();
		cityText.setWidth("70px");
		
		
		Button searchButton = new Button();
		searchButton.setIcon(VaadinIcons.SEARCH);
		
		
		form.addComponents(cityText);
		form.addComponents(unitSelect);
		form.addComponents(searchButton);
		mainLayout.addComponents(form);
		
	}
	
	private void dashboardTitle() {
		HorizontalLayout dashboardLayout = new HorizontalLayout();
		dashboardLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		
		Label location = new Label("Place to show: ");
		location.addStyleName(ValoTheme.LABEL_H2);
        location.addStyleName(ValoTheme.LABEL_LIGHT);
        
        Label currentTemp = new Label("20C");
        currentTemp.setStyleName(ValoTheme.LABEL_BOLD);
        currentTemp.setStyleName(ValoTheme.LABEL_H1);
        
        dashboardLayout.addComponents(location,currentTemp);
        mainLayout.addComponents(dashboardLayout);
	}
}
