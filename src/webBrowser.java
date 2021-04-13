
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class webBrowser extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("webBrows.fxml"));        
        Scene scene = new Scene( root);
        stage.setMaximized(true);
        
        stage.setTitle("chOoRoOm");
        Image image = new Image(getClass().getResourceAsStream("chooroom.png"));
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
