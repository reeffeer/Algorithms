public class Main {
    public static void main(String[] args) {
        Array<Integer> list = new ArrayImpl<>();
        for (int i = 0; i < 100_000; i++) {
            list.add((int) (Math.random() * 1000));
        }
        //были заготовки с тех пор, когда сам тестил, поэтому добавил еще парочку)
        list.bubbleSort(); //35162
        list.selectSort(); //12091
        list.insertSort(); //4480
        list.shuttleSort(); //19195
        list.quickSort(); //24
    }
}
