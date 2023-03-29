package InterfaceControllers;

import Business_Layer.DeliveryService;
import Presentation.AdminWindow;
import Presentation.GenerateReportsWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class GenerateReportsController implements ActionListener {

    JFrame jFrame;
    DeliveryService deliveryService;

    public GenerateReportsController(JFrame jFrame, DeliveryService deliveryService){
        this.jFrame = jFrame;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GenerateReportsWindow generateReportsWindow = (GenerateReportsWindow) jFrame;
        if(Objects.equals(e.getActionCommand(), "home")){
            jFrame.dispose();
        }
        else if(Objects.equals(e.getActionCommand(), "first")){
            jFrame.dispose();
            deliveryService.generateFirstReport(Integer. parseInt(generateReportsWindow.hourMin.getText()) ,
                    Integer. parseInt(generateReportsWindow.hourMax.getText()));
        }
        else if(Objects.equals(e.getActionCommand(), "second")){
            jFrame.dispose();
            deliveryService.generateSecondReport(Integer. parseInt(generateReportsWindow.numberOfTimes.getText()));
        }
        else if(Objects.equals(e.getActionCommand(), "third")){
            jFrame.dispose();
            deliveryService.generateThirdReport(Integer. parseInt(generateReportsWindow.numberOfOrders.getText()) ,
                    Integer. parseInt(generateReportsWindow.sum.getText()));
        }
        else if(Objects.equals(e.getActionCommand(), "fourth")){
            jFrame.dispose();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date yourDate = sdf.parse(generateReportsWindow.date.getText());
                deliveryService.generateForthReport(yourDate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }

        try {
            AdminWindow adminWindow = new AdminWindow(deliveryService);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
