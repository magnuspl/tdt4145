import database.workout;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
    protected Label kommentar;
    @FXML
    protected TextField belastning, reps, sett, lengde, varig, puls;
    @FXML
    protected Button addOvelse, btnLukk;
    @FXML
    protected ComboBox dropStyrke, dropStyrkeAlt, dropKond,dropKondAlt, dropUth, dropUthAlt;
    @FXML
    protected RadioButton radStyrke, radKond, radUth;

    Connection conn;
    PreparedStatement pst = null;
    ResultSet rs = null;
    final ObservableList options = FXCollections.observableArrayList();

    public ObservableList<String> loadDDL() throws SQLException{
        String query = "SELECT * FROM Okt";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        ObservableList<String> myList = FXCollections.observableArrayList();
        int col = rs.getMetaData().getColumnCount();
        while(rs.next()){
            for (int i=2; i <= col; i++){
                myList.add(rs.getString(i));
            }
        }

        conn.close();
        myList.sorted();
        myList.add(0, "Alle");
        return myList;
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


    public void fillcombobox(ComboBox box, String str){
        try {
            Statement stmt;
            conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/henrisor_69","henrisor_tdt4145","123");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(str);
            while(rs.next())
            {
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
        fillcombobox(dropStyrke,queryS);
        fillcombobox(dropKond, queryK);
        fillcombobox(dropUth, queryU);
        kommentar.setText("skal vi ha kommentarer her?");
    }

    public void getShit(ComboBox cb){
        String varName = (String)cb.getValue();
        System.out.println(varName);

    }

    public void handleAddOv(ActionEvent actionEvent) {
        getShit(dropStyrke);
        getShit(dropKond);
        getShit(dropUth);
        System.out.println("Antall kg: "+belastning.getText());
        System.out.println("Antall reps "+reps.getText());
        System.out.println("Antall sett: "+sett.getText());
        System.out.println("Puls: "+puls.getText());
        System.out.println("GPS: "+varig.getText());
        System.out.println("Lengde : "+lengde.getText());
    }

    public void handleLukk(ActionEvent actionEvent) {
    Stage stage = (Stage) btnLukk.getScene().getWindow();
    stage.close();
    }
}

