package Presentation;

import Business_Layer.DeliveryService;
import InterfaceControllers.FoundProductsController;
import Model.MenuItem;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class FoundProductsWindow extends JFrame{

    JButton homeButton;
    JTable jTable;
    JFrame frame;

    public FoundProductsWindow(DeliveryService deliveryService, HashSet<MenuItem> foundProducts, User user){

        super("Results");
        frame = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600,350);
        this.setLayout(null);

        String[][] data = getData(foundProducts);
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};

        FoundProductsController foundProductsController = new FoundProductsController(this, deliveryService, user);

        // Initializing the JTable
        jTable = new JTable(data, columnNames);
        jTable.setBounds(10, 100, 570, 200);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(70);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jTable);
        sp.setBounds(10,100,570,200);
        this.add(sp);

        JLabel text = new JLabel("These are your results:");
        text.setBounds(10,65,300,20);
        this.add(text);

        Icon homeIcon = new ImageIcon("D:\\home_icon.png");
        homeButton = new JButton(homeIcon);
        homeButton.setBounds(10,10,40,40);
        homeButton.setBackground(Color.GRAY);
        homeButton.addActionListener(foundProductsController);
        homeButton.setActionCommand("home");
        this.add(homeButton);

        this.setVisible(true);
        this.revalidate();
        this.repaint();

    }

    public String[][] getData(HashSet<MenuItem> menuItems){
        int value = 0;
        if(menuItems != null)
            value = menuItems.size();
        String [][] data = new String[value][7];
        int i = 0;
        assert menuItems != null;
        for (MenuItem menuItem:menuItems){
            data[i][0] = menuItem.getTitle();
            data[i][1] = String.valueOf(menuItem.getRating());
            data[i][2] = String.valueOf(menuItem.getCalories());
            data[i][3] = String.valueOf(menuItem.getProtein());
            data[i][4] = String.valueOf(menuItem.getFat());
            data[i][5] = String.valueOf(menuItem.getSodium());
            data[i][6] = String.valueOf(menuItem.getPrice());
            i++;
        }
        return data;
    }
}