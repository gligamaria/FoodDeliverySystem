package Presentation;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow extends JFrame {

    JFrame frame;

    public ErrorWindow(String errorMessage){
        super("Error");
        frame = this;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(350,200);
        this.setLayout(null);

        JLabel labelUsername = new JLabel(errorMessage);
        labelUsername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        labelUsername.setBounds(60, 75, 250, 30);
        this.add(labelUsername);

        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }
}
