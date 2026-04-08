import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleTest {
    private JPanel panel1;
    private JLabel myLabel;
    private JButton actionButton;


    public SimpleTest() {
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myLabel.setText("Witaj w Swing!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SimpleTest");
        frame.setContentPane(new SimpleTest().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}