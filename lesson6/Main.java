package lesson6;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        createTrees(500, 6, 21);
    }

    private static void createTrees(int trees, int level, int range) {
        int balancedTrees = 0;
        int notBalancedTrees = 0;

        Random random = new Random();

        for (int i = 0; i < trees; i++) {
            Tree<Integer> tree = new TreeImpl<>();
            for (int j = 0; j < Math.pow(2, level) - 1; j++) {
                tree.addByLevel(random.nextInt(2 * range) - range, level);
            }
//            tree.display(level);
            if (tree.isBalanced()) {
                balancedTrees++;
            } else {
                notBalancedTrees++;
            }
        }

        System.out.println("Total number of trees = " + trees + System.lineSeparator());

        System.out.println("Balanced trees = " + balancedTrees);
        System.out.println("Not balanced trees = " + notBalancedTrees);
        System.out.printf("Success percent = %.2f%%\r\n\r\n", (double) balancedTrees/trees * 100);
    }
}
