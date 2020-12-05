import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyDoubleLinkedList<T> implements Iterable<T> {
    private Node first;
    private Node last;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }


    public ListIterator<T> listIterator() {
        return new ListIter();
    }

    public MyDoubleLinkedList() {
        first = null;
        last = null;
    }

    class Iter implements Iterator<T> {
        Node current = new Node(null, first);

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public T next() {
            current = current.getNext();
            return (T) current.getValue();
        }

        public boolean remove(T value) {
            Node<T> current = first;
            Node<T> previous = null;
            while (current != null) {
                if (current.value.equals(value)) {
                    break;
                }
                previous = current;
                current = current.next;
            }

            if (current == null) {
                return false;
            }

            if (current == first) {
                first = first.next;
            }
            else {
                previous.next = current.next;
            }

            current.next = null;
            current.value = null;

            size--;
            return true;
        }
    }

    private class ListIter implements ListIterator<T> {
        Node nextNode = first;
        Node prevNode = null;
        int index = -1;
        boolean lastOperationIsNext;

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public T next() {
            T temp = (T) nextNode.value;
            prevNode = nextNode;
            nextNode = nextNode.next;
            index++;
            lastOperationIsNext = true;
            return temp;
        }

        @Override
        public boolean hasPrevious() {
            return prevNode != null;
        }

        @Override
        public T previous() {
            T temp = (T) prevNode.value;
            nextNode = prevNode;
            prevNode = prevNode.prev;
            lastOperationIsNext = false;
            return temp;
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            return index;
        }

        @Override
        public void remove() {
          //delete the element that prevNode points to
          if (lastOperationIsNext) {
              if (!hasNext()) {
                  removeLast();
                  nextNode = null;
                  prevNode = last;
                  return;
              }
              nextNode.prev = prevNode.prev;
              if (prevNode.prev != null) {
                  prevNode.prev.next = nextNode;
              }
              prevNode = nextNode.prev;
          } else { //delete the element that nextNode points to
              if (!hasPrevious()) {
                  removeFirst();
                  prevNode = null;
                  nextNode = first;
                  return;
              }
              prevNode.next = nextNode.next;
              if (nextNode.next != null) {
                  nextNode.next.prev = prevNode;
              }
              nextNode = prevNode.next;
          }
        }

        @Override
        public void set(T t) {
            //change the element that prevNode points to if it was next()
            //change the element that nextNode points to if it was prev()
        }

        @Override
        public void add(T t) {
            //insert the element between prevNode and nextNode
        }
    }

    private class Node<T> {
        T value;
        Node next;
        Node prev;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }


        public Node(T value, Node next, Node prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
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

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
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
        if (isEmpty()) {
            last = newNode;
        } else {
            first.setPrev(newNode);
        }
        first = newNode; //а 1й узел указывает теперь на новый узел
        size++; //размер списка увеличился на 1 узел
    }

    public void insertLast(T value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            first = newNode;
        } else {
            newNode.setPrev(last);
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) first.getValue();
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) last.getValue();
    }

    public T removeFirst() {
        T oldFirst = getFirst();
        first = first.getNext();
        if (isEmpty()) {
            last = null;
        } else {
            first.setPrev(null);
        }
        size--;
        return  oldFirst;
    }

    public T removeLast() {
        T oldLast = getLast();
        if (last.getPrev() != null) {
            last.getPrev().setNext(null);
        } else {
            first = null;
        }
        last = last.getPrev();
        size--;
        return  oldLast;
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
        if (index == size) {
            insertLast(value);
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
        newNode.setPrev(current);
        current.setNext(newNode);
        newNode.getNext().setPrev(newNode);
        size++;
    }

    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node current = first;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        T temp = (T) current.getValue();
        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());
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
        while (current != null && !current.getValue().equals(value)) {
            current = current.getNext();
        }
        if (current == null) {
            return false;
        }
        if (current == last) {
            removeLast();
            return true;
        }
        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());
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
