package InterfaceControllers;


        import Business_Layer.DeliveryService;
        import Model.User;
        import Presentation.*;

        import javax.swing.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.IOException;
        import java.util.Objects;

public class FoundProductsController implements ActionListener {

    JFrame jFrame;
    DeliveryService deliveryService;
    User user;

    public FoundProductsController(JFrame jFrame, DeliveryService deliveryService, User user){
        this.jFrame = jFrame;
        this.deliveryService = deliveryService;
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(Objects.equals(e.getActionCommand(), "home")){
            jFrame.dispose();
            try {
                if(user == null){
                    AdminWindow adminWindow = new AdminWindow(deliveryService);
                }
                else {
                    ClientWindow clientWindow = new ClientWindow(deliveryService,user);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}

