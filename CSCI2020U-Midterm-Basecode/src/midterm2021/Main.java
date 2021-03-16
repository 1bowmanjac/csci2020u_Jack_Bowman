package midterm2021;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    // initialize other scenes
    Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("CSCI2020U - Midterm");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);


//      Creating the menu buttons
        Button btApp1 = new Button("Animation");
        btApp1.setPrefWidth(200);
        Button btApp2 = new Button("2D Graphics");
        btApp2.setPrefWidth(200);
        Button btApp3 = new Button("About");
        btApp3.setPrefWidth(200);



//        setting the Event handlers for each buttons
        btApp1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                TODO: Replace the scene or the root
//                      Display the "Animation" in the CENTER,
//                      and a "Back to Main" on the TOP
                GridPane animationGrid = new GridPane();
                animationGrid.setAlignment(Pos.CENTER);
                animationGrid.setHgap(10);
                animationGrid.setVgap(10);


//               Creating the menu buttons
                Button animationReturnButton = new Button("Back To Main");
                animationReturnButton.setPrefWidth(200);
                animationGrid.add(animationReturnButton, 0,0);
                Scene animationScene = new Scene(animationGrid, 300, 275);
                primaryStage.setScene(animationScene);
                primaryStage.show();
                animationReturnButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked on animationReturnButton");
                        primaryStage.setScene(mainScene);
                    }
                });
                 System.out.println("Clicked on Animation button");
            }
        });

        btApp2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                TODO: Replace the scene or the root
//                    Display the "2D Drawing" in the CENTER,
//                    and a "Back to Main" on the TOP
                GridPane GraphicsGrid = new GridPane();
                GraphicsGrid.setAlignment(Pos.CENTER);
                GraphicsGrid.setHgap(10);
                GraphicsGrid.setVgap(10);

                //start drawing JB
                Canvas mainCanvas = new Canvas(300,300);
                GraphicsContext gc = mainCanvas.getGraphicsContext2D();
                //draw J
                gc.fillRect(50,50,100,20);
                gc.fillRect(90,50,20,120);
                gc.fillRect(50,170,60,20);
                //draw B
                gc.fillRect(160,50,80,20);
                gc.fillRect(240,70,20,40);
                gc.fillRect(200,110,40,20);
                gc.fillRect(240,130,20,40);
                gc.fillRect(160,170,80,20);
                gc.fillRect(160,50,20,120);




                GraphicsGrid.add(mainCanvas,0,1);

//               Creating the menu buttons
                Button graphicsReturnBtn = new Button("Back To Main");
                graphicsReturnBtn.setPrefWidth(200);
                GraphicsGrid.add(graphicsReturnBtn, 0,0);
                Scene graphicsScene = new Scene(GraphicsGrid, 300, 275);
                primaryStage.setScene(graphicsScene);
                primaryStage.show();
                System.out.println("Clicked on Graphics 2D button");









                graphicsReturnBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked on graphicsReturnBtn");
                        primaryStage.setScene(mainScene);
                    }
                });

            }
        });

        btApp3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
//                TODO: Replace the scene or the root
//                    Display the "About" in the CENTER,
//                    and a "Back to Main" on the TOP
                GridPane aboutGrid = new GridPane();
                aboutGrid.setAlignment(Pos.CENTER);
                aboutGrid.setHgap(10);
                aboutGrid.setVgap(10);


//               Creating the menu buttons
                Button aboutReturnBtn = new Button("Back To Main");
                aboutReturnBtn.setPrefWidth(200);
                aboutGrid.add(aboutReturnBtn, 0,0);
                Scene aboutScene = new Scene(aboutGrid, 300, 275);
                primaryStage.setScene(aboutScene);
                primaryStage.show();
                System.out.println("Clicked on About button");

                aboutReturnBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("Clicked on aboutReturnBtn");
                        primaryStage.setScene(mainScene);
                    }
                });
            }
        });

//        Add the menu buttons to the grid
        grid.add(btApp1, 0,1);
        grid.add(btApp2, 0,2);
        grid.add(btApp3, 0,3);

        // main App Scene
        mainScene = new Scene(grid, 300, 275);


        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
