package lesson_8;

import java.util.LinkedList;

public class HashTableImpl<K, V> implements HashTable<K, V> {

    private final int DEFAULT_CAPACITY = 10;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int capacity;
    private final float loadFactor;

    private int size;
    private LinkedList<Node<K, V>>[] data;

    public HashTableImpl() {
        capacity = DEFAULT_CAPACITY;
        loadFactor = DEFAULT_LOAD_FACTOR;
        data = new LinkedList[capacity];
    }

    public HashTableImpl(int capacity) {
        this.capacity = capacity;
        loadFactor = DEFAULT_LOAD_FACTOR;
        data = new LinkedList[capacity];
    }

    public HashTableImpl(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        data = new LinkedList[capacity];
    }

    static class Node<K, V> implements Entry<K, V> {

        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    @Override
    public boolean put(K key, V value) {
        if (size + 1 >= capacity * loadFactor) {
            rehash();
        }

        int index = hash(key);

        if (data[index] == null) {
            data[index] = new LinkedList<>();
        }
        data[index].add(new Node<>(key, value));

        for (Node<K, V> node : data[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                size++;
                return true;
            }
        }

        size++;
        return data[index].add(new Node<>(key, value));
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity - 1);
//        return key.hashCode() & (capacity - 1);
    }

    private void rehash() {
        capacity = capacity * 2 + 1;
        LinkedList<Node<K, V>>[] oldData = data;
        data = new LinkedList[capacity];

        for (LinkedList<Node<K, V>> nodeList : oldData) {
            if (nodeList != null) {
                for (Node<K, V> node : nodeList) {
                    size--;
                    put(node.key, node.value);
                }
            }
        }
    }

    @Override
    public V get(K key) {
        int index = hash(key);

        if (data[index] == null) {
            return null;
        } else {
            for (Node<K, V> node : data[index]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }

        return null;
    }

    @Override
    public V remove(K key) {
        int index = hash(key);

        if (data[index] == null) {
            return null;
        } else {
            for (int i = 0; i < data[index].size(); i++) {
                if (data[index].get(i).key.equals(key)) {
                    size--;
                    return data[index].remove(i).value;
                }
            }
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println("----------");
        for (LinkedList<Node<K, V>> nodeList : data) {
            System.out.println(nodeList);
        }
        System.out.println("----------");
    }


}