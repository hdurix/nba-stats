package fr.hippo.nbastats.domain;

public class TwoValues {
    private final int success;
    private final int total;

    public TwoValues(int success, int total) {
        this.success = success;
        this.total = total;
    }

    public int getSuccess() {
        return success;
    }

    public int getTotal() {
        return total;
    }

    public int getMissed() {
        return total - success;
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
