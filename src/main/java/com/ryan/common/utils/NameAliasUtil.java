package com.ryan.common.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class NameAliasUtil {
    public String nameAlias(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        String normalized = name.trim().toLowerCase();
        normalized = Normalizer.normalize(normalized, Normalizer.Form.NFD);

        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        normalized = pattern.matcher(normalized).replaceAll("");
        return normalized.trim().replaceAll("\\s+", "-");
    }
}
