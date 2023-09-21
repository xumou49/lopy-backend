package com.lopy.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
public class DataUtil {

    private DataUtil() {
    }

    private static final Random random = new Random();

    private static double[] scores = new double[8];

    static {
        int j = 0;
        for (double i = 1.5; i <= 5.0; i += 0.5, j++) {
            scores[j] = i;
        }
    }

    public static boolean toBoolean(Boolean val) {
        return val != null && val;
    }

    public static long toLong(Long val) {
        return val == null ? 0L : val;
    }

    public static long toLong(Object val) {
        try {
            if (val instanceof Number) {
                return ((Number) val).longValue();
            }
            return StringUtil.isBlank(val) ? 0 : Long.parseLong(StringUtil.trim(val));
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    public static int toInteger(Integer val) {
        return val == null ? 0 : val;
    }

    public static int toInteger(Object val) {
        try {
            if (val instanceof Number) {
                return ((Number) val).intValue();
            }
            return StringUtil.isBlank(val) ? 0 : Integer.parseInt(StringUtil.trim(val));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static double toDouble(Double val) {
        if (val == null) {
            return 0;
        }
        BigDecimal bg = new BigDecimal(val);
        return bg.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static double toCeilDouble(Double val, int scale) {
        if (val == null) {
            return 0;
        }
        BigDecimal bg = BigDecimal.valueOf(val);
        return bg.setScale(scale, RoundingMode.HALF_DOWN).doubleValue();
    }

    public static double generatePrice(int upperBound) {
        double value = random.nextInt(upperBound) + 10.99;
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(value));
    }

    public static double generateScore() {
        return scores[random.nextInt(8)];
    }

    public static String handleNone(String text) {
        return Objects.equals(text, "None") ? "-" : text;
    }

    public static String handleCatalogProp(String text) {
        if (Objects.equals(text, "None")) {
            return "-";
        }
        return StringUtil.stringToList(text, ",").get(0);
    }

    public static String takeFirst8Element(String text) {
        List<String> list = StringUtil.stringToList(text, ",");
        if (list.size() <= 8) {
            return text;
        }
        return list.stream().limit(8).collect(Collectors.joining(","));
    }

    public static int generateRandomInt(int lowBound , int upperBound) {
        return lowBound + random.nextInt(upperBound);
    }


    public static void main(String[] args) {
        double v = generatePrice(50);
        System.out.println(v);
    }
}
