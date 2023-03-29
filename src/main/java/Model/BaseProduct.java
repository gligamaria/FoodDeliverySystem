package Model;

import Model.MenuItem;

public class BaseProduct extends MenuItem {
    public BaseProduct(String title, Float rating, Integer calories, Integer protein, Integer fat, Integer sodium, Integer price){
        this.setTitle(title);
        this.setRating(rating);
        this.setCalories(calories);
        this.setProtein(protein);
        this.setFat(fat);
        this.setSodium(sodium);
        this.setPrice(price);
    }
    @Override
    public Integer computePrice() {
        return null;
    }
}
