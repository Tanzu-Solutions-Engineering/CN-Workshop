package io.pivotal.cloudnativespringui;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;

import io.pivotal.cloudnativespringui.CloudNativeSpringUiApplication.CityClient;

@SpringUI
@Theme("valo")
public class AppUI extends UI {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6544799702420451096L;

	private final CityClient cityClient;

    private final Grid<City> grid;

    @Autowired
    public AppUI(CityClient client) {
        this.cityClient = client;
        grid = new Grid<>(City.class);
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(grid);
        grid.setWidth(100, Unit.PERCENTAGE);
        grid.setHeight(100, Unit.PERCENTAGE);
        Collection<City> collection = new ArrayList<>();
        cityClient.getCities().forEach(collection::add);
        grid.setItems(collection);
    }
}