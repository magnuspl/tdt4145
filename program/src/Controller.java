
import database.DBConnect;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private DBConnect dc;
    private ObservableList<HentInfo> data;

    @FXML
    protected TableView<HentInfo> table;
    @FXML
    protected TableColumn tblID, tblDato, tblTid, tblVar, tblPers, tblPrest, tblIU, tblNotat;
    @FXML
    protected Button addButton, WeeksBestButton, StatistikkButton, btnTidlig;

    @FXML protected void handleAddButton (ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nyOkt.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            System.out.println("Kunne ikke åpne shitn");
        }

    }

    @FXML protected void handleWeeksBest(Event event){

    }

    @FXML protected void handleStatistikk(Event event){

    }

    public void initialize(URL url, ResourceBundle rb){
        dc = new DBConnect();
    }

    //Henter info om tidligere økter fra databasen når man trykker på hent tidligere økter"
    public void handleHent(ActionEvent actionEvent) {
        try{
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * from okt");
            while(rs.next()){
                data.add(new HentInfo(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        }catch(SQLException ex){
            System.err.println("u fkd up"+ex);
        }
        //Setter verdier på tabellene i table-viewen til det den henter fra databasen
        tblID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tblDato.setCellValueFactory(new PropertyValueFactory<>("dato"));
        tblTid.setCellValueFactory(new PropertyValueFactory<>("tidspunkt"));
        tblVar.setCellValueFactory(new PropertyValueFactory<>("varighet"));
        tblPers.setCellValueFactory(new PropertyValueFactory<>("personligf"));
        tblPrest.setCellValueFactory(new PropertyValueFactory<>("prestasjon"));
        tblIU.setCellValueFactory(new PropertyValueFactory<>("iu"));
        tblNotat.setCellValueFactory(new PropertyValueFactory<>("notat"));


        table.setItems(null);
        table.setItems(data);
    }
}
