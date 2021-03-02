package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.awt.*;

public class Controller {
    @FXML
    private Canvas mainCanvas;
    @FXML
    private GraphicsContext gc;
    @FXML

    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1, 308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,1472362.0,1583521.9,1613246.3
    };
    private static String[] ageGroups = { "18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static int[] purchasesByAgeGroup = { 648, 1021, 2453, 3173, 1868, 2247};
    private static Color[] pieColours = { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    public void initialize(){
        gc = mainCanvas.getGraphicsContext2D();
//        double[] data = {1,2,3,4,5,6,7,8};
//        double[] data2 = {0,1,2,3,4,5,6,7};

//        drawGraph(100,300,avgHousingPricesByYear,Color.RED,0);
//        drawGraph(100,300,avgCommercialPricesByYear,Color.BLUE,100/avgCommercialPricesByYear.length);

        drawLabGraph(100,300,avgHousingPricesByYear,avgCommercialPricesByYear,Color.RED,Color.BLUE);
        gc.setFill(Color.INDIANRED);
//        gc.fillArc(100,100,150,150,0,90, ArcType.ROUND);

        drawPie(200,150,purchasesByAgeGroup,pieColours);
    }
    public void drawPie(int w, int h, int[] values,Color[] colours){
        double total = 0;

        for(int entry: values){
            total += entry;
        }
        double startAngle = 0;

        for(int i=0;i<values.length;i++){
            System.out.println(startAngle);
            gc.setFill(colours[i]);
            double chartPercent = values[i]/total;
            double endAngle = startAngle + chartPercent * 360;

            System.out.println(endAngle);
            gc.fillArc(w,h,150,150,startAngle,endAngle-startAngle, ArcType.ROUND);
            startAngle = endAngle;

        }
    }
    //the graph made by this code show the sets proportional to each other
    public void drawLabGraph(int w, int h, double[] data1, double[] data2, Color colour1,Color colour2){
        gc.setFill(colour1);
        double xInc = (w/data1.length);

        double maxVal = Double.NEGATIVE_INFINITY;
        double minVal = Double.MAX_VALUE;

        for(double val: data1){
            if(val > maxVal){
                maxVal = val;
            } if(val < minVal){
                minVal = val;
            }
        }
        for(double val: data2){
            if(val > maxVal){
                maxVal = val;
            } if(val < minVal){
                minVal = val;
            }
        }
//        System.out.println(maxVal);
//        System.out.println(minVal);
        double x = 0;
        for(double val: data1){
            double height = ((val-minVal) / (maxVal - minVal)) * h;
            gc.fillRect(x,(h-height),xInc,height+10);
            x += 2 * xInc;
        }
        gc.setFill(colour2);

        x = xInc;
        for(double val: data2){
            double height = ((val-minVal) / (maxVal - minVal)) * h;
            gc.fillRect(x,(h-height),xInc,height+10);
            x += 2 * xInc;
        }
        x += 2 * xInc;

    }
    public void drawGraph(int w, int h, double[] data, Color colour,int startX){
        gc.setFill(colour);
        double xInc = (w/data.length);

        double maxVal = Double.NEGATIVE_INFINITY;
        double minVal = Double.MAX_VALUE;

        for(double val: data){
            if(val > maxVal){
                maxVal = val;
            } if(val < minVal){
                minVal = val;
            }
        }
//        System.out.println(maxVal);
//        System.out.println(minVal);
        double x = startX;
        for(double val: data){
            double height = ((val-minVal) / (maxVal - minVal)) * h;
            gc.fillRect(x,(h-height),xInc,height);
            x += 2 * xInc;
        }

    }
}
