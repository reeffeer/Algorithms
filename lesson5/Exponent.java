package lesson5;

public class Exponent {

    public int recExponentiation(int a, int n) {
        if (n < 0) {
            System.out.println("Показатель степени не должен быть отрицательным.");
            return -1;
        }
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return a;
        }
        return recExponentiation(a, n - 1) * a;
    }
}
