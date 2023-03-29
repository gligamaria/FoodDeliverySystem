package Presentation;

import Business_Layer.DeliveryService;
import InterfaceControllers.EmployeeController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

public class EmployeeWindow extends JFrame implements Observer {

    JFrame frame;
    JTable jTable;
    String[][] data;
    DeliveryService deliveryService;
    JButton notificationButton;

    @Override
    public void update(Observable o, Object arg) {
        initializeTable();
        notificationButton.setBackground(Color.pink);
    }

    public EmployeeWindow(DeliveryService deliveryService) throws IOException, ClassNotFoundException {

        super("Welcome, employee!");
        frame = this;
        this.deliveryService = deliveryService;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600,350);
        this.setLayout(null);

        initializeTable();

        EmployeeController employeeController = new EmployeeController(this, deliveryService);

        Icon homeIcon = new ImageIcon("D:\\home_icon.png");
        JButton homeButton = new JButton(homeIcon);
        homeButton.setBounds(10,10,40,40);
        homeButton.setBackground(Color.GRAY);
        homeButton.addActionListener(employeeController);
        homeButton.setActionCommand("home");
        this.add(homeButton);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setBounds(250,10,150,40);
        refreshButton.setBackground(Color.GRAY);
        refreshButton.addActionListener(employeeController);
        refreshButton.setActionCommand("refresh");
        this.add(refreshButton);

        notificationButton = new JButton();
        notificationButton.setBounds(70,10,150,40);
        notificationButton.setBackground(Color.GRAY);
        this.add(notificationButton);

        this.setVisible(true);
        this.revalidate();
        this.repaint();

    }

    public void initializeTable(){
        data = deliveryService.employeeOrders(deliveryService.getOrders());
        String[] columnNames = {"Orders"};

        // Initializing the JTable
        jTable = new JTable(data, columnNames);
        jTable.setBounds(10, 70, 570, 200);
        //jTable.getColumnModel().getColumn(0).setPreferredWidth(570);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jTable);
        sp.setBounds(10,70,570,200);
        this.add(sp);
    }

}
