package org.example.zjazd.ai.zadanie.drugie;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class TwoClassDivide {

    @SneakyThrows
    public static BufferedImage readImage() {
        return ImageIO.read(new File("C:\\Users\\adrian.dabrowski\\IdeaProjects\\AI\\src\\main\\resources\\cameraman.png"));
    }

    public static int[] getGrey(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int[] H = new int[256];

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);
                int gray = (pixel >> 16) & 0xFF;
                H[gray]++;
            }
        }
        return H;
    }

    @SneakyThrows
    public static void main(String[] args) {
        BufferedImage image = readImage();

        int width = image.getWidth();
        int height = image.getHeight();
        int[] H = getGrey(image);

        int xMin = 0;
        int xMax = 254;
        double N = height * width;
        double N0;
        double ms0;
        double ms1;
        double P0;
        double P1;
        double out;
        double najlepszeWyjscie = 0;
        double najlepszeT = 0;
        for(int t = xMin+1; t<xMax; t++) {
            N0=0;
            ms0 = 0.0;
            ms1 = 0.0;

            System.out.println("dla t = " + t);
            for( int i = xMin; i < t; i++) {
                N0+=H[i];
            }
            for( int i = xMin; i < t; i++) {
                ms0 += (H[i]*i);
            }
            if (N0 != 0) {
                ms0 /= (N0);
            }
            P0 = N0/N;
            System.out.println("N0 = "+N0);
            System.out.println("ms0 = "+ms0);
            System.out.println("P0 = "+P0);
            P1 = 1.0 - P0;
            for(int i = t; i<xMax; i++) {
                ms1 += (i*H[i]);
            }
            ms1 /= (N-N0);
            out = P0*P1 * Math.pow(ms0 - ms1, 2);
            if(out > najlepszeWyjscie) {
                najlepszeT = t;
                najlepszeWyjscie = out;
            }
            System.out.println("P1 = "+P1);
            System.out.println("ms1 = "+ms1);
            System.out.println("out = "+out);
        }
        System.out.println("Najlepszy Wynik = " + najlepszeWyjscie);
        System.out.println("Najlepsza wartość t = " + najlepszeT);
    }
}