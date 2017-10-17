import java.util.Scanner;

public class Arithmetic {
  
  public static void main(String[] args) {
    String[] symbols = {"+", "-", "*", "/", "gcd", "<", ">", "="};
    String[] arguments = new String[3];
    NewInteger a;
    NewInteger b;
    Scanner scan = new Scanner(System.in);
    char operator;
    
    while (scan.hasNextLine()) {
      String s = scan.nextLine();
      arguments = s.split(" ");
      if (arguments.length < 3) {
          System.out.println("# Syntax error");
          System.out.println();
          continue;
        }
      operator = arguments[1].charAt(0);
      
      if (s.contains("#") == false && s.length() > 0) {
        System.out.println(s);
        
        for (int i = 0; i < arguments.length; i++) {
          if (arguments[i].isEmpty()) {
            System.out.println("# Syntax error");
            System.out.println();
          }
        }
        
        for (int i = 0; i < symbols.length; i++) {
          if (arguments[1].equals(symbols[i])) {
            break;
          }
          if (i == symbols.length-1){
            System.out.println("# Syntax error");
            System.out.println();
          }
        }
        
        if (arguments[0].matches("-?\\d+") == false || arguments[2].matches("-?\\d+") == false) {
          System.out.println("# Syntax error");
        } else {
          a = new NewInteger(arguments[0]);
          b = new NewInteger(arguments[2]);
          
          switch(operator) {
            case '+':
              System.out.println("# " + NewInteger.add(a, b).toString());
              System.out.println();
              break;
            case '-':
              System.out.println("# " + NewInteger.subtract(a, b).toString());
              System.out.println();
              break;
            case '*':
              System.out.println("# " + NewInteger.multiply(a, b).toString());
              System.out.println();
              break;
            case '/':
              System.out.println("# " + NewInteger.divide(a, b));
              System.out.println();
              break;
            case 'g':
              System.out.println("# " + NewInteger.gcd(a, b).toString());
              System.out.println();
              break;
            case '<':
              System.out.println("# " + NewInteger.lessThan(a, b));
              System.out.println();
              break;
            case '>':
              System.out.println("# " + NewInteger.greaterThan(a, b));
              System.out.println();
              break;
            case '=':
              System.out.println("# " + NewInteger.equals(a, b));
              System.out.println();
              break;
          }
        }
      }
    }
  }
}
