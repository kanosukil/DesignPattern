package cn.edu.ncu.learn.Util;

import java.util.Random;

public class RandomUtil {
    private final static Random rand = new Random(System.currentTimeMillis());

    public static int getInt() {
        return rand.nextInt();
    }

    public static int getInt(int max) {
        return rand.nextInt(max);
    }

    public static int getInt(int min, int range) {
        return rand.nextInt(min, range);
    }

    public static double getDouble() {
        return rand.nextDouble();
    }

    public static double getDouble(double max) {
        return rand.nextDouble(max);
    }

    public static double getDouble(double min, double range) {
        return rand.nextDouble(min, range);
    }

    public static boolean getBoolean() {
        return rand.nextBoolean();
    }
}
