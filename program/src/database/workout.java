package database;

import java.sql.Date;
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

    public static void main(String[] args) {
        LocalDate test = LocalDate.now();


        workout mw = new workout();
        mw.addOktOut(test, "08:10:15", 30, "hei", 3, 4, 5,"sol");


            }



}
