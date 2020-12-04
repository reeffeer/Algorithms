public class Main {

    public static void main(String[] args) {
        testQueue();
      //testStack();
      //testDeque();

        //test reverse
        System.out.println(reverse("abcdefg"));
        System.out.println(reverse("Good morning!"));
}

    public static void testQueue() {
        MyQueue<Integer> queue = new MyQueueImpl<>(5);
        queue.insert(1);
        queue.insert(2);
        queue.insert(3);
        queue.insert(4);
        queue.insert(5);
        queue.insert(6);
        System.out.println("Size before: " + queue.size());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println("Size after: " + queue.size());
    }

    public static void testStack() {
        MyStack<Integer> stack = new MyStackImpl<>(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("Stack size = " + stack.size());
        System.out.println("Peek: " + stack.peek());
        System.out.println("Peek: " + stack.peek());
        System.out.println("Stack size = " + stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println("Stack size = " + stack.size());
    }

    public static void testDeque() {
        MyDeque<Integer> deque = new MyDequeImpl<>(5);
        System.out.println("Deque size = " + deque.size());
        deque.insertRight(1);
        deque.insertRight(2);
        deque.insertRight(3);
        deque.insertLeft(4);
        deque.insertLeft(5);
        System.out.println(deque.insertLeft(10));
        System.out.println(deque.insertRight(10));


        System.out.println("Deque size = " + deque.size());
        System.out.println(deque.removeRight());
        System.out.println(deque.removeRight());
        System.out.println(deque.removeRight());
        System.out.println(deque.removeRight());
        System.out.println(deque.removeRight());
        /*System.out.println(deque.removeLeft());
        System.out.println(deque.removeLeft());
        System.out.println(deque.removeLeft());
        System.out.println(deque.removeLeft());
        System.out.println(deque.removeLeft());*/
        System.out.println("Deque size = " + deque.size());
    }

    public static String reverse(String word) {

        if (word == null) {
            return null;
        }

        MyStack<Character> stack = new MyStackImpl<>(word.length());
        for (int i = 0; i < word.length(); i++) {
            stack.push(word.charAt(i));
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            result.append(stack.pop());
        }

        return result.toString();
    }
}
