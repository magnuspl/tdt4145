package database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

/**
 * Created by torresrl on 13/03/2017.
 */
public class workout extends DBConnect{


    public workout(){
        super();
    }


    public void addOkt(LocalDate date, String time, int duration, String note, int personligForm, int prestasjon,
                  Boolean inndors, int temp, String weather,String AirCondition){

        Time timeSql = java.sql.Time.valueOf(time);
        Date dateSql = Date.valueOf(date);

        int isInndor;
        if(inndors){
            isInndor = 1;
        } else {
            isInndor = 0;
        }

        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Okt(Dato, Tidspunkt, Varighet, note, PersonligForm, Prestasjon," +
                    " Inndors, Temp, Weather, AirCondition) VALUES('"+dateSql+"', '"+timeSql+"', '"+duration+"', '"+note+"'" +
                    ", '"+personligForm+"', '"+prestasjon+"', '"+isInndor+"', '"+temp+"', '"+weather+"', '"+AirCondition+"')");
            System.out.println("okt added");
        } catch( SQLException se){
            se.printStackTrace();
        }

    }

    public void addOktInn(LocalDate date, String time, int duration, String note, int personligForm, int prestasjon,
                        String AirCondition){

        Time timeSql = java.sql.Time.valueOf(time);
        Date dateSql = Date.valueOf(date);



        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Okt(Dato, Tidspunkt, Varighet, note, PersonligForm, Prestasjon," +
                    " Inndors,  AirCondition) VALUES('"+dateSql+"', '"+timeSql+"', '"+duration+"', '"+note+"'" +
                    ", '"+personligForm+"', '"+prestasjon+"', '"+1+"', '"+AirCondition+"')");
            System.out.println("okt added");
        } catch( SQLException se){
            se.printStackTrace();
        }

    }

    public void addOktOut(LocalDate date, String time, int duration, String note, int personligForm, int prestasjon,
                        int temp, String weather){

        Time timeSql = java.sql.Time.valueOf(time);
        Date dateSql = Date.valueOf(date);



        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Okt(Dato, Tidspunkt, Varighet, note, PersonligForm, Prestasjon," +
                    " Inndors, Temp, Weather) VALUES('"+dateSql+"', '"+timeSql+"', '"+duration+"', '"+note+"'" +
                    ", '"+personligForm+"', '"+prestasjon+"', '"+0+"', '"+temp+"', '"+weather+"')");
            System.out.println("okt added");
        } catch( SQLException se){
            se.printStackTrace();
        }

    }

    public int getOktID(LocalDate date, String time){
        Time timeSql = java.sql.Time.valueOf(time);
        Date dateSql = Date.valueOf(date);
        int OktID = -1;

        try{
            ResultSet data = stmt.executeQuery("SELECT * FROM Okt WHERE Okt.Dato = '"+dateSql+"' " +
                    "and Okt.Tidspunkt = '"+timeSql+"'");
            while(data.next()){
                OktID = data.getInt("OktID");
            }
            return OktID;

        } catch (SQLException se){
            se.printStackTrace();

        } catch (NullPointerException e){
            e.printStackTrace();
        }


         return -1;
    }

    public int getLastAddedOkt(){
        int id = -1;

        try{
            ResultSet data = stmt.executeQuery("SELECT OktID FROM Okt ORDER BY OktID DESC LIMIT 1");
            while(data.next()){
                id = data.getInt("OktID");
                return id;
            }
        } catch(SQLException se){
            System.out.println("problem med lastAddedOkt");
            se.printStackTrace();
        }
        return id;
    }







}
