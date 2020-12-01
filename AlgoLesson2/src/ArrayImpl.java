import java.util.Arrays;

public class ArrayImpl<E extends Comparable<? super E>> implements Array<E> {

    private final static int CAPACITY = 10;
    private E[] array;
    private int size;

    public ArrayImpl() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayImpl(int size) {
        this.array = (E[]) new Comparable[size];
    }

    public ArrayImpl(ArrayImpl<E> list) {
        this.array = Arrays.copyOf(list.array, list.size);
        this.size = list.size;
    }

    @Override
    public void add(E value) {
        checkLength();
        array[size++] = value;
    }

    @Override
    public void add(int index, E value) {
        checkIndex(index);
        checkLength();
        if (index == size) {
            add(value);
        } else {
            if (size - index >= 0) System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        }
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        if (index != -1) {
            remove(index);
        }
        return index != -1;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E value = array[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }

        array[size - 1] = null;
        size--;

        return value;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Array<E> bubbleSort() {
        ArrayImpl<E> tmp = new ArrayImpl<>(this);
        long start = System.currentTimeMillis();

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (tmp.array[j].compareTo(tmp.array[j+1]) > 0) {
                    E value = tmp.array[j];
                    tmp.array[j] = tmp.array[j+1];
                    tmp.array[j+1] = value;
                }
            }
        }

        System.out.println("Time for bubbleSort = " + (System.currentTimeMillis() - start));
        return tmp;
    }

    @Override
    public Array<E> selectSort() {
        ArrayImpl<E> tmp = new ArrayImpl<>(this);
        long start = System.currentTimeMillis();

        for (int i = 0; i < size(); i++) {
            E min = tmp.array[i];
            for (int j = i; j < size(); j++) {
                if (tmp.array[j].compareTo(min) < 0) {
                    min = tmp.array[j];
                    tmp.array[j] = tmp.array[i];
                    tmp.array[i] = min;
                }
            }
        }

        System.out.println("Time for selectSort = " + (System.currentTimeMillis() - start));
        return tmp;
    }

    @Override
    public Array<E> insertSort() {
        ArrayImpl<E> tmp = new ArrayImpl<>(this);
        long start = System.currentTimeMillis();

        for (int left = 0; left < tmp.size(); left++) {
            E value = tmp.array[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (tmp.array[i].compareTo(value) > 0) {
                    tmp.array[i+1] = tmp.array[i];
                } else {
                    break;
                }
            }
            tmp.array[i+1] = value;
        }

        System.out.println("Time for insertSort = " + (System.currentTimeMillis() - start));
        return tmp;
    }

    @Override
    public Array<E> shuttleSort() {
        ArrayImpl<E> tmp = new ArrayImpl<>(this);
        long start = System.currentTimeMillis();

        for (int i = 0; i < size() - 1; i++) {
            if (tmp.array[i].compareTo(tmp.array[i+1]) > 0) {
                E value = tmp.array[i];
                tmp.array[i] = tmp.array[i+1];
                tmp.array[i+1] = value;
                for (int j = i-1; j > 0; j--) {
                    if (tmp.array[j].compareTo(tmp.array[j-1]) < 0) {
                        E value2 = tmp.array[j];
                        tmp.array[j] = tmp.array[j-1];
                        tmp.array[j-1] = value2;
                    }
                }
            }
        }

        System.out.println("Time for shuttleSort = " + (System.currentTimeMillis() - start));
        return tmp;
    }

    @Override
    public Array<E> quickSort() {
        ArrayImpl<E> tmp = new ArrayImpl<>(this);
        long start = System.currentTimeMillis();

        tmp.quickSort(tmp.array, 0, size-1);

        System.out.println("Time for quickSort = " + (System.currentTimeMillis() - start));
        return tmp;
    }

    private void quickSort(E[] array, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;

        E pivot = array[(leftMarker + rightMarker) / 2];

        do {
            while (array[leftMarker].compareTo(pivot) < 0) {
                leftMarker++;
            }

            while (array[rightMarker].compareTo(pivot) > 0) {
                rightMarker--;
            }

            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    E tmp = array[leftMarker];
                    array[leftMarker] = array[rightMarker];
                    array[rightMarker] = tmp;
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        if (leftMarker < rightBorder) {
            quickSort(array, leftMarker, rightBorder);
        }

        if (leftBorder < rightMarker) {
            quickSort(array, leftBorder, rightMarker);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            result.append(array[i]).append(", ");
        }
        result.append(array[size-1]);
        return result.toString();
    }

    private void checkLength() {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length + array.length / 2 + 1);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new MyIndexOutOfBoundsException(index, size);
        }
    }
}