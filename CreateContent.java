import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CreateContent extends Application {
    public Parent createContent() {

    
        BorderPane layout = new BorderPane();

        
        TableView<Images> tableview = new TableView<Images>();

        

        ObservableList<Images> imgList = FXCollections.observableArrayList();
        Images item_1 = new Images(new ImageView(new Image("myAvatar.png")));
        
        Images item_2 = new Images(new ImageView(new Image("myAvatar.png")));
        imgList.addAll(item_1, item_2);

        /* initialize and specify table column */
        TableColumn<Images, ImageView> firstColumn = new TableColumn<Images, ImageView>("Images");
        firstColumn.setCellValueFactory(new PropertyValueFactory<Images, ImageView>("image"));
        firstColumn.setPrefWidth(300);

        TableColumn secondColumn = new TableColumn("Book Name");
        secondColumn.setPrefWidth(200);
        
        TableColumn thirdColumn = new TableColumn("Book Author");
        thirdColumn.setPrefWidth(200);
        
        TableColumn fourthColumn = new TableColumn("Review");
        fourthColumn.setPrefWidth(400);
        
        /* add column to the tableview and set its items */
        tableview.getColumns().addAll(firstColumn, secondColumn, thirdColumn, fourthColumn);
        tableview.setItems(imgList);

        /* add TableView to the layout */
        layout.setCenter(tableview);
        return layout;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setWidth(1100);
        stage.setHeight(900);
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}