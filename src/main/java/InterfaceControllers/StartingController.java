package InterfaceControllers;

import Business_Layer.DeliveryService;
import Model.User;
import Model.userType;
import Presentation.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class StartingController implements ActionListener {

    JFrame jFrame;
    StartingWindow startingWindow;
    DeliveryService deliveryService;

    public StartingController(JFrame jFrame, DeliveryService deliveryService){
        this.jFrame = jFrame;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(e.getActionCommand(), "register")){
            startingWindow = (StartingWindow) jFrame;
            String username = startingWindow.username.getText();
            String password = startingWindow.password.getText();
            jFrame.dispose();
            RegisterWindow registerWindow = new RegisterWindow(username, password, deliveryService);
        }
        else if(Objects.equals(e.getActionCommand(), "login")){
            startingWindow = (StartingWindow) jFrame;
            String username = startingWindow.username.getText();
            String password = startingWindow.password.getText();

            boolean found = false;

            for(User user:deliveryService.getUsers()){
                if (user.getUsername().equals(username)) {
                    if(user.getPassword().equals(password)){
                        try {
                            jFrame.dispose();
                            if(user.getType().equals(userType.CLIENT)){
                                ClientWindow clientWindow = new ClientWindow(deliveryService, user);
                                EmployeeWindow employeeWindow = new EmployeeWindow(deliveryService);
                                deliveryService.addObserver(employeeWindow);

                            }
                            else if (user.getType().equals(userType.ADMIN)){
                                AdminWindow adminWindow = new AdminWindow(deliveryService);
                            }
                            else{
                                EmployeeWindow employeeWindow = new EmployeeWindow(deliveryService);
                            }
                            found = true;
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                }
            }
            if(!found){
                ErrorWindow errorWindow = new ErrorWindow("Username or password are incorrect!");
            }

        }
    }
}
