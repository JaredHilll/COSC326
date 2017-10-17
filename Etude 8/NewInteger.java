import java.util.ArrayList;
import java.util.Collections;

public class NewInteger {
  
  private boolean negative;
  private ArrayList<Integer> digits = new ArrayList<Integer>();
  
  public NewInteger(String input) {
    if (input.charAt(0) != '-') {
      for (int i = 0; i < input.length(); i++) {
        digits.add(Integer.parseInt(input.substring(i, i+1)));
      }
    } else {
      this.negative = true;
      for (int i = 1; i < input.length(); i++) {
        digits.add(Integer.parseInt(input.substring(i, i+1)));
      }
    }
  }
  
  public String toString() {
    String s = "";
    if (this.negative == true) {
      s += "-";
      for (int i = 0; i < this.digits.size(); i++) {
        s += digits.get(i);
      }
      return s;
    } else {
      for (int i = 0; i < this.digits.size(); i++) {
        s += digits.get(i);
      }
      return s;
    }
  }
  
  public static ArrayList<Integer> removeZeros(NewInteger n){
    ArrayList<Integer> newList = new ArrayList<Integer>();
    
    for (int i = 0; i < n.digits.size(); i++) {
      if (n.digits.get(i) != 0) {
         newList.addAll(n.digits.subList(i, n.digits.size()));
         return newList;
      }
    }
    if (newList.size() == 0) {
      newList.add(0);
    }
    return newList; 
  }
  
  public static NewInteger add(NewInteger a, NewInteger b) {
    String result = "";
    String s = "";
    NewInteger t = new NewInteger("0");
    boolean negative = false;
    int temp = 0;
    int carry = 0;
    int ext = 0;
    
    if (a.negative && b.negative) {
      negative = true;
    }
    
    if (a.digits.size() < b.digits.size()) {
      for (int i = 0; i < b.digits.size(); i++){
        while (a.digits.size() != b.digits.size()) {
          a.digits.add(i, 0);
        }
      }
    } else if (b.digits.size() < a.digits.size()) {
      for (int i = 0; i < a.digits.size(); i++){
         while (a.digits.size() != b.digits.size()) {
           b.digits.add(i, 0);
         }
      }
    }

    if (!a.negative && b.negative) {
      t.digits = b.digits;
      return subtract(a, t);
    } else if (a.negative && !b.negative) {
      t.digits = a.digits;
      return subtract(b, t);
    } else {
      for (int i = a.digits.size()-1; i >= 0; i--) {
        temp = a.digits.get(i) + b.digits.get(i);
        if (temp > 9 && i == 0){
          ext = 1;
        }
        if (carry > 0) {
          temp += 1;
          carry = 0;
        }
        if (temp <= 9) {
          result += temp;
        } else {
          carry = 1;
          s = Integer.toString(temp);
          result += s.charAt(1);
        }
      }
      if (ext > 0){
        result += 1;
      }
    }
    
    NewInteger n = new NewInteger(result);
    Collections.reverse(n.digits);
    n.negative = negative;
    n.digits = removeZeros(n);
    a.digits = removeZeros(a);
    b.digits = removeZeros(b);
    
    return n;
  }

