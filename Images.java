import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Images {

	private ImageView image;

   public Images(ImageView img) {
        this.image = img;
    }

    public void setImage(ImageView value) {
        image = value;
    }

    public ImageView getImage() {
        return image;
    }
}