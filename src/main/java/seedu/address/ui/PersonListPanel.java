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
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> contactFullViewPanel;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, ObservableList<Person> contactFullView) {
        super(FXML);
        contactFullViewPanel.setItems(contactFullView);
        contactFullViewPanel.setCellFactory(listView -> new DetailedPersonCardCell());
        contactFullView.addListener(new EmptyListener(contactFullViewPanel));
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personList.addListener(new EmptyListener(personListView));
    }

    /**
     * Custom {@code ListChangeListener} to check when a given list contains no
     * elements, and hides the ListView so that the other ListView can use more
     * space in the MainWindow.
     */
    class EmptyListener implements ListChangeListener<Person> {
        private ListView<Person> listView;

        public EmptyListener(ListView<Person> listView) {
            this.listView = listView;
        }

        @Override
        public void onChanged(Change c) {
            int len = c.getList().size();
            logger.info("change detected, list size: " + len);
            if (len == 0) {
                listView.setPrefHeight(0);
            } else {
                listView.setPrefHeight(800);
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
