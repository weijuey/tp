package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.image.ImageDetails;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.logging.Logger;

public class ImageViewPanel extends UiPart<Region> {
    private static final String FXML = "ImageViewPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ImageViewPanel.class);

    @FXML
    private GridPane imageListView;

    /**
     * Creates a {@code ImageViewPanel} with the given {@code ObservableList}.
     */
    public ImageViewPanel(ObservableList<Person> persons) {
        super(FXML);

        // TODO: don't rely on using OL<Person>, figure out logic to display
        Person targetPerson = persons.get(0);
        ImageDetailsList images = targetPerson.getImageDetailsList();
        List<ImageDetails> imageList = images.getImages();
        int expectedNumberOfRows = images.size() / 3;

//        Image img = new Image(imageList.get(0).getJavaFXImageUrl());
//        ImageView iv = new ImageView(img);
//                iv.setFitWidth(100);
//                iv.setFitHeight(100);
//        imageListView.add(iv, 0,0);
//        for (int i = 0; i < expectedNumberOfRows; i++) {
            for (int j = 0; j < 2; j++) {
                Image img = new Image(imageList.get(j).getJavaFXImageUrl());
                ImageView iv = new ImageView(img);
                iv.setFitWidth(100);
                iv.setFitHeight(100);
                imageListView.add(iv, j, 0);
            }
//        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Image} using a {@code ImageCard}.
     */
    class ImageListViewCell extends ImageView {

    }

}
