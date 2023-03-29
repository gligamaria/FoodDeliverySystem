package Presentation;

import Business_Layer.DeliveryService;
import InterfaceControllers.ClientController;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class ClientWindow extends JFrame{

    JButton homeButton;
    JTable jTable;
    JFrame frame;
    public String productToAdd = "none";

    public String getProductToAdd() {return productToAdd;}

    public ClientWindow(DeliveryService deliveryService, User user) throws IOException, ClassNotFoundException {

        super("Products");
        frame = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600,350);
        this.setLayout(null);

        String[][] data = deliveryService.getData();
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};

        // Initializing the JTable
        jTable = new JTable(data, columnNames);
        jTable.setBounds(10, 70, 570, 200);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(70);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jTable);
        sp.setBounds(10,70,570,200);
        this.add(sp);

        ClientController clientController = new ClientController(this, deliveryService, user, null);

        Icon homeIcon = new ImageIcon("D:\\home_icon.png");
        homeButton = new JButton(homeIcon);
        homeButton.setBounds(10,10,40,40);
        homeButton.setBackground(Color.GRAY);
        homeButton.addActionListener(clientController);
        homeButton.setActionCommand("home");
        this.add(homeButton);

        JButton placeOrder = new JButton("Place order");
        placeOrder.setBounds(70,10,150,40);
        placeOrder.setBackground(Color.GRAY);
        placeOrder.addActionListener(clientController);
        placeOrder.setActionCommand("place order");
        this.add(placeOrder);

        JButton addToOrderButton = new JButton("Add to order");
        addToOrderButton.setBounds(240,10,150,40);
        addToOrderButton.setBackground(Color.GRAY);
        addToOrderButton.addActionListener(clientController);
        addToOrderButton.setActionCommand("add");
        this.add(addToOrderButton);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(410,10,150,40);
        searchButton.setBackground(Color.GRAY);
        searchButton.addActionListener(clientController);
        searchButton.setActionCommand("search");
        this.add(searchButton);

        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                productToAdd = data[jTable.getSelectedRow()][0];
            }
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
        });

        this.setVisible(true);
        this.revalidate();
        this.repaint();

    }

}