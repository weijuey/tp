package seedu.address.model.commandhistory;

import seedu.address.model.commandhistory.exceptions.HistoryDoesNotExistException;

public class CommandHistoryEntry {

    private static final CommandHistoryEntry EMPTY_COMMAND_HISTORY = new CommandHistoryEntrySentinel();

    private final String commandText;

    public CommandHistoryEntry(String commandText) {
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

    private static class CommandHistoryEntrySentinel extends CommandHistoryEntry {
        public CommandHistoryEntrySentinel() {
            super("");
        }

        @Override
        public boolean exists() {
            return false;
        }

        @Override
        public String getCommandText() {
            throw new HistoryDoesNotExistException();
        }
    }
}
