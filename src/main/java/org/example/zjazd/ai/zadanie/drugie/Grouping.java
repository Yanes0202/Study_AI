package org.example.zjazd.ai.zadanie.drugie;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grouping {

    public static void main(String[] args) {
        int[] x = {2,7,5,8,1,8,3,6,4,8,5,3,2,9,7,6,4,3,2,2,6,4,8,6,3,2};
        int[] y = {7,8,5,4,3,2,5,7,5,3,4,1,2,4,3,7,9,8,7,5,1,2,3,4,5,6};

        List<Integer> B1 = List.of(1,5,9);
        List<Integer> B2 = List.of(2,4,6,8,10);
        List<Integer> B3 = List.of(3,7);

        double avrx1 = 0;
        double avry1 = 0;
        for (Integer integer : B1) {
            avrx1 += x[integer - 1];
            avry1 += y[integer - 1];
        }
        avrx1 = avrx1 / B1.size();
        avry1 = avry1 / B1.size();

        double avrx2 = 0;
        double avry2 = 0;
        for (Integer integer : B2) {
            avrx2 += x[integer - 1];
            avry2 += y[integer - 1];
        }
        avrx2 = avrx2 / B2.size();
        avry2 = avry2 / B2.size();

        double avrx3 = 0;
        double avry3 = 0;
        for (Integer integer : B3) {
            avrx3 += x[integer - 1];
            avry3 += y[integer - 1];
        }
        avrx3 = avrx3 / B3.size();
        avry3 = avry3 / B3.size();

        double[][] d1 = new double[3][x.length];
        for(int i = 0; i < x.length; i++) {
            double sqrtValue1 = Math.sqrt(Math.pow(x[i] - avrx1, 2) + Math.pow(y[i] - avry1, 2));
            double sqrtValue2 = Math.sqrt(Math.pow(x[i] - avrx2, 2) + Math.pow(y[i] - avry2, 2));
            double sqrtValue3 = Math.sqrt(Math.pow(x[i] - avrx3, 2) + Math.pow(y[i] - avry3, 2));
            d1[0][i] = round(sqrtValue1);
            d1[1][i] = round(sqrtValue2);
            d1[2][i] = round(sqrtValue3);
        }

        int[][] b1 = new int[3][x.length];
        for(int i = 0; i < x.length; i++) {
            if(d1[0][i] < d1[1][i] && d1[0][i] < d1[2][i]) {
                b1[0][i] = 1;
                b1[1][i] = 0;
                b1[2][i] = 0;
            } else if(d1[1][i] < d1[0][i] && d1[1][i] < d1[2][i]){
                b1[0][i] = 0;
                b1[1][i] = 1;
                b1[2][i] = 0;
            } else {
                b1[0][i] = 0;
                b1[1][i] = 0;
                b1[2][i] = 1;
            }
        }

        List<Integer> b2g1index = new ArrayList<>();
        List<Integer> b2g2index = new ArrayList<>();
        List<Integer> b2g3index = new ArrayList<>();
        for(int i = 0; i < x.length; i++) {
            if(d1[0][i] < d1[1][i] && d1[0][i] < d1[2][i]) {
                b2g1index.add(i);
            } else if(d1[1][i] < d1[0][i] && d1[1][i] < d1[2][i]){
                b2g2index.add(i);
            } else {
                b2g3index.add(i);
            }
        }

        int[] g1 = new int[b2g1index.size()];
        int[] gy1 = new int[b2g1index.size()];
        for(int i = 0; i < b2g1index.size(); i++) {
            g1[i] = x[b2g1index.get(i)];
            gy1[i] = y[b2g1index.get(i)];
        }
        int[] g2 = new int[b2g2index.size()];
        int[] gy2 = new int[b2g2index.size()];
        for(int i = 0; i < b2g2index.size(); i++) {
            g2[i] = x[b2g2index.get(i)];
            gy2[i] = y[b2g2index.get(i)];
        }
        int[] g3 = new int[b2g3index.size()];
        int[] gy3 = new int[b2g3index.size()];
        for(int i = 0; i < b2g3index.size(); i++) {
            g3[i] = x[b2g3index.get(i)];
            gy3[i] = y[b2g3index.get(i)];
        }

        System.out.println(Arrays.toString(g1));
        System.out.println(Arrays.toString(gy1));
        System.out.println("==============================");

        System.out.println(Arrays.toString(g2));
        System.out.println(Arrays.toString(gy2));
        System.out.println("==============================");

        System.out.println(Arrays.toString(g3));
        System.out.println(Arrays.toString(gy3));

    }

    public static double round(double value) {
        int precision = 2;
        BigDecimal bigDecimal = BigDecimal.valueOf(value);
        bigDecimal = bigDecimal.setScale(precision, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
