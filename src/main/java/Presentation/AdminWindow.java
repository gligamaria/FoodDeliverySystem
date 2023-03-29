package Presentation;

import Business_Layer.DeliveryService;
import InterfaceControllers.AdminController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class AdminWindow extends JFrame{

    Color myPink = new Color(255, 204, 204);
    Color myDarkPink = new Color(226, 145, 145);

    JButton homeButton;
    JButton addProductButton;
    JButton editProductButton;
    JButton deleteProductButton;
    JTable jTable;
    JFrame frame;
    public JTextField title;
    public String productToDelete = "none";

    public String getProductToDelete() {return productToDelete;}

    public AdminWindow(DeliveryService deliveryService) throws IOException, ClassNotFoundException {

        super("Products");
        frame = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600,350);
        this.setLayout(null);

        String[][] data = deliveryService.getData();
        String[] columnNames = {"Title", "Rating", "Calories", "Protein", "Fat", "Sodium", "Price"};

        AdminController adminController = new AdminController(this, deliveryService, this);

        // Initializing the JTable
        jTable = new JTable(data, columnNames);
        jTable.setBounds(10, 160, 570, 200);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(3).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(5).setPreferredWidth(70);
        jTable.getColumnModel().getColumn(6).setPreferredWidth(70);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(jTable);
        sp.setBounds(10,160,570,200);
        this.add(sp);

        Icon homeIcon = new ImageIcon("D:\\home_icon.png");
        homeButton = new JButton(homeIcon);
        homeButton.setBounds(10,10,40,40);
        homeButton.setBackground(Color.GRAY);
        homeButton.addActionListener(adminController);
        homeButton.setActionCommand("home");
        this.add(homeButton);

        addProductButton = new JButton("Add Product");
        addProductButton.setBounds(70,10,120,40);
        addProductButton.setBackground(Color.GRAY);
        addProductButton.addActionListener(adminController);
        addProductButton.setActionCommand("add");
        this.add(addProductButton);

        editProductButton = new JButton("Edit Product");
        editProductButton.setBounds(210,10,120,40);
        editProductButton.setBackground(Color.GRAY);
        editProductButton.addActionListener(adminController);
        editProductButton.setActionCommand("modify");
        this.add(editProductButton);

        deleteProductButton = new JButton("Delete Product");
        deleteProductButton.setBounds(350,10,120,40);
        deleteProductButton.setBackground(Color.GRAY);
        deleteProductButton.addActionListener(adminController);
        deleteProductButton.setActionCommand("delete");
        this.add(deleteProductButton);

        JButton importButton = new JButton("Imp. products");
        importButton.setBounds(70,60,120,40);
        importButton.setBackground(Color.GRAY);
        importButton.addActionListener(adminController);
        importButton.setActionCommand("import");
        this.add(importButton);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(210,60,120,40);
        searchButton.setBackground(Color.GRAY);
        searchButton.addActionListener(adminController);
        searchButton.setActionCommand("search");
        this.add(searchButton);

        JButton generateReports = new JButton("Gen. Reports");
        generateReports.setBounds(350,60,120,40);
        generateReports.setBackground(Color.GRAY);
        generateReports.addActionListener(adminController);
        generateReports.setActionCommand("generate");
        this.add(generateReports);

        JButton addToMenu = new JButton("Add_Menu");
        addToMenu.setBounds(70,110,120,40);
        addToMenu.setBackground(Color.GRAY);
        addToMenu.addActionListener(adminController);
        addToMenu.setActionCommand("add_menu");
        this.add(addToMenu);

        JButton finishMenu = new JButton("Finish_Menu");
        finishMenu.setBounds(210,110,120,40);
        finishMenu.setBackground(Color.GRAY);
        finishMenu.addActionListener(adminController);
        finishMenu.setActionCommand("finish_menu");
        this.add(finishMenu);

        title = new JTextField();
        title.setBounds(350,110,120,40);
        this.add(title);

        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                productToDelete = data[jTable.getSelectedRow()][0];
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