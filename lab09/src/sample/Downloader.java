package sample;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import org.w3c.dom.*;
import javax.xml.parsers.*;

public class Downloader{
    private String sURL = "https://query1.finance.yahoo.com/v7/finance/download/GOOG?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
    private String result = "";
    private ArrayList<String[]> splitUp = new ArrayList<>();
    public void download() {
        try{
            URL netURL = new URL(sURL);

            URLConnection conn = netURL.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);

            InputStream inStream = conn.getInputStream();
            Scanner s = new Scanner(inStream);
            while(s.hasNext()){
                String line = s.nextLine();
                result += line + "\n";
                String[] columns = line.split(",");
                splitUp.add(columns);
            }
//            System.out.println(result);
            inStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<Double> parse(){
        ArrayList<Double> list = new ArrayList<>();
        boolean firstLine = true;
        for(int i = 0; i < splitUp.size(); i++){
            if(!firstLine){

                list.add(Double.valueOf(splitUp.get(i)[4]));
                System.out.println(list.get(i-1));
            } else {
                firstLine = false;
            }
        }
        return list;
    }

    public void setsURL(String sURL) {
        this.sURL = sURL;
    }
    public String getResult(){
        return result;
    }
}