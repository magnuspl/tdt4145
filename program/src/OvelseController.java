import database.oktOvelse;
import database.ovelse;
import database.workout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import database.DBConnect;
import javax.xml.transform.Result;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by henri on 13.03.2017.
 */
public class OvelseController implements Initializable {

    @FXML
    protected ToggleGroup Rtype;
    @FXML
    protected Label kommentar;
    @FXML
    protected TextField belastning, reps, sett, lengde, varig, puls;
    @FXML
    protected Button addOvelse, btnLukk;
    @FXML
    protected ComboBox<String> dropStyrke, dropStyrkeAlt, dropKond, dropKondAlt, dropUth, dropUthAlt;
    @FXML
    protected RadioButton radStyrke, radKond, radUth;


    //Validering
    boolean valBel = false;
    boolean valRep = false;
    boolean valSet = false;
    boolean valLen = false;
    boolean valDur = false;
    boolean valHb = false;
    boolean valdropS = false;
    boolean valdropK = false;
    boolean valdropU = false;


    int bel;
    int rep;
    int set;
    int len;
    int dur;
    int hb;


    @FXML
    String type;



    private void valBel() {
        try {
            bel = Integer.parseInt(belastning.getText());
            System.out.println("Bel: OK ");
            valBel = true;

        } catch (Exception e) {
            System.out.println("Bel: FEIL ");
            e.printStackTrace();
        }
    }

    private void valRep() {
        try {
            rep = Integer.parseInt(reps.getText());
            System.out.println("Rep: OK ");
            valRep = true;

        } catch (Exception e) {
            System.out.println("Rep: FEIL ");
            e.printStackTrace();
        }
    }

    private void valSet() {
        try {
            set = Integer.parseInt(sett.getText());
            System.out.println("set: OK ");
            valSet = true;

        } catch (Exception e) {
            System.out.println("set: FEIL ");
            e.printStackTrace();
        }
    }

    private void valLen() {
        try {
            len = Integer.parseInt(lengde.getText());
            System.out.println("Len: OK ");
            valLen = true;

        } catch (Exception e) {
            System.out.println("Len: FEIL ");
            e.printStackTrace();
        }
    }

    private void valDur() {
        try {
            dur = Integer.parseInt(varig.getText());
            System.out.println("Var: OK ");
            valDur = true;

        } catch (Exception e) {
            System.out.println("Var: FEIL ");
            e.printStackTrace();
        }
    }

    private void valHb() {
        try {
            hb = Integer.parseInt(puls.getText());
            System.out.println("HB: OK ");
            valHb = true;

        } catch (Exception e) {
            System.out.println("HB: FEIL ");
            e.printStackTrace();
        }
    }


    private void valdropS(){
        try {
            if(radStyrke.isSelected()){
                type = dropStyrke.getValue();
                valdropS = true;

            }

        } catch(Exception e){
            System.out.println("problemer med dropS");
            e.printStackTrace();

        }
    }

    private void valdropK(){
        try {
            if(radKond.isSelected()) {
                type = dropKond.getValue();
                valdropK = true;
            }

        } catch(Exception e){
            System.out.println("problemer med dropK");
            e.printStackTrace();

        }
    }

    private void valdropU(){
        try {
            if(radUth.isSelected()){
                type = dropUth.getValue();
                valdropU = true;
            }

        } catch(Exception e){
            System.out.println("problemer med dropS");
            e.printStackTrace();

        }
    }

    private boolean validation() {
        if (radStyrke.isSelected()) {
            valBel();
            valRep();
            valSet();
            valdropS();
        } else if (radUth.isSelected()) {
            valLen();
            valDur();
            valHb();
            valdropU();

        } else if(radKond.isSelected()){
            valBel();
            valRep();
            valSet();
            valdropK();

        }


        if (valBel && valRep && valSet && valdropS && radStyrke.isSelected()) {
            return true;

        } else if (valDur && valHb && valLen && valdropU && radUth.isSelected()) {
            return true;
        } else if (valBel && valRep && valSet && valdropK && radKond.isSelected()){
            return true;
        }



        return false;

    }




