package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the keywords given.
 */
public class TagContainsAnyKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsAnyKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean hasAllMatches = true;

        if (keywords.size() == 0) {
            return false;
        }

        Set<Tag> tags = person.getTags();

        for (Tag tag : tags) {
            boolean doesKeywordMatchAnyTags = keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(keyword, tag.tagName));
            hasAllMatches = hasAllMatches && doesKeywordMatchAnyTags;
        }

        return hasAllMatches;
    }

    public List<String> getKeywords() {
        return this.keywords;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsAnyKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsAnyKeywordsPredicate) other).keywords)); // state check
    }

}
