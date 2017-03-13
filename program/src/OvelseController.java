import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by henri on 13.03.2017.
 */
public class OvelseController implements Initializable {

    @FXML
    protected ComboBox dropStyrke, dropKond, dropUth;

    @FXML
    protected RadioButton radStyrke, radKond, radUth;

    final ToggleGroup group = new ToggleGroup();



    public void handleStyrk(ActionEvent actionEvent) {
        radStyrke.setToggleGroup(group);
        if(radStyrke.isSelected()){
            dropStyrke.setDisable(false);
        }else if (radKond.isSelected() || radUth.isSelected()){
            dropStyrke.setDisable(true);
        }
    }

    public void handleKond(ActionEvent actionEvent) {
        radKond.setToggleGroup(group);
        if(radKond.isSelected()){
            dropKond.setDisable(false);
        }else if (radStyrke.isSelected() || radUth.isSelected()){
            dropKond.setDisable(true);
        }
    }

    public void handleUth(ActionEvent actionEvent) {
        radUth.setToggleGroup(group);
        if(radUth.isSelected()){
            dropUth.setDisable(false);
        }else if (radStyrke.isSelected() || radKond.isSelected()){
            dropUth.setDisable(true);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Noen testverdier
        dropStyrke.getItems().addAll("Markløft","Benkpress", "Squats", "Biceps curls");
        dropKond.getItems().addAll("Intervall", "Spurt", "Bakkeløp");
        dropUth.getItems().addAll("Intervall", "10km rask jogg");
    }

}

