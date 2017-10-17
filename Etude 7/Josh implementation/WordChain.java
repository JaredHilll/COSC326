/**
 * Joined.java
 * COSC326 - Joined up writing
 * Created by Josh Lieshout, Jared Hill
 * For use with version 1.8.0_111 of the OpenJDK Runtime Environment
 */

import java.util.*;

/**
 * Class for keeping track of a chain of words.
 *
 * @author JoshLieshout
 * @author JaredHill
 */
public class WordChain {
  private ArrayList<String> chain;
  private String currentWord;
  
  public WordChain () {
    this.chain = new ArrayList<String>();
    this.currentWord = "";
  }
  
  public WordChain (String currentWord) {
    this.chain = new ArrayList<String>();
    this.currentWord = currentWord;
  }
  
  public WordChain (ArrayList<String> chain, String currentWord) {
    this.chain = chain;
    this.currentWord = currentWord;
  }
  
  public String getWord () {
    return currentWord;
  }
  
  public ArrayList<String> getChain () {
    return chain;
  }
  
  public void addChain (String word) {
    chain.add(word);
  }
}

