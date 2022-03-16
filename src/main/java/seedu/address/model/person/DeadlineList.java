package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

public class DeadlineList {
    private ArrayList<Deadline> deadlines = new ArrayList<>();

    public DeadlineList() {
        this.deadlines.add(new Deadline());
    }

    /**
     * Constructs a {@code DeadlineList} object.
     *
     * @param deadlines the list of the deadlines to be put into {@code DeadlineList}.
     */
    public DeadlineList(List<Deadline> deadlines) {
        requireNonNull(deadlines);
        this.deadlines.addAll(deadlines);
    }

    public ArrayList<Deadline> getDeadlines() {
        return deadlines;
    }

    /**
     * Produces a string representation of this {@code Deadline} object for printing to GUI.
     *
     * @return list view of deadlines in this {@code Deadline} object.
     */
    public String listFormat() {
        if (deadlines.size() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= deadlines.size(); i++) {
            result.append(deadlines.get(i - 1)).append("\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineList // instanceof handles nulls
                && deadlines.equals(((DeadlineList) other).deadlines)); // state check
    }
}
