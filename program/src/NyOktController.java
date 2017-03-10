import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by henri on 10.03.2017.
 */
public class NyOktController implements Initializable {

    @FXML
    protected Button registrer;
    @FXML
    protected TextArea notat;
    @FXML
    protected TextField temp, vaer, luft, varighet_t, varighet_m;
    @FXML
    protected CheckBox inneCheck, uteCheck;
    @FXML
    protected DatePicker dato;
    @FXML
    protected ComboBox<String> time, min;
    @FXML
    protected ComboBox<String> ovelser;
    @FXML
    protected Spinner<Integer> pers, pres;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        time.getItems().addAll("05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
        min.getItems().addAll("00","15","30","45");

        ovelser.getItems().addAll("Markløft","Benkpress", "Squats", "Biceps curls");

        vaer.setDisable(true);
        temp.setDisable(true);
        luft.setDisable(true);

        pers.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5, 1));
        pres.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5, 1));
    }

    public void OvelseValgt(ActionEvent actionEvent) {
    }

    public void uteCheck_changed(ActionEvent actionEvent) {
        if (uteCheck.isSelected()){
            temp.setDisable(false);
            vaer.setDisable(false);
        }
        else{
            temp.setDisable(true);
            vaer.setDisable(true);
        }
    }

    public void inneCheck_changed(ActionEvent actionEvent) {
        if(inneCheck.isSelected()){
            luft.setDisable(false);
        }
        else{
            luft.setDisable(true);
        }
    }

    public void valgt_ovelse(ActionEvent actionEvent) {
    }

    public void regOkt(ActionEvent actionEvent) {
        //Registrer økt i databasen
    }
}
