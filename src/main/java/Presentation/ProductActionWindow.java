package Presentation;

import Business_Layer.DeliveryService;
import InterfaceControllers.ProductActionController;
import Model.MenuItem;
import Model.User;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ProductActionWindow extends JFrame {

    Color myPink = new Color(255, 204, 204);
    Color myDarkPink = new Color(226, 145, 145);

    JButton homeButton;
    public JTextField titleText;
    public JTextField ratingText;
    public JTextField caloriesText;
    public JTextField proteinText;
    public JTextField fatText;
    public JTextField sodiumText;
    public JTextField priceText;
    JFrame frame;

    public ProductActionWindow(DeliveryService deliveryService, String action, MenuItem menuItem, User user) throws IOException, ClassNotFoundException {

        super(action);
        frame = this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600,350);
        this.setLayout(null);

        ProductActionController productActionController = new ProductActionController(this, deliveryService, menuItem, user);

        Icon homeIcon = new ImageIcon("D:\\home_icon.png");
        homeButton = new JButton(homeIcon);
        homeButton.setBounds(10,10,40,40);
        homeButton.setBackground(Color.GRAY);
        homeButton.addActionListener(productActionController);
        homeButton.setActionCommand("home");
        this.add(homeButton);

        JLabel titleLabel = new JLabel("Title", SwingConstants.RIGHT);
        titleLabel.setBounds(100, 30, 50, 20);
        this.add(titleLabel);

        JLabel ratingLabel = new JLabel("Rating", SwingConstants.RIGHT);
        ratingLabel.setBounds(100, 70, 50, 20);
        this.add(ratingLabel);

        JLabel caloriesLabel = new JLabel("Calories", SwingConstants.RIGHT);
        caloriesLabel.setBounds(100, 110, 50, 20);
        this.add(caloriesLabel);

        JLabel proteinLabel = new JLabel("Protein", SwingConstants.RIGHT);
        proteinLabel.setBounds(100, 150, 50, 20);
        this.add(proteinLabel);

        JLabel fatLabel = new JLabel("Fat", SwingConstants.RIGHT);
        fatLabel.setBounds(100, 190, 50, 20);
        this.add(fatLabel);

        JLabel sodiumLabel = new JLabel("Sodium", SwingConstants.RIGHT);
        sodiumLabel.setBounds(100, 230, 50, 20);
        this.add(sodiumLabel);

        JLabel priceLabel = new JLabel("Price", SwingConstants.RIGHT);
        priceLabel.setBounds(100, 270, 50, 20);
        this.add(priceLabel);

        titleText = new JTextField();
        titleText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        titleText.setBounds(170, 30, 100, 25);
        this.add(titleText);
        titleText.setColumns(10);

        ratingText = new JTextField();
        ratingText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        ratingText.setBounds(170, 70, 100, 25);
        this.add(ratingText);
        ratingText.setColumns(10);

        caloriesText = new JTextField();
        caloriesText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        caloriesText.setBounds(170, 110, 100, 25);
        this.add(caloriesText);
        caloriesText.setColumns(10);

        proteinText = new JTextField();
        proteinText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        proteinText.setBounds(170, 150, 100, 25);
        this.add(proteinText);
        proteinText.setColumns(10);

        fatText = new JTextField();
        fatText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        fatText.setBounds(170, 190, 100, 25);
        this.add(fatText);
        fatText.setColumns(10);

        sodiumText = new JTextField();
        sodiumText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        sodiumText.setBounds(170, 230, 100, 25);
        this.add(sodiumText);
        titleText.setColumns(10);

        priceText = new JTextField();
        priceText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        priceText.setBounds(170, 270, 100, 25);
        this.add(priceText);
        priceText.setColumns(10);

        if (menuItem != null){
            titleText.setText(menuItem.getTitle());
            ratingText.setText(String.valueOf(menuItem.getRating()));
            caloriesText.setText(String.valueOf(menuItem.getCalories()));
            proteinText.setText(String.valueOf(menuItem.getProtein()));
            fatText.setText(String.valueOf(menuItem.getFat()));
            sodiumText.setText(String.valueOf(menuItem.getSodium()));
            priceText.setText(String.valueOf(menuItem.getPrice()));
        }

        JButton customButton = new JButton(action);
        customButton.setBounds(370, 140, 150, 40);
        customButton.setBackground(myPink);
        customButton.addActionListener(productActionController);
        customButton.setActionCommand(action);
        this.add(customButton);

        this.setVisible(true);
        this.revalidate();
        this.repaint();

    }

}