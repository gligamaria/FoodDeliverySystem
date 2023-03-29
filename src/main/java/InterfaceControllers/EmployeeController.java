package InterfaceControllers;

import Business_Layer.DeliveryService;
import Presentation.EmployeeWindow;
import Presentation.StartingWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class EmployeeController implements ActionListener {

    JFrame jFrame;
    DeliveryService deliveryService;

    public EmployeeController(JFrame jFrame, DeliveryService deliveryService){
        this.jFrame = jFrame;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(e.getActionCommand(), "home")){
            jFrame.dispose();
            StartingWindow startingWindow = new StartingWindow(deliveryService);
        }
        else if(Objects.equals(e.getActionCommand(), "refresh")){
            jFrame.dispose();
            try {
                EmployeeWindow employeeWindow = new EmployeeWindow(deliveryService);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
