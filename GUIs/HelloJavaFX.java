


// CS-102: "Computing and Algorithms II"
// Prof. Giuseppe Turini
// Kettering University
// 2022-01-31

package GUIs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Note: the main class for a JavaFX application extends the javafx.application.Application class.
public class HelloJavaFX extends Application {

   // The start method is the main entry point for all JavaFX applications.
   // Stage: A JavaFX application defines the user interface container 
   //        by means of a stage and a scene.
   //        The JavaFX Stage class is the top-level JavaFX container.
   @Override
   public void start( Stage primaryStage ) {
   
      // EXAMPLE TO CHECK THE VERSION OF JAVA AND JAVAFX ON YOUR MACHINE.
      System.out.println("java.vendor = " + System.getProperty("java.vendor") );
      System.out.println("java.version = " + System.getProperty("java.version") );
      System.out.println("javafx.version = " + System.getProperty("javafx.version")); 
      System.out.println("javafx.runtime.version = " + System.getProperty("javafx.runtime.version")); 
   
      Button btn = new Button(); // Button control node.
      btn.setText( "Say 'Hello World'" ); // Button setup: button text.
      
      // Event handler for the button control node: print a message when the button is pressed.
      // setOnAction: Sets the value of the property onAction: 
      //              the button's action, which is invoked whenever the button is fired. 
      btn.setOnAction( 
         new EventHandler<ActionEvent>() {
            // handle: method invoked when a specific event 
            //         of the type for which this handler is registered happens.
            @Override
            public void handle( ActionEvent event ) { 
               System.out.println("Hello World!");
            }
         }
      );
      
      StackPane root = new StackPane(); // Root node of the scene graph: a resizeable layout node.
      root.getChildren().add( btn ); // Set the button control node as child of the root node (layout).
      // Scene: The A JavaFX application defines the user interface container by means of a stage and a scene.
      //        The JavaFX Scene class is the container for all content (hierarchical scene graph of nodes).
      Scene scene = new Scene( root, 500, 250 ); // Scene setup: root node, window width, and height.
      primaryStage.setTitle( "Hello World!" ); // Stage setup: window title.
      primaryStage.setScene(scene); // Stage setup: scene graph for the content.
      primaryStage.show(); // Show the window via the stage.
   }
   
   // The main method is not required for JavaFX applications.
   // However, it is useful when using an IDE in which JavaFX tools are not fully integrated.
   public static void main( String[] args ) {
      launch( args ); // Launch this standalone JavaFX application.
   }
   
}


