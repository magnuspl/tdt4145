import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by henri on 12.03.2017.
 */
public class HentInfo {

    private final StringProperty ID, dato, tidspunkt, varighet, personligf, prestasjon, iu, notat;

    public HentInfo(String ID, String dato, String tidspunkt, String varighet, String personligf, String prestasjon, String iu, String notat) {
        this.ID = new SimpleStringProperty(ID);
        this.dato = new SimpleStringProperty(dato);
        this.tidspunkt = new SimpleStringProperty(tidspunkt);
        this.varighet = new SimpleStringProperty(varighet);
        this.personligf = new SimpleStringProperty(personligf);
        this.prestasjon = new SimpleStringProperty(prestasjon);
        this.iu = new SimpleStringProperty(iu);
        this.notat = new SimpleStringProperty(notat);
    }

    //Getters
    public String getID() {return ID.get();}
    public String getDato() {return dato.get();}
    public String getTidspunkt() {return tidspunkt.get();}
    public String getVarighet() {return varighet.get();}
    public String getPersonligf() {return personligf.get();}
    public String getPrestasjon() {return prestasjon.get();}
    public String getIu() {return iu.get();}
    public String getNotat() {return notat.get();}

    //Setters
    public void setID(String value) {this.ID.set(value);}
    public void setDato(String value) {this.dato.set(value);}
    public void setTidspunkt(String value) {this.tidspunkt.set(value);}
    public void setVarighet(String value) {this.varighet.set(value);}
    public void setPersonligf(String value) {this.personligf.set(value);}
    public void setPrestasjon(String value) { this.prestasjon.set(value);}
    public void setIu(String value) {this.iu.set(value);}
    public void setNotat(String value) {this.notat.set(value);}

    //Property values
    public StringProperty IDProperty() {return ID;}
    public StringProperty datoProperty() {return dato;}
    public StringProperty tidspunktProperty() {return tidspunkt;}
    public StringProperty varighetProperty() {return varighet;}
    public StringProperty personligfProperty() {return personligf;}
    public StringProperty prestasjonProperty() {return prestasjon;}
    public StringProperty iuProperty() {return iu;}
    public StringProperty notatProperty() {return notat;}
}
