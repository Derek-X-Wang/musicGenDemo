import javax.swing.*;
import java.awt.*;

public class MusicUI {
    private JPanel panel1;
    private JButton stratButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MusicUI");
        frame.setContentPane(new MusicUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
