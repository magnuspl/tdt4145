import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    protected Button addButton, WeeksBestButton, StatestikkButton;

    @FXML protected void handleAddButton (ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nyOkt.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            System.out.println("Kunne ikke åpne shitn");
        }

    }

    @FXML protected void handleWeeksBest(Event event){

    }

    @FXML protected void handleStatestikk(Event event){

    }

}
