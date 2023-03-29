package InterfaceControllers;

import Business_Layer.DeliveryService;
import Model.BaseProduct;
import Model.MenuItem;
import Model.User;
import Presentation.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

public class ProductActionController implements ActionListener {

    JFrame jFrame;
    DeliveryService deliveryService;
    MenuItem newProduct, oldProduct;
    String title = null;
    Float rating = (float)-1;
    Integer calories = -1;
    Integer protein = -1;
    Integer fat = -1;
    Integer sodium = -1;
    Integer price = -1;
    User user;

    public ProductActionController(JFrame jFrame, DeliveryService deliveryService, MenuItem menuItem, User user){
        this.jFrame = jFrame;
        this.deliveryService = deliveryService;
        oldProduct = menuItem;
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ProductActionWindow productActionWindow = (ProductActionWindow) jFrame;
        if(oldProduct != null){

            newProduct = oldProduct;

            title = productActionWindow.titleText.getText();
            rating = Float.parseFloat(productActionWindow.ratingText.getText());
            calories = Integer.parseInt(productActionWindow.caloriesText.getText());
            protein = Integer.parseInt(productActionWindow.proteinText.getText());
            fat = Integer.parseInt(productActionWindow.fatText.getText());
            sodium = Integer.parseInt(productActionWindow.sodiumText.getText());
            price = Integer.parseInt(productActionWindow.priceText.getText());

            newProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
        }

        if(Objects.equals(e.getActionCommand(), "home")){
            jFrame.dispose();
            try {
                if(Objects.isNull(user)){
                    AdminWindow adminWindow = new AdminWindow(deliveryService);
                }
                else{
                    ClientWindow clientWindow = new ClientWindow(deliveryService, user);
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(Objects.equals(e.getActionCommand(), "Add product")){
            try {
                title = productActionWindow.titleText.getText();
                rating = Float.parseFloat(productActionWindow.ratingText.getText());
                calories = Integer.parseInt(productActionWindow.caloriesText.getText());
                protein = Integer.parseInt(productActionWindow.proteinText.getText());
                fat = Integer.parseInt(productActionWindow.fatText.getText());
                sodium = Integer.parseInt(productActionWindow.sodiumText.getText());
                price = Integer.parseInt(productActionWindow.priceText.getText());

                newProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
                deliveryService.createProduct(newProduct);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            jFrame.dispose();
            try {
                AdminWindow adminWindow = new AdminWindow(deliveryService);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(Objects.equals(e.getActionCommand(), "Modify product")){

            try {
                deliveryService.modifyProduct(newProduct, oldProduct);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            jFrame.dispose();
            try {
                AdminWindow adminWindow = new AdminWindow(deliveryService);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(Objects.equals(e.getActionCommand(), "Search for products")){
            if(!Objects.equals(productActionWindow.titleText.getText(), ""))
                title = productActionWindow.titleText.getText();

            if(!Objects.equals(productActionWindow.ratingText.getText(), ""))
                rating = Float.parseFloat(productActionWindow.ratingText.getText());

            if(!Objects.equals(productActionWindow.caloriesText.getText(), ""))
                calories = Integer.parseInt(productActionWindow.caloriesText.getText());

            if(!Objects.equals(productActionWindow.proteinText.getText(), ""))
                protein = Integer.parseInt(productActionWindow.proteinText.getText());

            if(!Objects.equals(productActionWindow.fatText.getText(), ""))
                fat = Integer.parseInt(productActionWindow.fatText.getText());

            if(!Objects.equals(productActionWindow.sodiumText.getText(), ""))
                sodium = Integer.parseInt(productActionWindow.sodiumText.getText());

            if(!Objects.equals(productActionWindow.priceText.getText(), ""))
                price = Integer.parseInt(productActionWindow.priceText.getText());

            HashSet<MenuItem> foundProducts = deliveryService.searchProduct(title, rating, calories, protein, fat, sodium, price);
            jFrame.dispose();
            FoundProductsWindow foundProductsWindow = new FoundProductsWindow(deliveryService, foundProducts, user);

        }
    }
}
