import java.util.*;
import java.io.*;

public class DirectInput{
  
  private int curX = 0;
  private int curY = 0;
  private char lastDirect = 'N';
  private ArrayList<char[]> direct = new ArrayList<char[]>();
  private HashMap<String, Character> colourM = new HashMap<String, Character>();
  private HashMap<Character, Integer> coIndex = new HashMap<Character, Integer>();
  private HashMap<Character, Integer> dIndex = new HashMap<Character, Integer>();
  private String key;
  private int runtim;
  
  public void move(){
    
    int dire =0;
    char[] control = direct.get(direct.size()-1);
    char curCol = ' ';
    int runtim = this.runtim;
    for(int i = 0; i < runtim; i++){
      key = "";
      key ="x"+curX+"y"+curY;
      if(colourM.containsKey(key)){
        curCol = ((colourM.get(key)).toString()).charAt(0);
      }else{
        curCol = direct.get(0)[0];
      }
      
      control = direct.get((Integer)coIndex.get(curCol));
      
      dire = (Integer)dIndex.get(lastDirect);
      colourM.put(key, control[dire+4]);
      lastDirect = control[dire];
      switch(lastDirect){
        case 'N':
          curY++;
          break;
        case 'E':
          curX++;
          break;
        case 'S':
          curY--;
          break;
        case 'W':
          curX--;
          break;
      }
    }
    System.out.print("# " + curX + " " + curY);
  }
  
  public static void main(String[] args) throws FileNotFoundException{
    Scanner sc = null;
    DirectInput dI = new DirectInput();
    char curChar = ' ';
    char[] instruct = new char[0];
    
    try {
      sc = new Scanner(System.in);
      while(sc.hasNextLine()){
        String curStr = sc.nextLine();
        String temp = curStr;
        if (curStr.length() > 0 && curStr.contains(" ") == false) {
          dI.runtim = Integer.parseInt(temp);
        } 
        curStr = curStr.replace(" ", "");
        if(curStr.length() > 0){
          if (curStr.charAt(0) != '#'){
            int len = curStr.length();
            instruct = new char[len];
            for(int i = 0; i <len; i++){
              curChar = curStr.charAt(i);
              if(curChar!=' '){
                instruct[i] =curChar;
              }
            }
            dI.direct.add(instruct);
          }
        } else if (curStr.length() == 0 || sc.hasNextLine()){
          for(int i = 0; i < dI.direct.size()-1; i++){
            curChar = dI.direct.get(i)[0];
            dI.coIndex.put(curChar, i);
          }
          dI.dIndex.put('N', 1);
          dI.dIndex.put('E', 2);
          dI.dIndex.put('S', 3);
          dI.dIndex.put('W', 4);
          for(int t = 0; t <dI.direct.size(); t++){
            for(int i = 0; i <dI.direct.get(t).length; i++){
              if (i < dI.direct.get(t).length-2 && i > 0){
                if (dI.direct.get(t)[i + 1] == 'E' || dI.direct.get(t)[i + 1] =='W' 
                      || dI.direct.get(t)[i + 1] == 'N'|| dI.direct.get(t)[i + 1] == 'S' 
                      || dI.direct.get(t)[i - 1] == 'E' || dI.direct.get(t)[i - 1] == 'W'
                      || dI.direct.get(t)[i - 1] == 'N' || dI.direct.get(t)[i - 1] == 'S'){
                  if (i == 1 || i == 5){
                    System.out.print(" ");
                  }
                }
              }
              System.out.print(dI.direct.get(t)[i]);
            }
            System.out.println();
          }
          dI.move();
          System.out.println();
          System.out.println();
          dI = new DirectInput();
        }
      } 
    } finally {
      if (sc != null){
        for(int i = 0; i < dI.direct.size()-1; i++){
          curChar = dI.direct.get(i)[0];
          dI.coIndex.put(curChar, i);
        }
        dI.dIndex.put('N', 1);
        dI.dIndex.put('E', 2);
        dI.dIndex.put('S', 3);
        dI.dIndex.put('W', 4);
        for(int t = 0; t <dI.direct.size(); t++){
          for(int i = 0; i <dI.direct.get(t).length; i++){
            if (i < dI.direct.get(t).length-2 && i > 0){
              if (dI.direct.get(t)[i + 1] == 'E' || dI.direct.get(t)[i + 1] =='W' 
                    || dI.direct.get(t)[i + 1] == 'N'|| dI.direct.get(t)[i + 1] == 'S' 
                    || dI.direct.get(t)[i - 1] == 'E' || dI.direct.get(t)[i - 1] == 'W'
                    || dI.direct.get(t)[i - 1] == 'N' || dI.direct.get(t)[i - 1] == 'S'){
                if (i == 1 || i == 5){
                  System.out.print(" ");
                }
              }
            }
            System.out.print(dI.direct.get(t)[i]);
          }
          System.out.println();
        }
        dI.move();
        System.out.println();
        dI = new DirectInput();
      }
      sc.close();
    }
  }
}
