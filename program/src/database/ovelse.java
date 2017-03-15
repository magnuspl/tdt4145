package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by torresrl on 14/03/2017.
 */
public class ovelse extends DBConnect{

    public ovelse(){
        super();
    }

    public void addOvelse(String name, String description, String type){
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Ovelse(Navn, Beskrivelse, type) VALUES ('"+name+"', '"+description+"', '"+type+"')");
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

    public int getLastAddedOvingID(){
        int id = -1;
        try{
            ResultSet data = stmt.executeQuery("SELECT OvelseID FROM Ovelse ORDER BY OvelseID DESC LIMIT 1");
            while(data.next()) {
                id = data.getInt("OvelseID");
            }
            return id;

        } catch (SQLException se){
            se.printStackTrace();
        }

        return id;
    }

    public int getIdOvelseOnNameAndType(String name, String type){
        int id = -1;
        try{
            ResultSet data = stmt.executeQuery("SELECT * FROM Ovelse WHERE Ovelse.Navn = '"+name+"' AND  Ovelse.type = '"+type+"'");
            while(data.next()){
                id = data.getInt("OvelseID");
            }

        } catch (SQLException se){
            System.out.println("problemer med getIdOvelseOnNameAndType");
            se.printStackTrace();
        }

        return id;
    }





    public static void main(String[] args) {
        ovelse e = new ovelse();
        //e.addKondis(10,10,10,15);
        //e.addStyrke(10,10,10,15);
       //e.addUtholdenhet(10,180,7, 15);
        //System.out.println(e.getIdOvelseOnNameAndType("markløft", "styrke"));
        //e.addOvelse("Benkpres", "Viktig å spenne kjerne muskelaturen og presse benna ned i baken", "styrke");


        e.close();



    }




}
