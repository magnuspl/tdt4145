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

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();


        workout test = new workout();
        test.addOkt(date, "17:30:00",60, "formen var bra og fullførte 10km" +
                " fortere en planlagt, og hadde mer energi til overs.", 3, 2, false, 1, "regn", "");
    }
}
