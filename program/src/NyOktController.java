import database.workout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.text.Text;

import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    protected TextField tidspunkt, temp, vaer, luft, varighet_m;
    @FXML
    protected CheckBox inneCheck, uteCheck;
    @FXML
    protected DatePicker dato;
    @FXML
    protected ComboBox<String> ovelser;
    @FXML
    protected Spinner<Integer> pers, pres;
    @FXML
    protected Text feilMessage;


    LocalDate date = dato.getValue();
    String time =  tidspunkt.getText();
    int duration = Integer.parseInt(varighet_m.getText());
    String note = notat.getText();
    int perForm = (Integer) pers.getValue();
    int prestasjon = (Integer) pres.getValue();
    String air = luft.getText();
    int tempS = Integer.parseInt(temp.getText());
    String weather = vaer.getText();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Noen testverdier
        ovelser.getItems().addAll("Markløft","Benkpress", "Squats", "Biceps curls");

        vaer.setDisable(true);
        temp.setDisable(true);
        luft.setDisable(true);

        pers.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5, 1));
        pres.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 5, 1));
    }



    public boolean checkFilds(){
        if(!inneCheck.isSelected() && !inneCheck.isSelected()){
            feilMessage.setText("FEIL");
            return false;

        }
        return true;
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

        if(checkFilds()){
            workout workout = new workout();
            if(inneCheck.isSelected()){
                workout.addOktInn(date, time, duration, note, perForm, prestasjon, air);
            } else if(uteCheck.isSelected()){
                workout.addOktOut(date, time, duration, note, perForm, prestasjon, tempS, weather);

            }


        }

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ovelse.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();


        } catch (IOException e) {
            System.out.println("Kunne ikke åpne shitn");
        }
    }
}
