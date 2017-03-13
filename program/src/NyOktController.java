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




        LocalDate date;
        String time;
        int duration;
        String note;
        int perForm;
        int prestasjon;
        String air;
        int tempS;
        String weather;


        //validering
    boolean valDate = false;
    boolean valTime = false;
    boolean valDuration = false;
    boolean valNote = false;
    boolean valPerForm = false;
    boolean valPrestasjon = false;
    boolean valAir = false;
    boolean valtempS = false;
    boolean valWeather = false;





    private void validateDate(){
        try{
            date =dato.getValue();
            valDate = true;
            System.out.println("date: OK " + date);

        } catch(Exception e){
            System.out.println("problem med date");

        }
    }

    private void validateTime(){
        try{
            time = tidspunkt.getText();
            String[] time2 = time.split(":");
            if(time2.length == 3){
                int t1 = Integer.parseInt(time2[0]);
                int t2 = Integer.parseInt(time2[1]);
                int t3 = Integer.parseInt(time2[2]);
                if(t1 >= 0 && t2 >= 0 && t3 >= 0){
                    valTime = true;
                }
            }
            System.out.println("Time: OK " + time);

        } catch(Exception e){
            System.out.println("problemer med tid");
        }
    }

    public void validateDuration(){
        try {
            duration = Integer.parseInt(varighet_m.getText());
            valDuration = true;
            if(duration > 0){
                valDuration = true;
            }
            System.out.println("Duration: OK " + duration);

        } catch (Exception e){
            System.out.println("Feil med Duration");

        }

    }

    public void validateNote(){
        try {
            note = notat.getText();
            valNote = true;
            System.out.println("Note: OK " + note);

        } catch (Exception e){
            System.out.println("Feil med Note");

        }

    }

    public void validatePerForm(){
        try {
            perForm = (Integer) pers.getValue();
            valPerForm = true;
            System.out.println("PerForm: OK " + perForm);

        } catch (Exception e){
            System.out.println("Feil med perForm");

        }

    }

    public void validatePrestasjon(){
        try {
            prestasjon = (Integer) pres.getValue();
            valPrestasjon = true;
            System.out.println("Prestasjon: OK " + prestasjon);

        } catch (Exception e){
            System.out.println("Feil med presentasjon");

        }

    }


    public void validateAir(){
        try {
            air = luft.getText();
            valAir = true;
            System.out.println("Air: OK " + air);

        } catch (Exception e){
            System.out.println("Feil med air");

        }

    }


    public void validateTempS(){
        try {
            tempS = Integer.parseInt(temp.getText());
            valtempS = true;
            System.out.println("Teampratur: OK " + tempS);

        } catch (Exception e){
            System.out.println("Feil med temp");

        }

    }

    public void validateWeather(){
        try {
            weather = vaer.getText();
            valWeather = true;
            System.out.println("Weather: OK " + weather);


        } catch (Exception e){
            System.out.println("Feil med Weather");

        }

    }

    public boolean checkFilds(){
        if(uteCheck.isSelected() || inneCheck.isSelected()){
            System.out.println("Check filds: OK");
            return true;

        }
        System.out.println("Check filds: FEIL");
        return false;
    }

    public boolean validate(){
        validateDate();
        validateTime();
        validateDuration();
        validateNote();
        validatePerForm();
        validatePrestasjon();
        validateAir();
        validateTempS();
        validateWeather();

        if(valDate && valTime && valDuration && valNote && valPerForm && valPrestasjon && checkFilds()){
            if(inneCheck.isSelected() && valAir){
                feilMessage.setText("");
                return true;

            } else if (uteCheck.isSelected() && valWeather && valtempS){
                feilMessage.setText("");
                return true;
            }
        } else {
            feilMessage.setText("FEIL");

        }

        return false;
    }






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





@FXML
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
@FXML
    public void inneCheck_changed(ActionEvent actionEvent) {
        if(inneCheck.isSelected()){
            luft.setDisable(false);
        }
        else{
            luft.setDisable(true);
        }
    }
@FXML
    public void valgt_ovelse(ActionEvent actionEvent) {
    }

@FXML
    public void regOkt(ActionEvent actionEvent) {


        if (validate()) {
            workout workout = new workout();
            if (inneCheck.isSelected()) {
                try {
                    workout.addOktInn(date, time, duration, note, perForm, prestasjon, air);
                    workout.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            } else if (uteCheck.isSelected()) {
                workout.addOktOut(date, time, duration, note, perForm, prestasjon, tempS, weather);
                workout.close();

            }


            try {
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
}
