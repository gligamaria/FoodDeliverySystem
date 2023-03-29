package InterfaceControllers;

import Business_Layer.DeliveryService;
import Model.CompositeProduct;
import Model.MenuItem;
import Presentation.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AdminController implements ActionListener {

    JFrame jFrame;
    AdminWindow adminWindow;
    DeliveryService deliveryService;
    ArrayList<MenuItem> forCompositeProduct = new ArrayList<>();

    public AdminController(JFrame jFrame, DeliveryService deliveryService, AdminWindow adminWindow){
        this.jFrame = jFrame;
        this.adminWindow = adminWindow;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(e.getActionCommand(), "home")){
            jFrame.dispose();
            StartingWindow startingWindow = new StartingWindow(deliveryService);
        }
        else if(Objects.equals(e.getActionCommand(), "delete")){
            if(!adminWindow.getProductToDelete().equals("none")){
                MenuItem productToDelete = deliveryService.findProduct(adminWindow.getProductToDelete());
                try {
                    deliveryService.deleteProduct(productToDelete);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            jFrame.dispose();
            try {
                AdminWindow adminWindow = new AdminWindow(deliveryService);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(Objects.equals(e.getActionCommand(), "import")){
            jFrame.dispose();
            try {
                deliveryService.importProducts();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                AdminWindow adminWindow = new AdminWindow(deliveryService);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(Objects.equals(e.getActionCommand(), "add")){
            jFrame.dispose();
            try {
                ProductActionWindow productActionWindow = new ProductActionWindow(deliveryService, "Add product", null, null);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(Objects.equals(e.getActionCommand(), "modify")){
            if(!adminWindow.getProductToDelete().equals("none")){
                MenuItem productToModify = deliveryService.findProduct(adminWindow.getProductToDelete());
                try {
                    ProductActionWindow productActionWindow = new ProductActionWindow(deliveryService, "Modify product", productToModify, null);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }

            jFrame.dispose();

        }
        else if(Objects.equals(e.getActionCommand(), "search")){
            jFrame.dispose();
            try {
                ProductActionWindow productActionWindow = new ProductActionWindow(deliveryService, "Search for products", null, null);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(Objects.equals(e.getActionCommand(), "generate")){
            jFrame.dispose();
            try {
                GenerateReportsWindow generateReportsWindow = new GenerateReportsWindow(deliveryService);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(Objects.equals(e.getActionCommand(), "add_menu")){
            AdminWindow adminWindow = (AdminWindow) jFrame;
            if(!adminWindow.getProductToDelete().equals("none")){
                MenuItem productToAdd = deliveryService.findProduct(adminWindow.getProductToDelete());
                forCompositeProduct.add(productToAdd);
            }
        }
        else if(Objects.equals(e.getActionCommand(), "finish_menu")){
            try {
                AdminWindow adminWindow = (AdminWindow) jFrame;
                CompositeProduct compositeProduct = new CompositeProduct(forCompositeProduct, adminWindow.title.getText());
                deliveryService.createProduct(compositeProduct);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
