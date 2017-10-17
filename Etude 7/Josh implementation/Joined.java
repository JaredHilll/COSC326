/**
 * Joined.java
 * COSC326 - Joined up writing
 * Created by Josh Lieshout, Jared Hill
 * For use with version 1.8.0_111 of the OpenJDK Runtime Environment
 */

import java.util.*;
import java.io.*;

/**
 * Class for finding chains of words with the same suffixs as prefixs.
 *
 * @author JoshLieshout
 * @author JaredHill
 */
public class Joined {
  public static void main(String[] args) {
    if (args.length == 2) {
      Scanner scan = new Scanner(System.in);
      HashSet<String> dict = new HashSet<String>();
      while (scan.hasNext()) {
        dict.add(scan.next().toLowerCase());
      }
      WordChain chain = findSing(args[0], args[1], dict);
      if (chain.getChain().size() != 0) {
        System.out.print((chain.getChain().size()+2) + " ");
        for (int i = 0; i < chain.getChain().size(); i++) {
          System.out.print(chain.getChain().get(i) + " ");
        }
        System.out.print(chain.getWord() + " ");
        System.out.println(args[1]);
      } else {
        System.out.println("0");
      }
      
      chain = findDoub(args[0], args[1], dict);
      if (chain.getChain().size() != 0) {
        System.out.print((chain.getChain().size()+2) + " ");
        for (int i = 0; i < chain.getChain().size(); i++) {
          System.out.print(chain.getChain().get(i) + " ");
        }
        System.out.print(chain.getWord() + " ");
        System.out.println(args[1]);
      } else {
        System.out.println("0");
      }
    }
  }
  
  /**
   * Returns a chain of words that share suffixs and prefixes
   * from the first parameter to the second parameter where the common part
   * only needs to be at least as long as one half of one of the words.
   */
  public static WordChain findSing(String s, String tar, HashSet<String> dict) {
    ArrayList<WordChain> wordList = new ArrayList<WordChain>();
    wordList.add(new WordChain(s));
    HashSet<String> newDict = new HashSet<String>(dict);
    while (wordList.size() > 0) {
      for (int i = wordList.size()-1; i >= 0; i--) {
        String checkWord = wordList.get(i).getWord();
        for (int y = 0; y < checkWord.length()/2+1; y++) {
          String wordSub = checkWord.substring(y);
          int l = tar.length();
          String targHalf = tar.substring(0, l%2 == 0 ? l/2 : l/2+1);
          if (tar.length() >= wordSub.length()) {
            if (tar.substring(0, wordSub.length()).equals(wordSub)) {
              return wordList.get(i);
            }
          }
          if (checkWord.length() >= targHalf.length()) {
            int c = checkWord.length();
            int t = targHalf.length();
            if (checkWord.substring(c-t).equals(targHalf)) {
              return wordList.get(i);
            }
          }
          ArrayList<String> toRemove = new ArrayList<String>();
          for (String testWord : newDict) {
            for (int t = testWord.length()/2; t <= testWord.length(); t++) {
              String testSub = testWord.substring(0, t);
              if (testWord.length() >= wordSub.length()) {
                if (testWord.substring(0, wordSub.length()).equals(wordSub)) {
                  toRemove.add(testWord);
                  WordChain w = wordList.get(i);
                  ArrayList<String> c = new ArrayList<String>(w.getChain());
                  c.add(wordList.get(i).getWord());
                  WordChain curWordChain = new WordChain(c, testWord);
                  wordList.add(curWordChain);
                }
              } else if (checkWord.length() >= testSub.length()) {
                int cW = checkWord.length();
                if (checkWord.substring(cW-testSub.length()).equals(testSub)) {
                  toRemove.add(testWord);
                  WordChain wL = wordList.get(i);
                  ArrayList<String> cC = new ArrayList<String>(wL.getChain());
                  cC.add(wL.getWord());
                  WordChain curWordChain = new WordChain(cC, testWord);
                  wordList.add(curWordChain);
                }
              }
            }
          }
          for (String word : toRemove) {
            newDict.remove(word);
          }
        }
        wordList.remove(i);
      }
    }
    return new WordChain();
  }

   /**
   * Returns a chain of words that share suffixs and prefixes
   * from the first parameter to the second parameter where the common part
   * needs to be at least as long as both words.
   */ 
  public static WordChain findDoub(String s, String tar, HashSet<String> dict) {
    ArrayList<WordChain> wordList = new ArrayList<WordChain>();
    wordList.add(new WordChain(s));
    HashSet<String> newDict = new HashSet<String>(dict);
    while (wordList.size() > 0) {
      for (int i = wordList.size()-1; i >= 0; i--) {
        String checkWord = wordList.get(i).getWord();
        for (int y = 0; y < checkWord.length()/2+1; y++) {
          String wordSub = checkWord.substring(y);
          int ta = tar.length();
          String targHalf = tar.substring(0, ta%2 == 0 ? ta/2 : ta/2+1);
          if (tar.length() >= wordSub.length()) {
            if (tar.substring(0, wordSub.length()).equals(wordSub)) {
              int wS = wordSub.length();
              if (wS+1 > (ta+1)/2 && wS+1 > (checkWord.length()+1)/2) {
                return wordList.get(i);
              }
            }
          }
          if (checkWord.length() >= targHalf.length()) {
            int cW = checkWord.length();
            int tH = targHalf.length();
            if (checkWord.substring(cW-tH).equals(targHalf)) {
              if (tH+1 > (ta+1)/2 && tH+1 > (cW+1)/2) {
                return wordList.get(i);
              }
            }
          }
          ArrayList<String> toRemove = new ArrayList<String>();
          for (String testWord : newDict) {
            for (int t = testWord.length()/2; t <= testWord.length(); t++) {
              String testSub = testWord.substring(0, t);
              if (testWord.length() >= wordSub.length()) {
                if (testWord.substring(0, wordSub.length()).equals(wordSub)) {
                  int wS = wordSub.length();
                  int cW = checkWord.length();
                  if (wS+1 > (testWord.length()+1)/2 && wS+1 > (cW+1)/2) {
                    toRemove.add(testWord);
                    WordChain w = wordList.get(i);
                    ArrayList<String> c = new ArrayList<String>(w.getChain());
                    c.add(w.getWord());
                    WordChain curWordChain = new WordChain(c, testWord);
                    wordList.add(curWordChain);
                  }
                }
              } else if (checkWord.length() >= testSub.length()) {
                int cW = checkWord.length();
                int tS = testSub.length();
                if (checkWord.substring(cW-testSub.length()).equals(testSub)) {
                  if (tS+1 > (testWord.length()+1)/2 && tS+1 > (cW+1)/2) {
                    toRemove.add(testWord);
                    WordChain w = wordList.get(i);
                    ArrayList<String> c = new ArrayList<String>(w.getChain());
                    c.add(w.getWord());
                    WordChain curWordChain = new WordChain(c, testWord);
                    wordList.add(curWordChain);
                  }
                }
              }
            }
          }
          for (String word : toRemove) {
            newDict.remove(word);
          }
        }
        wordList.remove(i);
      }
    }
    return new WordChain();
  }
}

