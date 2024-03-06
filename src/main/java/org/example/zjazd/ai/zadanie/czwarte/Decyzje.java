package org.example.zjazd.ai.zadanie.czwarte;


import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Decyzje {


    public static void main(String[] args) {
//        Double[] k1 = {0.7, 0.8, 0.7, 0.5, 0.3};
//        Double[] k2 = {0.2, 0.8, 0.8, 0.6, 0.4};
//        Double[] k3 = {0.5, 0.5, 0.4, 0.7, 0.6};
//        Double[] k4 = {0.3, 0.2, 0.5, 0.8, 0.9};
//
//        List<Double> zatrudniony = List.of(minimum(k1), minimum(k2), minimum(k3), minimum(k4));
//
//        System.out.println(max(zatrudniony));
//
//        Double[] p1 = {0.7, 0.0, 0.3, 0.8, 0.9};
//        Double[] p2 = {0.8, 0.9, 0.7, 0.6, 0.2};
//        Double[] p3 = {0.9, 0.9, 0.8, 0.3, 0.7};
//
//        List<Double> projekt = List.of(minimum(p1), minimum(p2), minimum(p3));
//
//        System.out.println(max(projekt));

//        Double[] s1 = {1.0, 1.0, 0.8, 0.25, 1.0};
//        Double[] s2 = {0.2, 0.8, 1.0, 0.5, 0.5};
//        Double[] s3 = {1.0, 1.0, 0.6, 1.0, 0.25};
//        Double[] s4 = {0.4, 1.0, 1.0, 1.0, 0.75};
//        Double[] s5 = {1.0, 0.6, 0.8, 0.5, 1.0};
//        Double[] s6 = {1.0, 0.4, 1.0, 0.75, 0.5};
//
//        List<Double> students = List.of(minimum(s1), minimum(s2), minimum(s3), minimum(s4), minimum(s5), minimum(s6));
//
//        System.out.println(max(students));


        Double[] sz1 = {0.8, 0.2, 0.5, 0.6, 0.75};
        Double[] sz2 = {0.9, 0.2, 0.3, 0.5, 1.0};
        Double[] sz3 = {0.4, 0.9, 0.6, 0.7, 0.25};
        Double[] sz4 = {0.5, 0.6, 0.7, 0.7, 0.5};

        List<Double> uczelnie = List.of(minimum(sz1), minimum(sz2), minimum(sz3), minimum(sz4));

        System.out.println(max(uczelnie));
    }

    public static double minimum(Double[] value) {
        double result = 1;
        for (Double aDouble : value) {
            if (result > aDouble) {
                result = aDouble;
            }
        }
        return result;
    }

    public static SimpleEntry<Integer, Double>  max(List<Double> doubles) {
        return IntStream.range(0, doubles.size())
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(i+ 1, doubles.get(i)))
                .max(Comparator.comparingDouble(AbstractMap.SimpleEntry::getValue))
                .orElse(new AbstractMap.SimpleEntry<>(-1, null));
    }
}