    public void disable(ComboBox dis1, ComboBox dis2) {
        dis1.setDisable(true);
        dis2.setDisable(true);
    }

    public void kondisStyrkeEn(TextField txt1, TextField txt2, TextField txt3) {
        txt1.setDisable(false);
        txt2.setDisable(false);
        txt3.setDisable(false);

    }

    public void kondisStyrkeDis(TextField txt1, TextField txt2, TextField txt3) {
        txt1.setDisable(true);
        txt2.setDisable(true);
        txt3.setDisable(true);
    }

    public void handleStyrk(ActionEvent actionEvent) {

        if (radStyrke.isSelected()) {
            dropStyrke.setDisable(false);
            disable(dropKond, dropUth);
            kondisStyrkeEn(belastning, reps, sett);
        } else {
            dropStyrke.setDisable(true);
            kondisStyrkeDis(belastning, reps, sett);
        }
    }

    public void handleKond(ActionEvent actionEvent) {
        if (radKond.isSelected()) {
            dropKond.setDisable(false);
            disable(dropStyrke, dropUth);
            kondisStyrkeEn(belastning, reps, sett);
        } else {
            dropKond.setDisable(true);
            kondisStyrkeDis(belastning, reps, sett);
        }
    }

    public void handleUth(ActionEvent actionEvent) {
        if (radUth.isSelected()) {
            dropUth.setDisable(false);
            disable(dropStyrke, dropKond);
            lengde.setDisable(false);
            varig.setDisable(false);
            puls.setDisable(false);
        } else {
            dropUth.setDisable(true);
            lengde.setDisable(true);
            varig.setDisable(true);
            varig.setDisable(true);
        }
    }


    public void fillcombobox(ComboBox box, String str) {
        try {
            Statement stmt;
            Connection conn;
            ResultSet rs;
            conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/henrisor_69", "henrisor_tdt4145", "123");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(str);
            while (rs.next()) {
                box.getItems().addAll(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String queryS = "select Navn from Ovelse WHERE type='styrke'";
        String queryK = "select navn from Ovelse where type='kondisjon'";
        String queryU = "select navn from Ovelse where type='utholdenhet'";
        fillcombobox(dropStyrke, queryS);
        fillcombobox(dropKond, queryK);
        fillcombobox(dropUth, queryU);
    }

    public void getShit(ComboBox cb) {
        String varName = (String) cb.getValue();
        System.out.println(varName);

    }

    @FXML
    public void handleAddOv(ActionEvent actionEvent) {


        if (validation()) {
            ovelse ov = new ovelse();
            workout wo = new workout();
            oktOvelse oo = new oktOvelse();


            int woId = wo.getLastAddedOkt();

            if (validation()) {


                if (radStyrke.isSelected()) {
                    int ovId = ov.getIdOvelseOnNameAndType(type, "styrke");
                    ov.addStyrke(bel, rep, set, ovId);
                    oo.addOktOvelse(ovId, woId);
                    belastning.setText("");
                    reps.setText("");
                    sett.setText("");


                } else if (radKond.isSelected()) {
                    int ovId = ov.getIdOvelseOnNameAndType(type, "kondisjon");
                    ov.addKondis(bel, rep, set, ovId);
                    oo.addOktOvelse(ovId, woId);
                    belastning.setText("");
                    reps.setText("");
                    sett.setText("");
                } else if (radUth.isSelected()) {
                    int ovId = ov.getIdOvelseOnNameAndType(type, "utholdenhet");
                    ov.addUtholdenhet(dur, hb, len, ovId);
                    oo.addOktOvelse(ovId, woId);
                    varig.setText("");
                    puls.setText("");
                    lengde.setText("");

                }

                ov.close();
            }
        }
    }

        public void handleLukk (ActionEvent actionEvent){
            Stage stage = (Stage) btnLukk.getScene().getWindow();
            stage.close();

        }
    }

