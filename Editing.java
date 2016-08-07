import javafx.application.*;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Editing extends Application{

	Pane pane = new Pane();
	Scene scene = new Scene(pane);
	ObservableList<Books> book;
	Books b;
	
	public Editing(ObservableList<Books> list)
	{
		book = list;
	}
	
	public Books getBook()
	{
		return b;
		
	}
	
	public void start(Stage stage)
	{
		addBoxes(stage);
		
		stage.setWidth(550);
		stage.setHeight(450);
		stage.setScene(scene);
		stage.show();
	}
	
	public Books addBoxes(Stage stage)
	{
		b = book.get(0);
		ImageView iv = b.getImage();
		Image i = iv.getImage();
		
		Rectangle image = new Rectangle(200, 200);
		image.setTranslateX(310);
		image.setTranslateY(50);
		image.setFill(new ImagePattern(i));
		
		TextField bookName = new TextField();
		bookName.setText(b.getBookName());
		bookName.setTranslateX(50);
		bookName.setTranslateY(50);
		
		TextField bookAuthor = new TextField();
		bookAuthor.setText(b.getBookAuthor());
		bookAuthor.setTranslateX(50);
		bookAuthor.setTranslateY(100);
		
		TextField genre = new TextField();
		genre.setText(b.getGenre());
		genre.setTranslateX(50);
		genre.setTranslateY(150);
		
		TextArea review = new TextArea();
		review.setText(b.getReview());
		review.setPrefWidth(250);
		review.setTranslateX(50);
		review.setTranslateY(200);
		
		Button save = new Button("Save");
		save.setTranslateX(400);
		save.setTranslateY(350);
		
		pane.getChildren().addAll(image, bookName, bookAuthor, review, genre, save);
		
		save.setOnAction(e ->
		{
			b.setBookName(bookName.getText());
			b.setBookAuthor(bookAuthor.getText());
			b.setGenre(genre.getText());
			b.setReview(review.getText());
			stage.close();
		});
		
		return b;
	}
	
	public static void main(String[] args)
	{
		Application.launch();
	}
}
