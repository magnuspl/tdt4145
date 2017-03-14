import database.ovelse;
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
    protected TextField belastning, reps, sett, lengde, varig, puls;
    @FXML
    protected Button addOvelse;
    @FXML
    protected ComboBox dropStyrke, dropStyrkeAlt, dropKond,dropKondAlt, dropUth, dropUthAlt;
    @FXML
    protected RadioButton radStyrke, radKond, radUth;

    //Validering
    boolean valBel = false;
    boolean valRep = false;
    boolean valSet = false;
    boolean valLen = false;
    boolean valDur = false;
    boolean valHb = false;

    int bel;
    int rep;
    int set;
    int len;
    int dur;
    int hb;


    private void valBel(){
        try{
            bel = Integer.parseInt(belastning.getText());
            System.out.println("Bel: OK ");
            valBel = true;

        } catch(Exception e){
            System.out.println("Bel: FEIL ");
            e.printStackTrace();
        }
    }

    private void valRep(){
        try{
            rep = Integer.parseInt(reps.getText());
            System.out.println("Rep: OK ");
            valRep = true;

        } catch(Exception e){
            System.out.println("Rep: FEIL ");
            e.printStackTrace();
        }
    }

    private void valSet(){
        try{
            set = Integer.parseInt(sett.getText());
            System.out.println("set: OK ");
            valSet = true;

        } catch(Exception e){
            System.out.println("set: FEIL ");
            e.printStackTrace();
        }
    }

    private void valLen(){
        try{
            len = Integer.parseInt(lengde.getText());
            System.out.println("Len: OK ");
            valLen = true;

        } catch(Exception e){
            System.out.println("Len: FEIL ");
            e.printStackTrace();
        }
    }

    private void valDur(){
        try{
            dur = Integer.parseInt(varig.getText());
            System.out.println("Var: OK ");
            valDur = true;

        } catch(Exception e){
            System.out.println("Var: FEIL ");
            e.printStackTrace();
        }
    }

    private void valHb(){
        try{
            hb = Integer.parseInt(belastning.getText());
            System.out.println("HB: OK ");
            valHb = true;

        } catch(Exception e){
            System.out.println("HB: FEIL ");
            e.printStackTrace();
        }
    }

    private boolean validation(){
        if(radStyrke.isSelected() || radKond.isSelected()){
            valBel();
            valRep();
            valSet();
        } else if(radUth.isSelected()){
            valLen();
            valDur();
            valHb();

        }



        if(valBel && valRep && valSet && (radStyrke.isSelected() || radKond.isSelected())){
            return true;

        } else if(valDur && valHb && valLen && radUth.isSelected())
            return true;


        return false;

    }


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
public void kondisStyrkeEn(TextField txt1, TextField txt2, TextField txt3){
    txt1.setDisable(false);
    txt2.setDisable(false);
    txt3.setDisable(false);

}public void kondisStyrkeDis(TextField txt1, TextField txt2, TextField txt3){
        txt1.setDisable(true);
        txt2.setDisable(true);
        txt3.setDisable(true);
    }

    public void handleStyrk(ActionEvent actionEvent) {

        if(radStyrke.isSelected()){
            enable(dropStyrke, dropStyrkeAlt);
            disable(dropKond, dropUth, dropKondAlt, dropUthAlt);
            kondisStyrkeEn(belastning, reps, sett);
        }else {
            dropStyrke.setDisable(true);
            kondisStyrkeDis(belastning, reps, sett);
        }
    }

    public void handleKond(ActionEvent actionEvent) {
        if(radKond.isSelected()){
            enable(dropKond, dropKondAlt);
            disable(dropStyrke, dropUth, dropStyrkeAlt, dropUthAlt);
            kondisStyrkeEn(belastning, reps, sett);
        }else{
            dropKond.setDisable(true);
            kondisStyrkeDis(belastning, reps, sett);
        }
    }

    public void handleUth(ActionEvent actionEvent) {
        if(radUth.isSelected()){
            enable(dropUth, dropUthAlt);
            disable(dropStyrke, dropKond, dropStyrkeAlt, dropKondAlt);
            lengde.setDisable(false);
            varig.setDisable(false);
            puls.setDisable(false);
        }else{
            dropUth.setDisable(true);
            lengde.setDisable(true);
            varig.setDisable(true);
            varig.setDisable(true);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Noen testverdier
        dropStyrke.getItems().addAll("Markløft","Benkpress", "Squats", "Biceps curls");
        dropKond.getItems().addAll("Intervall", "Spurt", "Bakkeløp");
        dropUth.getItems().addAll("Intervall", "10km hurtig jogg");
    }

    @FXML
    public void handleAddOv(ActionEvent actionEvent) {

        if(validation()){
            ovelse ov = new ovelse();
            //kobler det opp mot siste ovelse som ble lagt til, hvis oelse betyr eks benk, så
            //må tanke gangen endres.
            // da trengs get metode som henter oving etter navn med LIMIT 1

            int ovId = ov.getLastAddedOvingID();
            if(radStyrke.isSelected()){
                ov.addStyrke(bel, rep, set, ovId);
            } else if (radKond.isSelected()){
                ov.addKondis(bel, rep, set, ovId);
            }else if (radUth.isSelected()){
                ov.addUtholdenhet(dur, hb, len, ovId);
            }
            ov.close();



        }
    }
}

