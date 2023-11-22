package com.studen.bestfood;

public class TextUtils {
    public static String join(CharSequence delimiter, Iterable<? extends CharSequence> elements) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (CharSequence element : elements) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(element);
        }
        return sb.toString();
    }
}

