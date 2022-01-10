package fr.hippo.nbastats.domain;

import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

public class Identity {

    public static final int MAX_LENGTH = 11;
    private final int id;
    private final Optional<String> firstName;
    private final Optional<String> lastName;

    public Identity(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = Optional.ofNullable(firstName);
        this.lastName = Optional.ofNullable(lastName);
    }

    public Identity(int id) {
        this(id, null, null);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return truncateOrFill(firstName.orElse("?").charAt(0) + ". " + lastName.orElse("???"));
    }

    private String truncateOrFill(String name) {
        if (name.length() > MAX_LENGTH) {
            return name.substring(0, MAX_LENGTH);
        }

        return StringUtils.rightPad(name, MAX_LENGTH);
    }

    public boolean isUnknown() {
        return firstName.isEmpty();
    }
}
