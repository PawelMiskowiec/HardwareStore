package pl.edu.wszib.hardwareStore.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public enum Category {
    TOOLS, ACCESSORIES, MATERIALS, PARTS;
    public static Optional<Category> parseString(String value) {
        return Arrays.stream(values())
                .filter(it -> StringUtils.equalsIgnoreCase(it.name(), value))
                .findFirst();
    }
}