  public static NewInteger subtract(NewInteger a, NewInteger b) {
    int carry = 0;
    int diff;
    int temp = 0;
    int ext = 0;
    String s = "";
    boolean negative = false;
    String result = "";
    
    if (lessThan(a, b) == true) {
      negative = true;
    }
    
    if (a.digits.size() < b.digits.size()) {
      for (int i = 0; i < b.digits.size(); i++){
        while (a.digits.size() != b.digits.size()) {
          a.digits.add(i, 0);
        }
      }
    } else if (b.digits.size() < a.digits.size()) {
      for (int i = 0; i < a.digits.size(); i++){
        while (a.digits.size() != b.digits.size()) {
          b.digits.add(i, 0);
        }
      }
    }
    
    if (a.negative && !b.negative) {
      negative = true;
      for (int i = a.digits.size()-1; i >= 0; i--) {
        temp = a.digits.get(i) + b.digits.get(i);
        if (temp > 9 && i == 0){
          ext = 1;
        }
        if (carry > 0) {
          temp += 1;
          carry = 0;
        }
        if (temp <= 9) {
          result += temp;
        } else {
          carry = 1;
          s = Integer.toString(temp);
          result += s.charAt(1);
        }
      }
      if (ext > 0){
        result += 1;
      }
    } else if (!a.negative && b.negative) {
      negative = false;
      for (int i = a.digits.size()-1; i >= 0; i--) {
        temp = a.digits.get(i) + b.digits.get(i);
        if (temp > 9 && i == 0){
          ext = 1;
        }
        if (carry > 0) {
          temp += 1;
          carry = 0;
        }
        if (temp <= 9) {
          result += temp;
        } else {
          carry = 1;
          s = Integer.toString(temp);
          result += s.charAt(1);
        }
      }
      if (ext > 0){
        result += 1;
      }
    } else if (a.negative && b.negative) {
      for (int i = a.digits.size() - 1; i >= 0; i--){
        if (negative) {
          diff = b.digits.get(i) - a.digits.get(i) - carry;
        } else {
          diff = a.digits.get(i) - b.digits.get(i) - carry;
        }
        if (diff < 0) { 
          diff += 10;
          carry = 1;
        } else {
          carry = 0;
        }
        result +=diff;
      }
      if (negative) {
        negative = false;
      } else {
        negative = true;
      }
    } else {
      for (int i = a.digits.size() - 1; i >= 0; i--){
        if (negative) {
          diff = b.digits.get(i) - a.digits.get(i) - carry;
        } else {
          diff = a.digits.get(i) - b.digits.get(i) - carry;
        }
        if (diff < 0) { 
          diff += 10;
          carry = 1;
        } else {
          carry = 0;
        }
        result +=diff;
      }
    }
    
    NewInteger n = new NewInteger(result);
    Collections.reverse(n.digits);
    n.negative = negative;
    n.digits = removeZeros(n);
    a.digits = removeZeros(a);
    b.digits = removeZeros(b);
    
    return n;
  }
  
  public static NewInteger halve(NewInteger n) {
    String result = "";
    int r = 0;
    int s = 0;
    
    for (int i = 0; i < n.digits.size(); i++) {
      if (n.digits.get(i) > 0 && n.digits.get(i) % 2 == 1) {
        s = r;
        r = 5;
        result += (n.digits.get(i) / 2) + s;
      } else {
        s = r;
        result += (n.digits.get(i) / 2) + s;
        r = 0;
      }
    }
    
    NewInteger n2 = new NewInteger(result);
    n2.digits = removeZeros(n2);
    return n2;
  }
  
  public static boolean lessThan(NewInteger a, NewInteger b){
    boolean lessThan = false;
    
    if (a.negative == true && b.negative == false) {
      lessThan = true;
    } else if (a.negative == false && b.negative == true) {
      lessThan = false;
    } else if (a.digits.size() > b.digits.size()) {
      lessThan = false;
    } else if (a.digits.size() < b.digits.size()) {
      lessThan = true;
    } else if (a.digits.size() == b.digits.size()) {
      for (int i = 0; i < a.digits.size(); i++) {
        if (!a.negative && !b.negative) {
          if (a.digits.get(i).compareTo(b.digits.get(i)) < 0) {
            lessThan = true;
            return lessThan;
          } else if (a.digits.get(i).compareTo(b.digits.get(i)) > 0){
            lessThan = false;
            return lessThan;
          }
        } else {
          if (a.digits.get(i).compareTo(b.digits.get(i)) < 0) {
            lessThan = false;
            return lessThan;
          } else if (a.digits.get(i).compareTo(b.digits.get(i)) > 0){
            lessThan = true;
            return lessThan;
          }
        }
      }
    }   
    return lessThan;
  }
  
