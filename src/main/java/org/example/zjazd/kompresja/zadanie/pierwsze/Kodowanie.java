package org.example.zjazd.kompresja.zadanie.pierwsze;

import java.util.ArrayList;
import java.util.List;

public class Kodowanie {

    public static Integer[] compareStrings(String str1, String str2) {
        int index = -1;
        int length = 0;
        boolean found = false;

        if (str1.equals(str2)) {
            index = 0;
            length = 3;
        } else {
            for (int i = 0; i <= 3; i++) {
                String cut = str1.substring(i);
                if (str2.startsWith(cut)) {
                    index = i;
                    length = str1.length() - i;
                    found = true;
                }
            }
            if(!found) {
                for (int i = 1; i <= 3; i++) {
                    String cut = str1.substring(0, i);
                    if (str2.startsWith(cut)) {
                        index = 0;
                        length = i;
                        found = true;
                    }
                }
                if(!found) {
                    index = 3;
                    length = 0;
                }
            }
        }
        return new Integer[]{index, length};
    }

    public static List<String> compress(String input) {
        List<String> result = new ArrayList<>();
        int position = 4;
        String firstChar = String.valueOf(input.charAt(0));
        result.add(firstChar);

        String buffor = firstChar + firstChar + firstChar + firstChar;
        String bufforSlownikowy = input.substring(0, position);
        while (position < input.length()) {
            Integer[] trojka = compareStrings(buffor, bufforSlownikowy);
            String s = bufforSlownikowy.substring(trojka[1], trojka[1] + 1);
            result.add(trojka[0] + "" + trojka[1] + s);

            int przesuniecie = trojka[1] + 1;
            if (przesuniecie == 4) {
                buffor = bufforSlownikowy.substring(0, przesuniecie);
                bufforSlownikowy = bufforSlownikowy.substring(przesuniecie) + input.substring(position, position + przesuniecie);
                position += przesuniecie;
            } else {
                String usuniecieBuf = buffor.substring(przesuniecie);
                String dodanieBuf = bufforSlownikowy.substring(0, przesuniecie);
                buffor = usuniecieBuf + dodanieBuf;
                String usuniecieSl = bufforSlownikowy.substring(przesuniecie);
                String dodanieSl = input.substring(position, position + przesuniecie);
                bufforSlownikowy = usuniecieSl + dodanieSl;
                position += przesuniecie;
            }
        }
        Integer[] trojka = compareStrings(buffor, bufforSlownikowy);
        String s = bufforSlownikowy.substring(trojka[1], trojka[1] + 1);
        result.add(trojka[0] + "" + trojka[1] + s);

        return result;
    }

    public static void main(String[] args) {
        String input = "abaababacabacadecabc";
        compress(input);

        List<String> compres = compress(input);
        System.out.println(compres);

        decompress(compres);
    }

    public static void decompress(List<String> compressed) {
        String firstChar = compressed.get(0);
        String buffor = firstChar + firstChar + firstChar + firstChar;

        String result = firstChar;

        for (int i = 1; i < compressed.size(); i++) {
            String value = compressed.get(i);
            int index = Character.getNumericValue(value.charAt(0));
            int p = Character.getNumericValue(value.charAt(1));
            String wartosc = String.valueOf(value.charAt(2));
            int przesuniecie = p + 1;
            StringBuilder buff = new StringBuilder(buffor);
            if (index + p > 4) {
                buff.append("a".repeat(Math.max(0, index + p)));
            }

            String fromBuffor = buffor.substring(index, index + p);
            String cut = buff.substring(przesuniecie);
            buffor = cut + fromBuffor + wartosc;

            result += fromBuffor + wartosc;

            System.out.println(result);
        }
    }
}