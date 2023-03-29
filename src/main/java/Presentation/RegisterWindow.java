package Presentation;

import Business_Layer.DeliveryService;
import InterfaceControllers.RegisterController;

import javax.swing.*;
import java.awt.*;

public class RegisterWindow extends JFrame {

    Color myPink = new Color(255, 204, 204);
    Color myDarkPink = new Color(226, 145, 145);
    JFrame frame;
    public JTextField username;
    public JTextField password;
    public JTextField fullName;

    public RegisterWindow(String givenUsername, String givenPassword, DeliveryService deliveryService){
        super("Register");
        frame = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600,350);
        this.setLayout(null);

        RegisterController registerController = new RegisterController(this, deliveryService);

        Icon homeIcon = new ImageIcon("D:\\home_icon.png");
        JButton homeButton = new JButton(homeIcon);
        homeButton.setBounds(10,10,40,40);
        homeButton.setBackground(Color.GRAY);
        homeButton.addActionListener(registerController);
        homeButton.setActionCommand("home");
        this.add(homeButton);

        JLabel labelUsername = new JLabel("Username");
        labelUsername.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        labelUsername.setBounds(120, 60, 150, 30);
        this.add(labelUsername);

        username = new JTextField();
        username.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        username.setBounds(210, 60, 200, 25);
        username.setText(givenUsername);
        username.setColumns(10);
        this.add(username);

        JLabel labelPassword = new JLabel("Password");
        labelPassword.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        labelPassword.setBounds(120, 110, 150, 30);
        this.add(labelPassword);

        password = new JTextField();
        password.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        password.setBounds(210, 110, 200, 25);
        password.setColumns(10);
        password.setText(givenPassword);
        this.add(password);

        JLabel labelFullName = new JLabel("Full name");
        labelFullName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        labelFullName.setBounds(120, 160, 150, 30);
        this.add(labelFullName);

        fullName = new JTextField();
        fullName.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        fullName.setBounds(210, 160, 200, 25);
        this.add(fullName);
        fullName.setColumns(10);

        JButton registerButton = new JButton("Register");
        registerButton .setBounds(225,220,150,40);
        registerButton .setBackground(Color.GRAY);
        registerButton.addActionListener(registerController);
        registerButton.setActionCommand("register");
        this.add(registerButton);

        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }
}
