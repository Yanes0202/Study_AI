package org.example.zjazd.ai.zadanie.pierwsze;

public class Regression {
    public static void main(String[] args) {
        regression();
    }

    static void regression() {
        int[] x = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] y = {1, 4, 2, 7, 5, 8, 10, 8, 5, 4};

        for (int i = 1; i < x.length; i++) {
            System.out.println(y[i]);
        }
        System.out.println();

        double x1 = 0;
        for (int i : x) {
            x1 = x1 + Math.pow(x[i], 1);
        }

        double x2 = 0;
        for (int k : x) {
            x2 = x2 + Math.pow(k, 2);
        }

        double x3 = 0;
        for (int k : x) {
            x3 = x3 + Math.pow(k, 3);
        }

        double x4 = 0;
        for (int j : x) {
            x4 = x4 + Math.pow(j, 4);
        }

        double xIyI = 0;
        for (int i = 0; i < x.length; i++) {
            xIyI = xIyI + (x[i] * y[i]);
        }

        double x2yI = 0;
        for (int i = 0; i < x.length; i++) {
            x2yI = x2yI + (Math.pow(x[i], 2) * y[i]);
        }

        double yI = 0;
        for (int i = 0; i < x.length; i++) {
            yI = yI + Math.pow(y[i], 1);
        }

        double cA;
        double v = x.length * x3 - x1 * x2;
        cA = ((x.length * x2 - Math.pow(x1, 2) * (x2yI * x.length - yI * x2) - v * (xIyI * x.length - yI * x1)));
        double cB = ((x.length * x2 - Math.pow(x1, 2) * (x.length * x4 - Math.pow(x2, 2)) - v * v));

        double c = cA / cB;

        double b = (xIyI * x.length - yI * x1 - c * v) / (x.length * x2 - Math.pow(x1, 2));
        double a = (1.0 / x.length) * (yI - b * x1 - c * x2);

        double[] arr = new double[x.length];

        System.out.println("a " +a);
        System.out.println("b "+b);
        System.out.println("c "+c);
        for (int i = 1; i < x.length; i++) {
            double yRes = a + b * i + c * Math.pow(i, 2);
            arr[i] = yRes;
        }
    }
}