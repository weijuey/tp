package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.HighImportance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_FAVOURITE = "false";
    public static final String DEFAULT_HIGH_IMPORTANCE = "false";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private DeadlineList deadlines;
    private Notes notes;
    private Favourite favouriteStatus;
    private HighImportance highImportanceStatus;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        deadlines = new DeadlineList();
        notes = Notes.getNewNotes();
        favouriteStatus = Favourite.valueOf(DEFAULT_FAVOURITE);
        highImportanceStatus = HighImportance.valueOf(DEFAULT_HIGH_IMPORTANCE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        deadlines = personToCopy.getDeadlines();
        notes = Notes.loadNotesFromList(personToCopy.getNotes().value);
        favouriteStatus = personToCopy.getFavouriteStatus();
        highImportanceStatus = personToCopy.getHighImportanceStatus();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Person} that we are building.
     */
    public PersonBuilder withDeadlines(String[] deadlines) throws ParseException {
        if (deadlines.length == 1 && deadlines[0].equals(Deadline.NO_DEADLINE_PLACEHOLDER)) {
            this.deadlines = new DeadlineList();
            return this;
        }
        this.deadlines = ParserUtil.parseDeadlines(Arrays.asList(deadlines));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Notes} of the {@code Person} that we are building.
     */
    public PersonBuilder withNotes(List<String> notes) {
        this.notes = Notes.loadNotesFromList(notes);
        return this;
    }

    /**
     * Sets the {@code FavouriteStatus} of the {@code Person} that we are building.
     */
    public PersonBuilder withFavourite(String favourite) {
        this.favouriteStatus = Favourite.valueOf(favourite);
        return this;
    }

    /**
<<<<<<< HEAD
     * Adds a {@code Tag} to the current {@code Set<Tag>} of the {@code Person} that we are building.
     */
    public PersonBuilder withNewTag(Tag tag) {
        Set<Tag> newTags = new HashSet<>(this.tags);
        newTags.add(tag);
        this.tags = newTags;
        return this;
    }

    /**
     * Removes a {@code Tag} from the current {@code Set<Tag>} of the {@code Person} that we are building.
     */
    public PersonBuilder withoutTag(Tag tag) {
        Set<Tag> withoutTag = new HashSet<>(this.tags);
        withoutTag.remove(tag);
        this.tags = withoutTag;
        return this;
    }

    /**
     * Sets the {@code FavouriteStatus} of the {@code Person} that we are building.
     */
    public PersonBuilder withHighImportance(String highImportance) {
        this.highImportanceStatus = HighImportance.valueOf(highImportance);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, deadlines, notes, tags, favouriteStatus, highImportanceStatus);
    }
}
