package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.address.model.person.Person;

import java.util.Comparator;

public class DetailedPersonCard extends UiPart<Region> {
    private static final String FXML = "DetailedPersonCard.fxml";

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private VBox notesPane;
    @FXML
    private Label notes;
    @FXML
    private VBox deadlinesPane;
    @FXML
    private Label deadlines;
    @FXML
    private FlowPane tags;
    @FXML
    private Canvas starCanvas;

    public DetailedPersonCard(Person person) {
        super(FXML);

        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        notes.setText(person.getNotes().listFormat());
        deadlines.setText(person.getDeadlines().listFormat());

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        if (person.isFavourite()) {
            starCanvas.setVisible(true);
            drawStarShape(starCanvas.getGraphicsContext2D());
        }
    }

    private void drawStarShape(GraphicsContext gc) {
        //@@author takufunkai-reused
        //Reused from https://zetcode.com/gui/javafx/canvas/
        // with minor modifications to the points and fill, for suitable colour and size.

        double[] xpoints = {1, 7, 9, 11, 17, 13, 14, 9, 4, 5};
        double[] ypoints = {7, 6.5, 1, 6.5, 7, 10, 15, 12, 15, 10};

        gc.setFill(Color.YELLOW);
        gc.fillPolygon(xpoints, ypoints, xpoints.length);

        gc.setStroke(Color.BLACK);
        gc.strokePolygon(xpoints, ypoints, xpoints.length);

        //@@author
    }
}
