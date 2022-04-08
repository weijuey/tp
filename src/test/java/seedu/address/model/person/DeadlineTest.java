package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void hashcode_duplicateDeadline_equal() {
        assertEquals(new Deadline().hashCode(), new Deadline().hashCode());
    }
}
