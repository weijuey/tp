package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons or a detailed view of a person.
 */
public class ContactDisplayPanel extends UiPart<Region> {
    private static final String FXML = "ContactDisplayPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ContactDisplayPanel.class);

    @FXML
    private ListView<Person> detailedContactViewPanel;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code ContactDisplayPanel} with the given {@code ObservableList}.
     */
    public ContactDisplayPanel(ObservableList<Person> personList, ObservableList<Person> detailedContactView) {
        super(FXML);
        detailedContactViewPanel.setItems(detailedContactView);
        detailedContactViewPanel.setCellFactory(listView -> new DetailedPersonCardCell());
        detailedContactView.addListener(new EmptyListener(detailedContactViewPanel, "Detailed Contact View"));
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personList.addListener(new EmptyListener(personListView, "Contact List"));
    }

    /**
     * Custom {@code ListChangeListener} to check when a given list contains no
     * elements, and hides the ListView so that the other ListView can use more
     * space in the {@code ContactDisplayPanel}.
     */
    class EmptyListener implements ListChangeListener<Person> {
        private ListView<Person> listView;
        private String listName;

        public EmptyListener(ListView<Person> listView, String listName) {
            this.listView = listView;
            this.listName = listName;
        }

        @Override
        public void onChanged(Change c) {
            int len = c.getList().size();
            if (len == 0) {
                logger.info("Hiding " + listName);
                listView.setPrefHeight(0);
            } else {
                logger.info("Showing " + listName);
                listView.setPrefHeight(Region.USE_COMPUTED_SIZE);
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a
     * {@code DetailedPersonCard}.
     */
    class DetailedPersonCardCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DetailedPersonCard(person).getRoot());
            }
        }
    }
}
