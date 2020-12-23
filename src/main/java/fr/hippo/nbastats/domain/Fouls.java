package fr.hippo.nbastats.domain;

public class Fouls {

    public static final int MAX_FOULS = 6;
    private final int value;

    public Fouls(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value == MAX_FOULS) {
            return "*";
        }

        return " ";
    }
}
