package fr.hippo.nbastats.domain;

import org.apache.commons.lang3.StringUtils;

public class Stat {

    private final int value;

    public Stat(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("stat should be positive");
        }

        this.value = value;
    }

    int value() {
        return value;
    }

    @Override
    public String toString() {
        if (value < 10) {
            return StringUtils.leftPad(String.valueOf(value), 2);
        }

        return String.valueOf(value);
    }
}
