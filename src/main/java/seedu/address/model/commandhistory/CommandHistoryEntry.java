package seedu.address.model.commandhistory;

import seedu.address.model.commandhistory.exceptions.HistoryDoesNotExistException;

import static java.util.Objects.requireNonNull;

public class CommandHistoryEntry {

    private static final CommandHistoryEntry EMPTY_COMMAND_HISTORY = new CommandHistoryEntrySentinel();

    private final String commandText;

    // hidden default constructor
    private CommandHistoryEntry() {
        commandText = "";
    }

    public CommandHistoryEntry(String commandText) {
        requireNonNull(commandText);
        this.commandText = commandText;
    }

    public static CommandHistoryEntry getEmptyHistory() {
        return EMPTY_COMMAND_HISTORY;
    }

    public boolean exists() {
        return true;
    }

    public String getCommandText() {
        return this.commandText;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommandHistoryEntry // instanceof handles nulls
                && commandText.equals(((CommandHistoryEntry) other).commandText));
    }

    private static class CommandHistoryEntrySentinel extends CommandHistoryEntry {

        @Override
        public boolean exists() {
            return false;
        }

        @Override
        public String getCommandText() {
            throw new HistoryDoesNotExistException();
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof CommandHistoryEntrySentinel;
        }
    }
}
