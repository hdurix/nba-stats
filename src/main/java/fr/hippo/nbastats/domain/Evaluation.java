package fr.hippo.nbastats.domain;

import org.apache.commons.lang3.StringUtils;

public class Evaluation {

    private final int value;

    public Evaluation(int value) {
        this.value = value;
    }

    int value() {
        return value;
    }

    @Override
    public String toString() {
        if (value >= 0 && value < 10) {
            return StringUtils.leftPad(String.valueOf(value), 2);
        }

        return String.valueOf(value);
    }
}
