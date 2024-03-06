package org.example.zjazd.kompresja.zadanie.czwarte;

import java.util.ArrayList;
import java.util.List;

public class RLE {

    public static List<String> encodeBitwiseRLE(String input) {
        String encoded = "";
        List<String> result = new ArrayList<>();
        int count = 1;

        for (int i = 1; i <= input.length(); i++) {
            if (i == input.length() || input.charAt(i - 1) != input.charAt(i)) {
                encoded = String.valueOf((input.charAt(i - 1)));
                encoded += (Integer.toBinaryString(count));
                result.add(encoded);
                count = 1;
            } else {
                count++;
            }
        }
        return result;
    }

    public static List<String> toBajts(List<String> koded) {
        List<String> bajts = new ArrayList<>();
        String value = "";
        for (String x : koded) {
            value = String.valueOf(x.charAt(0));
            int zeros = 7 - x.length();
            for (int i= 0; i <= zeros; i++) {
                value += "0";
            }
            value += x.substring(1);
            bajts.add(value);
        }
        return bajts;
    }

    public static String decode(List<String> bajts) {
        StringBuilder result = new StringBuilder();
        String value = "";
        for (String s: bajts) {
            value = String.valueOf(s.charAt(0));
            int amount = Integer.parseInt(s.substring(1),2);
            result.append(value.repeat(Math.max(0, amount)));
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        String originalBinary = "110001111100001111111";
        System.out.println("Wejście: " + originalBinary);
        List<String> encodedBinary = encodeBitwiseRLE(originalBinary);
        System.out.println("Zakodowane: ");
        for (String s : encodedBinary) {
            System.out.print(s);
        }
        System.out.println();

        List<String> bajts = toBajts(encodedBinary);
        System.out.println("Bajtowa: ");
        for (String s: bajts) {
            System.out.print(s+",");
        }
        System.out.println();
        System.out.println("Dekodowanie: ");
        String decoded = decode(bajts);
        System.out.println(decoded);
    }
}
