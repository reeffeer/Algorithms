package lesson5.backpack;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Book", 3, 450.50));
        items.add(new Item("Telescope", 4, 4920));
        items.add(new Item("Compass", 4, 1000));
        items.add(new Item("Notebook", 6, 40000));
        items.add(new Item("Knife", 1, 300.50));

        Backpack backpack = new Backpack(12);
        backpack.makeAllSets(items);
        System.out.println(items);
        System.out.println("************************");
        System.out.println(backpack.getBestSet());

    }
}
