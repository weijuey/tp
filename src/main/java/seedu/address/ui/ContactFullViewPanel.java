package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

public class ContactFullViewPanel extends UiPart<Region> {
    private static final String FXML = "ContactFullViewPanel.fxml";

    @FXML
    private ListView<Person> detailedPerson;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public ContactFullViewPanel(ObservableList<Person> personToView) {
        super(FXML);
        detailedPerson.setItems(personToView);
        detailedPerson.setCellFactory(listView -> new DetailedPersonCardCell());
    }

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
