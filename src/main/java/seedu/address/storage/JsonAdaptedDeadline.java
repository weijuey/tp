package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Deadline;

class JsonAdaptedDeadline {

    private final String deadlineDate;

    @JsonCreator
    public JsonAdaptedDeadline(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public JsonAdaptedDeadline(Deadline source) {
        deadlineDate = source.value;
    }

    @JsonValue
    public String getDeadlineDate() {
        return deadlineDate;
    }

    public Deadline toModelType() throws IllegalValueException {
        if (!Deadline.isValidDeadline(deadlineDate)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        return new Deadline(deadlineDate);
    }
}
