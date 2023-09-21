package com.lopy.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.IDN;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class StringUtil {

    private StringUtil() {}

    public static String trim(Object obj) {
        return obj == null ? "" : StringUtils.trim(obj.toString());
    }

    public static boolean isEmpty(String obj) {
        return StringUtils.isEmpty(obj);
    }

    public static String capitalize(String obj) {
        return StringUtils.capitalize(obj);
    }

    public static boolean isBlank(Object obj) {
        return obj == null || StringUtils.isBlank(obj.toString());
    }

    public static boolean isNotBlank(Object obj) {
        return obj != null && StringUtils.isNotBlank(obj.toString());
    }

    public static String replaceContentByMap(String content, Map<String, String> replaceMap) {
        for (Map.Entry<String, String> item : replaceMap.entrySet()) {
            content = content.replaceAll(item.getKey(), Matcher.quoteReplacement(item.getValue()));
        }
        return content;
    }

    public static <T> String listToString(List<T> list, String separator) {
        if(CollectionUtils.isEmpty(list)) {
            return StringUtils.EMPTY;
        }
        return StringUtils.join(list.toArray(), separator);
    }

    public static <T> String setToString(Set<T> set, String separator) {
        return StringUtils.join(set.toArray(), separator);
    }

    public static List<String> stringToList(String str, String separator) {
        List<String> res = new ArrayList<>(Arrays.asList(str.split(separator)));
        res.remove("");
        return res;
    }

    public static Set<String> stringToSet(String str, String separator) {
        Set<String> res = new HashSet<>(Arrays.asList(str.split(separator)));
        res.remove("");
        return res;
    }

    public static List<String> splitBySquareBrackets(String str) {
        str = trim(str);
        if (StringUtils.isEmpty(str)) {
            return new ArrayList<>();
        }
        if (str.startsWith("[") && str.endsWith("]")) {
            str = str.substring(1, str.length() - 1);
            return new ArrayList<>(Arrays.asList(str.split("\\]\\[")));
        }
        return new LinkedList<>(Arrays.asList(str));
    }

    public static String joinBySquareBrackets(List<?> list) {
        String str = StringUtils.join(list.toArray(), "][");
        if (!str.isEmpty()) {
            str = "[" + str + "]";
        }
        return str;
    }

    public static void main(String[] args) {
        List<String> strings = StringUtil.stringToList(StringUtil.trim("abc\r\n").replaceAll("[\\s|\\n|\\r|;|,]+", " "), " ");
        log.info("{}", strings);
    }

    public static String joinBySquareBrackets(Set<String> list) {
        String str = StringUtils.join(list.toArray(), "][");
        if (!str.isEmpty()) {
            str = "[" + str + "]";
        }
        return str;
    }

    public static boolean isIP(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        String p = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[0-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        Pattern pattern = Pattern.compile(p);
        return pattern.matcher(str).matches();
    }

    public static boolean isFuzzyIP(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }

        // 匹配 d.d.d.d, d.d.d.d/16, d.d.d.d/24
        if (str.matches("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}"
                + "(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)"
                + "(/(16|24))?")) {
            return true;
        }

        // 匹配wildcard IP
        return str.matches("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){2}"
                + "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)|\\*)\\."
                + "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)|\\*)"
                + "(/(16|24))?");
    }

    public static boolean isUrl(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }

        if (str.indexOf('.') == -1) {
            return false;
        }

        String p = "^(?:https?://)[\\w-]{1,}(?:\\.?[\\w]{1,})+[\\w-_/?&=#%:\\.]*$";
        Pattern pattern = Pattern.compile(p);
        return pattern.matcher(str).matches();
    }

    public static boolean isUri(String str) {
        if (StringUtil.isBlank(str)) {
            return false;
        }
        return str.matches("^(\\/[\\w|_|\\-|.]+)+\\/?$");
    }

    public static boolean isCName(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }

        if (!str.contains(".")) {
            return false;
        }

        if (StringUtil.isIP(str)) {
            return false;
        }

        if (StringUtil.containChinese(str)) {
            str = IDN.toASCII(str, 1);
        }

        String p = "^(\\*\\.)?([a-z|0-9|\\-|_]+\\.)*[a-z|0-9|\\-|_]+(\\.[a-z]+){1,2}$";
        Pattern pattern = Pattern.compile(p);
        return pattern.matcher(str).matches();
    }

    public static boolean containChinese(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }

        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
        return pattern.matcher(str).find();
    }

    public static String removeDuplicateString(String str1, String str2) {
        if (StringUtil.isBlank(str1)) {
            return null;
        }
        if (StringUtil.isBlank(str2)) {
            return str1;
        }
        char[] chars = str2.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
            if (c == ']') {
                str1 = StringUtils.replace(str1, sb.toString(), "");
                sb.delete(0, sb.length());
            }
        }
        return str1;
    }

    public static String toCamelCase(String underlineStr) {
        if (underlineStr == null) {
            return null;
        }
        // 分成数组
        char[] charArray = underlineStr.toCharArray();
        // 判断上次循环的字符是否是"_"
        boolean underlineBefore = false;
        StringBuilder builder = new StringBuilder();
        for (char c : charArray) {
            // 判断当前字符是否是"_",如果跳出本次循环
            if (c == 95) {
                underlineBefore = true;
            } else if (underlineBefore) {
                // 如果为true，代表上次的字符是"_",当前字符需要转成大写
                builder.append(Character.toUpperCase(c));
                underlineBefore = false;
            } else {
                // 不是"_"后的字符就直接追加
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public static String toUnderlineCase(String camelCaseStr) {
        if (camelCaseStr == null) {
            return null;
        }
        // 将驼峰字符串转换成数组
        char[] charArray = camelCaseStr.toCharArray();
        StringBuilder builder = new StringBuilder();
        //处理字符串
        for (char c : charArray) {
            if (c >= 65 && c <= 90) {
                builder.append("_").append(Character.toLowerCase(c));
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
