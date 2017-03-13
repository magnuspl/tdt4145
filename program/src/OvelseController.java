import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by henri on 13.03.2017.
 */
public class OvelseController implements Initializable {

    @FXML
    protected Label kommentar;
    @FXML
    protected TextField belastning, reps, sett, lengde;
    @FXML
    protected Button addOvelse;
    @FXML
    protected ComboBox dropStyrke, dropStyrkeAlt, dropKond,dropKondAlt, dropUth, dropUthAlt;
    @FXML
    protected RadioButton radStyrke, radKond, radUth;





public void disable(ComboBox dis1,ComboBox dis2, ComboBox dis3, ComboBox dis4){
    dis1.setDisable(true);
    dis2.setDisable(true);
    dis3.setDisable(true);
    dis4.setDisable(true);
}
public void enable(ComboBox en1, ComboBox en2){
    en1.setDisable(false);
    en2.setDisable(false);
}
public void kondisStyrkeDis(TextField txt1, TextField txt2, TextField txt3){
    txt1.setDisable(false);
    txt2.setDisable(false);
    txt3.setDisable(false);

}public void kondisStyrkeEn(TextField txt1, TextField txt2, TextField txt3){
        txt1.setDisable(true);
        txt2.setDisable(true);
        txt3.setDisable(true);
    }

    public void handleStyrk(ActionEvent actionEvent) {

        if(radStyrke.isSelected()){
            enable(dropStyrke, dropStyrkeAlt);
            disable(dropKond, dropUth, dropKondAlt, dropUthAlt);
            kondisStyrkeDis(belastning, reps, sett);
        }else {
            dropStyrke.setDisable(true);
            kondisStyrkeEn(belastning, reps, sett);
        }
    }

    public void handleKond(ActionEvent actionEvent) {
        if(radKond.isSelected()){
            enable(dropKond, dropKondAlt);
            disable(dropStyrke, dropUth, dropStyrkeAlt, dropUthAlt);
            kondisStyrkeDis(belastning, reps, sett);
        }else{
            dropKond.setDisable(true);
            kondisStyrkeEn(belastning, reps, sett);
        }
    }

    public void handleUth(ActionEvent actionEvent) {
        if(radUth.isSelected()){
            enable(dropUth, dropUthAlt);
            disable(dropStyrke, dropKond, dropStyrkeAlt, dropKondAlt);
            lengde.setDisable(false);
        }else{
            dropUth.setDisable(true);
            lengde.setDisable(true);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Noen testverdier
        dropStyrke.getItems().addAll("Markløft","Benkpress", "Squats", "Biceps curls");
        dropKond.getItems().addAll("Intervall", "Spurt", "Bakkeløp");
        dropUth.getItems().addAll("Intervall", "10km hurtig jogg");
    }

    public void handleAddOv(ActionEvent actionEvent) {
    }
}

