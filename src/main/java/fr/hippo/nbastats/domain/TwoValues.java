package fr.hippo.nbastats.domain;

public class TwoValues {
    private final int success;
    private final int total;

    public TwoValues(int success, int total) {
        this.success = success;
        this.total = total;
    }

    @Override
    public String toString() {
        String result = "";

        if (success < 10) {
            result += " ";
        }

        result += success + "/" + total;

        if (total < 10) {
            result += " ";
        }

        return result;
    }
}