  public static boolean greaterThan(NewInteger a, NewInteger b){
    boolean greaterThan = false;
    
    if (a.negative == true && b.negative == false) {
      greaterThan = false;
    } else if (a.negative == false && b.negative == true) {
      greaterThan = true;
    } else if (a.digits.size() > b.digits.size()) {
      greaterThan = true;
    } else if (a.digits.size() < b.digits.size()) {
      greaterThan = false;
    } else if (a.digits.size() == b.digits.size()) {
      for (int i = 0; i < a.digits.size(); i++) {
        if (!a.negative && !b.negative) {
          if (a.digits.get(i).compareTo(b.digits.get(i)) > 0) {
            greaterThan = true;
            return greaterThan;
          } else if (a.digits.get(i).compareTo(b.digits.get(i)) < 0){
            greaterThan = false;
            return greaterThan;
          }
        } else {
          if (a.digits.get(i).compareTo(b.digits.get(i)) > 0) {
            greaterThan = false;
            return greaterThan;
          } else if (a.digits.get(i).compareTo(b.digits.get(i)) < 0){
            greaterThan = true;
            return greaterThan;
          }
        }
      }
    }
    
    return greaterThan;
  }
  
  public static boolean equals(NewInteger a, NewInteger b){
    boolean equal = true;
    
    a.digits = removeZeros(a);
    b.digits = removeZeros(b);
    
    if ((a.negative && !b.negative) || (!a.negative && b.negative)) {
      equal = false;
    } else if (a.digits.size() != b.digits.size()) {
      equal = false;
    } else {
      for (int i = 0; i < a.digits.size(); i++) {
        if (a.digits.get(i).compareTo(b.digits.get(i)) != 0) {
          equal = false;
        }
      }
    }
    
    return equal;
  }
  
  public static NewInteger multiply(NewInteger a, NewInteger b) {
    boolean negative = false;
    NewInteger a1;
    NewInteger num;
    ArrayList<Integer> one = new ArrayList<Integer>();
    ArrayList<Integer> two = new ArrayList<Integer>();
    String zero;
    int digits;
    int addAmount;
    int numZeroes = 0;
    String aTemp = a.toString();
    String bTemp = b.toString();
    
    if (a.toString().equals("0") || b.toString().equals("0")) {
      return new NewInteger("0");
    } else if (a.toString().equals("1")){
      return b;
    } else if (b.toString().equals("1")) {
      return a;
    }
    
    if (aTemp.length() < bTemp.length()) {
      String swap = aTemp;
      aTemp = bTemp;
      bTemp = swap;
    }
    
    if (aTemp.charAt(0) == '-') {
      aTemp = aTemp.substring(1);
    }
    if (bTemp.charAt(0) == '-') {
      bTemp = bTemp.substring(1);
    }
    for (int i = 0; i < aTemp.length(); i++) {
        one.add(Character.getNumericValue(aTemp.charAt(i)));
    }
    for (int i = 0; i < bTemp.length(); i++) {
        two.add(Character.getNumericValue(bTemp.charAt(i)));
    }
    
    a1 = new NewInteger(aTemp);
    
    if (a.negative && !b.negative) {
      negative = true;
    } else if (!a.negative && b.negative) {
      negative = true;
    }
    
    NewInteger n = new NewInteger("0");
    if (one.size() < two.size()) {
      digits = one.size();
    } else {
      digits = two.size();
    }
    for (int i = digits; i > 0; i--) {
      addAmount = two.get(i - 1);
      num = new NewInteger("0");
      zero = "";
      for (int j = 0; j < numZeroes; j++) {
        zero += "0";
      }    
      for (int j = 0; j < addAmount; j++) {
        num = add(num, a1);
      }    
      num = new NewInteger(num.toString() + zero);
      n = add(n, num);
      numZeroes++;
    }
    
    if (negative == true) {
      String result = "-" + n.toString();
      n = new NewInteger(result);
    }
    
    return n;
  }
  
