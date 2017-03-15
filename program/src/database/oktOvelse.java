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

            //TODO fiks denne slik den virker og kole den opp i Ovelse kontroller
            // må jobbe oss rundt constrainen
            // se link: http://stackoverflow.com/questions/1997998/insert-data-into-tables-linked-by-foreign-key
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO OktOvelse VALUES('" + OvelseId + "', '" + OktId + "') ");
            System.out.println("OktOvelse added");


        } catch (SQLException se) {
            System.out.println("problemer med okyOvelse");
            se.printStackTrace();
            ;
        }
    }

    public static void main(String[] args) {
        oktOvelse e = new oktOvelse();
        e.addOktOvelse(18,17);



    }
}

