package org.example.zjazd.kompresja.zadanie.trzecie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LZ78 {
    static Map<Integer, String> slownik = new HashMap<>();

    public static void main(String[] args) {
        String input = "AABABACBAACBAADAAA";
        List<String> wynik = kompresja(input);

        System.out.println("Słownik:");
        for (Map.Entry<Integer, String> entry : slownik.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nSkompresowane:");
        for (String s : wynik) {
            System.out.print(s);
        }

        String decodeResult = dekodowanie(wynik, slownik);
        System.out.println("\nZdekodowane:");
        System.out.println(decodeResult);
    }

    private static String dekodowanie(List<String> inputToDecode, Map<Integer, String> dictionary) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String s : inputToDecode) {
            int num = Integer.parseInt(s.substring(0, 1));
            StringBuilder stringBuilder1 = new StringBuilder();
            for (Map.Entry<Integer, String> entry : dictionary.entrySet()) {

                int x = entry.getKey();
                if (x == num) {
                    stringBuilder1.append(entry.getValue());
                }
            }
            stringBuilder1.append(s.charAt(1));
            stringBuilder.append(stringBuilder1);
        }
        return stringBuilder.toString();
    }


    private static List<String> kompresja(String input) {
        List<String> outputList = new ArrayList<>();
        char[] fromInput = input.toCharArray();
        int index = 0;

        while (index < fromInput.length) {
            StringBuilder currentPhrase = new StringBuilder();
            currentPhrase.append(fromInput[index]);
            int num = 0;
            while (slownik.containsValue(currentPhrase.toString())) {
                index++;
                if (index < fromInput.length) {
                    currentPhrase.append(fromInput[index]);
                } else {
                    break;
                }
            }
            String strToList = "";
            for (Map.Entry<Integer, String> entry : slownik.entrySet()) {
                char[] arr = currentPhrase.toString().toCharArray();
                StringBuilder stringBuilder = new StringBuilder();
                for (char c : arr) {
                    stringBuilder.append(c);
                    if (entry.getValue().contentEquals(stringBuilder)) {
                        num = entry.getKey();
                    } else {
                        strToList = String.valueOf(arr[arr.length - 1]);
                    }
                }
            }
            if (!slownik.containsValue(currentPhrase.toString())) {
                slownik.put(slownik.size() + 1, currentPhrase.toString());
                String compressedEntry = "";
                if (index == 0) {
                    compressedEntry = (num + currentPhrase.toString());
                } else {
                    compressedEntry = (num + strToList);
                }
                outputList.add(compressedEntry);
            }
            index++;
        }
        return outputList;
    }
}