public interface Array<E> {

    void add(E value);

    void add(int index, E value);

    boolean remove(E value);

    E remove(int index);

    int indexOf(E value);

    E get(int index);

    int size();

    Array<E> bubbleSort();

    Array<E> selectSort();

    Array<E> insertSort();

    Array<E> shuttleSort();

    Array<E> quickSort();
}
