package fr.hippo.nbastats.domain;

public class BinaryStat {
    private final int success;
    private final int total;

    public BinaryStat(int success, int total) {
        this.success = success;
        this.total = total;
    }

    int getSuccess() {
        return success;
    }

    int getTotal() {
        return total;
    }

    int getMissed() {
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
