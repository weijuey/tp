package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    public enum SpecialCommandResult {
        SHOW_HELP, // Help information should be shown to the user
        EXIT, // The application should exit
        VIEW_IMAGES, // Should load up the contact's images
        DETAILED_VIEW, // Should show the detailed contact view
        NONE
    }

    private final SpecialCommandResult specialCommandResult;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, SpecialCommandResult specialCommandResult) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.specialCommandResult = requireNonNull(specialCommandResult);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, SpecialCommandResult.NONE);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return this.specialCommandResult == SpecialCommandResult.SHOW_HELP;
    }

    public boolean isExit() {
        return this.specialCommandResult == SpecialCommandResult.EXIT;
    }

    public boolean isViewImages() {
        return this.specialCommandResult == SpecialCommandResult.VIEW_IMAGES;
    }

    public boolean isDetailedView() {
        return this.specialCommandResult == SpecialCommandResult.DETAILED_VIEW;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && specialCommandResult == otherCommandResult.specialCommandResult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, specialCommandResult);
    }

}
