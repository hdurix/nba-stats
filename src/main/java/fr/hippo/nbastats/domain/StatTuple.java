package fr.hippo.nbastats.domain;

import org.apache.commons.lang3.StringUtils;

public class StatTuple {

    private final int success;
    private final int total;

    public StatTuple(int success, int total) {
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
        String formattedSuccess = StringUtils.leftPad(String.valueOf(success), 2);
        String formattedTotal = StringUtils.rightPad(String.valueOf(total), 2);

        return formattedSuccess + "/" + formattedTotal;
    }
}
