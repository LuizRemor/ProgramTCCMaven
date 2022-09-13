package br.com.eng.laje;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {

		try {
						
			FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
			
			Parent parent = loader.load();
			
			Scene mainScene = new Scene(parent);
			
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Programa para Cálculo de Lajes");
			primaryStage.show();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static Scene getMainScene() {
		
		return mainScene;
	}

	public static void main(String[] args) {
		launch(args);
	}

}