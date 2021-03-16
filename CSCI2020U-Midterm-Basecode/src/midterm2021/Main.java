package midterm2021;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Main extends Application {
    Scene mainScene;
    Canvas animCanvas = new Canvas(250,250);


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




//                gc.drawImage(image,0,0);

                drawAnimation();

//               Creating the menu buttons
                Button animationReturnButton = new Button("Back To Main");
                animationReturnButton.setPrefWidth(200);
                animationGrid.add(animationReturnButton, 0,0);
                animationGrid.add(animCanvas,0,1);

                Scene animationScene = new Scene(animationGrid, 800, 800);
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
                Canvas mainCanvas = new Canvas(250,250);
                GraphicsContext gc = mainCanvas.getGraphicsContext2D();
                //draw J
                gc.fillRect(0,50,100,20);
                gc.fillRect(40,50,20,120);
                gc.fillRect(0,170,60,20);
                //draw B
                gc.fillRect(110,50,80,20);
                gc.fillRect(190,70,20,40);
                gc.fillRect(150,110,40,20);
                gc.fillRect(190,130,20,40);
                gc.fillRect(110,170,80,20);
                gc.fillRect(110,50,20,120);




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

                Label infoLabel= new Label();
                Label studentIDLabel= new Label();
                Label nameLabel= new Label();
                Label emailLabel= new Label();
                Label descriptionLabel = new Label();
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.setInitialDirectory(new File("."));
//                File xmlFile = fileChooser.showOpenDialog(primaryStage);
//                System.out.println(xmlFile.getPath());
                File xmlFile = new File("resources/info.xml");
                try {
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
                    Document document = docBuilder.parse(xmlFile);
                    document.getDocumentElement().normalize();
                    NodeList itemNodes = document.getElementsByTagName("info");

                    Node node = itemNodes.item(0);
                    infoLabel.setText(node.getNodeName());
                    Element itemElement = (Element)itemNodes.item(0);

                    Element element2 = (Element) itemNodes.item(0);
                    System.out.println(element2.getAttribute("id"));

                    studentIDLabel.setText("ID: " + itemElement.getAttribute("id") +                    "100752381");

                    nameLabel.setText( "Name & email:" + getTagValue("student",itemElement));

                    descriptionLabel.setText(getTagValue("software-description",itemElement));


                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



                aboutGrid.add(infoLabel,0,1);
                aboutGrid.add(studentIDLabel,0,2);
                aboutGrid.add(nameLabel,0,3);
                aboutGrid.add(emailLabel,0,4);
                aboutGrid.add(descriptionLabel,0,5);


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

    private static String getTagValue(String tagName, Element element) {
        NodeList tags = element.getElementsByTagName(tagName);
        if(tags.getLength() > 0){
            return tags.item(0).getTextContent();
        }
        else return null;
    }

    private int frameWidth = 32;
    private int frameHeight = 32;
    private int totalHeight = 260;
    private int numFrames = 3;
    private int sourceHeightOffset = 3;
    private int sourceWidthOffset = 287;
    private int frameIndex = 0;
    int cycleNum = 0;

    private void drawAnimation() {
        GraphicsContext gc = animCanvas.getGraphicsContext2D();
//loading image sprite using relative path
        Image image = new Image(getClass().getClassLoader().getResource("ducks.png").toString());

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(333), new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {
//                background rect fpr the characters
                gc.setFill(javafx.scene.paint.Color.BLACK);
                gc.fillRect(0, 0, frameWidth, frameHeight);

                gc.drawImage(image, sourceWidthOffset, sourceHeightOffset, frameWidth, frameHeight, 0, 0, frameWidth, frameHeight);
//                we want to vary frameIndex from 0 to numFrames (not included) using the %
                frameIndex = (frameIndex+1) % numFrames;

                if(frameIndex == 0){
                    cycleNum++;
                } if(cycleNum >= 2*4){
                    sourceHeightOffset += 36;
                    cycleNum = 0;
                } if(sourceHeightOffset > totalHeight){
                    sourceHeightOffset =3;
                }

//                System.out.println(cycleNum);

//                calculating the offset height based on the frameIndex
                sourceWidthOffset = 287 + frameWidth*frameIndex;

            }
        }));

//      Starting the timeline
        timeline.playFromStart();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
