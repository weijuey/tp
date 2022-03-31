package seedu.address.model.commandhistory;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandHistory implements Iterable<CommandHistoryEntry> {

    private final ObservableList<CommandHistoryEntry> internalList = FXCollections.observableArrayList();

    public void cacheCommand(String commandText) {
        this.internalList.add(new CommandHistoryEntry(commandText));
    }

    /**
     * Retrieves the command at size - i position.
     * If I is out of bounds, returns an empty history entry.
     *
     * @param i the number of commands to step back from
     * @return the resulting entry
     */
    public CommandHistoryEntry retrieveCommand(int i) {
        if (i > internalList.size() || i < 0) {
            return CommandHistoryEntry.getEmptyHistory();
        }
        return internalList.get(internalList.size() - i);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<CommandHistoryEntry> iterator() {
        return internalList.iterator();
    }
}
