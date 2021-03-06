package rak.healthcenter;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import rak.utility.ResourceLoader;

public class HealthCenterApplication extends Application {
	private final static String MAIN_MENU = "MainMenu";
	private static Stage primaryStage;
	
	public static void main(String[] args) {
		ResourceLoader.setRootClass(HealthCenterApplication.class);
		launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		HealthCenterApplication.primaryStage = primaryStage;
		
        primaryStage.setTitle("Health Center");
        String pathToResource = "images/Logo Icon.png";
        primaryStage.getIcons().add(new Image(ResourceLoader.getResourceAsStream(pathToResource)));
        setScene(MAIN_MENU);
        primaryStage.show();
	}

	public static void setScene(String sceneName){
		try {
			Scene scene = loadFXML(sceneName);
			primaryStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Scene loadFXML(String fileName) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(HealthCenterApplication.class.getResource("view/" + fileName + ".fxml"));
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		return scene;
	}
	
}
