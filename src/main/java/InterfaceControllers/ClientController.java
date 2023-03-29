package InterfaceControllers;

import Business_Layer.DeliveryService;
import Model.MenuItem;
import Model.User;
import Presentation.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ClientController implements ActionListener {

    JFrame jFrame;
    DeliveryService deliveryService;
    User user;
    ArrayList<MenuItem> itemsToBuy;

    public ClientController(JFrame jFrame, DeliveryService deliveryService, User user, ArrayList<MenuItem> itemsToBuy){
        this.jFrame = jFrame;
        this.deliveryService = deliveryService;
        this.user = user;
        if(Objects.isNull(itemsToBuy)){
            this.itemsToBuy = new ArrayList<>();
        }
        else{
            this.itemsToBuy = itemsToBuy;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(e.getActionCommand(), "home")){
            jFrame.dispose();
            StartingWindow startingWindow = new StartingWindow(deliveryService);
        }
        else if(Objects.equals(e.getActionCommand(), "search")){
            jFrame.dispose();
            try {
                ProductActionWindow productActionWindow = new ProductActionWindow(deliveryService, "Search for products", null, user);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        else if(Objects.equals(e.getActionCommand(), "add")){
            ClientWindow clientWindow = (ClientWindow) jFrame;
            if(!clientWindow.getProductToAdd().equals("none")){
                MenuItem productToAdd = deliveryService.findProduct(clientWindow.getProductToAdd());
                itemsToBuy.add(productToAdd);
            }
        }
        else if(Objects.equals(e.getActionCommand(), "place order")){
            try {
                deliveryService.createOrder(user.getUserID(), itemsToBuy);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
