package rak.healthcenter.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import rak.healthcenter.model.Patient;
import rak.healthcenter.model.Tool;
import rak.healthcenter.model.enums.HealthSystem;
import rak.healthcenter.model.enums.ZoomLevel;
import rak.healthcenter.ui.HealthStationHelper;
import rak.healthcenter.ui.LocationHighlightArea;
import rak.utility.ResourceLoader;

public class PatientViewController {
	
	@FXML private Pane patientView;
	
	private Patient patient;
	private HealthStationHelper healthStationHelper;
	
	private static HealthSystem currentSystem = HealthSystem.NONE;
	private static ZoomLevel currentZoom = ZoomLevel.NAKED_EYE;
	
	private static final int WIDTH_DEFAULT = 215;
	private static final int HEIGHT_DEFAULT = 570;
	
	public PatientViewController(Patient patient, HealthStationHelper healthStationHelper){
		this.patient = patient;
		this.healthStationHelper = healthStationHelper;
	}
	
	public void initialize(){
		drawView();
	}
	
	@FXML
	private void onClick(MouseEvent mouseEvent){
		LocationHighlightArea area = getHighlightAreaFromClick(mouseEvent.getX(), mouseEvent.getY());
		healthStationHelper.diagnoseSymptoms(currentSystem, area.getLocation(), currentZoom);
	}
	
	private LocationHighlightArea getHighlightAreaFromClick(double x, double y) {
		for (LocationHighlightArea area : LocationHighlightArea.values()){
			if (area.getPolygon().intersects(x, y, 1, 1)){
				return area;
			}
		}
		
		return null;
	}

	public void drawView(){
		GraphicsContext gg = createGraphicsContext();
		Tool tool = healthStationHelper.getHealthStation().getCurrentTool();
		if (tool == null){
			tool = new Tool();
		}
		drawBackground(gg, tool.getAffectedSystem());
		drawVisibleSymptoms(gg, tool.getAffectedSystem(),  tool.getAffectedLevel());
//		drawAllSymptoms(gg);
	}

	private GraphicsContext createGraphicsContext() {
		Canvas canvas = getOrCreateCanvas();
		
		GraphicsContext gg = canvas.getGraphicsContext2D();
		gg.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		return gg;
	}
	
	private Canvas getOrCreateCanvas() {
		for (Node child : patientView.getChildren()){
			if (child instanceof Canvas){
				return (Canvas) child;
			}
		}
		double width = patientView.getWidth() != 0 ? patientView.getWidth() : WIDTH_DEFAULT;
		double height = patientView.getHeight() != 0 ? patientView.getHeight() : HEIGHT_DEFAULT;
		Canvas canvas = new Canvas(width, height);
		patientView.getChildren().add(canvas);
		return canvas;
	}
	
	private void drawBackground(GraphicsContext gg, HealthSystem system) {
		Image image = new Image(ResourceLoader.getResourceAsStream("images/systems/" + system.getImageName()));
		gg.drawImage(image, 0, 0, image.getWidth()*2.5, image.getHeight()*2.5);
	}
	
	private void drawAllSymptoms(GraphicsContext gg) {
		Color highlight = new Color(1, 0, 0, .2f);
		gg.setFill(highlight);
		
		for (LocationHighlightArea area : LocationHighlightArea.values()){
			gg.fillPolygon(area.getXPoints(), area.getYPoints(), area.getXPoints().length);
		}		
	}
	
	
	private void drawVisibleSymptoms(GraphicsContext gg, HealthSystem system, ZoomLevel level) {
		Color highlight = new Color(1, 0, 0, .2f);
		gg.setFill(highlight);
		
		for (LocationHighlightArea area : LocationHighlightArea.values()){
			if (patient.hasSymptoms(system, area.getLocation(), level)){
				gg.fillPolygon(area.getXPoints(), area.getYPoints(), area.getXPoints().length);
			}
		}	
	}
	
	public static PatientViewController createGrid(Pane parentPane, HealthStationHelper healthStationHelper) {
		parentPane.getChildren().clear();
		String panelName = "view/PatientViewPanel.fxml";
		PatientViewController controller = new PatientViewController(healthStationHelper.getHealthStation().getPatient(), healthStationHelper);
		GridPane grid = MainMenuController.loadController(controller, panelName);
		controller.drawView();
		parentPane.getChildren().add(grid);
		return controller;
	}

}
