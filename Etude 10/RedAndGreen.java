import java.util.TreeMap;
import java.util.Scanner;

public class RedAndGreen {
  
  private static TreeMap<Integer, Character> factors = new TreeMap<Integer, Character>();
 // private static int max = 0;
  private static String output = "";
  
  //NEED TO MAKE FASTER. TRY NOT RESETTING THE TREEMAP, AND FIND A WAY TO REMOVE A FOR LOOP
  //ONLY CALL SET COLOUR IF ITERATED VALUE IS LOWER THAN MAXIMUM KEY?
  public static void setColour(int num){
    int d = 0;
    int greenCount = 0;
    int redCount = 0;
    // 1 is the first near factor, near factors cant be above n/2
    for (int i = 1; i <= num/2; i++){
      //rearrange k = n/d: d = n/k
      d = num/i;
      //k is a near factor if n divided by d results in k
      if(i == num/d){
        if (factors.containsKey(i)) {
          if (factors.get(i) == 'G') {
            greenCount++;
          } else if (factors.get(i) == 'R') {
            redCount++;
          }
        } else {
          setColour(i);
          //max = num;
        }
      }
    }
    if (greenCount <= redCount){
      factors.put(num, 'G');
      return;
    } else {
      factors.put(num, 'R');
      return; 
    }
  }
  
  public static void main(String[] args){
    Scanner scan = null;   
    int a = 0;
    int b = 0;    
    String cur = "";
    try{
      scan = new Scanner(System.in);
      while (scan.hasNextLine()) {
        cur = scan.nextLine();
        if (cur.startsWith("#") == true){
          cur = scan.nextLine();
        }
        if (cur.length() > 0) {
          for (int i = 0; i < cur.length(); i++) {
            if (cur.charAt(i) == ' '){
              a = Integer.parseInt(cur.substring(0, i));
              b = Integer.parseInt(cur.substring(i+1, cur.length()));
            }
          }          
        } else if (cur.length() == 0) {
          if (a == 1) {
            for (int i = a; i < a+b; i++){
              setColour(i);
              if (factors.containsKey(i)) {
                output += factors.get(i);
              }
            }
          } else if (a > 1 && a < b) {
            for (int i = 1; i < b+1; i++){
              setColour(i);
            }
            for (int i = a+1; i < b+1; i++) {
              if (factors.containsKey(i)) {
                output += factors.get(i);
              }
            }
          } else {
            for (int i = 1; i < a+(a-b); i++){
              setColour(i);
            }
            for (int i = a; i < a+b; i++) {
              if (factors.containsKey(i)) {
                output += factors.get(i);
              }
            }
          }
          
          System.out.println(a + " " + b);
          System.out.println("# " + output);
          System.out.println();
          output = "";
        }
      }
    } finally {
      if (scan != null){
        if (a == 1) {
          for (int i = a; i < a+b; i++){
            setColour(i);
            if (factors.containsKey(i)) {
              output += factors.get(i);
            }
          }
        } else if (a > 1 && a < b) {
          for (int i = 1; i < b+1; i++){
            setColour(i);
          }
          for (int i = a+1; i < b+1; i++) {
            if (factors.containsKey(i)) {
              output += factors.get(i);
            }
          }
        } else {
          for (int i = 1; i < a+(a-b); i++){
            setColour(i);
          }
          for (int i = a; i < a+b; i++) {
            if (factors.containsKey(i)) {
              output += factors.get(i);
            }
          }
        }
        System.out.println(a + " " + b);
        System.out.println("# " + output);
        scan.close();
      }
    }
  }
}