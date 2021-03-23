package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Canvas mainCanvas;
    @FXML
    public GraphicsContext gc;
    @FXML
    public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        Downloader dl = new Downloader();
        dl.download();
        ArrayList<Double> myList = dl.parse();

        Downloader dl2 = new Downloader();
        dl2.setsURL("https://query1.finance.yahoo.com/v7/finance/download/AAPL?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true");
        dl2.download();
        ArrayList<Double> myList2 = dl2.parse();
        drawLinePlot(myList,myList2);

    }

    public void drawLinePlot(ArrayList<Double> lineOne,ArrayList<Double> lineTwo){
        gc.strokeLine(50,700,1000,700);
        gc.strokeLine(50,700,50,0);
        plotLine(lineOne,100,Color.RED);
        plotLine(lineTwo,0,Color.BLUE);
        System.out.println(lineTwo.size());


    }
    public void plotLine(ArrayList<Double> line, double startingY, Color fill){
        gc.setStroke(fill);
        double currentX = 50;
        double defaultY = 750 - 50;
        for(int i = 0; i < line.size(); i++){
            try{
                gc.strokeLine(currentX,(defaultY - line.get(i)+startingY),
                        currentX + 10,defaultY - line.get(i+1)+startingY);
            } catch(IndexOutOfBoundsException e){
                return;
            }
            currentX += 10;
        }
    }
}