  public static String divide(NewInteger a, NewInteger b) {
    String quotient = "";
    String remainder = "";
    String result = "";
    String a1 = a.toString();
    String b1 = b.toString();
    boolean bool = true;
    NewInteger n;
    ArrayList<Integer> numerator = new ArrayList<Integer>();
    
    if (a1.charAt(0) == '-') {
      a1 = a1.substring(1);
    }
    if (b1.charAt(0) == '-') {
      b1 = b1.substring(1);
    }
    
    for (int i = 0; i < a1.length(); i++) {
      numerator.add(Character.getNumericValue(a1.charAt(i)));
    }
    
    if (b.toString().equals("0")) {
      result = "Cannot divide by zero";
      return result;
    } else if (a.toString().equals("0")) {
      quotient = "0";
      remainder = "0";
      result = quotient + " " + remainder;
      return result;
    } else if (a1.length() < b1.length()) {
      quotient = "0";
      remainder = a.toString();
      result = quotient + " " + remainder;
      return result;
    } else if (equals(a, b)) {
      quotient = "1";
      remainder = "0";
      result = quotient + " " + remainder;
      return result;
    } else if (a1.length() < 9 && b1.length() < 9) {
      int a2 = Integer.parseInt(a1);
      int b2 = Integer.parseInt(b1);
      int quo = a2 / b2;
      int rem = a2 % b2;
        quotient = Integer.toString(quo);
        remainder = Integer.toString(rem);
      result = quotient + " " + remainder;
      return result;
    }
    
    int i = 0;
    int num = numerator.get(i);
    n = new NewInteger(Integer.toString(num));
    NewInteger temp = new NewInteger(a.toString());
    
    while (i < numerator.size()) {
      num = numerator.get(i);
      n = new NewInteger(remainder + num);
      if (lessThan(n, b)) {
        quotient = "";
        remainder = n.toString();
      } else {
        quotient = "";       
        while (lessThan(temp, b) == false) {
          quotient += "1";
          temp = subtract(temp, b);
          if (equals(temp, b)) {
            remainder = "0";
          } else {
            remainder = temp.toString();
          }
        }
        if (quotient.length() > 0 && quotient.charAt(0) != 0) {
          quotient = Integer.toString(quotient.length());
        }
      }
        result += quotient;
      i++;
    }
    quotient = result;
    
    while (remainder.length() > 1 && remainder.charAt(0) == '0') {
      remainder = remainder.substring(1);
    }
    if (remainder.length() > quotient.length()) {
      for (char c: remainder.toCharArray()) {
        if (c != remainder.charAt(0)) {
          bool = false;
        }
      }
      if (bool == true) {
        remainder = remainder.substring(remainder.length()/2);
      }
    }  
    
    result = quotient + " " + remainder;
    return result;
  }
  
  public static NewInteger gcd(NewInteger a, NewInteger b) {
    String result = "";
    NewInteger a1;
    NewInteger b1;
    
    if (lessThan(a, b)) {
      a1 = new NewInteger(b.toString());
      b1 = new NewInteger(a.toString());
    } else {
      a1 = new NewInteger(a.toString());
      b1 = new NewInteger(b.toString());
    }
 
    NewInteger temp = new NewInteger("-0");
    
    while (temp.toString().compareTo("0") != 0) {
      result = divide(a1, b1);
      for (int i = 0; i < result.length(); i++) {
        if (result.charAt(i) == ' ') {
          temp = new NewInteger(result.substring(i+1));
        }
      }
      if (!temp.toString().equals("0")) {
        a1 = b1;
        b1 = temp;
      }
    }
    
    NewInteger n = new NewInteger(b1.toString());
    return n;
  }
}  
