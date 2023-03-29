package Data_Layer;

import Model.BaseProduct;
import Model.MenuItem;
import Model.Order;
import Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Serializator {

    public ArrayList<User> readUsers(String file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<User> users = (ArrayList<User>) objectInputStream.readObject();
        objectInputStream.close();

        return users;
    }

    public void writeUsers(ArrayList<User> userArrayList, String file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(userArrayList);
        objectOutputStream.close();
    }

    public HashSet<MenuItem>  readProducts(String file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        HashSet<MenuItem> menuItems =  (HashSet<MenuItem>) objectInputStream.readObject();
        objectInputStream.close();

        return  menuItems;
    }

    public void writeProducts(HashSet<MenuItem> myMenuItems, String file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(myMenuItems);
        objectOutputStream.close();
    }

    public HashSet<MenuItem> importProducts(String file) throws IOException {
        String line = "";
        HashSet<MenuItem> menuItems =  new HashSet<>();

        BufferedReader br = new BufferedReader(new FileReader(file));
        int i = 1;
        while ((line = br.readLine()) != null)
        {
            //because the first line of the csv file consists of the table header, we have to skip it
            if (i != 1){
                String[] product = line.split(",");
                BaseProduct baseProduct = new BaseProduct(product[0], Float.parseFloat(product[1]), Integer.parseInt(product[2]),
                        Integer.parseInt(product[3]), Integer.parseInt(product[4]), Integer.parseInt(product[5]), Integer.parseInt(product[6]));
                menuItems.add(baseProduct);
            }
            i++;
        }
        return menuItems;

    }

    public HashMap<Order, ArrayList<MenuItem>>  readOrders(String file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        HashMap<Order, ArrayList<MenuItem>> orders = (HashMap<Order, ArrayList<MenuItem>>) objectInputStream.readObject();
        objectInputStream.close();

        return  orders;
    }

    public void writeOrders(HashMap<Order, ArrayList<MenuItem>> orders, String file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(orders);
        objectOutputStream.close();
    }


}
