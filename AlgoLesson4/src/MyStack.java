public class MyStack<T> {
    private MyDoubleLinkedList<T> stack = new MyDoubleLinkedList<>();

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(T value) {
        stack.insertFirst(value);
    }

    public T pop() {
        return stack.removeFirst();
    }
    public T peek() {
        return stack.getFirst();
    }
}
