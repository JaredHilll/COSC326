import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class Poker {

  public static boolean isValid(String input){
    Pattern p = Pattern.compile("(((([0-9])|([0-9][0-3]))|t|j|q|k|a)(c|d|h|s)"
                                  + "( |-|/)){4}((([0-9])|([0-9][0-3]))"
                                  + "|t|j|q|k|a)(c|d|h|s)",
                                Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(input);
    boolean valid = m.matches();
    
    if ((input.contains(" ") && input.contains("-")) 
          || (input.contains("/") && input.contains("-"))
          || (input.contains(" ") && input.contains("/"))){
      valid = false;
    }
    
    return valid;
  }
  
  public static void aceHigh(int[] number, char[]suit){
    int j = 0;
    int temp = 0;
    char temp2 = ' ';
    ArrayList<Integer> numTemp = new ArrayList<Integer>();
    ArrayList<Character> suitTemp = new ArrayList<Character>();
    for (int i = 0; i < number.length; i++){
      numTemp.add(number[i]);
      suitTemp.add(suit[i]);
    }
    while (j < 4){
      for (int i = 0; i < number.length; i++){
        if (numTemp.get(i) == 1){
          temp = numTemp.get(i);
          temp2 = suitTemp.get(i);
          
          numTemp.add(temp);
          suitTemp.add(temp2);
          
          numTemp.remove(i);
          suitTemp.remove(i);
        }
      }
      j++;
    }
    for(int i = 0; i < number.length; i++){
      number[i] = numTemp.get(i);
      suit[i] = suitTemp.get(i);
    }
  }
  
  public static void swap(char[] suit, int index1, int index2){
    char swap;
    swap = suit[index1];
    suit[index1] = suit[index2];
    suit[index2] = swap;
  }
  
  public static void suitUpper(char[] suit) {
    for (int i = 0; i < suit.length; i++){
      suit[i] = Character.toUpperCase(suit[i]);
    } 
  }
  
  public static void suitSort(int[] number, char[] suit){
    int j = 0;
    while (j < 4){
      int i = 0;
      while (i < number.length-1){
        if (number[i] == number[i+1]){
          if (suit[i+1] == 'C' && suit[i] =='D'){
            swap(suit, i+1, i);
            i++;
          } else if (suit[i+1] == 'C' && suit[i] =='H'){
            swap(suit, i+1, i);
            i++;
          } else if (suit[i+1] == 'C' && suit[i] =='S'){
            swap(suit, i+1, i);
            i++;
          } else if (suit[i+1] == 'D' && suit[i] =='H') {
            swap(suit, i+1, i);
            i++;
          } else if (suit[i+1] == 'D' && suit[i] =='S') {
            swap(suit, i+1, i);
            i++;
          } else if (suit[i+1] == 'H' && suit[i] =='S'){
            swap(suit, i+1, i);
            i++;
          }
        }
        i++;
      }
      j++;
    }
  }
  
  public static void numSort(int[]num, char[] suit){
    int j = 0;
    int swap = 0;
    int min = 0;
    int min_inx = 0;
    char swap2 = 0;
    int min_inx2 = 0;
    
    for (int i = 0; i < num.length-1; i++){
      min = num[i];
      min_inx = i;
      
      min_inx2 = i;
      for (j = i+1; j < num.length; j++){
        if (min > num[j]){
          min = num[j];
          min_inx = j;
          
          min_inx2 = j;
        }
      }
      swap = num[min_inx];
      num[min_inx] = num[i];
      num[i] = swap;
      
      swap2 = suit[min_inx2];
      suit[min_inx2] = suit[i];
      suit[i] = swap2;     
    }
  }
  
  public static void main(String[] args){
    int[] number = new int[5];
    char[] suit = new char[5];
    int count = 0;
    int numberCount = 1;
    int suitCount = 0;
    Scanner scan = null;
    StringBuilder output = new StringBuilder();
    
    try {
      scan = new Scanner(System.in);
      while (scan.hasNextLine()){
        String handString = scan.nextLine();
        if (isValid(handString) == true){
          for (int i = 0; i < handString.length(); i++){
            if (count == number.length){
              count = 0;
            }
            if ((handString.charAt(i) != ' ') || (handString.charAt(i) != '/') 
                  || (handString.charAt(i) != '-')){
              if (handString.charAt(i) == '1' && handString.charAt(i + 1) == '0'){
                number[count] = 10;
                count++;
                i++;
              } else if (handString.charAt(i) == '1' 
                           && handString.charAt(i + 1) == '1'){
                number[count] = 11;
                count++;
                i++;
              } else if(handString.charAt(i) == '1' 
                          && handString.charAt(i + 1) == '2') {
                number[count] = 12;
                count++;
                i++;
              } else if (handString.charAt(i) == '1'
                           && handString.charAt(i + 1) == '3') {
                number[count] = 13;
                count++;
                i++;
              } else if (handString.charAt(i) == '1' || handString.charAt(i) == '2'
                           || handString.charAt(i) == '3' 
                           || handString.charAt(i) == '4' 
                           || handString.charAt(i) == '5' 
                           || handString.charAt(i) == '6'
                           || handString.charAt(i) == '7' 
                           || handString.charAt(i) == '8' 
                           || handString.charAt(i) == '9') {
                int num = Integer.parseInt(handString.substring(i, i + 1));
                number[count] = num;
                count++;
              } else if (handString.charAt(i) == 'a' 
                           || handString.charAt(i) == 'A'){
                number[count] = 1;
                count++;
              } else if (handString.charAt(i) == 't' 
                           || handString.charAt(i) == 'T'){
                number[count] = 10;
                count++;
              } else if (handString.charAt(i) == 'j' 
                           || handString.charAt(i) == 'J') {
                number[count] = 11;
                count++;
              } else if (handString.charAt(i) == 'q' 
                           || handString.charAt(i) == 'Q'){
                number[count] = 12;
                count++;
              } else if (handString.charAt(i) == 'k' 
                           || handString.charAt(i) == 'K') {
                number[count] = 13;
                count++;
              }
            }
          }
          for (int i = 0; i < handString.length(); i++){
            if (count == number.length){
              count = 0;
            }
            if (handString.charAt(i) == 'c' || handString.charAt(i) == 'C' 
                  || handString.charAt(i) == 'h' ||
                handString.charAt(i) == 'H' || handString.charAt(i) == 'd' 
                  || handString.charAt(i) == 'D' ||
                handString.charAt(i) == 's' || handString.charAt(i) == 'S') {
              suit[count] = handString.charAt(i);
              count++;
            }
          }
          suitUpper(suit);
          numSort(number, suit);
          aceHigh(number, suit);
          suitSort(number, suit);
          for (int i = 0; i < number.length-1; i++){
            if (number[i] == number[i + 1]){
              if (suit[i] == suit[i + 1]){
                suitCount++;
              }
              numberCount++;
            }
          }
          
          for (int i = 0; i < number.length; i++){
            output.append(number[i]);
            output.append(suit[i]);
            output.append(" ");
          }
          for (int i = 0; i < output.length(); i++){
            if (output.charAt(i) == '1' && output.charAt(i + 1) == '1'){
              output.replace(i, i + 2, "J");
            } else if (output.charAt(i) == '1' && output.charAt(i + 1) == '2'){
              output.replace(i, i + 2, "Q");
            } else if (output.charAt(i) == '1' && output.charAt(i + 1) == '3'){
              output.replace(i, i + 2, "K");
            } else if (output.charAt(i) == '1' && output.charAt(i + 1) == '0'){
              output.replace(i, i + 2, "10");
            } else if (output.charAt(i) == '1'){
              output.replace(i, i + 1, "A");           
            }
          }
          if (numberCount > 4) {
            System.out.println("Invalid: " + handString);
            numberCount = 1;
          } else if (suitCount > 1) {
            System.out.println("Invalid: " + handString);
            suitCount = 0;
          } else {
            output.deleteCharAt(output.length()-1);
            System.out.print(output + "\n");
          }
          output = new StringBuilder();
        } else if (isValid(handString) == false) {
          System.out.println("Invalid: " + handString);
        }  
      }
    } finally {
      if (scan != null){
        scan.close();
      }
    }
  }
}
