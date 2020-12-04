public class MyDequeImpl<E> extends MyQueueImpl<E> implements MyDeque<E> {

    public MyDequeImpl(int maxSize) {
        super(maxSize);
    }

    @Override
    public boolean insertLeft(E value) {
        if (isFull()) {
            return false;
        }

        if (head == DEFAULT_HEAD) {
            head = data.length;
        }

        data[--head] = value;
        size++;
        return true;
    }

    @Override
    public boolean insertRight(E value) {
        return super.insert(value);
    }

    @Override
    public E removeLeft() {
        return super.remove();
    }

    @Override
    public E removeRight() {
        if (isEmpty()) {
            return null;
        }

        if (tail == DEFAULT_TAIL) {
            tail = data.length - 1;
        }

        E removedValue = data[tail--];
        size--;
        return removedValue;
    }
}
