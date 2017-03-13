package database;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by torresrl on 13/03/2017.
 */
public class workout extends DBConnect{


    public workout(){
        super();
    }


    public void addOkt(LocalDate date, String time, int duration, String note, String personligForm, String prestasjon,
                  Boolean inndors, int temp, String weather,String AirCondition){

        try {
            stmt = conn.createStatement();
        } catch( SQLException se){
            se.printStackTrace();
        }

    }
}
