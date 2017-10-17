/** COSC326 - Etude 2
  * @author Jared Hill
  * July 2017
  **/

import java.util.Scanner;

public class Syllable {
  
  /**
   * Takes a String and an int, and determines if the char
   * at the location of the index in the string is a vowel.
   * @param word the word to examine
   * @param index the character location in the word to examine
   **/
  public static boolean vowel(String word, int index){
    boolean vowel = false;
      if (word.charAt(index) == 'a' || word.charAt(index) == 'e' 
            || word.charAt(index) == 'i' || word.charAt(index) == 'o'
            || word.charAt(index) == 'u' || word.charAt(index) == 'y') {
        vowel = true;
      }
      return vowel;
  }
  
  /**
   * Method to count the syllables in a word.
   * Checks and counts the amount of vowels in a word,
   * then corrects the count based on whether there are
   * consecutive vowels.
   * @param word the word used to count syllables.
   **/
  public static int count(String word){
    int count = 0;
    for (int i = 0; i < word.length(); i++){
      if (vowel(word, i) == true) {
        count++; 
      }
    }
    
    for (int i = 1; i < word.length()-1; i++){
      if ((vowel(word, i) == true && vowel(word, i-1) == true)){
        count --;
      }
    }

    if (count == 0){
      count = 1;
    }
    return count;
  }
  
  /**
   * Main method.
   * Creates a scanner, and cycles through each line of standard input,
   * counting the syllables in each word. Prints the output returned by
   * the count method.
   **/
  public static void main(String[] args) {
    Scanner scan = null;
    System.out.println("Enter words to count their syllables: ");
    
    try{
    scan = new Scanner(System.in);
    
    while(scan.hasNextLine()){
      String word = scan.nextLine();
      System.out.println("\n" + count(word));
    }
    } finally {
      if (scan != null){
        scan.close();
      }
    }
  }
}