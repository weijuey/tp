package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Represents a Command class that is additionally able to execute a different
 * path of logic when there is a Person placed in detailed contact view.
 */
public abstract class DetailedViewExecutableCommand extends Command {
    public abstract CommandResult executeDetailedView(Model model);
}
