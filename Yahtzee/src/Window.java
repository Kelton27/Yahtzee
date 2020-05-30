
import javax.swing.JOptionPane;

public class Window {
    //This method prints a message in a JOptionPane window
    public static void msg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
    //This method prints a message in a JOptionPane window button
    public String in(String msg) {
        return JOptionPane.showInputDialog(msg);
    }
}
