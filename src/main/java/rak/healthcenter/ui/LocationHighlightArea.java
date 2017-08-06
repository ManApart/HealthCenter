package rak.healthcenter.ui;

import javafx.scene.shape.Polygon;
import rak.healthcenter.model.enums.Location;

public enum LocationHighlightArea {
	HEAD(Location.HEAD, new double[] {70, 70, 145, 145}, new double[] {0, 90, 90, 0}),
	ARM_LEFT(Location.ARM_LEFT, new double[] {0,0,60,60}, new double[] {100, 330, 330, 100}),
	ARM_RIGHT(Location.ARM_RIGHT, new double[] {155, 155, 215, 215}, new double[] {100, 330, 330, 100}),
	TORSO(Location.TORSO, new double[] {60, 60, 155, 155}, new double[] {90, 300, 300, 90}),
	LEG_LEFT(Location.LEG_LEFT, new double[] {60, 60, 107, 107}, new double[] {300, 558, 558, 300}),
	LEG_RIGHT(Location.LEG_RIGHT, new double[] {108, 108, 155, 155}, new double[] {300, 558, 558, 300});
	
	private Location location;
	private double[] xPoints;
	private double[] yPoints;
	private Polygon polygon;
	
	private LocationHighlightArea(Location location, double[] xPoints, double[] yPoints){
		this.location = location;
		this.xPoints = xPoints;
		this.yPoints = yPoints;
		createPolygon();
	}
	
	
	private void createPolygon() {
		double[] points = new double[xPoints.length*2];
		for (int i=0; i < xPoints.length; i++){
			points[i*2] = xPoints[i];
			points[i*2 + 1] = yPoints[i];
		}
		polygon = new Polygon(points);		
	}


	public Location getLocation(){
		return location;
	}
	
	public double[] getXPoints(){
		return xPoints;
	}
	
	public double[] getYPoints(){
		return yPoints;
	}
	
	public Polygon getPolygon(){
		return polygon;
	}
	
}
