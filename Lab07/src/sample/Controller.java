package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.util.Map;
import java.util.TreeMap;


public class Controller {
    @FXML
    private Canvas mainCanvas;
    @FXML
    public GraphicsContext gc;
    @FXML
    public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
        FileLoader fl = new FileLoader("weatherwarnings-2015.csv");
        fl.loadFile();
        Map<String, Double> percentageMap = fl.getPercentageMap();
        Color[] pieColours = { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};
        double x = 200 ;
        double y = 100;
        int i = 0;
        double startAngle = 0;
        double endAngle = 0;
        for(Map.Entry<String,Double> entry : percentageMap.entrySet()) {
            String word = entry.getKey();
            Double value = entry.getValue();
            endAngle = startAngle + value * 360;
            System.out.println("startAngle = " + startAngle + "\n endAngle = " + endAngle);
            gc.setFill(pieColours[i]);
            gc.fillArc(x + 50,y,150,150,startAngle,endAngle-startAngle, ArcType.ROUND);
            gc.fillRect(x-150,y + 25 * i - 10, 20,10);
            gc.setFill(Color.BLACK);
            gc.fillText(word,x-125,y + 25*i);
            startAngle = endAngle;
            i++;
        }
//        for(int i=0;i<values.length;i++){
//            System.out.println(startAngle);
//            gc.setFill(colours[i]);
//            double chartPercent = values[i]/total;
//            double endAngle = startAngle + chartPercent * 360;
//
//            System.out.println(endAngle);
//            gc.fillArc(w,h,150,150,startAngle,endAngle-startAngle, ArcType.ROUND);
//            startAngle = endAngle;
//
//        }

    }

}
