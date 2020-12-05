public class MyQueue<T> {
    private MyDoubleLinkedList<T> queue = new MyDoubleLinkedList<>();

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void enqueue(T value) {
        queue.insertFirst(value);
    }

    public T dequeue() {
        return queue.removeLast();
    }
    public T peekFront() {
        return queue.getLast();
    }
}
