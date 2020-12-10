package lesson6;

import java.util.function.Consumer;

public interface Tree<E extends Comparable<? super E>> {

    void display(int level);

    enum TraverseMode {
        IN_ORDER,
        PRE_ORDER,
        POST_ORDER,
    }

    boolean add(E value);

    void addByLevel(E value, int level);

    boolean contains(E value);

    boolean remove(E value);

    boolean isEmpty();

    int size();

    void traverse(TraverseMode mode, Consumer<E> action);

    void display();

    boolean isBalanced();

}
