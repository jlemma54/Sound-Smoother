import java.awt.*; 
import java.util.*;
import java.util.ArrayList;
import java.io.*;

public class SoundSmoother {
  // instance variables 
  private Color rawDataPen = Color.BLACK; 
  private Color smoothPen = Color.RED;
  private int xInterval;
  private int xAxis; 
  private int yAxis;
  private int[] xValues; 
  private int[] yValues; 
  private int[] yValuesNew; 
  private int scaleFactor; 
  private Scanner sc; 
  final static BasicStroke STROKE = new BasicStroke(1.0f); 
  final static BasicStroke WIDESTROKE = new BasicStroke(3.0f);

  // Constructor filename x interval y scale factor

  public SoundSmoother(String fileName, int axInterval, int aScaleFactor, int xBaseLine, int yBaseLine){
    xAxis = xBaseLine; 
    yAxis = yBaseLine; 
    scaleFactor = aScaleFactor; 
    xInterval = axInterval;
    loadData(fileName);   //loads data
         

  }

  //private helper method: loadData

  private void loadData(String filePath){
    //temporary x  and y ArraryLists
    ArrayList<Integer> txValues = new ArrayList<Integer>();
    ArrayList<Integer> tyValues = new ArrayList<Integer>(); 
   
    //reading file and catches any errors
    try{
      File myObj = new File(filePath);
      sc = new Scanner(myObj);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found!");

    }
    //currentX = xAaxis
    int currentX = xAxis;
    while(sc.hasNextDouble()){
      //Convert data to the y pixel value and add it to the y-ArrayList
         
      int currentY = (int)(sc.nextDouble());
      currentY = (yAxis - currentY) * scaleFactor;
      tyValues.add(currentY);
      // add currentX to the xArrayList   
      txValues.add(currentX); 
      currentX += xInterval;
      //end

    }

    //convert arrayList to array
    xValues = new int[txValues.size()]; 
    for(int i = 0; i < txValues.size(); i++){
      xValues[i] = txValues.get(i);
    }

    System.out.println(xValues.length);

    //convert arrayList to array
    yValues = new int[tyValues.size()]; 
    for(int i = 0; i < tyValues.size(); i++){
      yValues[i] = tyValues.get(i); 
    }

    System.out.println(yValues.length);
    
  }



  //smoothIt method 
  public void smoothIt(){
    
    // calculate end point values 
    
    //creates temporary array
    int[] yValuesT = new int[yValues.length];

    //checks to see if the yValuesNew array is null
    //if it is, it will clone the origianl array and instatiate the yValuesNew array,
    //otherwise it justs clones the previous version of the yValuesNew array.
    if(yValuesNew == null){
      yValuesT = yValues.clone();
      yValuesNew = new int[yValues.length];
      
    }
    else{
      yValuesT = yValuesNew.clone();
    }

    System.out.println(yValuesT[yValuesT.length-1]);


    //calculates the first endpoint for yValuesNew
    yValuesNew[0] = (int)((yValuesT[0] + yValuesT[1])/2);
    
    //calculates the last endpoint for yValuesNew 
    yValuesNew[yValuesT.length-1] = (int)((yValuesT[yValuesT.length-1] + yValuesT[yValuesT.length-2])/2);
    
    //calculates all other values for yValuesNew
    for(int i = 1; i < yValuesNew.length-1; i++){
      yValuesNew[i] = (int)((yValuesT[i-1] + yValuesT[i] + yValuesT[i+1])/3);
    }
    
  }

  //paint method

  public void paint(Graphics g){
    //draws the original sound data
    Graphics2D g2 = (Graphics2D) g; 
    g2.setColor(rawDataPen);
    g2.setStroke(STROKE);
    g2.drawPolyline(xValues, yValues, xValues.length); 
    //checks to see if yValuesNew is null so it knows when to draw new data
    if(yValuesNew != null){
      g2.setColor(smoothPen); 
      g2.setStroke(WIDESTROKE);
      g2.drawPolyline(xValues, yValuesNew, xValues.length);
    }
    

    




  }

}
