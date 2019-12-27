package fr.hippo.nbastats.domain;

public class TwoDigitNumber {
    private final int value;

    public TwoDigitNumber(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        if (value < 10) {
            return " " + value;
        }
        return String.valueOf(value);
    }
}
