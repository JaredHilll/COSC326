import javax.swing.*;

public class QuiltApp {
  public static void main(String[] args){
    JFrame f = new JFrame("Quilt");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Quilt q = new Quilt();
    f.add(q);
    f.setSize(500, 500);
    f.setVisible(true);
  }
}
