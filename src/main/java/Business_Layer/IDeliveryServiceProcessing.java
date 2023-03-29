package Business_Layer;

import Model.MenuItem;
import Model.Order;
import Model.User;

import java.io.IOException;
import java.util.*;

public interface IDeliveryServiceProcessing {

    /**
     * Method for importing the initial products from the given file
     */
    void importProducts() throws IOException, ClassNotFoundException;

    /**
     * Method for turning the data about the orders in the right
     * format in order to print them in the table
     */
    String[][] getData();

    /**
     * @pre username != null && password != null
     * @post users.size() == users.size()@pre + 1
     * @throws IOException
     */
    boolean addClient(String username, String password, String name) throws IOException, ClassNotFoundException;

    /**
     * Method for adding a new product
     * @pre menuItem != null
     * @post menuItems.size() == menuItems.size()@pre + 1
     */
    void createProduct(MenuItem newProduct) throws IOException;

    /**
     * @pre menuItem != null
     * @post menuItemHashSet.size() == menuItemHashSet.size()@pre - 1
     * @throws IOException
     */
    void deleteProduct(MenuItem productToDelete) throws IOException;

    /**
     * Method for updating a menuItem
     * @pre oldItem != null
     * @pre newItem != null
     * @post the oldItem will be updated
     */
    void modifyProduct(MenuItem newItem, MenuItem toDeleteItem) throws IOException;

    /**
     * Method for getting a report based on the time interval of the orders
     * @pre hourMin != null
     * @pre hourMax != null
     */
    void generateFirstReport(int hourMin, int hourMax);

    /**
     * Method for getting a report based on the products ordered more than a specified number of times so far
     * @pre numberOfTimes != null
     */
    void generateSecondReport(int numberOfTimes);

    /**
     * Method for getting a report based on the clients that have ordered more than a specified number of
     * times so far and the value of the order was higher than a specified amount
     * @pre numberOfOrders != null
     * @pre sum != null
     */
    void generateThirdReport(int numberOfOrders, int sum);

    /**
     * Method for getting a report based on the products ordered within a specified day with the number of
     * times they have been ordered.
     * @pre givenDate != null
     */
    void generateForthReport(Date givenDate);


    void createOrder(Integer clientID, ArrayList<MenuItem> items) throws IOException;
    HashSet<MenuItem> searchProduct(String title, Float rating, Integer calories, Integer protein,
                       Integer fat, Integer sodium, Integer price);
    void generateBill(Order order);


}
