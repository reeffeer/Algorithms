package lesson_8;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        HashTable<Product, Integer> table = createBigHashTable();
        table.display();
        System.out.println(table.size());
//      testMethods();
    }

    private static HashTable<Product, Integer> createBigHashTable() {
        Random random = new Random();
        HashTable<Product, Integer> table = new HashTableImpl<>();

        for (int i = 0; i < 1000; i++) {
            table.put(new Product(i, "Product #" + (i+1)), random.nextInt(1000));
        }

        return table;
    }

    private static void testMethods() {
        HashTable<Product, Integer> hashTable = new HashTableImpl<>();
        hashTable.put(new Product(1, "Boots"), 500);
        hashTable.put(new Product(34, "Skirt"), 340);
        hashTable.put(new Product(56, "Falda"), 570);
        hashTable.put(new Product(34, "Cap"), 125);
        hashTable.put(new Product(52, "Bonnet"), 985);
        hashTable.put(new Product(29, "Socks"), 53);

        System.out.println("Size is " + hashTable.size());
        hashTable.display();

        System.out.println("Price bonnet is " + hashTable.get(new Product(52, "Bonnet")));
        System.out.println("Price cap is " + hashTable.get(new Product(34, "Cap")));

        hashTable.remove(new Product(52, "Bonnet"));
        hashTable.remove(new Product(34, "Cap"));

        System.out.println("Price bonnet is " + hashTable.get(new Product(52, "Bonnet")));
        System.out.println("Price cap is " + hashTable.get(new Product(34, "Cap")));

        hashTable.display();
    }
}
