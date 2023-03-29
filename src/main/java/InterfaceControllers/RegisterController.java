package InterfaceControllers;

import Business_Layer.DeliveryService;
import Presentation.ErrorWindow;
import Presentation.RegisterWindow;
import Presentation.StartingWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class RegisterController implements ActionListener {

    JFrame jFrame;
    RegisterWindow registerWindow;
    DeliveryService deliveryService;

    public RegisterController(JFrame jFrame, DeliveryService deliveryService){
        this.jFrame = jFrame;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(e.getActionCommand(), "register")){
            registerWindow = (RegisterWindow) jFrame;
            String username = registerWindow.username.getText();
            String password = registerWindow.password.getText();
            String fullName = registerWindow.fullName.getText();
            boolean actionCompleted = true;
            try {
                actionCompleted = deliveryService.addClient(username, password, fullName);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
            if (actionCompleted){
                jFrame.dispose();
                StartingWindow startingWindow = new StartingWindow(deliveryService);
            }
            else {
                ErrorWindow errorWindow = new ErrorWindow("This username is already taken!");
            }
        }
        else if(Objects.equals(e.getActionCommand(), "home")){

            jFrame.dispose();
            StartingWindow startingWindow = new StartingWindow(deliveryService);
        }

    }
}
