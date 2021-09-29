package weatherApp.view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
		
	}
	
	private void mainLayout() {
		mainLayout = new VerticalLayout();
		mainLayout.setMargin(true);
		mainLayout.setSizeFull();
		setContent(mainLayout);
	}
	
	private void setHeader(){
		HorizontalLayout header = new HorizontalLayout();
		header.setDefaultComponentAlignment(Alignment.BOTTOM_CENTER);
		Label title = new Label("Simple Weather APP");
		title.addStyleName(ValoTheme.LABEL_BOLD);
		header.addComponent(title);
		
		mainLayout.addComponents(header);
	}
	
	
}