public class MyIndexOutOfBoundsException extends ArrayIndexOutOfBoundsException {
    public MyIndexOutOfBoundsException(int index, int size) {
        super(String.format("Invalid index %d for array with length %d", index, size));
    }
}
