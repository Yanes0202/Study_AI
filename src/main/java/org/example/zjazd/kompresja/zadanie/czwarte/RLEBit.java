package org.example.zjazd.kompresja.zadanie.czwarte;

import java.util.ArrayList;
import java.util.List;

public class RLEBit {
    private static String stringToBits(String  bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes.getBytes()) {
            for (int i = 7; i >= 0; i--) {
                result.append((b & (1 << i)) != 0 ? '1' : '0');
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
    public static List<String> encodeBitwiseRLE(String input) {
        List<String> result = new ArrayList<>();
        int count = 1;

        for (int i = 1; i <= input.length(); i++) {
            if (i == input.length() || input.charAt(i - 1) != input.charAt(i)) {
                StringBuilder value = new StringBuilder();
                String bits = Integer.toBinaryString(count);
                int zeros = 7 - bits.length();
                value.append("0".repeat(Math.max(0, zeros + 1)));
                value.append(bits);
                result.add(value.toString());
                result.add(stringToBits(String.valueOf(input.charAt(i - 1))));
                count = 1;
            } else {
                count++;
            }
        }
        return result;
    }

    public static String decode(List<String> bajts) {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < bajts.size(); i++) {
            if(i%2 ==0) {
                int amount = Integer.parseInt(bajts.get(i),2);
                for (int j =0; j <=amount; j++) {
                    value.append((char) Integer.parseInt(bajts.get(i + 1), 2));
                }
            }
        }
        return value.toString();
    }

    public static void main(String[] args) {
        String originalBinary = "AAABBBB";
        System.out.println("Wejście: " + originalBinary);
        List<String> encodedBinary = encodeBitwiseRLE(originalBinary);
        System.out.println("Zakodowane: ");
        for (String s : encodedBinary) {
            System.out.print(s+",");
        }
        System.out.println();


        System.out.println(decode(encodedBinary));
    }
}
