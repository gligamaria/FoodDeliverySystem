package Model;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

    private ArrayList<MenuItem> items;
    private Float ratingAverage= Float.valueOf(0);
    private Integer caloriesTotal= 0;
    private Integer proteinTotal= 0;
    private Integer fatTotal= 0;
    private Integer sodiumTotal= 0;
    private Integer priceTotal = 0;

    public CompositeProduct(ArrayList<MenuItem> itemsToAdd, String title){
        items =  itemsToAdd;
        this.setTitle(title);
        computePrice();
        computeCharacteristic();
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    @Override
    public Integer computePrice() {

        for(MenuItem item:items){
            priceTotal += item.getPrice();
        }
        this.setPrice(priceTotal);

        return priceTotal;
    }

    public void computeCharacteristic(){
        for(MenuItem item:items){
            caloriesTotal += item.getCalories();
            proteinTotal += item.getProtein();
            fatTotal += item.getFat();
            sodiumTotal += item.getSodium();
            ratingAverage += item.getRating();
        }
        ratingAverage = ratingAverage/items.size();
        this.setCalories(caloriesTotal);
        this.setProtein(proteinTotal);
        this.setFat(fatTotal);
        this.setRating(ratingAverage);
        this.setSodium(sodiumTotal);
    }
}
