package Presentation;

import Business_Layer.DeliveryService;
import InterfaceControllers.GenerateReportsController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GenerateReportsWindow extends JFrame {

    Color myPink = new Color(255, 204, 204);
    Color myDarkPink = new Color(226, 145, 145);

    JButton homeButton;
    public JTextField hourMin, hourMax, numberOfTimes, numberOfOrders, sum, date;
    JFrame frame;

    public GenerateReportsWindow(DeliveryService deliveryService) throws IOException, ClassNotFoundException {

        super("Generate reports");
        frame = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600,350);
        this.setLayout(null);

        GenerateReportsController generateReportsController = new GenerateReportsController(this, deliveryService);

        Icon homeIcon = new ImageIcon("D:\\home_icon.png");
        homeButton = new JButton(homeIcon);
        homeButton.setBounds(10,10,40,40);
        homeButton.setBackground(Color.GRAY);
        homeButton.addActionListener(generateReportsController);
        homeButton.setActionCommand("home");
        this.add(homeButton);

        JLabel firstLabel = new JLabel("1st report", SwingConstants.RIGHT);
        firstLabel.setBounds(10, 30,150, 20);
        this.add(firstLabel);

        JLabel secondLabel = new JLabel("2nd report", SwingConstants.RIGHT);
        secondLabel.setBounds(10, 70, 150, 20);
        this.add(secondLabel);

        JLabel thirdLabel = new JLabel("3rd report", SwingConstants.RIGHT);
        thirdLabel.setBounds(10, 110, 150, 20);
        this.add(thirdLabel);

        JLabel forthLabel = new JLabel("4th report", SwingConstants.RIGHT);
        forthLabel.setBounds(10, 150, 150, 20);
        this.add(forthLabel);

        hourMin = new JTextField();
        hourMin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        hourMin.setBounds(170, 30, 100, 25);
        this.add(hourMin);
        hourMin.setColumns(10);

        hourMax = new JTextField();
        hourMax.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        hourMax.setBounds(300, 30, 100, 25);
        this.add(hourMax);
        hourMax.setColumns(10);

        JButton firstButton = new JButton("Generate");
        firstButton.setBounds(430, 30, 100, 25);
        firstButton.setBackground(myPink);
        firstButton.addActionListener(generateReportsController);
        firstButton.setActionCommand("first");
        this.add(firstButton);

        numberOfTimes = new JTextField();
        numberOfTimes.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        numberOfTimes.setBounds(170, 70, 100, 25);
        this.add(numberOfTimes);
        numberOfTimes.setColumns(10);

        JButton secondButton = new JButton("Generate");
        secondButton.setBounds(430, 70, 100, 25);
        secondButton.setBackground(myPink);
        secondButton.addActionListener(generateReportsController);
        secondButton.setActionCommand("second");
        this.add(secondButton);

        numberOfOrders = new JTextField();
        numberOfOrders.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        numberOfOrders.setBounds(170, 110, 100, 25);
        this.add(numberOfOrders);
        numberOfOrders.setColumns(10);

        sum = new JTextField();
        sum.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        sum.setBounds(300, 110, 100, 25);
        this.add(sum);
        sum.setColumns(10);

        JButton thirdButton = new JButton("Generate");
        thirdButton.setBounds(430, 110, 100, 25);
        thirdButton.setBackground(myPink);
        thirdButton.addActionListener(generateReportsController);
        thirdButton.setActionCommand("third");
        this.add(thirdButton);

        date = new JTextField();
        date.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        date.setBounds(170, 150, 100, 25);
        this.add(date);
        date.setColumns(10);

        JButton forthButton = new JButton("Generate");
        forthButton.setBounds(430, 150, 100, 25);
        forthButton.setBackground(myPink);
        forthButton.addActionListener(generateReportsController);
        forthButton.setActionCommand("fourth");
        this.add(forthButton);

        this.setVisible(true);
        this.revalidate();
        this.repaint();

    }

}