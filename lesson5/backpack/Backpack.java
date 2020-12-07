package lesson5.backpack;

import java.util.ArrayList;
import java.util.List;

public class Backpack {

    private List<Item> bestSet = null;

    private double maxWeight;
    private double bestPrice;

    //total weight of all items
    private double calcWeight(List<Item> items) {
        double result = 0;
        for (Item item : items) {
            result += item.getWeight();
        }
        return result;
    }

    //total price
    private double calcPrice(List<Item> items) {
    double result = 0;
    for(Item item :items) {
        result += item.getPrice();
    }
        return result;
    }

    //check if this combination is the best
    private void checkSet(List<Item> items) {
        if (this.bestSet == null) {
            if (this.calcWeight(items) <= this.maxWeight) {
                this.bestSet = items;
                this.bestPrice = this.calcPrice(items);
            }
        } else {
            if (this.calcWeight(items) <= this.maxWeight && this.calcPrice(items) > this.bestPrice) {
                this.bestSet = items;
                this.bestPrice = this.calcPrice(items);
            }
        }
    }

    //all combinations
    public void makeAllSets(List<Item> items) {
        if (items.size() > 0) {
            this.checkSet(items);
        }
        for (int i = 0; i < items.size(); i++) {
            List<Item> newSet = new ArrayList<>(items);
            newSet.remove(i);
            makeAllSets(newSet);
        }
    }

    public Backpack(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public List<Item> getBestSet() {
        return this.bestSet;
    }
}
