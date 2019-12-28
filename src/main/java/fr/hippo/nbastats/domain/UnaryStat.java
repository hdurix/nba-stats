package fr.hippo.nbastats.domain;

public class UnaryStat {
    private final int value;

    public UnaryStat(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        if (value >= 0 && value < 10) {
            return " " + value;
        }

        return String.valueOf(value);
    }
}
