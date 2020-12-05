import java.util.NoSuchElementException;

public class MyLinkedList<T> {

    private Node first;
    private int size;

    public MyLinkedList() {
        first = null;
    }

    private class Node<T> {
        T value;
        Node next;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void insertFirst(T value) {
        Node newNode = new Node(value); //создали новый узел
        newNode.setNext(first); //сказали, что он указывает на узел, на который указывал раньше 1й узел
        first = newNode; //а 1й узел указывает теперь на новый узел
        size++; //размер списка увеличился на 1 узел
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) first.getValue();
    }

    public T removeFirst() {
        T oldFirst = getFirst();
        first = first.getNext();
        size--;
        return  oldFirst;
    }

    public final int indexOf(T value) { //этот метод сделали неизменяемым final, потому что от него зависит другой метод (contains)
        Node current = first; // но можно было сделать final'ом класс MyLinkedList
        int index = 0;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    public boolean contains(T value) {
        return indexOf(value) > -1;
    }

    public void insert(int index, T value) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        if (index == 0) {
            insertFirst(value);
            return;
        }
        Node current = first;
        int i = 0;
        while (i < index - 1) {
            current = current.getNext();
            i++;
        }
        Node newNode = new Node(value);
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
    }

    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        if (index == 0) {
            return removeFirst();
        }
        Node current = first;
        int i = 0;
        while (i < index - 1) {
            current = current.getNext();
            i++;
        }
        T temp = (T) current.getNext().getValue();
        current.setNext(current.getNext().getNext());
        size--;
        return temp;
    }


    public boolean remove(T value) {
        if (isEmpty()) {
            return false;
        }
        if (first.getValue().equals(value)) {
            removeFirst();
            return true;
        }
        Node current = first;
        while (current.getNext() != null && !current.getNext().getValue().equals(value)) {
            current = current.getNext();
        }
        if (current.getNext() == null) {
            return false;
        }
        current.setNext(current.getNext().getNext());
        size--;
        return true;
    }

    @Override
    public String toString() {
        Node current = first;
        StringBuilder sb = new StringBuilder();
        while (current !=null) {
            sb.append(current.getValue() + ", ");
            current = current.getNext();
        }
        if (!isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }
}
