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



    public static void main(String[] args) {
        ovelse e = new ovelse();
        e.addOvelse("benk", "3 set med 90kg x 10");

    }




}
