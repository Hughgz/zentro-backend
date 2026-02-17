package com.ryan.common.utils;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.regex.Pattern;

@Component
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
