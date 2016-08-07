import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.imageio.ImageIO;
import com.csvreader.CsvWriter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Table extends Application{

	Pane pane = new Pane();
	Scene scene = new Scene(pane, 1300, 800);
	private final TableView<Books> table = new TableView<Books>();
	String path = "";
	ImageView myImageView = new ImageView();
	Button choose = new Button("Add Book Image");
	Image outside;
	Rectangle rect = new Rectangle(150, 150);
	Writer writer = null;
	private ObservableList<Books> data = 
			FXCollections.observableArrayList();
	File filer;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		
		makeTable();
		addContents(stage);
		menuBar();
		
		stage.setTitle("The Book Keeper");
		stage.setResizable(false);
		stage.setScene(scene);	
		stage.show();
	}

	@SuppressWarnings("unchecked")
	public void makeTable()
	{
		table.setEditable(true);
		table.setTranslateX(450);
		table.setTranslateY(40);
		table.setPrefWidth(820);
		table.setPrefHeight(600);
		
		TableColumn<Books, ImageView> first = new TableColumn<Books, ImageView>("Book Image");
		first.setMinWidth(200);
		first.setCellValueFactory(new PropertyValueFactory<Books, ImageView>("image"));
	
		TableColumn<Books, String> second = new TableColumn<Books, String>("Book Name");
		second.setMinWidth(150);
		second.setCellValueFactory(new PropertyValueFactory<Books, String> ("bookName"));
		
		
		TableColumn<Books, String> third = new TableColumn<Books, String>("Book Author");
		third.setMinWidth(150);
		third.setCellValueFactory(new PropertyValueFactory<Books, String> ("bookAuthor"));
		
		
		TableColumn<Books, String> fourth = new TableColumn<Books, String>("Review ");
		fourth.setMinWidth(200);
		fourth.setCellValueFactory(new PropertyValueFactory<Books, String> ("review"));
		
		TableColumn<Books, String> fifth = new TableColumn<Books, String>("Genre");
		fifth.setMinWidth(150);
		fifth.setCellValueFactory(new PropertyValueFactory<Books, String> ("genre"));
		
		
		table.getColumns().addAll(first, second, third, fourth, fifth);
		
		pane.getChildren().add(table);
	}
	
	public void getPicture()
	{
		
		FileChooser fileChooser = new FileChooser();
		
		FileChooser.ExtensionFilter filterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter filterPNG = new FileChooser.ExtensionFilter("JPG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(filterJPG, filterPNG);
		
		
		File file = fileChooser.showOpenDialog(null);
		
		try
		{
			BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            myImageView.setImage(image);
            outside = image;
            path = file.getPath();
            
		}
		catch(IOException ex)
		{
			System.out.print("no file");
		
		}
		
		
	}
	
	public void getBook(Image i)
	{
		outside = i;
		
	}
	
	public void addContents(Stage s) throws FileNotFoundException

	{
		
		pane.getChildren().add(choose);
		
		choose.setTranslateX(100);
		choose.setTranslateY(100);
		
		rect.setTranslateX(280);
		rect.setTranslateY(90);
		rect.setFill(Color.WHITE);
		pane.getChildren().add(rect);
		
		choose.setOnAction(e ->
		{
			getPicture();
				
			rect.setFill(new ImagePattern(outside));
		});
		
		
		TextField bookName = new TextField();
		bookName.setPromptText("Book Name");
		bookName.setTranslateX(100);
		bookName.setTranslateY(150);
		
		TextField bookAuthor = new TextField();
		bookAuthor.setPromptText("Book Author");
		bookAuthor.setTranslateX(100);
		bookAuthor.setTranslateY(200);
		
		TextField genre = new TextField();
		genre.setPromptText("Genre");
		genre.setTranslateX(100);
		genre.setTranslateY(250);
		
		TextArea review = new TextArea();
		review.setPromptText("Review");
		review.setPrefWidth(300);
		review.setPrefHeight(400);
		review.setTranslateX(100);
		review.setTranslateY(300);
		
		Button addButton = new Button("Add");
		addButton.setTranslateX(350);
		addButton.setTranslateY(720);
		
		Button editButton = new Button("Edit");
		editButton.setTranslateX(500);
		editButton.setTranslateY(650);

		Button removeButton = new Button("Remove");
		removeButton.setTranslateX(550);
		removeButton.setTranslateY(650);

		Button saveButton = new Button("Save");
		saveButton.setTranslateX(630);
		saveButton.setTranslateY(650);
		
		pane.getChildren().addAll(bookName, bookAuthor, review, addButton, saveButton, editButton, removeButton, genre);
		
		table.setItems(data);
		
		
		myImageView.setFitWidth(200);
		myImageView.setFitHeight(200);
		addButton.setOnAction(e ->
		{
			data.add(new Books(myImageView, bookName.getText(), bookAuthor.getText(), review.getText(), genre.getText(), path));
			
			
			rect.setFill(Color.WHITE);
			myImageView = new ImageView();
			myImageView.setFitWidth(200);
			myImageView.setFitHeight(200);
			bookName.clear();
			bookAuthor.clear();
			review.clear();
			genre.clear();
		
		});
		
		
		editButton.setOnAction(e ->
		{
			ObservableList<Books> selected, allBooks;
			
			allBooks = table.getItems();
			selected = table.getSelectionModel().getSelectedItems();
			
			Stage stage = new Stage();
		
			Editing edit;
		
			edit = new Editing(selected);
			
			edit.start(stage);
			
			if(!stage.isShowing()){
			data.remove(table.getSelectionModel().getSelectedItems());
			data.add(edit.getBook());
			
			}
			
		});
		
		removeButton.setOnAction(e ->
		{
			
			data.remove(table.getSelectionModel().getSelectedItem());
			
		});
		
		
		
		saveButton.setOnAction(e ->
		{
			
			String outputFile = "users.csv";
			
			// before we open the file check to see if it already exists
			boolean alreadyExists = new File(outputFile).exists();
			
			
			
			try {
				// use FileWriter constructor that specifies open for appending
				CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
				
				// if the file didn't already exist then we need to write out the header line
				if (!alreadyExists)
				{
					csvOutput.write("Book Image");
					csvOutput.write("Book Name");
					csvOutput.write("Book Author");
					csvOutput.write("Review");
					csvOutput.write("Genre");
					csvOutput.endRecord();
				}
				// else assume that the file already has the correct header line
				
				// write out a few records
				
				for(Books i : data)
				{
				
				csvOutput.write(i.getPath());
				csvOutput.write(i.getBookName());
				csvOutput.write(i.getBookAuthor());
				csvOutput.write(i.getReview());
				csvOutput.write(i.getGenre());
				csvOutput.endRecord();
				}
				
				csvOutput.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
		});
	}
	
	public void menuBar()
	{
		MenuBar menuBar = new MenuBar();
		menuBar.setPrefWidth(1800);
		
		Menu menuOpen = new Menu("File");
		MenuItem add = new MenuItem("Open");
		
		
		
		add.setOnAction(e ->
		{
			 try {
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(new FileReader("users.csv"));
				
				String line = reader.readLine();
				
				while((line = reader.readLine()) != null && !line.isEmpty())
				{
					String[] fields = line.split(",");
					
					String image = fields[0];
					String bookName = fields[1];
					String bookAuthor = fields[2];
					String review = fields[3];
					String genre = fields[4];
					
					ImageView myImage = new ImageView(new Image("file:" + image.toString()));
					myImage.setFitWidth(200);
					myImage.setFitHeight(200);
					
					Books newBook = new Books(myImage, bookName, bookAuthor, review, genre, image);
					
					data.add(newBook);
					
				}
				
			 } catch (Exception ex) {
				
				ex.printStackTrace();
			}
		});
		menuOpen.getItems().add(add);
		
		menuBar.getMenus().addAll(menuOpen);
		
		pane.getChildren().add(menuBar);
	}
	
	public static void main(String[] args)
	{
		Application.launch();
	}
}








