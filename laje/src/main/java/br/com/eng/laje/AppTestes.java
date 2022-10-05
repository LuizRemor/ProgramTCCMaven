package br.com.eng.laje;

import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.shape.Polyline;

public class AppTestes extends Application {  
   @Override 
   public void start(Stage stage) {        
      //Creating a polyline 
      Polyline polyline = new Polyline();
       
      //Adding coordinates to the polygon 
      polyline.getPoints().addAll(new Double[]{        
         200.0, 50.0, 
         220.0, 50.0, 
         220.0, 100.0,          
         220.0, 150.0,
         200.0, 150.0});
          
      //Creating a Group object  
      Group root = new Group(polyline);
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 300);
      
      //Setting title to the Stage 
      stage.setTitle("Drawing a Polyline"); 
         
      //Adding scene to the stage 
      stage.setScene(scene); 
         
      //Displaying the contents of the stage 
      stage.show(); 
   }    
   public static void main(String args[]){ 
      launch(args); 
   } 
}