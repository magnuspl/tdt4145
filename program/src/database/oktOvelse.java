package database;

import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by torresrl on 15/03/2017.
 */
public class oktOvelse extends DBConnect {

    public oktOvelse() {
        super();
    }

    public void addOktOvelse(int OvelseId, int OktId) {
        try {

            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO OktOvelse VALUES(" +
                    "(SELECT OvelseID FROM Ovelse WHERE Ovelse.OvelseID = '"+OvelseId+"')," +
                    "(SELECT  OktID FROM Okt WHERE Okt.OktID = '"+OktId+"')) ");
            System.out.println("OktOvelse added");


        } catch (SQLException se) {
            System.out.println("problemer med okyOvelse");
            se.printStackTrace();
            ;
        }
    }


}

