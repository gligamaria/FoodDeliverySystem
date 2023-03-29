package Business_Layer;

import Data_Layer.FileWriter;
import Data_Layer.Serializator;
import Model.MenuItem;
import Model.Order;
import Model.User;
import Model.userType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing{

    private Serializator serializator;
    private ArrayList<User> users;
    private HashSet<MenuItem> menuItems =  new HashSet<>();
    private HashMap<Order, ArrayList<MenuItem>> orders;

    public HashMap<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<Order, ArrayList<MenuItem>> orders) {
        this.orders = orders;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public HashSet<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(HashSet<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public DeliveryService() throws IOException, ClassNotFoundException {
        this.serializator = new Serializator();
        this.users = serializator.readUsers("users.ser");
        this.orders = serializator.readOrders("orders.ser");
    }

    public String[][] getData(){
        int value = 0;
        if(menuItems != null)
            value = menuItems.size();
        String [][] data = new String[value][7];
        int i = 0;
        assert menuItems != null;
        for (MenuItem menuItem:menuItems){
            data[i][0] = menuItem.getTitle();
            data[i][1] = String.valueOf(menuItem.getRating());
            data[i][2] = String.valueOf(menuItem.getCalories());
            data[i][3] = String.valueOf(menuItem.getProtein());
            data[i][4] = String.valueOf(menuItem.getFat());
            data[i][5] = String.valueOf(menuItem.getSodium());
            data[i][6] = String.valueOf(menuItem.getPrice());
            i++;
        }
        return data;
    }

    public MenuItem findProduct(String title){
        for(MenuItem menuItem:menuItems){
            if(title.equals(menuItem.getTitle()))
                return menuItem;
        }
        return null;
    }

    @Override
    public void importProducts() throws IOException {
        menuItems = serializator.importProducts("products.csv");
    }

    public boolean uniqueUsername(String username){
        for(User user:users){
            if(user.getUsername().equals(username))
                return false;
        }
        return true;
    }

    public void addAdmin(User admin) throws IOException, ClassNotFoundException {
        users.add(admin);
        serializator.writeUsers(users, "users.ser");
        users = serializator.readUsers("users.ser");
    }

    public void addEmployee(User employee) throws IOException, ClassNotFoundException {
        users.add(employee);
        serializator.writeUsers(users, "users.ser");
        users = serializator.readUsers("users.ser");
    }

    public boolean addClient(String username, String password, String name) throws IOException, ClassNotFoundException {
        // adauga password not null
        if(password != null){
            if(users.isEmpty()){
                users.add(new User(1, username, password, name, userType.CLIENT));
            }
            else {
                if(uniqueUsername(username)){
                    int maximumID = 0;
                    for(User user:users){
                        if (maximumID < user.getUserID())
                            maximumID = user.getUserID();
                    }
                    users.add(new User(maximumID + 1, username, password, name, userType.CLIENT));
                    serializator.writeUsers(users, "users.ser");
                    return true;
                }
                else {
                    System.out.println("Error: username already exists!");
                }
            }
        }
        else {
            System.out.println("Don't forget to introduce a password!");
        }
        return false;
    }

    @Override
    public void createProduct(MenuItem newProduct) throws IOException {
        //trebuie verificat sa aibe numele unique
        menuItems.add(newProduct);
        serializator.writeProducts(menuItems, "menu_items.ser");
    }

    @Override
    public void deleteProduct(MenuItem productToDelete) throws IOException {
        menuItems.remove(productToDelete);
        serializator.writeProducts(menuItems, "menu_items.ser");
    }

    @Override
    public void modifyProduct(MenuItem newItem, MenuItem toDeleteItem) throws IOException {
        menuItems.remove(toDeleteItem);
        menuItems.add(newItem);
        serializator.writeProducts(menuItems,"menu_items.ser");
    }

    @Override
    public void generateFirstReport(int hourMin, int hourMax) {

        HashMap<Order, ArrayList<MenuItem>> foundOrders = this.orders.entrySet().stream().filter(map -> map.getKey().getOrderDate().getHours() > hourMin).
                filter(map -> map.getKey().getOrderDate().getHours() < hourMax).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (prev, next) -> next, HashMap::new));

        if(!foundOrders.isEmpty()){
            FileWriter fileWriter = new FileWriter();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd___HH_mm");
            fileWriter.setFile("report1_" + dtf.format(LocalDateTime.now()));
            fileWriter.write("Report1 from " + dtf.format(LocalDateTime.now()) + "\n");
            fileWriter.write("Orders from the time interval: " + hourMin + "-" + hourMax + "\n" + "\n");

            Iterator it = foundOrders.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                fileWriter.write("Order no. " + ((Order) pair.getKey()).getOrderID() + "\n");
                fileWriter.write("Order time: " + ((Order) pair.getKey()).getOrderDate() + "\n");
                fileWriter.write("\n");
            }
            fileWriter.closeFile();
        }
    }

    @Override
    public void generateSecondReport(int numberOfTimes) {
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList = orders.values().stream().flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<MenuItem> foundMenuItems = new ArrayList<>();
        menuItemList.stream()
                .collect(Collectors.groupingBy(menuItem -> menuItem, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(menuItem -> menuItem.getValue() >= numberOfTimes)
                .forEach(menuItem -> foundMenuItems.add(menuItem.getKey()));

        if(!foundMenuItems.isEmpty()){
            FileWriter fileWriter = new FileWriter();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd___HH_mm");
            fileWriter.setFile("report2_" + dtf.format(LocalDateTime.now()));
            fileWriter.write("Report2 from " + dtf.format(LocalDateTime.now()) + "\n");
            fileWriter.write("Products ordered more than " + numberOfTimes + " times" + "\n" + "\n");
            for(MenuItem menuItem:foundMenuItems){
                fileWriter.write(menuItem.getTitle() + "\n");
            }
            fileWriter.closeFile();
        }

    }

    private boolean isWellFormed(){
        return orders != null &&
                orders.keySet().stream().anyMatch(Objects::nonNull) &&
                orders.values().stream().anyMatch(Objects::nonNull) &&
                menuItems != null && menuItems.stream().anyMatch(Objects::nonNull) &&
                orders.entrySet().stream().anyMatch(Objects::nonNull) &&
                orders.entrySet().stream().allMatch(entry -> entry.getValue().size()>0);
    }


    @Override
    public void generateThirdReport(int numberOfOrders, int sum) {
        assert isWellFormed();
        List<MenuItem> menuItemList = new ArrayList<>();
        menuItemList = orders.values().stream().flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<MenuItem> foundMenuItems = new ArrayList<>();
        List<Order> ordersWithUniqueClients = new ArrayList<>();

        Map<Integer, Long> clientsFrequency = orders.keySet().stream()
                .collect(Collectors.groupingBy(Order::getClientID, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() >= numberOfOrders)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Set<Integer> clientsFilteredBySum = new HashSet<Integer>();

        orders.entrySet().stream()
                .filter(entry -> entry.getValue().stream().mapToDouble(MenuItem::getPrice).sum() >= sum)
                .forEach(entry -> clientsFilteredBySum.add(entry.getKey().getClientID()));

        List<Integer> foundClientID = clientsFilteredBySum.stream()
                .filter(clientsFrequency::containsKey)
                .collect(Collectors.toList());

        if(!foundClientID.isEmpty()){
            FileWriter fileWriter = new FileWriter();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd___HH_mm");
            fileWriter.setFile("report3_" + dtf.format(LocalDateTime.now()));
            fileWriter.write("Report3 from " + dtf.format(LocalDateTime.now()) + "\n");
            fileWriter.write("Clients that ordered more than " + numberOfOrders + ", with a total sum\nof more than " + sum + "\n");
            for(Integer id:foundClientID){
                for(User user:users){
                    if(user.getUserID().equals(id)){
                        fileWriter.write("Client ID: " + user.getUserID() + "\n" +
                                "Username: " + user.getUsername() + "\n" +
                                "Name: " + user.getName() + "\n\n");
                    }
                }
            }
            fileWriter.closeFile();
        }
    }

    @Override
    public void generateForthReport(Date givenDate) {

        List<String> productsToPrint = new ArrayList<>();

        Map<Order, ArrayList<MenuItem>> ordersFromThatDate = orders.entrySet().stream()
                .filter(entry -> entry.getKey().getOrderDate().getDay() == givenDate.getDay() &&
                        entry.getKey().getOrderDate().getYear() == givenDate.getYear() &&
                        entry.getKey().getOrderDate().getMonth() == givenDate.getMonth())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        ordersFromThatDate.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
                .forEach(((menuItem, aLong) -> productsToPrint.add(menuItem.getTitle() + " ordered " + aLong + " times\n")));

        if(!productsToPrint.isEmpty()){
            FileWriter fileWriter = new FileWriter();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd___HH_mm");
            fileWriter.setFile("report4_" + dtf.format(LocalDateTime.now()));
            fileWriter.write("Report4 from " + dtf.format(LocalDateTime.now()) + "\n");
            fileWriter.write("Products on " + givenDate + "\n");
            for(String toPrint:productsToPrint){
                fileWriter.write(toPrint);
            }
            fileWriter.closeFile();
        }
    }

    @Override
    public void createOrder(Integer clientID, ArrayList<MenuItem> items) throws IOException {
        int maximumID = 0;
        if(!orders.isEmpty()){
            Iterator it = orders.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                if(((Order) pair.getKey()).getOrderID() > maximumID){
                    maximumID = ((Order) pair.getKey()).getOrderID();
                }
            }
        }
        Order newOrder = new Order();
        newOrder.setOrderID(maximumID + 1);
        newOrder.setClientID(clientID);
        Date date = new Date();
        Timestamp orderDate = new Timestamp(date.getTime());
        newOrder.setOrderDate(orderDate);
        // vezi aici ca cezar a facut ceva cu observable list,

        orders.put(newOrder, items);
        serializator.writeOrders(orders, "orders.ser");
        generateBill(newOrder);

        setChanged();
        notifyObservers(orders);
    }

    @Override
    public HashSet<MenuItem> searchProduct(String title, Float rating, Integer calories, Integer protein, Integer fat, Integer sodium, Integer price) {

        Predicate<MenuItem> titlePredicate = menuItem -> (title == null) || menuItem.getTitle().contains(title);
        Predicate<MenuItem> ratingPredicate = menuItem -> (rating == -1) || Objects.equals(menuItem.getRating(), rating);
        Predicate<MenuItem> caloriesPredicate = menuItem -> (calories == -1) || Objects.equals(menuItem.getCalories(), calories);
        Predicate<MenuItem> proteinPredicate = menuItem -> (protein == -1) || Objects.equals(menuItem.getProtein(), protein);
        Predicate<MenuItem> fatPredicate = menuItem -> (fat == -1) || Objects.equals(menuItem.getFat(), fat);
        Predicate<MenuItem> sodiumPredicate = menuItem -> (sodium == -1) || Objects.equals(menuItem.getSodium(), sodium);
        Predicate<MenuItem> pricePredicate = menuItem -> (price == -1) || Objects.equals(menuItem.getPrice(), price);

        return (HashSet<MenuItem>) menuItems.stream().filter(titlePredicate).filter(ratingPredicate).filter(caloriesPredicate).filter(proteinPredicate).
                filter(fatPredicate).filter(sodiumPredicate).filter(pricePredicate).collect(Collectors.<MenuItem>toSet());

    }

    @Override
    public void generateBill(Order order) {
        int totalPrice = 0;
        FileWriter fileWriter = new FileWriter();
        fileWriter.setFile("order" + order.getOrderID());
        fileWriter.write("Order no. " + order.getOrderID() + "\n");
        fileWriter.write("Client name: ");
        for(User user:users){
            if(user.getUserID().equals(order.getClientID())){
                fileWriter.write(user.getName() + "\n");
            }
        }
        fileWriter.write("Time: " + order.getOrderDate() + "\n");
        ArrayList<MenuItem> menuItemsList = orders.get(order);
        ArrayList<MenuItem> menuItemsListCopy = orders.get(order);
        ArrayList<MenuItem> countedMenuItems = new ArrayList<>();

        for(MenuItem menuItem:menuItemsList){
            if (!countedMenuItems.contains(menuItem)){
                int quantity = 0;
                for(MenuItem secondMenuItem:menuItemsListCopy){
                    if(secondMenuItem.equals(menuItem)){
                        quantity++;
                    }
                }
                countedMenuItems.add(menuItem);
                totalPrice = totalPrice + quantity * menuItem.getPrice();
                fileWriter.write(menuItem.getTitle() + " --> quantity: " + quantity + "\n");
            }
        }
        fileWriter.write("Total price: " + totalPrice + "\n");
        fileWriter.closeFile();
    }

    public String[][] employeeOrders(HashMap<Order, ArrayList<MenuItem>> newOrders){
        int value = 0;
        if(newOrders != null)
            value = newOrders.size();
        String [][] data = new String[value][1];
        int i = 0;
        if(!newOrders.isEmpty()){
            Iterator it = newOrders.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String orderString = new String("Order ID: " + ((Order) pair.getKey()).getOrderID() + ", " +
                        "Client ID: " + ((Order) pair.getKey()).getClientID() + ", " +
                        "Date: " + ((Order) pair.getKey()).getOrderDate() + ", ");

                List<MenuItem> products = newOrders.get(pair.getKey());

                for(MenuItem menuItem:products){
                    orderString = orderString.concat(menuItem.getTitle() + ", ");
                }
                data[i][0] = orderString;
                i++;
            }
        }
        return data;
    }
}
