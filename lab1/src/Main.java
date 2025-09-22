import static java.lang.Math.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] w = createW();
        double[] x = createX();
        double[][] s = computeMatrix(w, x);
        printMatrix(s);
    }

    public static int[] createW() {
        int[] w = new int[16];
        for (int i = 0; i < 16; i++) {
            w[i] = 20 - i;
        }
        return w;
    }

    public static double[] createX() {
        double[] x = new double[17];
        Random r = new Random();
        for (int i = 0; i < 17; i++) {
            x[i] = -6.0 + r.nextDouble() * 9.0;
        }
        return x;
    }

    public static double[][] computeMatrix(int[] w, double[] x) {
        double[][] s = new double[w.length][x.length];
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < x.length; j++) {
                s[i][j] = computeElement(w[i], x[j]);
            }
        }
        return s;
    }

    public static double computeElement(int w_i, double x_j) {
        switch (w_i) {
            case 12:
                return cos(tan(atan((x_j - 1.5) / 9.0)));
            case 6, 8, 10, 13, 14, 15, 17, 19:
                return sin(pow(pow(x_j, (1 - x_j) / 1.0 / 4.0) / 2.0, pow(3 / (x_j - 1), 2)));
            default:
                double a = pow(tan(x_j), 3 * (4 - atan((x_j - 1.5) / 9.0))) + 1;
                double b = pow(pow((2 / 3.0) / (x_j - (3 / 4.0)), x_j) * (asin((x_j - 1.5) / 9.0) - 1), 2);
                return atan(cos(pow(a, b)));
        }
    }

    public static void printMatrix(double[][] s) {
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                System.out.printf("%8.3f", s[i][j]);
            }
            System.out.println();
        }
    }
}
