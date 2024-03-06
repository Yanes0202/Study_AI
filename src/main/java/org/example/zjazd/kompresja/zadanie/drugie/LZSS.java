package org.example.zjazd.kompresja.zadanie.drugie;

import java.util.ArrayList;
import java.util.List;

public class LZSS {
    public static String compress(String input) {
        StringBuilder compressed = new StringBuilder();
        List<String> dictionary = new ArrayList<>();
        int currentIndex = 0;

        while (currentIndex < input.length()) {
            String currentSubstring = input.substring(currentIndex, Math.min(currentIndex + 1, input.length()));
            int matchIndex = findLongestMatch(dictionary, currentSubstring);

            if (matchIndex != -1) {
                compressed.append(String.format("%d,%d", matchIndex, currentSubstring.length()));
                currentIndex += currentSubstring.length();
            } else {
                compressed.append(String.format("0,%s", currentSubstring));
                dictionary.add(currentSubstring);
                currentIndex++;
            }
        }

        return compressed.toString();
    }

    public static String decompress(String compressed) {
        StringBuilder decompressed = new StringBuilder();
        List<String> dictionary = new ArrayList<>();
        String[] tokens = compressed.split(",");

        for (int i = 0; i < tokens.length; i += 2) {
            try {
                int matchIndex = Integer.parseInt(tokens[i]);
                int length = Integer.parseInt(tokens[i + 1]);

                if (matchIndex == 0) {
                    decompressed.append(tokens[i + 1]);
                    if (length > 0) {
                        dictionary.add(tokens[i + 1]);
                    }
                } else {
                    if (matchIndex <= dictionary.size()) {
                        String match = dictionary.get(matchIndex - 1);
                        decompressed.append(match.substring(0, length));
                        dictionary.add(match + tokens[i + 1]);
                    } else {
                        decompressed.append(tokens[i + 1]);
                    }
                }
            } catch (NumberFormatException e) {
                decompressed.append(tokens[i]);
                if (i + 1 < tokens.length) {
                    decompressed.append(",").append(tokens[i + 1]);
                }
            }
        }

        return decompressed.toString();
    }

    private static int findLongestMatch(List<String> dictionary, String currentSubstring) {
        int matchIndex = -1;
        int maxLength = 0;

        for (int i = 0; i < dictionary.size(); i++) {
            String entry = dictionary.get(i);

            for (int j = 0; j < currentSubstring.length(); j++) {
                int length = commonPrefixLength(entry.substring(j), currentSubstring);

                if (length > maxLength) {
                    maxLength = length;
                    matchIndex = i + 1;
                }
            }
        }

        return matchIndex;
    }

    private static int commonPrefixLength(String str1, String str2) {
        int length = 0;

        while (length < str1.length() && length < str2.length() && str1.charAt(length) == str2.charAt(length)) {
            length++;
        }

        return length;
    }

    public static void main(String[] args) {
        String original = "ababababab";
        String compressed = compress(original);
        System.out.println("Compressed: " + compressed);

        String decompressed = decompress(compressed);
        System.out.println("Decompressed: " + decompressed);
    }
}
