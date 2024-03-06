package org.example.zjazd.ai.zadanie.trzecie;

import java.util.Map;

public class EntropyCalculator {

        public static double calculateEntropy(Map<Character, Integer> frequencies) {
            int total = frequencies.values().stream().mapToInt(Integer::intValue).sum();
            double entropy = 0.0;

            for (int frequency : frequencies.values()) {
                double probability = (double) frequency / total;
                entropy -= probability * log2(probability);
            }

            return entropy;
        }

        private static double log2(double value) {
            if (value == 0) {
                return 0;
            } else {
                return Math.log(value) / Math.log(2);
            }
        }


        public static void main(String[] args) {
            Map<Character, Integer> frequencies = Map.of('A', 1, 'B', 1, 'C', 1,'D', 1);
            double entropy = calculateEntropy(frequencies);
            System.out.println("Entropia: " + entropy);
        }
}
