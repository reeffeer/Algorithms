public class Main {
    public static void main(String[] args) {
//        MyLinkedList<String> mll = new MyLinkedList<>();
//        mll.insertFirst("Berry");
//        mll.insertFirst("Marry");
//        mll.insertFirst("Terry");

 /*       for (int i = 0; i < 3; i++) {
            System.out.println(mll.removeFirst());
        }
*/
//        MyDoubleLinkedList<String> mdll = new MyDoubleLinkedList<>();
//        mdll.insertLast("Berry");
//        mdll.insertLast("Marry");
//        mdll.insertLast("Terry");
//        mdll.insertLast("Sherry");
////        for (int i = 0; i < 3; i++) {
////            System.out.println(mdll.removeLast());
////        }
//        System.out.println(mdll);
//        mdll.insert(2, "Jerry");
//        System.out.println(mdll);
//        System.out.println(mdll.remove(3));
//        System.out.println(mdll.remove("Terry"));
//        System.out.println(mdll);
//        System.out.println(mdll.indexOf("Marry"));
//        System.out.println(mll.indexOf("Marry"));

//        System.out.println(mll);
//        mll.insert(2, "Jerry");
//        System.out.println(mll);
//        System.out.println(mll.remove(2));
//        System.out.println(mll);
//        mll.remove("Terry");
//        System.out.println(mll);

//        for (String s: mdll) {
//            System.out.println(s);
//        }

        MyQueue<String> mq = new MyQueue<>();
        mq.enqueue("ds");
        mq.enqueue("dxg");
        mq.enqueue("bnmt");
        for (int i = 0; i < 3; i++) {
            System.out.println(mq.dequeue());
        }

    }
}
