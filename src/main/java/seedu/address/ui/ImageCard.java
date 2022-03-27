package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.model.image.ImageDetails;

public class ImageCard extends UiPart<Region> {

    private static final String FXML = "ImageCard.fxml";

    public final ImageDetails imageDetails;
    public final Image image;

    @FXML
    private Label name;
    @FXML
    private ImageView imageView;

    public ImageCard(ImageDetails imageDetails, int displayedIndex) {
        super(FXML);
        this.imageDetails = imageDetails;
        this.image = new Image(imageDetails.getJavaFXImageUrl());
        this.imageView.setImage(image);
        this.name.setText(imageDetails.getName());
    }
}
