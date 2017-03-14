package database;

import java.sql.SQLException;

/**
 * Created by torresrl on 14/03/2017.
 */
public class ovelse extends DBConnect{

    public ovelse(){
        super();
    }

    public void addOvelse(String name, String description){
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Ovelse(Navn, Beskrivelse) VALUES ('"+name+"', '"+description+"')");
            System.out.println("ovelse added");

        } catch(SQLException se){
            se.printStackTrace();
            System.out.println("problemer med add ovelse");
        }
    }

    public void addKondis(int belastning, int rep, int sett, int ovelseID){
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Kondisjon(Belastning, AntallRepetisjoner, AntallSett, OvelseID) VALUES( " +
                    "'"+belastning+"', '"+rep+"', '"+sett+"', '"+ovelseID+"')");
            System.out.println("kondis added");
        } catch(SQLException se){
            se.printStackTrace();
        }

    }

    public void addStyrke(int belastning, int rep, int sett, int ovelseID){
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Styrke(Belastning, AntallRepetisjoner, AntallSett, OvelseID) VALUES( " +
                    "'"+belastning+"', '"+rep+"', '"+sett+"', '"+ovelseID+"')");
            System.out.println("Styrke added");
        } catch(SQLException se){
            se.printStackTrace();
        }

    }



    public void addUtholdenhet(int varighet, int puls, int km, int ovelseID){
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Utholdenhet(Varighet, Puls, LengdeIKm, OvingsID) VALUES(" +
                    " '"+varighet+"', '"+puls+"', '"+km+"', '"+ovelseID+"')");
            System.out.println("utholdenhet added");
        } catch(SQLException se){
            se.printStackTrace();
        }

    }





    public static void main(String[] args) {
        ovelse e = new ovelse();
        //e.addKondis(10,10,10,15);
        e.addStyrke(10,10,10,15);
       //e.addUtholdenhet(10,180,7, 15);


    }




}
