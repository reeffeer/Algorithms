public interface MyQueue<E> {

    boolean insert(E value);

    E remove();

    E peekHead();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    boolean isFull();
}
