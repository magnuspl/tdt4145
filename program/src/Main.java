import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("frontpage.fxml"));
        primaryStage.setTitle("Treningsdagbok");
        Scene scene =  new Scene(root, 581, 400);
        String css = this.getClass().getResource("./css/buttonFrontPage.css").toExternalForm();
        scene.getStylesheets().add(css);


        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
