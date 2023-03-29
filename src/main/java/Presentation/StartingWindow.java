package Presentation;

import Business_Layer.DeliveryService;
import InterfaceControllers.StartingController;

import javax.swing.*;
import java.awt.*;

public class StartingWindow extends JFrame {

    Color myPink = new Color(255, 204, 204);
    Color myDarkPink = new Color(226, 145, 145);
    JFrame frame;
    public JTextField username;
    public JTextField password;

    public StartingWindow(DeliveryService deliveryService){
        super("Hello!");
        frame = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600,350);
        this.setLayout(null);

        StartingController startingController = new StartingController(this, deliveryService);

        JLabel labelUsername = new JLabel("Username");
        labelUsername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        labelUsername.setBounds(120, 60, 150, 30);
        this.add(labelUsername);

        username = new JTextField();
        username.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        username.setBounds(210, 60, 200, 25);
        this.add(username);
        username.setColumns(10);

        JLabel labelPassword = new JLabel("Password");
        labelPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        labelPassword.setBounds(120, 110, 150, 30);
        this.add(labelPassword);

        password = new JTextField();
        password.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        password.setBounds(210, 110, 200, 25);
        this.add(password);
        password.setColumns(10);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(225,160,150,40);
        loginButton.setBackground(Color.GRAY);
        loginButton.addActionListener(startingController);
        loginButton.setActionCommand("login");
        this.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton .setBounds(225,220,150,40);
        registerButton .setBackground(Color.GRAY);
        registerButton.addActionListener(startingController);
        registerButton.setActionCommand("register");
        this.add(registerButton);

        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }
}
