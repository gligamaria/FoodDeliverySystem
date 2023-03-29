import Business_Layer.DeliveryService;
import Data_Layer.Serializator;
import Model.User;
import Model.userType;
import Presentation.StartingWindow;

import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {

        DeliveryService deliveryService = new DeliveryService();
        Serializator serializator = new Serializator();
        //deliveryService.importProducts();
        deliveryService.setMenuItems(serializator.readProducts("menu_items.ser"));
        deliveryService.importProducts();
        User admin = new User(-2,"admin", "admin", "admin", userType.ADMIN);
        //deliveryService.addAdmin(admin);
        User employee = new User(-1, "employee", "parola", "employee", userType.EMPLOYEE);
        //deliveryService.addEmployee(employee);
        StartingWindow startingWindow = new StartingWindow(deliveryService);
        //EmployeeWindow employeeWindow = new EmployeeWindow(deliveryService);
        for(User user:deliveryService.getUsers()){
            System.out.println(user.getUsername() + " " + user.getName());
        }
        //deliveryService.generateFirstReport(13, 15);
        //deliveryService.generateSecondReport(2);
        //deliveryService.generateThirdReport(1,10);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        java.util.Date yourDate = sdf.parse("2022-07-22");
        //deliveryService.generateForthReport(yourDate);

    }
}
