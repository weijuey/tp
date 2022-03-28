package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
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
    private TilePane imageListView;

    /**
     * Creates a {@code ImageViewPanel} with the given {@code ObservableList}.
     */
    public ImageViewPanel(ImageDetailsList images) {
        super(FXML);

        double totalWidth = this.getRoot().getWidth();
        int individualWidth = (int) totalWidth / 3;

        for (ImageDetails imageDetails : images) {
            imageListView.getChildren().add(new ImageCard(imageDetails, 120, individualWidth).getRoot());
        }
    }

}
