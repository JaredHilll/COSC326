import java.awt.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Quilt extends JPanel {
  
  private static ArrayList<Double> scale = new ArrayList<Double>();
  private static int window = 600;
  private static int baseScale = 200;
  private static ArrayList<Integer> tempX = new ArrayList<Integer>();
  private static ArrayList<Integer> tempY = new ArrayList<Integer>();
  private static ArrayList<Integer> red = new ArrayList<Integer>();
  private static ArrayList<Integer> green = new ArrayList<Integer>();
  private static ArrayList<Integer> blue = new ArrayList<Integer>();
  
  public void input(Scanner scan) {
    while (scan.hasNext()){
        scale.add(scan.nextDouble());
        red.add(scan.nextInt());
        green.add(scan.nextInt());
        blue.add(scan.nextInt());
    }
  }
  
  public void resize() {
    double maxScale = 0.0;
    for (int i = 0; i < scale.size(); i++){
      if (maxScale < scale.get(i)){
        maxScale = scale.get(i);
      }
    }
    if (maxScale > 2.0){
      for (int i = 0; i < scale.size(); i++){
        scale.set(i, scale.get(i)/3);
      }
    } else if (maxScale > 1.0){
    for (int i = 0; i < scale.size(); i++){
        scale.set(i, scale.get(i)/2);
      }
    } else if (maxScale < 0.5) {
      for (int i = 0; i < scale.size(); i++){
        scale.set(i, scale.get(i)*3);
      }
    }else if (maxScale < 1.0) {
      for (int i = 0; i < scale.size(); i++){
        scale.set(i, scale.get(i)*2);
      }
    }
  }
  
  public void drawRec(Graphics g) {
    int inc = 0;
    Scanner scan = new Scanner(System.in);
    int curScale = 0;
    int x = this.getWidth()/2;
    int y = this.getHeight()/2;
    int curX = 0;
    int curY = 0;
    int count = 0;
    ArrayList<Integer> cX = new ArrayList<Integer>();
    ArrayList<Integer> cY = new ArrayList<Integer>();
    int prevScale = 0;
    
    input(scan);
    resize();
    
    g.setColor(new Color(red.get(0), green.get(0), blue.get(0)));
    curScale = (int)(baseScale * scale.get(0));
    prevScale = curScale;
    g.fillRect(x - (curScale/2), y - (curScale/2), curScale, curScale);
    
    for (int i = 1; i < scale.size(); i++) {
      prevScale = curScale;
      g.setColor(new Color(red.get(i), green.get(i), blue.get(i)));
      x = x - (curScale/2);
      y = y - (curScale/2);
      curScale = (int)(baseScale * scale.get(i));
      if (count < 4) {
        curX = x - (curScale/2);
        curY = y - (curScale/2);
        cX.add(curX);
        cY.add(curY);
        count++;
        g.fillRect(curX, curY, curScale, curScale);
        
        curX = x + prevScale - (curScale/2);
        curY = y - (curScale/2);
        cX.add(curX);
        cY.add(curY);
        count++;
        g.fillRect(curX, curY, curScale, curScale);
        
        curX = x - (curScale/2);
        curY = y + prevScale - (curScale/2);
        cX.add(curX);
        cY.add(curY);
        count++;
        g.fillRect(curX, curY, curScale, curScale);
        
        curX = x + prevScale - (curScale/2);
        curY = y + prevScale - (curScale/2);
        cX.add(curX);
        cY.add(curY);
        count++;
        g.fillRect(curX, curY, curScale, curScale);
      } else {
          while (cX.size() > 0){
            curX = (int)cX.get(inc) - (curScale/2);
            curY = (int)cY.get(inc) - (curScale/2);
            tempX.add(curX);
            tempY.add(curY);
            g.fillRect(curX, curY, curScale, curScale);
            
            curX = (int)cX.get(inc) + prevScale - (curScale/2);
            curY = (int)cY.get(inc) - (curScale/2);
            tempX.add(curX);
            tempY.add(curY);
            g.fillRect(curX, curY, curScale, curScale);
            
            curX = (int)cX.get(inc) - (curScale/2);
            curY = (int)cY.get(inc) + prevScale - (curScale/2);
            tempX.add(curX);
            tempY.add(curY);
            g.fillRect(curX, curY, curScale, curScale);
            
            curX = (int)cX.get(inc) + prevScale - (curScale/2);
            curY = (int)cY.get(inc) + prevScale - (curScale/2);
            tempX.add(curX);
            tempY.add(curY);
            g.fillRect(curX, curY, curScale, curScale);
            
            cX.remove(0);
            cY.remove(0);
          }
          cX.addAll(tempX);
          cY.addAll(tempY);
          tempX.clear();
          tempY.clear();
        }
      }
  }
  
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    this.setBackground(Color.WHITE);
    drawRec(g);
  }
  
   public static void main(String[] args){
    JFrame f = new JFrame("Quilt");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Quilt q = new Quilt();
    f.add(q);
    f.setSize(window, window);
    f.setVisible(true);
  }
}